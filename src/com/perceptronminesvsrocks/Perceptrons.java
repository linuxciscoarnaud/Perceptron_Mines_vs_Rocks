/**
 * 
 */
package com.perceptronminesvsrocks;

/**
 * @author Arnaud
 *
 */
public class Perceptrons {

	private int nIn;       // dimensions of input data
    private double[] w;  // weight vector of perceptrons
    private double bias;  // bias
    
    ActivationFunction activationFunction;
	
    // Constructor
    public Perceptrons(int nIn, double bias) {

        this.nIn = nIn;
        w = new double[nIn];
        this.bias = bias;
    }
    
    // trainer
    public int train(double[] x, int t, double learningRate) {
    	activationFunction = new ActivationFunction();
    	int classified = 0;
    	int activatedVal = 0;
        double c = 0.;
        
        // check if the data is classified correctly
        for (int i = 0; i < nIn; i++) {
        	c += w[i] * x[i];
        }
        c += bias;
        
        activatedVal = activationFunction.theshold(c);
        
        if ((activatedVal == 1 && t == 1) || (activatedVal == -1 && t == -1)) {
        	classified = 1;       	
        } else { // then gradient descent needs to be applied
        	//update the parameter
        	for (int i = 0; i < nIn; i++) {
        		w[i] += learningRate * (t - activatedVal) * x[i];
        	}
        	
        	// update the bias
        	bias += learningRate * (t - activatedVal);
        }
        
        return classified;
    }
    
    // predictor
    public int predict (double[] x) {
    	
    	activationFunction = new ActivationFunction();
        double preActivation = 0.;
        
        for (int i = 0; i < nIn; i++) {
        	preActivation += w[i] * x[i];
        }
        preActivation += bias;
        
        return activationFunction.theshold(preActivation);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//
        // Declare (Prepare) variables and constants for perceptrons
        //

		Param param = new Param();
		
        final int train_N = param.getTrainN();   // number of training data
        final int test_N = param.getTestN();                       // number of test data
        final int nIn = param.getNTn();        // dimensions of input data
        
        double[][] train_X = new double[train_N][nIn];  // input data for training
        int[] train_T = new int[train_N];               // output data (label) for training
        
        double[][] test_X = new double[test_N][nIn];  // input data for test
        int[] test_T = new int[test_N];               // label of inputs
        int[] predicted_T = new int[test_N];          // output data predicted by the model

        final int epochs = param.getEpochs();   // maximum training epochs
        final double learningRate = param.getLearningRate();  // learning rate can be 1 in perceptrons
        final double initialBias = param.getInitialBias();  // initial bias
        
        //
        // Load train and test data
        //
        Dataset dataSet = new Dataset();
        dataSet.LoadTrainingData(train_X, train_T);
        dataSet.LoadTestData(test_X, test_T);
        
        //
        // Build Mines vs Rocks model
        //
        
        int epoch = 0;  // training epochs
        
        // construct perceptrons
        Perceptrons classifier = new Perceptrons(nIn, initialBias);

        // train the model
        while (true) {
        	int classified_ = 0;
        	
        	for (int i=0; i < train_N; i++) {
        		classified_ += classifier.train(train_X[i], train_T[i], learningRate);
        	}
        	
        	if (classified_ == train_N) { // when all data classified correctly
        		//System.out.println("ALL DATA CLASSIFIED CORRECTLY");
        		break;
        	}
        	
        	epoch++;
        	if (epoch > epochs) {  //stepping the epoch
        		//System.out.println("STEPPING OVER THE EPOCH");
            	break;
        	}
        }
              
        //
        // Test Mines vs Rocks model
        //
        
        for (int i = 0; i < test_N; i++) {
        	predicted_T[i] = classifier.predict(test_X[i]);
        }
        
        //I want to display the predicted labels of testing data (to be removed later)
        for (int p = 0; p < predicted_T.length; p++) {
			System.out.println(p+1 + " predicted data: label = " + predicted_T[p]);
		}
        
        
        //
        // Evaluate the Mines vs Rocks model
        //
        
        int[][] confusionMatrix = new int[2][2];
        double accuracy = 0.;
        double precision = 0.;
        double recall = 0.;

        for (int i = 0; i < test_N; i++) {

            if (predicted_T[i] > 0) {
                if (test_T[i] > 0) {
                    accuracy += 1;
                    precision += 1;
                    recall += 1;
                    confusionMatrix[0][0] += 1;
                } else {
                    confusionMatrix[1][0] += 1;
                }
            } else {
                if (test_T[i] > 0) {
                    confusionMatrix[0][1] += 1;
                } else {
                    accuracy += 1;
                    confusionMatrix[1][1] += 1;
                }
            }

        }

        accuracy /= test_N;
        precision /= confusionMatrix[0][0] + confusionMatrix[1][0];
        recall /= confusionMatrix[0][0] + confusionMatrix[0][1];

        System.out.println("----------------------------");
        System.out.println("Mines vs Rocks model evaluation");
        System.out.println("----------------------------");
        System.out.printf("Accuracy:  %.1f %%\n", accuracy * 100);
        System.out.printf("Precision: %.1f %%\n", precision * 100);
        System.out.printf("Recall:    %.1f %%\n", recall * 100);
	}
}

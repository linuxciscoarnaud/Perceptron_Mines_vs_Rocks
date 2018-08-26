/**
 * 
 */
package com.perceptronminesvsrocks;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Arnaud
 *
 */
public class Perceptrons {

	private int nIn;       // dimensions of input data
    private double[] w;  // weight vector of perceptrons
    private double bias;  // bias
    
    static Param param = new Param();
    ActivationFunction activationFunction;
    static double[] val = new double[param.getTestN()];
    int index;
	
    // Constructor
    public Perceptrons(int nIn, double bias) {

        this.nIn = nIn;
        w = new double[nIn];
        this.bias = bias;
    }
    
    // trainer
    public int train(double[] x, int t, double learningRate) throws IOException, BiffException, WriteException {
    	activationFunction = new ActivationFunction();
    	Results results = new Results();
    	
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
        	// first write weight and bias on file
        	//results.writeParameters(w, bias);
        	
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
        val[index] = preActivation;
        index++;
        
        return activationFunction.theshold(preActivation);
    }
    
	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException, BiffException {
		// TODO Auto-generated method stub

		//
        // Declare (Prepare) variables and constants for perceptrons
        //

		Param param = new Param();
		Results results = new Results();
		
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
        
        // write labels of test and predicted data on .xls file
        results.writeLabels(test_T, predicted_T, val);
        
        
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

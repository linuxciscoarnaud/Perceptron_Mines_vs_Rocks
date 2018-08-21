/**
 * 
 */
package com.perceptronminesvsrocks;

/**
 * @author Arnaud
 *
 */


public class Param {

	// constants
	private String trainDataFileName = "sonar.all-data.csv";
	private String testDataFileName = "sonarTesDdata.csv";
	
	private int trainN = 208;
	private int testN = 3;
	private int nTn = 60;
	
	private int epochs = 2000;
	private double learningRate = 1.0;
	private double initialBias = 0.5;
	
	// getters ...
	public String getTrainDataFileName() {
		return trainDataFileName;
	}
	
	public String getTestDataFileName() {
		return testDataFileName;
	}
	
	public int getTrainN() {
		return trainN;
	}
	
	public int getTestN() {
		return testN;
	}
	
	public int getNTn() {
		return nTn;
	}
	
	public int getEpochs() {
		return epochs;
	}
	
	public double getLearningRate() {
		return learningRate;
	}
	
	public double getInitialBias() {
		return initialBias;
	}
}

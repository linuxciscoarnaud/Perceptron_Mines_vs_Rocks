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
	private String resultsFileName = "resultsFile.xls";
	
	private String mines_vs_rocksSheetName = "Mines_vs_Rocks";
	private String parametersSheetName;
	
	private int trainN = 208;
	private int testN = 20;
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
	public String getResultsFileName() {
		return resultsFileName;
	}
	
	public String getMines_vs_rocksSheetName() {
		return mines_vs_rocksSheetName;
	}
	
	public String getParametersSheetName() {
		return parametersSheetName;
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

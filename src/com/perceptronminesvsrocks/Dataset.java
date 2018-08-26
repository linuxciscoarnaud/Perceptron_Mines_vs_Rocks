/**
 * 
 */
package com.perceptronminesvsrocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Arnaud
 *
 */

public class Dataset {

	Param param = new Param();
	Results results = new Results();
	
	public void LoadTrainingData(double[][] train_X, int[] train_T) {
		
		File file = new File(param.getTrainDataFileName());
		try {
			Scanner inputStream = new Scanner(file);
			int i = 0;
			while(inputStream.hasNext()) {
				String data = inputStream.next();
				String[] values = data.split(",");
				for (int j = 0; j < values.length - 1; j++) {
					train_X[i][j] = Double.parseDouble(values[j]);
				}
				
				// set labels of training data
				if (values[values.length - 1].equals("M")) {  // "M" if it is a mine (metal cylinder)
					train_T[i] = 1;
				} else if (values[values.length - 1].equals("R")) {  // "R" if the object is a rock
					train_T[i] = -1;
				}
				
				i++;
			}
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void LoadTestData(double[][] test_X, int[] test_T) throws RowsExceededException, WriteException, IOException {
		
		File file = new File(param.getTestDataFileName());
		try {
			Scanner inputStream = new Scanner(file);
			int k = 0;
			while(inputStream.hasNext()) {
				String data = inputStream.next();
				String[] values = data.split(",");
				for (int j = 0; j < values.length - 1; j++) {
					test_X[k][j] = Double.parseDouble(values[j]);
				}
				
				// set labels of test data
				if (values[values.length - 1].equals("R")) {
					test_T[k] = -1;
				} else if (values[values.length - 1].equals("M")) {
					test_T[k] = 1;
				}
				
				k++;
			}
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
	}
}

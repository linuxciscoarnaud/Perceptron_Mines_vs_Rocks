/**
 * 
 */
package com.perceptronminesvsrocks;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Arnaud
 *
 */

public class Results {
	
	Param param = new Param();
	
	File file = new File(param.getResultsFileName());
	WritableWorkbook wwb;
	WritableSheet mysheet = null;
	
	public void writeLabels(int[] dataT, int[] dataP, double[] val) throws RowsExceededException, WriteException, IOException, BiffException {
		
		manageResultFile(param.getMines_vs_rocksSheetName(), 0);
		for (int i = 0; i < dataT.length; i++) {
			Label labelT = new Label(0, i, Integer.toString(dataT[i]));
			Label labelP = new Label(1, i, Integer.toString(dataP[i]));
			Label labelVal = new Label(2, i, Double.toString(val[i]));
			mysheet.addCell(labelT);
			mysheet.addCell(labelP);
			mysheet.addCell(labelVal);
		}
		wwb.write();
		wwb.close();
	}
	
	public void writeParameters(double[] w, double bias) throws IOException, BiffException, WriteException {
		manageResultFile(param.getParametersSheetName(), 1);
		
		wwb.write();
		wwb.close();
	}
	
	public boolean isSheetExistInWritableObject(WritableWorkbook obj, String sheetName) {
		boolean exist = false;
		
		int nunOfSheets = obj.getNumberOfSheets();
		if ( nunOfSheets != 0) {
			for (int i = 0; i < nunOfSheets; i++) {
				if (obj.getSheet(i).getName().contentEquals(sheetName)) {
					exist = true;
					break;
				}
			}
		}
		
		return exist;
	}
	
	public void manageResultFile(String sheetName, int sheetRow) throws BiffException, IOException {
		
		if (file.exists()) {
			Workbook workbook = Workbook.getWorkbook(new File(param.getResultsFileName()));
			wwb = Workbook.createWorkbook(file, workbook);
		} else {
			wwb = Workbook.createWorkbook(file);
		}
		
		if (isSheetExistInWritableObject(wwb, sheetName) == false) {
			mysheet = wwb.createSheet(sheetName, sheetRow);
		} else {
			mysheet = wwb.getSheet(sheetName);
		}
	}
}

package matrix_processor;

import exceptions.WrongFormatException;

public class Processor {
	public Matrix add(Matrix a, Matrix b) throws WrongFormatException {
		if(a.getCols() != b.getCols() || a.getRows() != b.getRows()) throw new WrongFormatException("Error");
		Matrix res = new Matrix(a.getRows(), a.getCols());
		for(int i = 0; i < res.getRows(); i++) {
			for(int j = 0; j < res.getCols(); j++) {
				double val = a.getIndex(i, j) + b.getIndex(i, j);
				res.setIndex(i, j, val);
			}
		}
		return res;
	}
	
	public Matrix mult(Matrix a, double val) {
		Matrix res = new Matrix(a.getRows(), a.getCols());
		for(int i = 0; i < res.getRows(); i++) {
			for(int j = 0; j < res.getCols(); j++) {
				double multiplied = a.getIndex(i, j) * val;
				res.setIndex(i, j, multiplied);
			}
		}
		return res;
	}
	
	public Matrix matrixMult(Matrix a, Matrix b) {
		Matrix res = new Matrix(a.getRows(), b.getCols());
		for(int i = 0; i < res.getRows(); i++) {
			for(int j = 0; j < res.getCols(); j++) {
				double val = 0;
				for(int k = 0; k < a.getCols(); k++) {
					val += (a.getIndex(i, k) * b.getIndex(k, j));
					System.out.println("mult " + a.getIndex(i, k) * b.getIndex(k, j));
				}
				res.setIndex(i, j, val);
			}
		}
		return res;
	}
	public Matrix selectTransposeAlgo(Matrix a, int choice) {
		switch(choice) {
		case 1 : return transposeMain(a);
		case 2 : return transposeSide(a);
		case 3 : return transposeVertical(a);
		case 4: return  transposeHorizontal(a);
		}
		return null;
	}
	
	public Matrix transposeMain(Matrix a) {
		//Matrix tmp = new Matrix(a.getRows(), a.getCols());
		Matrix res = new Matrix(a.getCols(), a.getRows());
		//tmp.fill(a.getMatrix());
		for(int i = 0; i < res.getRows(); i++) {
			res.setRow(a.getCol(i), i);
		}
		return res;
	}
	
	public Matrix transposeSide(Matrix a) {
		Matrix res = new Matrix(a.getCols(), a.getRows());
		//tmp.fill(a.getMatrix());
		for(int i = 0; i < res.getRows(); i++) {
			res.setRowInverse(a.getCol(a.getCols()-i-1), i);
		}
		return res;
	}
	
	public Matrix transposeVertical(Matrix a) {
		Matrix res = new Matrix(a.getRows(), a.getCols());
		//tmp.fill(a.getMatrix());
		for(int i = 0; i < res.getCols(); i++) {
			res.setCol(a.getCol(a.getCols()-i-1), i);
		}
		return res;
	}
	
	public Matrix transposeHorizontal(Matrix a) {
		Matrix res = new Matrix(a.getRows(), a.getCols());
		//tmp.fill(a.getMatrix());
		for(int i = 0; i < res.getCols(); i++) {
			res.setRow(a.getRow(a.getCols()-i-1), i);
		}
		return res;
	}
	
	public double computeDeterminant(Matrix a) throws WrongFormatException {
		if(a.getCols() != a.getRows()) throw new WrongFormatException("No square matrix");
		if(a.getCols() == 1) return a.getIndex(0, 0);
		if(a.getCols() == 2) return a.getIndex(0, 0) * a.getIndex(1, 1) - (a.getIndex(0,1) * a.getIndex(1, 0));
		else {
			double determinant = 0;
			for(int i = 0; i < a.getCols(); i++) {
				System.out.println(Math.pow(-1, i) * a.getIndex(0, i));
				determinant += (Math.pow(-1, i) * a.getIndex(0, i) * computeDeterminant(a.getMinor(0, i)));
			}
			return determinant;
		}
	}
	
	public Matrix inverseMatrix(Matrix a) throws WrongFormatException {
		double determinant = computeDeterminant(a);
		Matrix cofactorMatrix = new Matrix(a.getRows(), a.getCols());
		double factor = 1/determinant;
		for(int i = 0; i < a.getRows(); i++) {
			for(int j = 0; j < a.getCols(); j++) {
				double val = Math.pow(-1, i+j) * computeDeterminant(a.getMinor(i, j));
				cofactorMatrix.setIndex(i, j, val);
			}
		}
		return mult(transposeMain(cofactorMatrix), factor);
	}

}

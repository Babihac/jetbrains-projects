import exceptions.WrongFormatException;
import matrix_processor.Matrix;

public class Util {
	public double computeDeterminant(Matrix a) {
		if(a.getCols() == 1) return a.getIndex(0, 0);
		if(a.getCols() == 2) return a.getIndex(0, 0) * a.getIndex(1, 1) - (a.getIndex(0,1) * a.getIndex(1, 0));
		else {
			double determinant = 0;
			for(int i = 0; i < a.getCols(); i++) {
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
	
	public Matrix transposeMain(Matrix a) {
		//Matrix tmp = new Matrix(a.getRows(), a.getCols());
		Matrix res = new Matrix(a.getCols(), a.getRows());
		//tmp.fill(a.getMatrix());
		for(int i = 0; i < res.getRows(); i++) {
			res.setRow(a.getCol(i), i);
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

}

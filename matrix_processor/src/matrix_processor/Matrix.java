package matrix_processor;

import java.io.File;
import java.util.Scanner;

public class Matrix {
	private int rows;
	private int cols;
	private double[][] matrix;
	public Matrix(int row, int col) {
		this.rows = row;
		this.cols = col;
		matrix = new double[rows][cols];
	}
	
	public void fill() {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] = sc.nextDouble();
			}
		}
	}
	
	public void fill(File f) {
		
	}
	
	public void fill(double[][] m) {
		matrix = m;
		rows = m[0].length;
		cols = m.length;
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}
	
	public double getIndex(int row, int col) {
		return matrix[row][col];
	}
	
	public void setIndex(int row, int col, double val) {
		matrix[row][col] = val;
	}
	
	public void printMatrix() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public double[] getRow(int index) {
		double[] m = new double[cols];
		for(int i = 0; i < cols; i++) {
			m[i] = matrix[index][i];
		}
		return m;
	}
	
	public double[] getCol(int index) {
		double[] m = new double[rows];
		for(int i = 0; i < cols; i++) {
			m[i] = matrix[i][index];
		}
		return m;
	}
	
	public void setRow(double[] a, int index) {
		for(int i = 0; i < cols; i++) {
			System.out.println("Max row is " + rows + " and index is " + index + " i = " + i);
			matrix[index][i] = a[i];
		}
	}
	
	public void setRowInverse(double[] a, int index) {
		for(int i = 0; i < cols; i++) {
			System.out.println("Max row is " + rows + " and index is " + index + " i = " + i);
			matrix[index][i] = a[a.length-i-1];
		}
	}
	
	public void setCol(double[] a, int index) { 
		for(int i = 0; i < rows; i++) {
			matrix[i][index] = a[i];
		}
	}
	
	public Matrix getMinor(int row, int col) {
		int rIndex = 0;
		int cIndex = 0;
		Matrix res = new Matrix(rows-1, cols-1);
		for(int i = 0; i < rows; i++) {
			if(i == row) continue;
			for(int j = 0; j < cols; j++) {
				if(j == col) continue;
				res.setIndex(rIndex, cIndex, matrix[i][j]);
				cIndex++;
			}
			rIndex++;
			cIndex = 0;
		}
		return res;
	}
	
}

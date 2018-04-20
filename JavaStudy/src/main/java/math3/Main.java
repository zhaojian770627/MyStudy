package math3;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class Main {

	public static void main(String[] args) {
//		double m1[][] = new double[][] { { 1, 2, 3 } };
//		double m2[][] = new double[][] { { 1 }, { 2 }, { 3 } };
//
//		RealMatrix matrix1 = new Array2DRowRealMatrix(m1);
//		RealMatrix matrix2 = new Array2DRowRealMatrix(m2);
//		System.out.println(matrix1.multiply(matrix2));

		algebraSolve();
	}

	/*
	 * 解方程
	 */
	private static void algebraSolve() {
		DoubleMatrix2D matrix = new DenseDoubleMatrix2D(2, 2);
		matrix.set(0, 0, 3.0);
		matrix.set(0, 1, 5.0);
		matrix.set(1, 0, 4.0);
		matrix.set(1, 1, 2.0);

		DoubleMatrix2D D;
		D = new DenseDoubleMatrix2D(2, 1);
		D.set(0, 0, 16);
		D.set(1, 0, 18);
		DoubleMatrix2D X;
		Algebra algebra = new Algebra();
		X = algebra.solve(matrix, D);
		System.out.println(X);

	}

}
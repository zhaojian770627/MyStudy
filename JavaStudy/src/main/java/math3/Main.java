package math3;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class Main {

	public static void main(String[] args) {
		double m1[][] = new double[][] { { 1, 2, 3 } };
		double m2[][] = new double[][] { { 1 }, { 2 }, { 3 } };

		RealMatrix matrix1 = new Array2DRowRealMatrix(m1);
		RealMatrix matrix2 = new Array2DRowRealMatrix(m2);
		System.out.println(matrix1.multiply(matrix2));

	}

}
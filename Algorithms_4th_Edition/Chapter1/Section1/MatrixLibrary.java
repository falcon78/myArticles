package Chapter1.Section1;

import java.util.Arrays;

/*
Exercise 1.1.33 Page 60
 */
public class MatrixLibrary {
    public static void main(String[] args) {
        System.out.println("Dot Product");
        System.out.println(dot(
                new double[]{1, 2},
                new double[]{3, 4}
        ));

        System.out.println("Matrix - Matrix Multiplication");
        double[][] result = mult(
                new double[][]{
                        new double[]{1, 2, 3},
                        new double[]{4, 5, 6}
                },
                new double[][]{
                        new double[]{7, 8},
                        new double[]{9, 10},
                        new double[]{11, 12}
                }
        );
        for (double[] row : result) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("Matrix Transpose");
        double[][] transposedMatrix = transpose(
                new double[][]{
                        new double[]{1, 2},
                        new double[]{3, 4}
                }
        );
        for (double[] vector : transposedMatrix) {
            System.out.println(Arrays.toString(vector));
        }

        System.out.println("Matrix - Vector Multiplication");
        double[] matrixVector = matrixVectorMult(
                new double[][]{
                        new double[]{1, -1, 2},
                        new double[]{0, -3, 1}
                },
                new double[]{2, 1, 0}
        );
        System.out.println(Arrays.toString(matrixVector));

        System.out.println("Vector - Matrix Multiplication");
        double[] vectorMatrix = vectorMatrixMult(
                new double[]{2, 1},
                new double[][]{
                        new double[]{1, -1, 2},
                        new double[]{0, -3, 1}
                }
        );
        System.out.println(Arrays.toString(vectorMatrix));
    }

    public static double dot(double[] x, double[] y) {
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * y[i];
        }
        return result;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a.length];
        if (a.length != b[0].length) throw new IllegalArgumentException();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                int sum = 0;
                for (int k = 0; k < b.length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }

    public static double[] matrixVectorMult(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        if (matrix[0].length != vector.length) throw new IllegalArgumentException();

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }

        return result;
    }

    public static double[] vectorMatrixMult(double[] vector, double[][] matrix) {
        double[] result = new double[matrix[0].length];
        if (vector.length != matrix.length) throw new IllegalArgumentException();

        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[j] += matrix[i][j] * vector[i];
            }
        }

        return result;
    }

}

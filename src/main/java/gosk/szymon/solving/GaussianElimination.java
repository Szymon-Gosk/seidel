package gosk.szymon.solving;

import gosk.szymon.math.Field;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac GaussianElimination.java
 *  Execution:    java GaussianElimination
 *
 *  Gaussian elimination with partial pivoting.
 *
 *  % java GaussianElimination
 *  -1.0
 *  2.0
 *  2.0
 *
 ******************************************************************************/

public class GaussianElimination {

    // Gaussian elimination with partial pivoting
    public static <T> T[] lsolve(T[][] A, T[] b, Field<T> field) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (field.ge(field.abs(A[i][p]), field.abs(A[max][p]))) {
                    max = i;
                }
            }
            T[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            T   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (field.leq(field.abs(A[p][p]), field.EPSILON())) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < n; i++) {
                T alpha = field.divide().apply(A[i][p], A[p][p]);
                b[i] = field.subtract().apply(b[i], field.multiply().apply(alpha, b[p]));
                for (int j = p; j < n; j++) {
                    A[i][j] = field.subtract().apply(A[i][j], field.multiply().apply(alpha, A[p][j]));
                }
            }
        }

        // back substitution
        T[] x = newArray(n);
        for (int i = n - 1; i >= 0; i--) {
            T sum = field.zero();
            for (int j = i + 1; j < n; j++) {
                sum = field.add().apply(sum, field.multiply().apply(A[i][j], x[j]));
            }
            x[i] = field.divide().apply(field.subtract().apply(b[i], sum), A[i][i]);
        }
        return x;
    }

    @SafeVarargs
    public static <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }

}

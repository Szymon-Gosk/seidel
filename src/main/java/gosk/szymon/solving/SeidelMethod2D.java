package gosk.szymon.solving;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.algebra.Row;
import gosk.szymon.model.Result2D;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class SeidelMethod2D<T> implements SolvingStrategy<T> {

    @Override
    public Result2D<T> apply(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        return seidel(A, b, c);
    }

    private Result2D<T> seidel(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        int constrainsSize = A.height();
        int variablesSize = c.height();

        if (variablesSize == 1) {
            return solveForOneVariable(A, b, c);
        }
        if (variablesSize == constrainsSize) {
            return solveWithGauss(A, b, c);
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(0, constrainsSize + 1);
        Result2D<T> x = seidel(A.removeRow(randomIndex), b.removeRow(randomIndex), c);

        if(satisfies(x, A.getRow(randomIndex), b.getRow(randomIndex))) {
            return x;
        } else {
            return null;
        }
    }

    private Result2D<T> solveForOneVariable(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        return null;
    }

    private Result2D<T> solveWithGauss(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        return null;
    }

    private boolean satisfies(Result2D<T> x, Row<T> a, Row<T> b) {
        return false;
    }

}

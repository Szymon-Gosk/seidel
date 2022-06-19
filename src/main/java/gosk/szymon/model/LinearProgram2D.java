package gosk.szymon.model;

import gosk.szymon.math.algebra.Matrix;
import org.jetbrains.annotations.NotNull;

public class LinearProgram2D<T> extends LinearBase<T> {

    public LinearProgram2D(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        super(A, b, c);
    }

    @Override
    public int dimension() {
        return 2;
    }

}

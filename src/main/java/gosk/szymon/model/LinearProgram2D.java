package gosk.szymon.model;

import gosk.szymon.math.algebra.Matrix;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LinearProgram2D<T> extends LinearBase<T> {

    public LinearProgram2D(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, List<Constraint> constraints) {
        super(A, b, c, constraints);
    }

    public LinearProgram2D(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, Constraint constraintType) {
        super(A, b, c, constraintType);
    }

    @Override
    public int dimension() {
        return 2;
    }

}

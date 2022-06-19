package gosk.szymon.model;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.geometry.Point;
import gosk.szymon.solving.SolvingStrategy;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class LinearProgram extends LinearBase<BigDecimal> {

    public LinearProgram(@NotNull Matrix<BigDecimal> A, @NotNull Matrix<BigDecimal> b, @NotNull Matrix<BigDecimal> c) {
        super(A, b, c);
    }

    @Override
    public Point<BigDecimal> solve(@NotNull SolvingStrategy<BigDecimal> strategy) {
        return null;
    }

}

package gosk.szymon.model.lp;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Constraint;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CanonicalLinearProgram2D extends LinearProgram2D<BigDecimal> {

    public CanonicalLinearProgram2D(@NotNull Matrix<BigDecimal> A, @NotNull Matrix<BigDecimal> b, @NotNull Matrix<BigDecimal> c) {
        super(A, b, c, Constraint.LESS_OR_EQUAL);
    }

}
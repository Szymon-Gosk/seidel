package gosk.szymon.model.lp;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Constraint;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CanonicalLinearProgram2D extends LinearProgram2D<Double> {

    public CanonicalLinearProgram2D(@NotNull Matrix<Double> A, @NotNull Matrix<Double> b, @NotNull Matrix<Double> c) {
        super(A, b, c, Constraint.LESS_OR_EQUAL);
    }

}

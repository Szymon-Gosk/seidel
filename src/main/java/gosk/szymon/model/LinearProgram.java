package gosk.szymon.model;

import gosk.szymon.math.Matrix;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class LinearProgram extends LinearBase {

    public LinearProgram(@NotNull Matrix<BigDecimal> A, @NotNull Matrix<BigDecimal> b, @NotNull Matrix<BigDecimal> c) {
        super(A, b, c);
    }
}

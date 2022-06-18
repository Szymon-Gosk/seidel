package gosk.szymon.model;

import gosk.szymon.math.Matrix;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
public abstract class LinearBase {

    protected final Matrix<BigDecimal> A;
    protected final Matrix<BigDecimal> b;
    protected final Matrix<BigDecimal> c;

    public LinearBase(@NotNull Matrix<BigDecimal> A, @NotNull Matrix<BigDecimal> b, @NotNull Matrix<BigDecimal> c) {
        validateFields(A, b, c);
        this.A = A;
        this.b = b;
        this.c = c;
    }

    private void validateFields(@NotNull Matrix<BigDecimal> A, @NotNull Matrix<BigDecimal> b, @NotNull Matrix<BigDecimal> c) {
        if(A.height() != b.height() || A.width() != c.height()) {
            throw new InvalidProgramException("Program input is invalid");
        }
    }

    protected static class InvalidProgramException extends RuntimeException {
        public InvalidProgramException(String m) {
            super(m);
        }
    }

}

package gosk.szymon.model;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.geometry.Point;
import gosk.szymon.solving.SolvingStrategy;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class LinearBase<T> {

    protected final Matrix<T> A;
    protected final Matrix<T> b;
    protected final Matrix<T> c;

    public LinearBase(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        validateFields(A, b, c);
        this.A = A;
        this.b = b;
        this.c = c;
    }

    public Point<T> solve(@NotNull SolvingStrategy<T> strategy) {
        return strategy.apply(A, b, c);
    }

    private void validateFields(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
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

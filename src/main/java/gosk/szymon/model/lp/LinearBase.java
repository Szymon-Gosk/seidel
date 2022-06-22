package gosk.szymon.model.lp;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Constraint;
import gosk.szymon.model.Result;
import gosk.szymon.solving.SolvingStrategy;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@Getter
public abstract class LinearBase<T> implements LinearProgram<T, Constraint> {

    protected final Matrix<T> A;
    protected final Matrix<T> b;
    protected final Matrix<T> c;

    protected final List<Constraint> constraints;

    public LinearBase(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, List<Constraint> constraints) {
        validateFields(A, b, c);
        this.A = A;
        this.b = b;
        this.c = c;
        this.constraints = constraints;
    }

    public LinearBase(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, Constraint constraintType) {
        validateFields(A, b, c);
        this.A = A;
        this.b = b;
        this.c = c;
        List<Constraint> constraints = new LinkedList<>();
        for (int i = 0; i < A.height(); i++) {
            constraints.add(constraintType);
        }
        this.constraints = constraints;
    }

    public abstract int dimension();

    @Override
    public @NotNull Result<T> solve(@NotNull SolvingStrategy<T> strategy) {
        return strategy.apply(A, b, c, constraints);
    }

    private void validateFields(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        if(A.height() != b.height() || A.width() != c.height() || A.width() != dimension()
                || b.width() != 1 || c.width() != 1) {
            throw new InvalidProgramException("Program input is invalid");
        }
    }

    protected static class InvalidProgramException extends RuntimeException {
        public InvalidProgramException(String m) {
            super(m);
        }
    }

}

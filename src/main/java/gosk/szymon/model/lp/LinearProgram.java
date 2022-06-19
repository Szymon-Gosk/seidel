package gosk.szymon.model.lp;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Result;
import gosk.szymon.solving.SolvingStrategy;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface LinearProgram<T, E extends Enum<E>> {

    int dimension();

    @NotNull Matrix<T> getA();

    @NotNull Matrix<T> getB();

    @NotNull Matrix<T> getC();

    @NotNull List<E> getConstraints();

    @NotNull Result<T> solve(@NotNull SolvingStrategy<T> strategy);

}

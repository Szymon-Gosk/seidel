package gosk.szymon.solving;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Result;
import org.jetbrains.annotations.NotNull;

public interface SolvingStrategy<T> {

    Result<T> apply(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c);

}

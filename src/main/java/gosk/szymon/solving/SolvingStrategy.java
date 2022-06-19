package gosk.szymon.solving;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.geometry.Point;
import org.jetbrains.annotations.NotNull;

public interface SolvingStrategy<T> {

    Point<T> apply(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c);

}

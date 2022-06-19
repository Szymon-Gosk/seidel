package gosk.szymon.model;

import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.geometry.Point;
import gosk.szymon.solving.SolvingStrategy;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface LinearProgram<T, E extends Enum<E>> {

    int dimension();

    @NotNull Matrix<T> getA();

    @NotNull Matrix<T> getB();

    @NotNull Matrix<T> getC();

    @NotNull List<E> getConstraints();

    @NotNull Point<T> solve(@NotNull SolvingStrategy<T> strategy);

}

package gosk.szymon.model;

import gosk.szymon.math.geometry.Point;
import gosk.szymon.solving.SolvingStrategy;
import org.jetbrains.annotations.NotNull;

public interface LinearProgram<T> {

    int dimension();

    Point<T> solve(@NotNull SolvingStrategy<T> strategy);

}

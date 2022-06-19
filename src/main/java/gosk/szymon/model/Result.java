package gosk.szymon.model;

import gosk.szymon.math.geometry.Point;
import gosk.szymon.math.geometry.Surface;

import java.util.Optional;

public interface Result<T> {

    Optional<Point<T>> getPoint();

    Optional<Surface<T>> getSurface();

}
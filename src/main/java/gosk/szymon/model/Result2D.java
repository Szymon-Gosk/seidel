package gosk.szymon.model;

import gosk.szymon.math.geometry.Point;
import gosk.szymon.math.geometry.Point2D;
import gosk.szymon.math.geometry.Segment2D;
import gosk.szymon.math.geometry.Surface;

import java.util.Optional;

public class Result2D<T> implements Result<T> {

    protected Point2D<T> point;
    protected Segment2D<T> segment;

    public Result2D(Point2D<T> point) {
        this.point = point;
    }

    public Result2D(Segment2D<T> segment) {
        this.segment = segment;
    }

    @Override
    public Optional<Point<T>> getPoint() {
        return Optional.ofNullable(point);

    }

    @Override
    public Optional<Surface<T>> getSurface() {
        return Optional.ofNullable(segment);
    }

}

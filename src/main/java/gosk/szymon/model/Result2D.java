package gosk.szymon.model;

import gosk.szymon.math.geometry.Point;
import gosk.szymon.math.geometry.Point2D;
import gosk.szymon.math.geometry.Segment2D;
import gosk.szymon.math.geometry.Surface;

import java.util.Optional;

public class Result2D<T> implements Result<T> {

    protected Point2D<T> point;
    protected Segment2D<T> segment;

    protected final boolean isPositiveInfinity;
    protected final boolean isNegativeInfinity;
    protected final boolean isNone;

    public Result2D(Point2D<T> point) {
        this.point = point;
        isPositiveInfinity = false;
        isNegativeInfinity = false;
        isNone = false;
    }

    public Result2D(Segment2D<T> segment) {
        this.segment = segment;
        isPositiveInfinity = false;
        isNegativeInfinity = false;
        isNone = false;
    }

    public Result2D(boolean isPositiveInfinity, boolean isNegativeInfinity, boolean isNone) {
        this.isPositiveInfinity = isPositiveInfinity;
        this.isNegativeInfinity = isNegativeInfinity;
        this.isNone = isNone;
    }

    @Override
    public boolean isPositiveInfinity() {
        return false;
    }

    @Override
    public boolean isNegativeInfinity() {
        return false;
    }

    @Override
    public boolean isNone() {
        return false;
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

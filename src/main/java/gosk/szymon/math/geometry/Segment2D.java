package gosk.szymon.math.geometry;

public record Segment2D<T>(Point2D<T> origin, Point2D<T> end) implements Surface<T> { }

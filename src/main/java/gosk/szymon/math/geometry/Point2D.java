package gosk.szymon.math.geometry;

import java.util.List;

public record Point2D<T>(T x, T y) implements Point<T> {

    @Override
    public List<T> getCoordinates() {
        return List.of(x, y);
    }

    @Override
    public T get(int i) {
        if (i == 1) {
            return x;
        } else if (i == 2) {
            return y;
        } else {
            throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}

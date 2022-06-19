package gosk.szymon.math.geometry;

import java.util.List;

public interface Point<T> {

    List<T> getCoordinates();

    T get(int i);

}

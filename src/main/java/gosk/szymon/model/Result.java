package gosk.szymon.model;

import java.util.List;

public interface Result<T> {

    T get(int index);

    List<T> getAll();

    int size();

}

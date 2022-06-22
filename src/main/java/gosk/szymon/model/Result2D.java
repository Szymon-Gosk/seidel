package gosk.szymon.model;

import java.util.List;

public class Result2D<T> implements Result<T> {

    private final List<T> values;

    public Result2D(List<T> values) {
        this.values = values;
    }

    @Override
    public T get(int index) {
        return values.get(index);
    }

    @Override
    public List<T> getAll() {
        return values;
    }

    @Override
    public int size() {
        return values.size();
    }
}

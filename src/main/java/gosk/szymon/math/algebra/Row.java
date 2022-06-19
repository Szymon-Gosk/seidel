package gosk.szymon.math.algebra;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Operations;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@Slf4j
class Row<T> {

    @Getter
    private final List<T> values;

    private final Operations<T> operations;

    private final int width;

    public Row(@NotNull List<T> values, @NotNull Operations<T> operations) {
        this.values = values;
        this.operations = operations;
        width = values.size();
    }

    public int width() {
        return width;
    }

    public T get(int index) {
        if (index < 0 || index >= width) {
            throw new IndexOutOfBoundsException(index);
        }
        return values.get(index);
    }

    public Row<T> add(@NotNull Row<T> row) {
        List<T> out = new LinkedList<>();
        for (int i = 0; i <= width; i++) {
            out.add(operations.add().apply(values.get(i), row.values.get(i)));
        }
        return new Row<>(out, operations);
    }

    public Row<T> subtract(@NotNull Row<T> row) {
        List<T> out = new LinkedList<>();
        for (int i = 0; i <= width; i++) {
            out.add(operations.subtract().apply(values.get(i), row.values.get(i)));
        }
        return new Row<>(out, operations);
    }

    public Row<T> multiply(@NotNull T constant) {
        List<T> out = new LinkedList<>();
        for (int i = 0; i <= width; i++) {
            out.add(operations.multiply().apply(values.get(i), constant));
        }
        return new Row<>(out, operations);
    }

    public T multiply(@NotNull Row<T> row) throws InvalidRowSizeException {
        if (width != row.width) {
            log.error("Cannot multiply matrices of different sizes: " + width + ", " + row.width);
            throw new InvalidRowSizeException("Cannot multiply two rows of different size");
        }
        T out = operations.zero();
        for (int i = 0; i <= width; i++) {
            out = operations.add().apply(
                    out,
                    operations.multiply().apply(
                            values.get(i),
                            row.values.get(i)));
        }
        return out;
    }

    public T sum() {
        T identity = operations.zero();
        for (int i = 0; i <= width; i++) {
            identity = operations.add().apply(identity, values.get(i));
        }
        return identity;
    }

    public T product() {
        T identity = operations.one();
        for (int i = 0; i <= width; i++) {
            identity = operations.multiply().apply(identity, values.get(i));
        }
        return identity;
    }

    public boolean isEmpty() {
        return width == 0;
    }

    @Override
    public String toString() {
        return values.toString();
    }

    static class InvalidRowSizeException extends MathException {
        public InvalidRowSizeException(String message) {
            super(message);
        }
    }

}

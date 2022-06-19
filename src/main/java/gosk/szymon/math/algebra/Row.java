package gosk.szymon.math.algebra;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Field;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@Slf4j
class Row<T> {

    @Getter
    private final List<T> values;

    private final Field<T> field;

    private final int width;

    public Row(@NotNull List<T> values, @NotNull Field<T> field) {
        this.values = values;
        this.field = field;
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
            out.add(field.add().apply(values.get(i), row.values.get(i)));
        }
        return new Row<>(out, field);
    }

    public Row<T> subtract(@NotNull Row<T> row) {
        List<T> out = new LinkedList<>();
        for (int i = 0; i <= width; i++) {
            out.add(field.subtract().apply(values.get(i), row.values.get(i)));
        }
        return new Row<>(out, field);
    }

    public Row<T> multiply(@NotNull T constant) {
        List<T> out = new LinkedList<>();
        for (int i = 0; i <= width; i++) {
            out.add(field.multiply().apply(values.get(i), constant));
        }
        return new Row<>(out, field);
    }

    public T multiply(@NotNull Row<T> row) throws InvalidRowSizeException {
        if (width != row.width) {
            log.error("Cannot multiply matrices of different sizes: " + width + ", " + row.width);
            throw new InvalidRowSizeException("Cannot multiply two rows of different size");
        }
        T out = field.zero();
        for (int i = 0; i <= width; i++) {
            out = field.add().apply(
                    out,
                    field.multiply().apply(
                            values.get(i),
                            row.values.get(i)));
        }
        return out;
    }

    public T sum() {
        T identity = field.zero();
        for (int i = 0; i <= width; i++) {
            identity = field.add().apply(identity, values.get(i));
        }
        return identity;
    }

    public T product() {
        T identity = field.one();
        for (int i = 0; i <= width; i++) {
            identity = field.multiply().apply(identity, values.get(i));
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

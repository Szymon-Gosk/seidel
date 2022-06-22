package gosk.szymon.math.algebra;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Field;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EqualsAndHashCode
public class ImmutableMatrix<T> implements Matrix<T> {

    @Getter
    private final List<Row<T>> rows;

    private final int width;
    private final int height;

    @Getter
    private final Field<T> field;

    public ImmutableMatrix(@NotNull List<List<T>> values, @NotNull Field<T> field) throws InvalidMatrixSizeException {
        this.height = values.size();
        this.width = values.get(0).size();
        this.field = field;
        this.rows = initializeRows(values);
    }

    private ImmutableMatrix(@NotNull List<Row<T>> rows, int width, int height, @NotNull Field<T> field) {
        this.height = height;
        this.width = width;
        this.field = field;
        this.rows = rows;
    }

    @Override
    public T get(int row, int column) {
        if (row < 0 || row > height) {
            throw new IndexOutOfBoundsException(row);
        }
        if (column < 0 || column > width) {
            throw new IndexOutOfBoundsException(column);
        }
        return getRow(row).get(column);
    }

    @Override
    public Row<T> getRow(int index) {
        if (index < 0 || index > height) {
            throw new IndexOutOfBoundsException(index);
        }
        return rows.get(index);
    }

    @Override
    public Row<T> getColumn(int index) {
        if (index < 0 || index > width) {
            throw new IndexOutOfBoundsException(index);
        }
        List<T> values = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            values.add(get(i, index));
        }
        return new Row<>(values, field);
    }

    @Override
    public Matrix<T> addColumn(Row<T> column) {
        List<Row<T>> rows = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<T> rowValues = getRow(i).getValues();
            rowValues.add(column.get(i));
            rows.add(new Row<>(rowValues, field));
        }
        return new ImmutableMatrix<>(rows, width, height, field);
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public <U> boolean isSizeOf(@NotNull Matrix<U> matrix) {
        return width == matrix.width() && height == matrix.height();
    }

    @Override
    public ImmutableMatrix<T> add(@NotNull Matrix<T> matrix) throws InvalidMatrixSizeException {
        validateSize(matrix);
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            out.add(getRow(i).add(matrix.getRow(i)));
        }
        return new ImmutableMatrix<>(out, width, height, field);
    }

    @Override
    public ImmutableMatrix<T> subtract(@NotNull Matrix<T> matrix) throws InvalidMatrixSizeException {
        validateSize(matrix);
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            out.add(getRow(i).subtract(matrix.getRow(i)));
        }
        return new ImmutableMatrix<>(out, width, height, field);
    }

    public ImmutableMatrix<T> multiply(@NotNull T constant) {
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            out.add(getRow(i).multiply(constant));
        }
        return new ImmutableMatrix<>(out, width, height, field);
    }

    @Override
    public ImmutableMatrix<T> multiply(@NotNull Matrix<T> matrix) throws InvalidMatrixSizeException {
        validateMultiplicationCondition(matrix);
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<T> nextRow = new ArrayList<>();
            for (int j = 0; j < matrix.width(); j++) {
                try {
                    nextRow.add(getRow(i).multiply(matrix.getColumn(j)));
                } catch (Row.InvalidRowSizeException e) {
                    throw new InvalidMatrixSizeException(e.getMessage());
                }
            }
            out.add(new Row<>(nextRow, field));
        }
        return new ImmutableMatrix<>(out, matrix.width(), height, field);
    }

    @Override
    public Matrix<T> transpose() {
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < width(); i++) {
            List<T> nextRow = new ArrayList<>();
            for (int j = 0; j < height(); j++) {
                nextRow.add(get(j, i));
            }
            out.add(new Row<>(nextRow, field));
        }
        return new ImmutableMatrix<>(out, width, height, field);
    }

    @Override
    public Matrix<T> removeRow(int index) {
        List<Row<T>> out = new ArrayList<>();
        if (index < 0 || index > height) {
            throw new IndexOutOfBoundsException(index);
        }
        for (int i = 0; i < height; i++) {
            if(i != index) {
                out.add(getRow(i));
            }
        }
        return new ImmutableMatrix<>(out, width, height - 1, field);
    }

    private <U> void validateMultiplicationCondition(Matrix<U> matrix) throws InvalidMatrixSizeException {
        if (width() != matrix.height()) {
            log.error("Cannot multiply matrices: sizes are " +
                    width() +
                    "x" +
                    height() +
                    " and " +
                    matrix.width() +
                    "x" +
                    matrix.height() +
                    " - condition is not satisfied width=height: " +
                    width() +
                    matrix.height());
            throw new InvalidMatrixSizeException("Width of first matrix must be equal to height of the second matrix");
        }
    }

    private <U> void validateSize(Matrix<U> matrix) throws InvalidMatrixSizeException {
        if (!matrix.isSizeOf(this)) {
            log.error("Cannot add matrices with different size: expected was " +
                    width +
                    "x" +
                    height +
                    ", but was " +
                    matrix.width() +
                    "x" +
                    matrix.height());
            throw new InvalidMatrixSizeException("Added matrices must have the same size");
        }
    }

    private List<Row<T>> initializeRows(@NotNull List<List<T>> input) throws InvalidMatrixSizeException {
        if (height == 0 || width == 0) {
            log.error("Cannot instantiate matrix with empty list");
            throw new InvalidMatrixSizeException("Matrix cannot be of size 0");
        }

        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<T> valuesInRow = input.get(i);
            if (valuesInRow.size() != width) {
                log.error("Cannot instantiate matrix: expected width was " + width + " but width of row " + i + " was " + valuesInRow.size());
                throw new InvalidMatrixSizeException("Rows of the matrix must have the same size");
            }
            out.add(new Row<>(valuesInRow, field));
        }
        return out;
    }

    private static class InvalidMatrixSizeException extends MathException {
        public InvalidMatrixSizeException(String message) {
            super(message);
        }
    }

}

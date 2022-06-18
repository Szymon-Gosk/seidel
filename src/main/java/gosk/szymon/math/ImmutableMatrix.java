package gosk.szymon.math;

import gosk.szymon.exception.MathException;
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

    private final Operations<T> operations;

    public ImmutableMatrix(@NotNull List<List<T>> values, @NotNull Operations<T> operations) throws InvalidMatrixSizeException {
        this.height = values.size();
        this.width = values.get(0).size();
        this.operations = operations;
        this.rows = initializeRows(values);
    }

    private ImmutableMatrix(@NotNull List<Row<T>> rows, int width, int height, @NotNull Operations<T> operations) {
        this.height = height;
        this.width = width;
        this.operations = operations;
        this.rows = rows;
    }

    @Override
    public T get(int row, int column) {
        return rows.get(row).get(column);
    }

    public int width() {
        return width;
    }

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
        for (int i = 0; i < width; i++) {
            out.add(rows.get(i).add(matrix.getRows().get(i)));
        }
        return new ImmutableMatrix<>(out, width, height, operations);
    }

    @Override
    public ImmutableMatrix<T> subtract(@NotNull Matrix<T> matrix) throws InvalidMatrixSizeException {
        validateSize(matrix);
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            out.add(rows.get(i).subtract(matrix.getRows().get(i)));
        }
        return new ImmutableMatrix<>(out, width, height, operations);
    }

    public ImmutableMatrix<T> multiply(@NotNull T constant) {
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            out.add(rows.get(i).multiply(constant));
        }
        return new ImmutableMatrix<>(out, width, height, operations);
    }

    @Override
    public Matrix<T> multiply(@NotNull Matrix<T> matrix) throws InvalidMatrixSizeException {
        validateMultiplicationCondition(matrix);
        List<Row<T>> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<T> nextRow = new ArrayList<>();
            for(int j = 0; j < matrix.width(); j++) {
                try {
                    nextRow.add(rows.get(i).multiply(matrix.getRows().get(j)));
                } catch (Row.InvalidRowSizeException e) {
                    throw new InvalidMatrixSizeException(e.getMessage());
                }
            }
            out.add(new Row<>(nextRow, operations));
        }
        return new ImmutableMatrix<>(out, width, height, operations);
    }

    public Matrix<T> transpose() {
        List<Row<T>> out = new ArrayList<>();
        for(int i = 0; i < width(); i++) {
            List<T> nextRow = new ArrayList<>();
            for(int j = 0; j < height(); j++) {
                nextRow.add(get(j, i));
            }
            out.add(new Row<>(nextRow, operations));
        }
        return new ImmutableMatrix<>(out, width, height, operations);
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
            out.add(new Row<>(valuesInRow, operations));
        }
        return out;
    }

    private static class InvalidMatrixSizeException extends MathException {
        public InvalidMatrixSizeException(String message) {
            super(message);
        }
    }

}

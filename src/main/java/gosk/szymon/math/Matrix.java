package gosk.szymon.math;

import gosk.szymon.exception.MathException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Matrix<T> {

    T get(int row, int column);

    List<Row<T>> getRows();

    int width();

    int height();

    <U> boolean isSizeOf(@NotNull Matrix<U> matrix);

    Matrix<T> add(@NotNull Matrix<T> matrix) throws MathException;

    Matrix<T> subtract(@NotNull Matrix<T> matrix) throws MathException;

    Matrix<T> multiply(@NotNull T constant);

    Matrix<T> multiply(@NotNull Matrix<T> matrix) throws MathException;

    Matrix<T> transpose();

}

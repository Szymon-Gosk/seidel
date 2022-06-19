package gosk.szymon.util;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Field;
import gosk.szymon.math.algebra.ImmutableMatrix;
import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.parser.MatrixInputParser;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@UtilityClass
public class MatrixGenerator {

    public static <T> @NotNull List<ImmutableMatrix<T>> generate(String inputPath, Function<String, T> mapper, Field<T> field) throws IOException {
        MatrixInputParser<T> parser = new MatrixInputParser<>();
        InputStream fis = parser.loadResource(inputPath);

        List<List<List<T>>> output = parser.parse(fis, mapper);
        List<ImmutableMatrix<T>> matrices = new ArrayList<>();

        output.forEach(values -> matrices.add(new ImmutableMatrix<>(values, field)));
        return matrices;
    }

}

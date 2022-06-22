package gosk.szymon;

import gosk.szymon.math.Field;
import gosk.szymon.math.algebra.ImmutableMatrix;
import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.model.Result;
import gosk.szymon.model.Result2D;
import gosk.szymon.model.lp.CanonicalLinearProgram2D;
import gosk.szymon.parser.MatrixInputParser;
import gosk.szymon.solving.SeidelMethod2D;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

    }

    private static List<ImmutableMatrix<BigDecimal>> generateFrom(String path) {
        MatrixInputParser<BigDecimal> parser = new MatrixInputParser<>();
        InputStream fis = parser.loadResource(path);

        List<List<List<BigDecimal>>> output;
        try {
            output = parser.parse(fis, BigDecimal::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<ImmutableMatrix<BigDecimal>> matrices = new ArrayList<>();

        output.forEach(values -> matrices.add(new ImmutableMatrix<>(values, Field.BIG_DECIMAL_FIELD)));
        return matrices;
    }

}

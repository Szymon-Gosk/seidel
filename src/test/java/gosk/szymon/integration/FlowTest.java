package gosk.szymon.integration;

import gosk.szymon.math.Field;
import gosk.szymon.math.algebra.ImmutableMatrix;
import gosk.szymon.model.Result;
import gosk.szymon.model.lp.CanonicalLinearProgram2D;
import gosk.szymon.solving.SeidelMethod2D;
import gosk.szymon.util.MatrixGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class FlowTest {

    @Test
    public void canSolveWithSeidel() throws IOException {

        List<ImmutableMatrix<Double>> matrices = MatrixGenerator.
                generate("matrices/integration-data.txt", Double::parseDouble, Field.DECIMAL_FIELD);

        ImmutableMatrix<Double> A = matrices.get(0);
        ImmutableMatrix<Double> b = matrices.get(1);
        ImmutableMatrix<Double> c = matrices.get(2);

        CanonicalLinearProgram2D lp = new CanonicalLinearProgram2D(A, b, c);
        SeidelMethod2D<Double> seidel = new SeidelMethod2D<>();

        Result<Double> opt = lp.solve(seidel);

        if(opt.getAll().isEmpty()) {
            System.out.println("+infinity");
        }

        System.out.println(opt.getAll());

    }



}

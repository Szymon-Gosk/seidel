package gosk.szymon.math.algebra;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Field;
import gosk.szymon.parser.MatrixInputParser;
import gosk.szymon.util.MatrixGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ImmutableMatrixTest {

    private static final String validMatrixInput = "matrices/validInput.txt";
    private static final String multiplicationInput = "matrices/multiplicationInput.txt";

    private final MatrixInputParser<Integer> parser = new MatrixInputParser<>();

    @Test
    public void multipliesMatricesCorrectly() {
        // given
        List<ImmutableMatrix<Integer>> matrices = List.of();
        try {
            matrices = MatrixGenerator.generate(multiplicationInput, Integer::valueOf, Field.INTEGER_FIELD);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception on valid data");
        }

        ImmutableMatrix<Integer> byConstant = matrices.get(0).multiply(2);
        ImmutableMatrix<Integer> byMatrix = matrices.get(0).multiply(matrices.get(1));

        assertEquals(3, byConstant.width());
        assertEquals(2, byConstant.height());
        assertEquals(List.of(4, 6, 10), byConstant.getRows().get(0).getValues());
        assertEquals(List.of(14, 22, 26), byConstant.getRows().get(1).getValues());

        assertEquals(2, byMatrix.width());
        assertEquals(2, byMatrix.height());
        assertEquals(List.of(74, 92), byMatrix.getRows().get(0).getValues());
        assertEquals(List.of(212, 267), byMatrix.getRows().get(1).getValues());
    }

    @Test
    public void generatesMatrixCorrectly() {
        // given
        List<ImmutableMatrix<Integer>> matrices = List.of();
        try {
            matrices = MatrixGenerator.generate(validMatrixInput, Integer::valueOf, Field.INTEGER_FIELD);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception on valid data");
        }

        // when

        ImmutableMatrix<Integer> firstM = matrices.get(0);
        ImmutableMatrix<Integer> secondM = matrices.get(1);
        ImmutableMatrix<Integer> thirdM = matrices.get(2);

        // then
        assertEquals(3, firstM.width());
        assertEquals(2, firstM.height());

        assertEquals(2, firstM.get(0, 0));
        assertEquals(3, firstM.get(0, 1));
        assertEquals(5, firstM.get(0, 2));
        assertEquals(7, firstM.get(1, 0));
        assertEquals(11, firstM.get(1, 1));
        assertEquals(13, firstM.get(1, 2));

        assertEquals(2, secondM.width());
        assertEquals(3, secondM.height());

        assertEquals(List.of(4, 5), secondM.getRows().get(0).getValues());
        assertEquals(List.of(6, 7), secondM.getRows().get(1).getValues());
        assertEquals(List.of(8, 9), secondM.getRows().get(2).getValues());

        assertEquals(1, thirdM.width());
        assertEquals(1, thirdM.height());
        assertEquals(2, thirdM.get(0, 0));
    }

}
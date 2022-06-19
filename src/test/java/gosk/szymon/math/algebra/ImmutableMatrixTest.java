package gosk.szymon.math.algebra;

import gosk.szymon.exception.MathException;
import gosk.szymon.math.Field;
import gosk.szymon.parser.MatrixInputParser;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ImmutableMatrixTest {

    private final MatrixInputParser<Integer> parser = new MatrixInputParser<>();

    @Test
    public void generatesMatrixCorrectly() {
        // given
        InputStream fis = parser.loadResource("matrices/validInput.txt");

        List<List<List<Integer>>> output = List.of();
        try {
            output = parser.parse(fis, Integer::valueOf);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception on valid file");
        }

        // and
        List<List<Integer>> first = output.get(0);
        List<List<Integer>> second = output.get(1);
        List<List<Integer>> third = output.get(2);

        // when
        try {
            ImmutableMatrix<Integer> firstM = new ImmutableMatrix<>(first, Field.INTEGER_FIELD);
            ImmutableMatrix<Integer> secondM = new ImmutableMatrix<>(second, Field.INTEGER_FIELD);
            ImmutableMatrix<Integer> thirdM = new ImmutableMatrix<>(third, Field.INTEGER_FIELD);

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
        } catch (MathException e) {
            e.printStackTrace();
            fail("Should not throw exception on valid input data for matrix");
        }
    }

    @Test
    public void failsOnInvalidData() {

    }

}
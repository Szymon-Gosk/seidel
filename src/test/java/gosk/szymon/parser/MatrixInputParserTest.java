package gosk.szymon.parser;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MatrixInputParserTest {

    @Test
    public void canParseFiles() {
        // when
        MatrixInputParser<Integer> parser = new MatrixInputParser<>();
        InputStream fis = parser.loadResource("matrices/validInput.txt");

        // and
        List<List<Integer>> firstExpected = List.of(List.of(2, 3, 5), List.of(7, 11, 13));
        List<List<Integer>> secondExpected = List.of(List.of(4, 5), List.of(6, 7), List.of(8, 9));
        List<List<Integer>> thirdExpected = List.of(List.of(2));

        // then
        try {
            List<List<List<Integer>>> output = parser.parse(fis, Integer::valueOf);
            List<List<Integer>> first = output.get(0);
            List<List<Integer>> second = output.get(1);
            List<List<Integer>> third = output.get(2);

            assertEquals(firstExpected, first);
            assertEquals(secondExpected, second);
            assertEquals(thirdExpected, third);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception on valid file");
        }
    }

}
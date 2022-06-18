package gosk.szymon;

import gosk.szymon.math.ImmutableMatrix;

import java.util.List;

import static gosk.szymon.math.Operations.INTEGER_OPERATIONS;

public class Application {

    public static void main(String[] args) {

        List<Integer> values = List.of(1, 2, 3, 4, 5);

        ImmutableMatrix.Row<Integer> row = new ImmutableMatrix.Row<>(values, INTEGER_OPERATIONS);

        System.out.println(row.getValues());

    }

}

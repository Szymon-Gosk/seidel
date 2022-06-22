package gosk.szymon.solving;

import gosk.szymon.math.Field;
import gosk.szymon.math.algebra.Matrix;
import gosk.szymon.math.algebra.Row;
import gosk.szymon.model.Constraint;
import gosk.szymon.model.Result2D;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SeidelMethod2D<T> implements SolvingStrategy<T> {

    @Override
    public Result2D<T> apply(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, List<Constraint> constraints) {
        return seidel(A, b, c, constraints);
    }

    private Result2D<T> seidel(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c, List<Constraint> constraints) {
        int constrainsSize = A.height();
        int variablesSize = c.height();

        if (variablesSize == 1) {
            return solveForOneVariable(A, b, c);
        }
        if (variablesSize == constrainsSize) {
            return solveWithGauss(A, b);
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(0, constrainsSize);
        while (constraints.get(randomIndex) == Constraint.EQUAL) {
            randomIndex = ThreadLocalRandom.current().nextInt(0, constrainsSize);
        }

        List<Constraint> constraintsPrim = new LinkedList<>(constraints);
        constraintsPrim.remove(randomIndex);
        Result2D<T> x = seidel(A.removeRow(randomIndex), b.removeRow(randomIndex), c, constraintsPrim);

        if (satisfies(x, A.getRow(randomIndex), b.getRow(randomIndex), constraints.get(randomIndex))) {
            return x;
        } else {
            constraints.set(randomIndex, Constraint.EQUAL);
            return seidel(A, b, c, constraints);
        }
    }

    private Result2D<T> solveForOneVariable(@NotNull Matrix<T> A, @NotNull Matrix<T> b, @NotNull Matrix<T> c) {
        Field<T> field = b.getField();

        List<T> bPrim = new ArrayList<>();
        for (int i = 0; i < A.height(); i++) {
            bPrim.add(i, field.divide().apply(b.get(i, 0), A.get(i, 0)));
        }
        List<T> solution = new ArrayList<>();
        for (T value : bPrim) {
            T result = field.zero();
            for (T argument : c.getColumn(0).getValues()) {
                result = field.add().apply(result, field.multiply().apply(value, argument));
            }
            solution.add(result);
        }
        return new Result2D<>(solution.stream().max((o1, o2) -> (field.geq(o1, o2) ? 1 : 0)).stream().toList());
    }

    private boolean satisfies(Result2D<T> x, Row<T> a, Row<T> b, Constraint constraint) {
        Field<T> field = a.getField();
        T result = field.zero();
        for (int i = 0; i < x.size(); i++) {
            result = field.add().apply(result, field.add().apply(x.get(i), a.get(i)));
        }
        return switch (constraint) {
            case GREATER_OR_EQUAL -> field.geq(result, b.get(0));
            case EQUAL -> field.eq(result, b.get(0));
            case LESS_OR_EQUAL -> field.leq(result, b.get(0));
        };
    }

    private Result2D<T> solveWithGauss(@NotNull Matrix<T> A, @NotNull Matrix<T> b) {
        T[][] aPrim = newArray(A.height());
        for(int i = 0; i < A.height(); i++) {
            aPrim[i] = newArray(A.width());
            for(int j = 0; j < A.width(); j++) {
                aPrim[i][j] = A.get(i, j);
            }
        }

        T[] bPrim = newArray(b.height());
        for(int i = 0; i < b.height(); i++) {
            bPrim[i] = b.get(i, 0);
        }

        return new Result2D<>(
                Arrays.asList(
                        GaussianElimination.lsolve(aPrim, bPrim, A.getField())
                )
        );
    }

    @SafeVarargs
    public static <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }

}

package gosk.szymon.math;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class Field<T> {

    public static final Field<Integer> INTEGER_FIELD =
            new Field<>(
                    Integer::sum,
                    (a, b) -> a - b,
                    (a, b) -> a * b,
                    (a, b) -> a / b,
                    0,
                    1
            );

    public static final Field<BigDecimal> BIG_DECIMAL_FIELD =
            new Field<>(
                    BigDecimal::add,
                    BigDecimal::subtract,
                    BigDecimal::multiply,
                    BigDecimal::divide,
                    BigDecimal.ZERO,
                    BigDecimal.ONE
            );

    private final BiFunction<T, T, T> add;
    private final BiFunction<T, T, T> subtract;
    private final BiFunction<T, T, T> multiply;
    private final BiFunction<T, T, T> divide;
    private final T zero;
    private final T one;

    Field(@NotNull BiFunction<T, T, T> add,
          @NotNull BiFunction<T, T, T> subtract,
          @NotNull BiFunction<T, T, T> multiply,
          @NotNull BiFunction<T, T, T> divide,
          @NotNull T zero,
          @NotNull T one) {
        this.add = add;
        this.subtract = subtract;
        this.multiply = multiply;
        this.divide = divide;
        this.zero = zero;
        this.one = one;
    }

    public BiFunction<T, T, T> add() {
        return add;
    }

    public BiFunction<T, T, T> subtract() {
        return subtract;
    }

    public BiFunction<T, T, T> multiply() {
        return multiply;
    }

    public BiFunction<T, T, T> divide() {
        return divide;
    }

    public T zero() {
        return zero;
    }

    public T one() {
        return one;
    }

}
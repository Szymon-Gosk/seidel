package gosk.szymon.math;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public abstract class Field<T> {

    public static final Field<Double> DECIMAL_FIELD =
            new Field<>(
                    Double::sum,
                    (a, b) -> a - b,
                    (a, b) -> a * b,
                    (a, b) -> a / b,
                    0.0,
                    1.0,
                    1e-10
            ) {

                @Override
                public boolean ge(Double x1, Double x2) {
                    return x1 > x2;
                }

                @Override
                public boolean geq(Double x1, Double x2) {
                    return x1 >= x2;
                }

                @Override
                public boolean eq(Double x1, Double x2) {
                    return x1.equals(x2);
                }

                @Override
                public boolean leq(Double x1, Double x2) {
                    return x1 <= x2;
                }

                @Override
                public boolean le(Double x1, Double x2) {
                    return x1 < x2;
                }

                @Override
                public Double abs(Double x) {
                    return Math.abs(x);
                }
            };

    public static final Field<Integer> INTEGER_FIELD =
            new Field<>(
                    Integer::sum,
                    (a, b) -> a - b,
                    (a, b) -> a * b,
                    (a, b) -> a / b,
                    0,
                    1,
                    0
            ) {

                @Override
                public boolean ge(Integer x1, Integer x2) {
                    return x1 > x2;
                }

                @Override
                public boolean geq(Integer x1, Integer x2) {
                    return x1 >= x2;
                }

                @Override
                public boolean eq(Integer x1, Integer x2) {
                    return x1.equals(x2);
                }

                @Override
                public boolean leq(Integer x1, Integer x2) {
                    return x1 <= x2;
                }

                @Override
                public boolean le(Integer x1, Integer x2) {
                    return x1 < x2;
                }

                @Override
                public Integer abs(Integer x) {
                    return Math.abs(x);
                }
            };

    public static final Field<BigDecimal> BIG_DECIMAL_FIELD =
            new Field<>(
                    BigDecimal::add,
                    BigDecimal::subtract,
                    BigDecimal::multiply,
                    BigDecimal::divide,
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    new BigDecimal("1e-10")
            ) {

                @Override
                public boolean ge(BigDecimal x1, BigDecimal x2) {
                    return x1.compareTo(x2) > 0;
                }

                @Override
                public boolean geq(BigDecimal x1, BigDecimal x2) {
                    return x1.compareTo(x2) >= 0;
                }

                @Override
                public boolean eq(BigDecimal x1, BigDecimal x2) {
                    return x1.compareTo(x2) == 0;
                }

                @Override
                public boolean leq(BigDecimal x1, BigDecimal x2) {
                    return x1.compareTo(x2) <= 0;
                }

                @Override
                public boolean le(BigDecimal x1, BigDecimal x2) {
                    return x1.compareTo(x2) < 0;
                }

                @Override
                public BigDecimal abs(BigDecimal x) {
                    return x.abs();
                }
            };

    private final BiFunction<T, T, T> add;
    private final BiFunction<T, T, T> subtract;
    private final BiFunction<T, T, T> multiply;
    private final BiFunction<T, T, T> divide;
    private final T zero;
    private final T one;
    private final T EPSILON;

    Field(@NotNull BiFunction<T, T, T> add,
          @NotNull BiFunction<T, T, T> subtract,
          @NotNull BiFunction<T, T, T> multiply,
          @NotNull BiFunction<T, T, T> divide,
          @NotNull T zero,
          @NotNull T one,
          @NotNull T EPSILON) {
        this.add = add;
        this.subtract = subtract;
        this.multiply = multiply;
        this.divide = divide;
        this.zero = zero;
        this.one = one;
        this.EPSILON = EPSILON;
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

    public T EPSILON() {
        return EPSILON;
    }

    public abstract boolean ge(T x1, T x2);

    public abstract boolean geq(T x1, T x2);

    public abstract boolean eq(T x1, T x2);

    public abstract boolean leq(T x1, T x2);

    public abstract boolean le(T x1, T x2);

    public abstract T abs(T x);

}
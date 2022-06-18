package gosk.szymon.model;

import java.math.BigDecimal;

public abstract class LinearBase {

    protected BigDecimal[][] A;
    protected BigDecimal[] b;
    protected BigDecimal[] c;

    public LinearBase(BigDecimal[][] A, BigDecimal[] b, BigDecimal[] c) {
       try {
           validateFields(A, b, c);
       } catch (IllegalArgumentException e) {
           throw new InvalidProgramException(e.getMessage());
       }
       this.A = A;
       this.b = b;
       this.c = c;
    }

    public abstract BigDecimal[] solve();

    private void validateFields(BigDecimal[][] A, BigDecimal[] b, BigDecimal[] c) {
        if (c.length > 2) {
            throw new IllegalArgumentException("Cannot have two than two variables");
        }
        if(A.length == 0 || A[0].length == 0) {
            throw new IllegalArgumentException("Cannot have zero constraints");
        }
        if(A.length != b.length || A[0].length != c.length) {
            throw new IllegalArgumentException("Program input is invalid");
        }
    }

    public BigDecimal[][] getA() {
        return A;
    }

    protected void setA(BigDecimal[][] a) {
        A = a;
    }

    public BigDecimal[] getB() {
        return b;
    }

    protected void setB(BigDecimal[] b) {
        this.b = b;
    }

    public BigDecimal[] getC() {
        return c;
    }

    protected void setC(BigDecimal[] c) {
        this.c = c;
    }

    private static class InvalidProgramException extends RuntimeException {
        public InvalidProgramException(String m) {
            super(m);
        }
    }

}

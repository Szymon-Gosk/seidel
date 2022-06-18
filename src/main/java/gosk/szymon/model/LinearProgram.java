package gosk.szymon.model;

import java.math.BigDecimal;

public class LinearProgram extends LinearBase implements Printable {

    public LinearProgram(BigDecimal[][] A, BigDecimal[] b, BigDecimal[] c) {
        super(A, b, c);
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder("max:\n  ");
        for(int i = 0 ; i < c.length ; i ++) {
            sb.append(c[i].toPlainString()).append("*[x").append(i).append("]");
            if(i != c.length - 1) {
                sb.append(" + ");
            }
        }

        sb.append("\nfor:\n");
        for(int i = 0 ; i < A.length ; i++) {
            sb.append("  ");
            for(int j = 0 ; j < A[0].length ; j++) {
                sb.append(A[i][j].toPlainString()).append("*[x").append(j).append("]");
                if(j != A[0].length - 1) {
                    sb.append(" + ");
                }
            }
            sb.append(" \u2264 ").append(b[i]).append("\n");
        }

        System.out.println(sb);
    }

    @Override
    public BigDecimal[] solve() {
        return new BigDecimal[0];
    }

}

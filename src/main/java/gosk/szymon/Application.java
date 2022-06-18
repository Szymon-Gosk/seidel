package gosk.szymon;

import gosk.szymon.model.LinearProgram;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("1");
        BigDecimal bd2 = new BigDecimal("2");

        BigDecimal[] M2 = new BigDecimal[]{bd1, bd2};
        BigDecimal[][] M4 = new BigDecimal[][]{{bd1, bd2}, {bd1, bd2}};

        LinearProgram lp = new LinearProgram(M4, M2, M2);
        lp.print();
    }

}

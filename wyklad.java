package org.example;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.IntStream;

public class wyklad {



    public static BigDecimal getSilnia(int rangeEnd) {
        return IntStream.rangeClosed(1, rangeEnd)
                .parallel()
                .mapToObj(BigDecimal::valueOf)
                .reduce(BigDecimal.valueOf(1),(x,y) -> x.multiply(y));
    }

    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();
        BigDecimal x = getSilnia(100000);
        end = System.currentTimeMillis() - start;
        // System.out.println(x);
        System.out.println("Ilosc znakow "+x.toString().length());

        System.out.print("Program działał przez: "+ end);

    }


}













package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class wyklad2 {


    public static BigDecimal getSilnia(int number){
        int cores = Runtime.getRuntime().availableProcessors();
        Map<Integer, List<Integer>> group = IntStream.rangeClosed(1, number)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(x -> x%cores));

        List<BigDecimal> wyniki = new CopyOnWriteArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<cores;i++){
            int j=i;
            Thread thread = new Thread(() -> {
                List<Integer> v = group.get(j);
                BigDecimal wynik= BigDecimal.valueOf(1);
                for (Integer k: v) {

                    wynik = wynik.multiply(BigDecimal.valueOf(k));
                }
                wyniki.add(wynik);
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        BigDecimal wynik= BigDecimal.valueOf(1);
        for (BigDecimal k: wyniki) {
            wynik = wynik.multiply(k);
        }

        return wynik;
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

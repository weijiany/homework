package homework.taxi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Car {

    private Scanner scanner = new Scanner(System.in);

    private void sayWelcome() {
        System.out.println("Welcome to use taxi billing system");
        System.out.println("---------------------------------------------------");
        System.out.println("please input start time, distance per second. for example: yyyy-MM-ddTHH:mm:ss;0.02,0.08");
    }

    public void run() {
        sayWelcome();
        while (true) {
            logic();
        }
    }

    protected void logic() {
        try {
            String[] inputs = scanner.next().split(";");
            if (inputs.length != 2) {
                System.out.println("input not correct");
                return;
            }
            LocalDateTime startAt = LocalDateTime.parse(inputs[0]);
            LinkedList<BigDecimal> distancePerSecond = Arrays.stream(inputs[1].split(",")).map(BigDecimal::new).collect(Collectors.toCollection(LinkedList::new));
            int cost = new Journey(startAt, distancePerSecond).cost();
            System.out.println("cost: " + cost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

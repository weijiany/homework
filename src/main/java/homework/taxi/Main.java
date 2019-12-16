package homework.taxi;

import homework.taxi.exception.DomainException;
import homework.taxi.module.Car;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to use taxi billing system");
        System.out.println("---------------------------------------------------");
        System.out.println("Price strategy:");

        while (true) {
            try {
                System.out.println("please input start time, distance per second. for example: yyyy-MM-ddTHH:mm:ss;0.02,0.08");
                String[] inputs = scanner.next().split(";");
                if (inputs.length != 2) {
                    System.out.println("input not correct");
                    continue;
                }
                LocalDateTime startAt = LocalDateTime.parse(inputs[0]);
                LinkedList<BigDecimal> distancePerSecond = Arrays.stream(inputs[1].split(",")).map(BigDecimal::new).collect(Collectors.toCollection(LinkedList::new));
                int cost = new Car(startAt, distancePerSecond).cost();
                System.out.println("cost: " + cost);
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

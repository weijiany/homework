package homework.taxi;

import homework.taxi.exception.DomainException;
import homework.taxi.exception.StrategyTypeValueNotExistException;
import homework.taxi.module.Car;
import homework.taxi.strategy.StrategyType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to use taxi billing system");
        System.out.println("---------------------------------------------------");
        System.out.println("Price strategy:");
        Arrays.stream(StrategyType.values()).forEach(type -> System.out.println("\t" + type.name() + ": " + type.value));
        Car car = new Car();

        while (true) {
            try {
                System.out.println("please input start time, end time, distance and price strategy. Delimiter is ','");
                String[] inputs = scanner.next().split(",");
                if (inputs.length != 4) {
                    System.out.println("input not correct");
                    continue;
                }
                LocalDateTime startAt = LocalDateTime.parse(inputs[0]);
                LocalDateTime endAt = LocalDateTime.parse(inputs[1]);
                double distance = Double.parseDouble(inputs[2]);
                int typeValue = Integer.parseInt(inputs[3]);
                StrategyType strategyType = StrategyType.fromValue(typeValue);
                int cost = car.run(startAt, endAt, distance, strategyType);
                System.out.println("cost: " + cost);
            } catch (DomainException | StrategyTypeValueNotExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

package homework.taxi.model;

import homework.taxi.exception.KilometerMusBeNonNegativeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Car {

    private Scanner scanner = new Scanner(System.in);

    public void run() throws KilometerMusBeNonNegativeException {
        String[] inputs = scanner.next().split(";");
        if (inputs.length != 2) {
            System.out.println("input not correct");
            return;
        }
        LocalDateTime startAt = LocalDateTime.parse(inputs[0]);
        LinkedList<BigDecimal> distancePerSecond = Arrays.stream(inputs[1].split(",")).map(BigDecimal::new).collect(Collectors.toCollection(LinkedList::new));
        int cost = new Journey(startAt, distancePerSecond).cost();
        System.out.println("cost: " + cost);
    }
}

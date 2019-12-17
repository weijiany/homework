package homework.taxi;

import homework.taxi.model.Car;

public class Main {

    private static Car car = new Car();;

    public static void main(String[] args) {
        sayWelcome();

        while (true) {
            try {
                car.run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void sayWelcome() {
        System.out.println("Welcome to use taxi billing system");
        System.out.println("---------------------------------------------------");
        System.out.println("please input start time, distance per second. for example: yyyy-MM-ddTHH:mm:ss;0.02,0.08");
    }
}

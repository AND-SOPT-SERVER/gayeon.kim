package org.sopt.practice;

public class Main {

    public static void main(String[] args) {
        Person person = new Person(30);
        Driver driver = new Driver(person);

        Vehicle vehicle1 = new Car("GV80", "현대");
        Vehicle vehicle2 = new Cycle();

        System.out.println(vehicle1.run(driver));
        System.out.println(vehicle2.run(driver));

        Car car = new Car("GV80", "현대");
        car.klaxon();
        // vehicle1.klaxon(); -> 불가능
    }
}

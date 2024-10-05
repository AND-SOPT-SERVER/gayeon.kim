package org.sopt.practice;

public class Car implements Vehicle{
    private final String name;
    private final String manufacturer;
    int fuel;

    public Car(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public void klaxon() {
        System.out.println("빵!!!!");
    }

    public String getName() {
        return this.name;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public int getFuel() {
        return this.fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String run(Driver driver) {
        if (driver.canDrive()) {
            return "자동차가 시동 겁니다.";
        }
        return "no";
    }
}

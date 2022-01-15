package com.petrov.metodichka;

class LightWeightCar extends Car {

    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void start() {
        System.out.println("Car is starting");
    }

    @Override
    public void open() {
        System.out.println("Car is open");
    }

    public void stop() {
        System.out.println("Car is stop");
    }
}

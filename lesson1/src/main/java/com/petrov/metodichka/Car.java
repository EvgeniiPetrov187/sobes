package com.petrov.metodichka;

abstract class Car implements Movable, Stopable { //абстрактный класс автомобиль
    public Engine engine;
    private String color;
    private String name;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void start();

    abstract void open();

    @Override
    public void stop() {
    }

    @Override
    public void move() {
    }
}

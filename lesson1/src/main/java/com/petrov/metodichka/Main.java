package com.petrov.metodichka;



public class Main {
    public static void main(String[] args) {
        Car lorry = new Lorry(); // создаем грузовик
        lorry.setEngine(new Engine()); // ставим мотор
        lorry.open(); //садимся
        lorry.start();// старт
        lorry.getEngine().makeSound(); // звук мотора
        lorry.move(); // поехали
        lorry.stop(); // приехали


        Car lightWeightCar = new LightWeightCar();
        lightWeightCar.setEngine(new Engine());
        lightWeightCar.open();
        lightWeightCar.start();
        lightWeightCar.move();
        lightWeightCar.stop();
        lightWeightCar.getEngine().makeSound();
    }
}






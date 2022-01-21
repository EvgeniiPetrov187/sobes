package com.petrov.figures;

public  class Figure {
    public void drawFigure(int point1, int point2, int point3){
        System.out.println("Triangle " + point1 + point2 + point3);
    }

    public void drawFigure(int point1, int point2, int point3, int point4){
        System.out.println("Square "+ point1 + point2 + point3 + point4);
    }

    public void drawFigure(int point1, int point2, int point3, int point4, int point5){
        System.out.println("Pentagon "+ point1 + point2 + point3 + point4 + point5);
    }


}

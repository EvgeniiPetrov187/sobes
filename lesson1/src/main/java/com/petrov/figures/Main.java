package com.petrov.figures;

public class Main extends Figure {


    public static void main(String[] args) {
        Figure figure = new Figure();
        figure.drawFigure(1, 2, 3);
        figure.drawFigure(1, 2, 3, 4);
        figure.drawFigure(1, 2, 3, 4, 5);
    }
}

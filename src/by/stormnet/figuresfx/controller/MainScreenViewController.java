package by.stormnet.figuresfx.controller;

import by.stormnet.figuresfx.CustomException;
import by.stormnet.figuresfx.drawutils.Drawer;
import by.stormnet.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {
    List<Figure> figures = new ArrayList<>();
    private Random random;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random = new Random(System.currentTimeMillis());
        System.out.println("I'm java FX!");
    }


    private void addFigure(Figure figure) {
        figures.add(figure);
    }

    private Figure createFigure(double x, double y) {
        Figure figure = null;

        switch (random.nextInt(5)) {
            case Figure.FIRUTE_TYPE_CIRLCE:
                figure = new Circle(x, y, random.nextInt(3), Color.GREEN, random.nextInt(50));
                break;
            case Figure.FIRUTE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(4), Color.RED, random.nextInt(100), random.nextInt(100));
                break;
            case Figure.FIRUTE_TYPE_TRIANGLE:
                figure = new Triangle(x, y, random.nextInt(4), Color.BLUE, random.nextInt(100));
                break;
            case Figure.FIRUTE_TYPE_HEXAGON:
                figure = new Hexagon(x, y, random.nextInt(4), Color.GREEN, random.nextInt(100));
                break;
            default:
                try {
                    figure.generateCustomExeption();
                } catch (CustomException e) {
                    e.printStackTrace();
                }
        }
        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        Drawer<Figure> drawer = new Drawer<>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }


    @FXML
    private void onClicked(MouseEvent mouseEvent) {
        addFigure(createFigure(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }
}

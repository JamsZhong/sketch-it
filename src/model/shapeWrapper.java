package model;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class shapeWrapper {

    Rectangle boundingBox;
    Boolean isSelected = false;
    double startX;
    double startY;
    double mouseSelectX;
    double mouseSelectY;
    Color lineColour;
    Color fillColour;

    abstract public void draw(MouseEvent e);
    abstract public void select(MouseEvent e);
    abstract public void deselect();
    abstract public void move(MouseEvent e);
    abstract public Boolean contains(MouseEvent e);
    abstract public void updatePos();
    abstract public void updateProperties();
    abstract public Object getShape();
    abstract public Object getBox();
    abstract public void updateFill();
}

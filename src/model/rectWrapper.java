package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.awt.*;

public class rectWrapper extends shapeWrapper {
    Rectangle rect;

    public rectWrapper(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        rect = new Rectangle(startX, startY, 0, 0);

        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        rect.setFill(fillColour);
        rect.setStroke(lineColour);
        rect.setStrokeWidth(shapeProperties.lineThickness);
        rect.setStyle(shapeProperties.currLineStyle);
        rect.setStrokeType(StrokeType.INSIDE);

        boundingBox = new Rectangle(e.getX(), e.getX(), 0, 0);
        boundingBox.setFill(Color.TRANSPARENT);
        boundingBox.setStroke(Color.DARKBLUE);
        boundingBox.setStrokeWidth(1);
        boundingBox.setStrokeType(StrokeType.OUTSIDE);
        boundingBox.getStrokeDashArray().addAll(5d);
        boundingBox.setVisible(isSelected);
    }

    @Override
    public void draw(MouseEvent e) {
        rect.setX(Math.min(e.getX(), startX));
        rect.setY(Math.min(e.getY(), startY));
        rect.setWidth(Math.abs(e.getX() - startX));
        rect.setHeight(Math.abs(e.getY() - startY));

        boundingBox.setX(rect.getX());
        boundingBox.setY(rect.getY());
        boundingBox.setHeight(rect.getHeight());
        boundingBox.setWidth(rect.getWidth());
    }

    @Override
    public void select(MouseEvent e) {
        isSelected = true;

        //Change global shape properties to selected shape
        shapeProperties.fillColour.setValue(fillColour);
        shapeProperties.lineColour.setValue(lineColour);

        //Show the bounding box
        boundingBox.setVisible(isSelected);

        //Store selection point for moving
        mouseSelectX = e.getX();
        mouseSelectY = e.getY();
    }

    @Override
    public void deselect() {
        isSelected = false;

        //Hide the bounding box
        boundingBox.setVisible(isSelected);
    }

    @Override
    public void move(MouseEvent e) {
        double xShift = mouseSelectX - startX;
        double yShift = mouseSelectY - startY;

        rect.setX(e.getX() - xShift);
        rect.setY(e.getY() - yShift);

        boundingBox.setX(e.getX() - xShift);
        boundingBox.setY(e.getY() - yShift);
    }

    @Override
    public Boolean contains(MouseEvent e) {
        return rect.contains(e.getX(), e.getY());
    }

    @Override
    public void updatePos() {
        startX = rect.getX();
        startY = rect.getY();
    }

    @Override
    public void updateProperties() {
        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        rect.setStrokeWidth(shapeProperties.lineThickness);
        rect.setStyle(shapeProperties.currLineStyle);

        rect.setFill(fillColour);
        rect.setStroke(lineColour);
    }

    @Override
    public Object getShape() {
        return rect;
    }

    @Override
    public Object getBox() {
        return boundingBox;
    }

    @Override
    public void updateFill() {
        fillColour = shapeProperties.fillColour.getValue();
        rect.setFill(fillColour);
    }
}

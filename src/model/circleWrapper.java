package model;

import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class circleWrapper extends shapeWrapper {
    Circle circle;

    public circleWrapper(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        circle = new Circle(startX, startY, 0);

        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        circle.setFill(fillColour);
        circle.setStroke(lineColour);
        circle.setStrokeWidth(shapeProperties.lineThickness);
        circle.setStyle(shapeProperties.currLineStyle);
        circle.setStrokeType(StrokeType.INSIDE);

        boundingBox = new Rectangle(e.getX(), e.getX(), 0, 0);
        boundingBox.setFill(Color.TRANSPARENT);
        boundingBox.setStroke(Color.DARKBLUE);
        boundingBox.setStrokeWidth(0.5);
        boundingBox.setStrokeType(StrokeType.OUTSIDE);
        boundingBox.getStrokeDashArray().addAll(5d);
        boundingBox.setVisible(isSelected);
    }

    @Override
    public void draw(MouseEvent e) {
        circle.setRadius(Math.max(Math.abs(e.getX() - startX), Math.abs(e.getY() - startY)));
        boundingBox.setX(circle.getCenterX() - circle.getRadius());
        boundingBox.setY(circle.getCenterY() - circle.getRadius());
        boundingBox.setHeight(2 * circle.getRadius());
        boundingBox.setWidth(2 * circle.getRadius());
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

        circle.setCenterX(e.getX() - xShift);
        circle.setCenterY(e.getY() - yShift);

        boundingBox.setX(e.getX() - circle.getRadius() - xShift);
        boundingBox.setY(e.getY() - circle.getRadius() - yShift);
    }

    @Override
    public Boolean contains(MouseEvent e) {
        return circle.contains(e.getX(), e.getY());
    }

    @Override
    public void updatePos() {
        startX = circle.getCenterX();
        startY = circle.getCenterY();
    }

    @Override
    public void updateProperties() {
        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        circle.setStrokeWidth(shapeProperties.lineThickness);
        circle.setStyle(shapeProperties.currLineStyle);

        circle.setFill(fillColour);
        circle.setStroke(lineColour);
    }

    @Override
    public Object getShape() {
        return circle;
    }

    @Override
    public Object getBox() {
        return boundingBox;
    }

    @Override
    public void updateFill() {
        fillColour = shapeProperties.fillColour.getValue();
        circle.setFill(fillColour);
    }
}

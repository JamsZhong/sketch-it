package model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class lineWrapper extends shapeWrapper {
    Line line;
    double lineX;
    double lineY;

    public lineWrapper(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        line = new Line(startX, startY, startX, startY);

        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        line.setStroke(lineColour);
        line.setStrokeWidth(shapeProperties.lineThickness);
        line.setStyle(shapeProperties.currLineStyle);

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
        line.setEndX(e.getX());
        line.setEndY(e.getY());
        boundingBox.setX(Math.min(line.getEndX(), line.getStartX()));
        boundingBox.setY(Math.min(line.getEndY(), line.getStartY()));
        boundingBox.setHeight(Math.abs(line.getEndY() - line.getStartY()));
        boundingBox.setWidth(Math.abs(line.getEndX() - line.getStartX()));

        lineX = line.getEndX() - line.getStartX();
        lineY = line.getEndY() - line.getStartY();
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

        line.setStartX(e.getX() - xShift);
        line.setStartY(e.getY() - yShift);
        line.setEndX(e.getX() - xShift + lineX);
        line.setEndY(e.getY() - yShift + lineY);

        boundingBox.setX(Math.min(e.getX() - xShift, e.getX() - xShift + lineX));
        boundingBox.setY(Math.min(e.getY() - yShift, e.getY() - yShift + lineY));
    }

    @Override
    public Boolean contains(MouseEvent e) {
        return line.contains(e.getX(), e.getY());
    }

    @Override
    public void updatePos() {
        startX = line.getStartX();
        startY = line.getStartY();
    }

    @Override
    public void updateProperties() {
        fillColour = shapeProperties.fillColour.getValue();
        lineColour = shapeProperties.lineColour.getValue();

        line.setStrokeWidth(shapeProperties.lineThickness);
        line.setStyle(shapeProperties.currLineStyle);

        line.setStroke(lineColour);
    }

    @Override
    public Object getShape() {
        return line;
    }

    @Override
    public Object getBox() {
        return boundingBox;
    }

    @Override
    public void updateFill() {
        fillColour = shapeProperties.fillColour.getValue();
    }
}


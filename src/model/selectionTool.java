package model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class selectionTool {
    Group g;
    shapeWrapper currSelection;

    public void deselect() {
        if(currSelection != null) {
            currSelection.deselect();
            currSelection = null;
        }
    }

    public void select(shapeWrapper shape, MouseEvent e) {
        currSelection = shape;
        shape.select(e);
    }

    public void move(MouseEvent e) {
        if(currSelection != null) {
            currSelection.move(e);
        }
    }

    public void updatePos() {
        if(currSelection != null) {
            currSelection.updatePos();
        }
    }

    public void updateProperties() {
        if(currSelection != null) {
            currSelection.updateProperties();
        }
    }

    public void erase(ArrayList<shapeWrapper> shapeObjects, Group shapes, Group boundingBoxes) {
        if(currSelection != null) {
            shapes.getChildren().remove(currSelection.getShape());
            boundingBoxes.getChildren().remove(currSelection.getBox());
            shapeObjects.remove(currSelection);
            currSelection = null;
        }
    }
}

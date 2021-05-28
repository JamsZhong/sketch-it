package model;

import javafx.scene.Group;
import java.util.ArrayList;

public class eraseTool {
    public void erase(shapeWrapper shape, ArrayList<shapeWrapper> shapeObjects, Group shapes, Group boundingBoxes) {
        shapes.getChildren().remove(shape.getShape());
        boundingBoxes.getChildren().remove(shape.getBox());
        shapeObjects.remove(shape);
    }
}

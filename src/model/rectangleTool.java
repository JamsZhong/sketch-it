package model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class rectangleTool {
    public rectWrapper startRect(MouseEvent e, Group shapes, Group boundingBoxes) {
        rectWrapper temp = new rectWrapper(e);
        shapes.getChildren().add(temp.rect);
        boundingBoxes.getChildren().add(temp.boundingBox);

        return temp;
    }

    public void draw(rectWrapper c, MouseEvent e) {
        c.draw(e);
    }
}
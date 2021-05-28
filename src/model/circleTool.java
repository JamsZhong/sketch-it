package model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class circleTool {
    public circleWrapper startCircle(MouseEvent e, Group shapes, Group boundingBoxes) {
        circleWrapper temp = new circleWrapper(e);
        shapes.getChildren().add(temp.circle);
        boundingBoxes.getChildren().add(temp.boundingBox);

        return temp;
    }

    public void draw(circleWrapper c, MouseEvent e) {
        c.draw(e);
    }
}
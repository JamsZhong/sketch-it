package model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class lineTool {
    public lineWrapper startLine(MouseEvent e, Group shapes, Group boundingBoxes) {
        lineWrapper temp = new lineWrapper(e);
        shapes.getChildren().add(temp.line);
        boundingBoxes.getChildren().add(temp.boundingBox);

        return temp;
    }

    public void draw(lineWrapper c, MouseEvent e) {
        c.draw(e);
    }
}
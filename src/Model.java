import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Model {
    public class menuBar {
        void newDrawing() {

        }

        void load() {

        }

        void save() {

        }

        void quit() {

        }

    }

    public class selectionTool {

    }

    public class eraseTool {

    }

    /*public class lineTool {
        Canvas canvas;
        GraphicsContext gc;
        ColorPicker lineColour;
        double startX;
        double startY;
        double endX;
        double endY;

        lineTool(Canvas c, GraphicsContext gc, ColorPicker lc, ColorPicker fc) {
            canvas = c;
            this.gc = gc;
            lineColour = lc;
        }

        void setLineColour() {
            gc.setStroke(lineColour.getValue());
        }

        Circle getLine() {
            return circle;
        }

        void draw(double startX, double endX, double startY, double endY) {
            circle.setCenterX(startX);
            circle.setCenterY(startY);
            circle.setRadius(Math.max(endX - startX, endY - startY));
        }
    }*/

    public class rectangleTool {
        Canvas canvas;
        GraphicsContext gc;
        ColorPicker lineColour;
        ColorPicker fillColour;
        Rectangle rect;

        rectangleTool(Canvas c, GraphicsContext gc, ColorPicker lc, ColorPicker fc) {
            canvas = c;
            this.gc = gc;
            lineColour = lc;
            fillColour = fc;
        }

        void setLineColour() {
            gc.setStroke(lineColour.getValue());
        }

        void setFillColour() {
            gc.setFill(fillColour.getValue());
        }

        Rectangle getRectangle() {
            return rect;
        }

        void draw(double startX, double endX, double startY, double endY) {
            rect.setX(startX);
            rect.setY(startY);
            rect.setWidth(Math.abs(endX - startX));
            rect.setHeight(Math.abs(endY - startY));
        }
    }

    public class fillTool {

    }

    public class lineColour {

    }

    public class fillColour {

    }

    public class lineThickness {

    }

    public class lineStyle {

    }
}

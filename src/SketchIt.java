import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.*;

import javax.tools.Tool;
import java.util.ArrayList;

public class SketchIt extends Application {

    @Override
    public void start(Stage stage) {
        Group shapes = new Group();
        Group boundingBoxes = new Group();

        Group root = new Group();
        Scene s = new Scene(root, 800, 500);

        VBox toolbar = ToolbarView.Toolbar();

        final Canvas c = new Canvas(800, 500);
        /*
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);*/

        //Tool objects
        circleTool ct = new circleTool();
        selectionTool st = new selectionTool();
        eraseTool et = new eraseTool();
        rectangleTool rt = new rectangleTool();
        lineTool lt = new lineTool();
        fillTool ft = new fillTool();

        ArrayList<shapeWrapper> shapeObjects = new ArrayList<>();

        s.setOnMousePressed(e -> {
            if(toolProperties.circleButton.isSelected()) {
                st.deselect();
                if (!toolbar.contains(e.getX(), e.getY())) {
                    shapeObjects.add(ct.startCircle(e, shapes, boundingBoxes));
                }
            } else if(toolProperties.selectionButton.isSelected()) {
                Boolean intersectsNone = true;

                for(shapeWrapper shape : shapeObjects) {
                    if(shape.contains(e)) {
                        intersectsNone = false;
                        st.deselect();
                        st.select(shape, e);
                        break;
                    }
                }

                if(intersectsNone) {
                    st.deselect();
                }
            } else if(toolProperties.eraseButton.isSelected()) {
                for(shapeWrapper shape : shapeObjects) {
                    if(shape.contains(e)) {
                        et.erase(shape, shapeObjects, shapes, boundingBoxes);
                        break;
                    }
                }
            } else if(toolProperties.rectButton.isSelected()) {
                st.deselect();
                if (!toolbar.contains(e.getX(), e.getY())) {
                    shapeObjects.add(rt.startRect(e, shapes, boundingBoxes));
                }
            } else if(toolProperties.lineButton.isSelected()) {
                st.deselect();
                if (!toolbar.contains(e.getX(), e.getY())) {
                    shapeObjects.add(lt.startLine(e, shapes, boundingBoxes));
                }
            } else if(toolProperties.fillButton.isSelected()) {
                for(shapeWrapper shape : shapeObjects) {
                    if(shape.contains(e)) {
                        ft.fill(shape);
                        break;
                    }
                }
            }
        });

        s.setOnMouseDragged(e -> {
            if(toolProperties.circleButton.isSelected()) {
                if (!toolbar.intersects(e.getX(), e.getY(), e.getSceneX(), e.getSceneY())) {
                    ct.draw((circleWrapper) shapeObjects.get(shapeObjects.size() - 1), e);
                }
            } else if(toolProperties.selectionButton.isSelected()) {
                st.move(e);
            } else if(toolProperties.rectButton.isSelected()) {
                if (!toolbar.intersects(e.getX(), e.getY(), e.getSceneX(), e.getSceneY())) {
                    rt.draw((rectWrapper) shapeObjects.get(shapeObjects.size() - 1), e);
                }
            } else if(toolProperties.lineButton.isSelected()) {
                if (!toolbar.intersects(e.getX(), e.getY(), e.getSceneX(), e.getSceneY())) {
                    lt.draw((lineWrapper) shapeObjects.get(shapeObjects.size() - 1), e);
                }
            }
        });

        s.setOnMouseReleased(e -> {
            if(toolProperties.rectButton.isSelected() ||
                    toolProperties.lineButton.isSelected() ||
                    toolProperties.circleButton.isSelected()) {
                    st.select(shapeObjects.get(shapeObjects.size() - 1), e);
            }
            st.updatePos();
        });

        s.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE) {
                st.deselect();
            } else if(e.getCode() == KeyCode.DELETE) {
                st.erase(shapeObjects, shapes, boundingBoxes);
            }
        });

        shapeProperties.fillColour.setOnAction( actionEvent -> {
            st.updateProperties();
        });

        shapeProperties.lineColour.setOnAction( actionEvent -> {
            st.updateProperties();
        });

        toolProperties.thickness1.setOnAction( actionEvent -> {
            shapeProperties.lineThickness = 1;
            st.updateProperties();
        });

        toolProperties.thickness2.setOnAction( actionEvent -> {
            shapeProperties.lineThickness = 2;
            st.updateProperties();
        });

        toolProperties.thickness3.setOnAction( actionEvent -> {
            shapeProperties.lineThickness = 3;
            st.updateProperties();
        });

        toolProperties.thickness4.setOnAction( actionEvent -> {
            shapeProperties.lineThickness = 4;
            st.updateProperties();
        });

        toolProperties.styleSolid.setOnAction( actionEvent -> {
            shapeProperties.currLineStyle = shapeProperties.solid;
            st.updateProperties();
        });

        toolProperties.styleDashed.setOnAction( actionEvent -> {
            shapeProperties.currLineStyle = shapeProperties.dashed;
            st.updateProperties();
        });

        toolProperties.styleDotted.setOnAction( actionEvent -> {
            shapeProperties.currLineStyle = shapeProperties.dotted;
            st.updateProperties();
        });

        root.getChildren().addAll(c, toolbar, boundingBoxes, shapes);

        stage.setScene(s);
        stage.show();
    }
}

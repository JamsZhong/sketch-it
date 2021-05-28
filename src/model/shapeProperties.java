package model;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class shapeProperties {
    public static ColorPicker fillColour = new ColorPicker(Color.BLACK);
    public static ColorPicker lineColour = new ColorPicker(Color.BLACK);
    public static int lineThickness = 1;
    public static final String solid = "-fx-stroke-dash-array: 100000000;";
    public static final String dashed = "-fx-stroke-dash-array: 25 10;";
    public static final String dotted = "-fx-stroke-dash-array: 5 5;";
    public static String currLineStyle = solid;
}

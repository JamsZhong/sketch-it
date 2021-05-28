package model;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class toolProperties {
    //Tools
    public static ToggleGroup toolSelection = new ToggleGroup();
    public static ToggleButton selectionButton = new ToggleButton("Selection");
    public static ToggleButton circleButton = new ToggleButton("Circle");
    public static ToggleButton eraseButton = new ToggleButton("Erase");
    public static ToggleButton rectButton = new ToggleButton("Rectangle");
    public static ToggleButton lineButton = new ToggleButton("Line");
    public static ToggleButton fillButton = new ToggleButton("Fill");


    //Line thickness buttons
    public static ToggleGroup lineThicknessButtons = new ToggleGroup();
    public static ToggleButton thickness1 = new ToggleButton("1");
    public static ToggleButton thickness2 = new ToggleButton("2");
    public static ToggleButton thickness3 = new ToggleButton("3");
    public static ToggleButton thickness4 = new ToggleButton("4");

    //Line style buttons
    public static ToggleGroup lineStyleButtons = new ToggleGroup();
    public static ToggleButton styleSolid = new ToggleButton("1");
    public static ToggleButton styleDashed = new ToggleButton("2");
    public static ToggleButton styleDotted = new ToggleButton("3");
}

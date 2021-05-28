import model.*;

import javafx.scene.Cursor;
import javafx.scene.control.*;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ToolbarView {
    public static VBox Toolbar() {
        //
        // All tools
        //
        VBox toolbar = new VBox();

        // Tool Options
        //
        GridPane toolOptions = new GridPane();

        ArrayList<ToggleButton> toolArray = new ArrayList<>();
        toolArray.add(toolProperties.circleButton);
        toolArray.add(toolProperties.selectionButton);
        toolArray.add(toolProperties.eraseButton);
        toolArray.add(toolProperties.rectButton);
        toolArray.add(toolProperties.lineButton);
        toolArray.add(toolProperties.fillButton);

        for(ToggleButton tool : toolArray) {
            tool.setToggleGroup(toolProperties.toolSelection);
            tool.setCursor(Cursor.HAND);
        }

        //Add to tool options
        toolOptions.add(toolProperties.selectionButton, 0, 0);
        toolOptions.add(toolProperties.eraseButton, 1, 0);
        toolOptions.add(toolProperties.circleButton, 0, 1);
        toolOptions.add(toolProperties.rectButton, 1, 1);
        toolOptions.add(toolProperties.lineButton, 0, 2);
        toolOptions.add(toolProperties.fillButton, 1, 2);

        // Selection Options
        //
        VBox selectionOptions = new VBox();

        //Colour Pickers
        VBox colourPickers = new VBox();
        Label lineColourLabel = new Label("Line");
        Label fillColourLabel = new Label("Fill");
        colourPickers.getChildren().addAll(lineColourLabel, shapeProperties.lineColour,
                fillColourLabel, shapeProperties.fillColour);

        //Line Thickness
        Label lineThicknessLabel = new Label("Line Thickness");
        HBox lineThicknessPalette = new HBox();

        toolProperties.thickness1.setToggleGroup(toolProperties.lineThicknessButtons);
        toolProperties.thickness2.setToggleGroup(toolProperties.lineThicknessButtons);
        toolProperties.thickness3.setToggleGroup(toolProperties.lineThicknessButtons);
        toolProperties.thickness4.setToggleGroup(toolProperties.lineThicknessButtons);

        lineThicknessPalette.getChildren().addAll(toolProperties.thickness1,
                toolProperties.thickness2,
                toolProperties.thickness3,
                toolProperties.thickness4);

        //Line Style
        Label lineStyleLabel = new Label("Line Style");
        HBox lineStylePalette = new HBox();



        toolProperties.styleSolid.setToggleGroup(toolProperties.lineThicknessButtons);
        toolProperties.styleDashed.setToggleGroup(toolProperties.lineThicknessButtons);
        toolProperties.styleDotted.setToggleGroup(toolProperties.lineThicknessButtons);

        lineStylePalette.getChildren().addAll(toolProperties.styleSolid,
                toolProperties.styleDashed,
                toolProperties.styleDotted);

        //Add to selection options
        selectionOptions.getChildren().addAll(colourPickers,
                lineThicknessLabel, lineThicknessPalette,
                lineStyleLabel, lineStylePalette);

        // Add to toolbar
        //
        toolbar.getChildren().addAll(toolOptions, selectionOptions);
        toolbar.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

        return toolbar;
    }
}

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by Eddy on 01.06.2017.
 * Tiny class for popUps and other jank
 */
public class UnlockedFeatures
{
    private static final HashMap<String, String> allFeatures = getAllFeatures();
    private static  HashMap<String, String> getAllFeatures()
    {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("Debug Tool", "The Debug Tool!\nUseful for Debugging... well, bugs");
        hm.put("Analyzer", "The Analyzer lets you refactor spaghetti-code\nFeel \"His n00dly appendage\"");
        hm.put("Extra Ram", "More RAM!\nNow you can render even more of your surroundings");
        hm.put("PenTest", "The Penetration tester\nPenetrates more than Fire Walls...");
        return hm;
    }

    private static Stage window = new Stage();
    private static Button button = new Button("Stop bugging me...");
    private static Label label = new Label();
    private static GridPane layout = new GridPane();
    private static Scene scene = new Scene(layout);


    static void newFeaturePopUp(Tile t)
    {

        button.setOnAction(e -> window.close());
        label.setText(allFeatures.get(t.getFeatureType()));
        label.setFont(new Font("Calibri", 20));
        label.setTextFill(Color.BLUE);
        ImageView iv = new ImageView(t.getImage());

        layout.getChildren().clear();
        layout.add(label,0,0);
        layout.add(iv, 0,1);
        layout.add(button, 0, 2);

        window.setScene(scene);
        window.setTitle("Feature Unlocked!");
        window.show();
    }

    static void noPenTesters()
    {
        button.setOnAction(e -> window.close());

        label.setText("You can't penetrate this wall\nYou need a Penetration Testing Script!");
        label.setFont(new Font("Calibri", 20));
        label.setTextFill(Color.BLUE);
        ImageView iv = new ImageView(Entity.penTestImage);
        ImageView ivw = new ImageView(Entity.badWallImage);

        layout.getChildren().clear();
        layout.add(label, 0, 0);
        layout.add(iv, 0, 1);
        layout.add(ivw, 1, 1);
        layout.add(button, 0, 2);

        window.setScene(scene);
        window.setTitle("Missing Feature!");
        window.show();
    }

    static void noAnalyzers()
    {
        button.setOnAction(e -> window.close());

        label.setText("You can't refactor this wall\nYou need an Analyzer!");
        label.setFont(new Font("Calibri", 20));
        label.setTextFill(Color.BLUE);
        ImageView iv = new ImageView(Entity.analyzerImage);
        ImageView ivw = new ImageView(Entity.spaghettiWallImage);

        layout.getChildren().clear();
        layout.add(label, 0, 0);
        layout.add(iv, 0, 1);
        layout.add(ivw, 1, 1);
        layout.add(button, 0, 2);

        window.setScene(scene);
        window.setTitle("Missing Feature!");
        window.show();
    }
}


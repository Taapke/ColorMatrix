package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage)
    {

        // set title for the stage
        stage.setTitle("creating canvas");

        int canvasWidth = 512;
        int canvasHeight = 512;
        int numBlocksWidth = 4;
        int numBlocksHeight = 4;

        // create a canvas
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        // graphics context
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();


        float[][][] colorMatrix = new float[numBlocksWidth][numBlocksHeight][3];

        int blockWidth = canvasWidth/numBlocksWidth;
        int blockHeight = canvasHeight/numBlocksHeight;
        int upperLeftX = 0;
        int upperLeftY = canvasHeight;

        for (int i = 0; i < numBlocksWidth; i++) {
            upperLeftY = canvasHeight;
            for (int j = 0; j < numBlocksHeight; j++) {

                // generate random rgb value
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();

                upperLeftY = upperLeftY - blockWidth;
                colorMatrix[i][j][0] = r;
                colorMatrix[i][j][1] = g;
                colorMatrix[i][j][2] = b;

                // set fill for rectangle
                graphics_context.setFill(Color.color(r,g,b));
                graphics_context.fillRect(upperLeftX, upperLeftY, blockWidth, blockHeight);
            }
            upperLeftX = upperLeftX + blockWidth;
        }

        float[][] newColumn = new float[colorMatrix.length * 2][3];

        for (int i = 0; i < colorMatrix.length * 2; i++) {
            //TODO verder met matrix opdelen

        }


        // create a Group
        Group group = new Group(canvas);

        // create a scene
        Scene scene = new Scene(group, 512, 512);

        // set the scene
        stage.setScene(scene);

        stage.show();
    }



    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
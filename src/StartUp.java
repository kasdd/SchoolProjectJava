
import gui.InlogPaneelController;
import domein.DomeinController;
import domein.InlogController;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        
        InlogController ic = new InlogController();
        Scene scene = new Scene(new InlogPaneelController(ic)); 
        scene.getStylesheets().add("style/myStyle.css");
        primaryStage.setTitle("Didactische leermiddelen");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
         Application.launch(StartUp.class, args);
    }
}

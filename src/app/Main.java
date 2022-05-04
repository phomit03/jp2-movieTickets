package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage rootStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            rootStage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("../view-list/HomeMenu.fxml"));
            primaryStage.setTitle("Quản lí rạp chiếu phim");
            primaryStage.setScene(new Scene(root, 1200, 650));
            primaryStage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

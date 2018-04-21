package com.icytown.calculator;

import java.util.logging.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * 主类, 包含main静态方法和JavaFX界面生成
 */
public class Main extends Application {
    // 常量, 声明了窗体初始化大小
    private static final int INIT_WIDTH = 480;
    private static final int INIT_HEIGHT = 280;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 加载FXML样式文件
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Calculator.java.fxml"));
            loader.setController(new Calculator());
            GridPane root = (GridPane) loader.load();
            Scene scene = new Scene(root, INIT_WIDTH, INIT_HEIGHT);
            stage.setTitle("Syl Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Logger.getLogger("Calculator").info("Cannot load fxml");
        }
    }
}

package com.icytown.calculator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * 该项目的根节点GridPane的Controller
 */
public class Calculator implements Initializable {
    @FXML private TextField inputA;
    @FXML private TextField inputB;

    @FXML private Label labelOpr;
    @FXML private Label labelResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Do nothing
    }

    /**
     * 创建一个Alert, 用于发送消息给用户
     */
    private void messageBox(String msg) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Message");
        information.setHeaderText(msg);
        information.showAndWait();
    }

    /**
     * 修改显示计算运算符的Label的Text
     *
     * 绑定@FXML Button buttonAdd, buttonSub, buttonMul, buttonDiv的OnAction
     */
    public void onClickOpr(ActionEvent event) {
        Button btn = (Button) event.getSource();
        labelOpr.setText(btn.getText());
    }

    /**
     * 计算最终结果
     *
     * 绑定@FXML Button buttonOk
     */
    public void onClickResult(ActionEvent event) {
        double a;
        double b;
        try {
            a = Double.parseDouble(inputA.getText());
            b = Double.parseDouble(inputB.getText());
        } catch (Exception e) {
            messageBox("Illegal Number");
            return;
        }
        switch (labelOpr.getText()) {
            case "":
                messageBox("Please input operator");
                break;
            case "+":
                labelResult.setText(Converter.convertDoubleToString(a + b));
                break;
            case "-":
                labelResult.setText(Converter.convertDoubleToString(a - b));
                break;
            case "*":
                labelResult.setText(Converter.convertDoubleToString(a * b));
                break;
            case "/":
                if (b == 0) {
                    messageBox("Cannot divided by 0");
                    return;
                }
                labelResult.setText(Converter.convertDoubleToString(a / b));
                break;
            default:
                messageBox("Wrong operator");
        }
    }
}
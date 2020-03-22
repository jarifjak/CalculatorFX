/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;


/**
 *
 * @author zzz
 */
public class CalculatorFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Parent root = loader.load();
        uiController c = loader.getController();
        
        Scene scene = new Scene(root);
        
        
        stage.setTitle("CalculatorFX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        
        scene.setOnKeyPressed((final KeyEvent keyevent) -> {
            
            switch (keyevent.getCode()) {
                
                case ENTER:
                    c.equalBTN.fire();
                    break;
                case ADD:
                    c.addBTN.fire();
                    break;
                case SUBTRACT:
                    c.subBTN.fire();
                    break;
                case DIVIDE:
                    c.divBTN.fire();
                    break;
                case MULTIPLY:
                    c.mulBTN.fire();
                    break;      
                case DIGIT0:
                    c.zeroBTN.fire();
                    break;
                case DIGIT1:
                    c.oneBTN.fire();
                    break;
                case DIGIT2:
                    c.twoBTN.fire();
                    break;
                case DIGIT3:
                    c.threeBTN.fire();
                    break;
                case DIGIT4:
                    c.fourBTN.fire();
                    break;
                case DIGIT5:
                    c.fiveBTN.fire();
                    break;
                case DIGIT6:
                    c.sixBTN.fire();
                    break;
                case DIGIT7:
                    c.sevenBTN.fire();
                    break;
                case DIGIT8:
                    c.eightBTN.fire();
                    break;
                case DIGIT9:
                    c.nineBTN.fire();
                    break;
                case NUMPAD0:
                    c.zeroBTN.fire();
                    break;
                case NUMPAD1:
                    c.oneBTN.fire();
                    break;
                case NUMPAD2:
                    c.twoBTN.fire();
                    break;
                case NUMPAD3:
                    c.threeBTN.fire();
                    break;
                case NUMPAD4:
                    c.fourBTN.fire();
                    break;
                case NUMPAD5:
                    c.fiveBTN.fire();
                    break;
                case NUMPAD6:
                    c.sixBTN.fire();
                    break;
                case NUMPAD7:
                    c.sevenBTN.fire();
                    break;
                case NUMPAD8:
                    c.eightBTN.fire();
                    break;
                case NUMPAD9:
                    c.nineBTN.fire();
                    break;
                
                case PERIOD:
                    c.dotBTN.fire();
                    break;
                case DECIMAL:
                    c.dotBTN.fire();
                    break;    
                case BACK_SPACE:
                    c.cBTN.fire();
                    break;
                case DELETE:
                    c.cBTN.fire();
                    break;
                case M:
                    c.mBTN.fire();
                    break;
                case A:
                    c.ansBTN.fire();
                    break;
                

            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

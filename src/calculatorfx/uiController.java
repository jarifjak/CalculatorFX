/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorfx;

import java.net.URL;
//import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.text.DecimalFormat;

/**
 *
 * @author zzz
 */
public class uiController implements Initializable {
    
    @FXML
    private Label output;
  
    public Button divBTN = new Button();
    public Button subBTN = new Button();
    public Button mulBTN = new Button();
    public Button addBTN = new Button();
    public Button equalBTN = new Button();
    public Button cBTN = new Button();
    public Button dotBTN = new Button();
    public Button mBTN = new Button();
    public Button ansBTN = new Button();
    public Button zeroBTN = new Button();
    public Button oneBTN = new Button();
    public Button twoBTN = new Button();
    public Button threeBTN = new Button();
    public Button fourBTN = new Button();
    public Button fiveBTN = new Button();
    public Button sixBTN = new Button();
    public Button sevenBTN = new Button();
    public Button eightBTN = new Button();
    public Button nineBTN = new Button();
    public Button c2BTN = new Button();

    
    private Model model = new Model();
    private String op = "";
    private double number;
    private boolean start = true;
    private int doubleOpCheck = -100;
    private boolean doubleDotCheck = false;
    
    /**new model**/
    
    private ArrayList<String> opList = new ArrayList<String>();       //store operator input in list
    private ArrayList<String> valList = new ArrayList<String>();            //store value input in list
    private ArrayList<Integer> parList = new ArrayList<Integer>(); //track to maintain operator
    private int divUsed = 0;                                                //precendence for equation solve
    private int mulUsed = 0;                                              
    private int addUsed = 0;
    private int subUsed = 0;
    private int parUsed = 0;
    
    double valueOne = 0.0;
    double valueTwo = 0.0;
    String currentValue = "";   //saves the value from the input buffer 
    
    @FXML
    private Label liveResult;
    
    
    
    private String calEquation(ArrayList<String> copyOpList, ArrayList<String> copyValList,
                                int d, int m, int a, int s, int p) {
        
        int index = 0;
        double demoResult = 0.0;
        String strResult = "";
        
        
        while(p > 0) {
            
            ArrayList<String> subOp = new ArrayList<String>(copyOpList.subList(parList.get(index), parList.get(index+1)));
            ArrayList<String> subVal = new ArrayList(copyValList.subList(parList.get(index), parList.get(index+1) + 1));
            strResult = calEquation(subOp, subVal, d, m, a, s, 0);
            copyOpList.subList(parList.get(index), parList.get(index+1)).clear();
            copyValList.subList(parList.get(index), parList.get(index+1) +1 ).clear();
            copyValList.add(parList.get(index), strResult);
            p--;
            index =+ 2;
        }
        
        while (d > 0) {
            
            index = copyOpList.indexOf("\u00f7");
            
            if (index == -1)
                break;
            
            valueOne = Double.valueOf(copyValList.get(index));
            valueTwo = Double.valueOf(copyValList.get(index + 1));
            demoResult = model.performCal(valueOne, valueTwo, "\u00f7");
            
            copyOpList.remove(index);
            copyValList.remove(index + 1);
            copyValList.remove(index);
            copyValList.add(index, String.valueOf(demoResult));
            d--;
        }
        
        while (m > 0) {
            
            index = copyOpList.indexOf("\u00d7");
            
            if (index == -1)
                break;
            
            valueOne = Double.valueOf(copyValList.get(index));
            valueTwo = Double.valueOf(copyValList.get(index + 1));
            demoResult = model.performCal(valueOne, valueTwo, "\u00d7");
            
            copyOpList.remove(index);
            copyValList.remove(index + 1);
            copyValList.remove(index);
            copyValList.add(index, String.valueOf(demoResult));
            m--;
        }
        
        while (s > 0) {
            
            index = copyOpList.indexOf("\u2013");
            
            if (index == -1)
                break;
            
            valueOne = Double.valueOf(copyValList.get(index));
            valueTwo = Double.valueOf(copyValList.get(index + 1));
            demoResult = model.performCal(valueOne, valueTwo, "\u2013");
            
            copyOpList.remove(index);
            copyValList.remove(index + 1);
            copyValList.remove(index);
            copyValList.add(index, String.valueOf(demoResult));
            s--;
        }
        
        while (a > 0) {
            
            index = copyOpList.indexOf("\u002b");
            
            if (index == -1)
                break;
            
            valueOne = Double.valueOf(copyValList.get(index));
            valueTwo = Double.valueOf(copyValList.get(index + 1));
            demoResult = model.performCal(valueOne, valueTwo, "\\\u002b");
            
            copyOpList.remove(index);
            copyValList.remove(index + 1);
            copyValList.remove(index);
            copyValList.add(index, String.valueOf(demoResult));
            a--;
        }
        
        if(demoResult % 1 == 0)                                       
                strResult = String.format("%.0f", demoResult);              //Avoiding
            else {                                                      //non-trailing
                DecimalFormat df = new DecimalFormat("0.#######");      //zero &
                strResult = String.valueOf(df.format(demoResult));          //formatting
        }
        
        return strResult;
    }
        
     
    private void parenthesis(ArrayList<String> op_List, ArrayList<String> val_List,int parUsed) {
        
        int i = 0;
        
        while(parUsed > 0) {
            
            ArrayList<String> a = new ArrayList(op_List.subList(parList.get(i), parList.get(i+1)));
            ArrayList<String> b = new ArrayList(val_List.subList(parList.get(i), parList.get(i+1) + 1));
            String strResult = calEquation(a, b, divUsed, mulUsed, addUsed, subUsed, 0);
            op_List.subList(parList.get(i), parList.get(i+1)).clear();
            val_List.subList(parList.get(i), parList.get(i+1) +1 ).clear();
            val_List.add(parList.get(i), strResult);
            parUsed--;
            i=+2;
        }
        
    }
 
    
    @FXML
    private void numberProcess(ActionEvent event) {
        
        if (start && op == "") {
            output.setText("");
            start = false;
            c2BTN.setText("\u232b");
        }
        
        String value = ((Button)event.getSource()).getText();
        
        if (!(currentValue + value).matches("[0-9]{1,14}[.]?[0-9]{0,6}"))
            return;
        
        
        if (currentValue.equals("")) {
        
            currentValue += value;
            valList.add(currentValue);
        }
        else {
           
            currentValue += value;
            valList.set(valList.size() - 1, currentValue);
        }
            
        
        output.setText(output.getText() + value);
        
        if (opList.size() > 0 && opList.size() + 1 == valList.size()) {
            
            ArrayList<String> a = new ArrayList<String>(opList);
            ArrayList<String> b = new ArrayList<String>(valList);
            liveResult.setText(calEquation(a, b, divUsed, mulUsed, addUsed, subUsed, parUsed));
        }
    }
    
    @FXML
    private void operatorProcess(ActionEvent event) {
        
        if (start && opList.isEmpty()) {
            start = false;
            c2BTN.setText("\u232b");
            
        }
        else if (valList.size() == opList.size())      //if the input is only operator
            return;                                    //or if there is a operator pressed before
        
        String value = ((Button)event.getSource()).getText();
        String tOut = output.getText();
        
        if(!value.equals("=")) {
            
            if (value == "\u002b")
                addUsed++;
            else if (value == "\u2013")
                subUsed++;
            else if (value == "\u00f7")
                divUsed++;
            else if (value == "\u00d7")
                mulUsed++;
            
            opList.add(value);
            currentValue = "";
            
            if (valList.isEmpty())            //if we want to perform calculation on result
                valList.add(tOut);
            
            op = value;
            output.setText(tOut + op);
        }
        else if(value.equals("=")) {
          
            output.setText(liveResult.getText());
            liveResult.setText("");
            model.setM("Ans", Double.parseDouble(output.getText()));
            
            
            opList.clear();
            valList.clear();
            currentValue = "";
            addUsed = 0;
            subUsed = 0;
            mulUsed = 0;
            divUsed = 0;
            op = "";
            start = true;
            doubleOpCheck = -100;
            doubleDotCheck = false;
            c2BTN.setText("C");
            
        }
    }
    
    @FXML
    private void parButtonAction(ActionEvent event) {
        
        parList.add(opList.size());   //0+1+(2+3+4+5) op 2 4 val 2 5
        start = false;
        
        if (parList.size() % 2 == 0) {
            parUsed++;
            output.setText(output.getText() + ")");
        }
        else
            output.setText(output.getText() + "(");
    }
    
    @FXML
    private void cButtonAction() {
        
        String str = output.getText();
        
        if(str.equals(""))
            return;
        
        if (start) {
            output.setText("");
            c2BTN.setText("\u232b");
            opList.clear();
            valList.clear();
            currentValue = "";
            addUsed = 0;
            subUsed = 0;
            mulUsed = 0;
            divUsed = 0;
            op = "";
            return;
        }
        else if (!str.equals(""))
            output.setText(str.substring(0,str.length()-1));
        
        if (opList.size() == valList.size()) {
            
            if (opList.get(opList.size() - 1).equals("\u002b"))
                addUsed--;
            else if (opList.get(opList.size() - 1).equals("\u2013"))
                subUsed--;
            else if (opList.get(opList.size() - 1).equals("\u00f7"))
                divUsed--;
            else if (opList.get(opList.size() - 1).equals("\u00d7"))
                mulUsed--;
            
            opList.remove(opList.size() - 1);
            currentValue = valList.get(valList.size() - 1);
        }
        else {
            
            currentValue = currentValue.substring(0, currentValue.length() - 1);
            valList.set(valList.size() - 1, currentValue);
                
            if (currentValue.equals(""))
                valList.remove(valList.size() - 1);
        }
        
        if (opList.size() > 0 && opList.size() + 1 == valList.size()) {
            
            ArrayList<String> a = new ArrayList<String>(opList);
            ArrayList<String> b = new ArrayList<String>(valList);
            liveResult.setText(String.valueOf(calEquation(a, b, divUsed, mulUsed, addUsed, subUsed, parUsed)));
        
        }else if (opList.size() > 0)
            liveResult.setText("");
    }
    
    @FXML
    private void mButtonAction(ActionEvent event) {  //M & Ans button
        
        String m = ((Button)event.getSource()).getText();
        
        if(output.getText().equals(""))
            return;
        
        if(op.equals(""))
            model.setM("M", Double.parseDouble(output.getText()));
        else
            output.setText(output.getText() + model.getM(m));
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        divBTN.setText("\u00f7");        
        subBTN.setText("\u2013");
        mulBTN.setText("\u00d7");
        addBTN.setText("\u002b");
        cBTN.setText("()");
        c2BTN.setText("\u232b");
        output.setText("");
        liveResult.setText("");
    }    
    
}

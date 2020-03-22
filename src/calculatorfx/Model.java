/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorfx;

/**
 *
 * @author zzz
 */
public class Model {
    
    private double M1;
    private double Ans;

    
    public double performCal(double value1, double value2, String operator) {
        
        switch (operator) {
            
            case "\\\u002b":
                return (value1 + value2);
            
            case "\u2013":
                return (value1 - value2);
            
            case "\u00f7":
                if(value2 == 0)
                    return 0;
                else
                    return (value1 / value2);
                
            case "\u00d7":
                return (value1 * value2);
                
        }
        
        return 111.1;
    }
    
    public void setM(String M, double value) {
        
        if(M.equals("M"))
            M1 = value;
        else
            Ans = value;
            
    }
    
    public double getM(String M) {
        
        if(M.equals("M"))
            return M1;
        else
            return Ans;
    }
    
    
    
}

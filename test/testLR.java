
import com.mysql.jdbc.util.LRUCache;
import org.rmj.apprdiver.util.LRUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mac
 */
public class testLR {
    public static void main (String [] args){
        double reb = 100.00;
        
        double amort = 3000.00;
        double due = 6000.00;
        double paid = 12000.00;
        
        double lnRebate = LRUtil.getRebate(paid, amort, due, reb);
        
        if (lnRebate > 0.00){
            System.out.println("Amount paid is: " + (paid - lnRebate));
            System.out.println("Rebate is: " + lnRebate);
        } else {
            System.out.println("Amount paid is: " + paid);
            System.out.println("Rebate is: " + lnRebate);
        }
        
        if (lnRebate < 250.00){
            System.err.println("Rebate given is greater than the supposed rebate.");
        }
    }
}

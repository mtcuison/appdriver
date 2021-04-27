
package org.rmj.apprdiver.util;

public class LRUtil {
    public static final double FC_MAX_REBATE = 100.00;
    
    public static double getRebate(double fnAmtPaidx, double fnMonAmort, double fnAmtDuexx, double fnRebatesx){
        if (fnRebatesx == 0.00) fnRebatesx = FC_MAX_REBATE;

        double lnDiff = 0.00;

        lnDiff = fnAmtPaidx - fnAmtDuexx;

        if (lnDiff > 0.00){
            if (lnDiff/fnMonAmort > 0.00){
                double lnMod = lnDiff % fnMonAmort;
                
                lnDiff -= lnMod;
                
                return lnDiff / fnMonAmort * fnRebatesx;
            }   
        }

        return 0.00;
    }
}

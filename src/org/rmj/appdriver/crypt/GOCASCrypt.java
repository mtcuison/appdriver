package org.rmj.appdriver.crypt;

import org.rmj.apprdiver.util.MiscUtil;
import org.rmj.apprdiver.util.StringHelper;

public class GOCASCrypt {    
    public GOCASCrypt(){
        initValues();
    }
    
    public void setTransactionNo(String fsValue){
        sTransNox = fsValue;
    }
    public String getTransactionNo(){
        return sTransNox;
    }
    
    public void setUserID(String fsValue){
        sUserIDxx = fsValue;
    }
    public String getUserID(){
        return sUserIDxx;
    }
    
    public void setLastName(String fsValue){
        sLastName = fsValue;
    }
    public String getLastName(){
        return sLastName;
    }
    
    public void setFirstName(String fsValue){
        sFrstName = fsValue;
    }
    public String getFirstName(){
        return sFrstName;
    }
    
    public void setSuffixName(String fsValue){
        sSuffixNm = fsValue;
    }
    public String getSuffixName(){
        return sSuffixNm;
    }
    
    public void setMiddleName(String fsValue){
        sMiddName = fsValue;
    }
    public String getMiddleName(){
        return sMiddName;
    }
    
    public void IsCINeeded(boolean fbValue){
        cWithCIxx = fbValue ? "1" : "0";
    }
    public boolean IsCINeeded(){
        return cWithCIxx.equals("1");
    }
    
    public void setDownPayment(long fnValue){
        nDownPaym = fnValue;
    }
    public long getDownPayment(){
        return nDownPaym;
    }
    
    private void setMessage(String fsValue){
        sWarnMsgx = fsValue;
    }
    public String getMessage(){
        return sWarnMsgx;
    }
    
    public String getGOCASNumber(){
        return sGOCASNox;
    }
    
    public boolean Decode(String fsGOCASNox){
        setMessage("Unable to decode the GOCAS Number.");
        
        if (fsGOCASNox.length() != 18){
            sWarnMsgx = sWarnMsgx + "Invalid code format detected.";
            return false;
        }
        
        fsGOCASNox = fsGOCASNox.toUpperCase();
        
        String lsUserxx = "";
        String lsSeries = "";
        String lsNameCd = "";
        String lsRand01 = "";
        String lsRand02 = "";
        String lsDownPy = "";

        long lnRand01 = 0;
        long lnRand02 = 0;
        long lnResult = 0;

        sGOCASNox = fsGOCASNox;
        
        lsUserxx = sGOCASNox.substring(0, 5);   //XXXXX
        lsSeries = sGOCASNox.substring(5, 10);  //XXXXX
        lsNameCd = sGOCASNox.substring(10, 14); //XXXX
        lsRand01 = sGOCASNox.substring(14, 15); //X
        lsDownPy = sGOCASNox.substring(15, 17); //XX
        lsRand02 = sGOCASNox.substring(17, 18); //X
        
        //deserialize 1st random number
        lnRand01 = MiscUtil.DeSerializeNumber(lsRand01);
        //deserialize 2nd random number
        lnRand02 = MiscUtil.DeSerializeNumber(lsRand02);
        
        //deserialize user
        lnResult = MiscUtil.DeSerializeNumber(lsUserxx) - lnRand01;
        sUserIDxx = BRANCHCD + String.valueOf(lnResult);
        
        //deserialize transaction number
        sTransNox = MiscUtil.SerializeNumber(lnResult);
        lnResult = MiscUtil.DeSerializeNumber(lsSeries) - lnRand02;
        sTransNox = sTransNox + String.valueOf(lnResult);
        
        //deseriaized downpayment
        if (lsDownPy.substring(0, 1).equals("0")) lsDownPy = lsDownPy.substring(1);
        
        lnResult = MiscUtil.DeSerializeNumber(lsDownPy);
        if (lnResult > 1000) {
            cWithCIxx = "1";
            lnResult = Long.parseLong(String.valueOf(lnResult).substring(1));
        } else{
            cWithCIxx = "0";
        }
        nDownPaym = lnResult - lnRand01 - lnRand02;
        
        //deserialize name
        lnResult = MiscUtil.DeSerializeNumber(lsNameCd, 16);
        lnResult = lnResult - lnRand01 - lnRand02;
        sClientNm = MiscUtil.SerializeNumber(lnResult);
        
        return true;
    }
    
    public boolean Encode(String fsGOCASNox, int fnNewDownP, boolean fbNeedCI){
        if (!Decode(fsGOCASNox)) return false;
        
        //set the new downpayment
        nDownPaym = fnNewDownP;
        //is CI needed?
        cWithCIxx = fbNeedCI ? "1" : "0";
        
        if (!Encode()){
            setMessage("Unable to issue FINAL GOCAS Number.");
            return false;
        }
        
        return true;
    }
    
    public boolean Encode(){
        String lsUserxx = "";
        String lsSeries = "";
        String lsNameCd = "";
        String lsRand01 = "";
        String lsRand02 = "";
        String lsDownPy = "";

        int lnRand01 = 0;
        int lnRand02 = 0;

        sGOCASNox = "";
        
        if (sTransNox.isEmpty()){
            setMessage("UNSET Transaction Number.");
            return false;
        }
        
        if (sUserIDxx.isEmpty()){
            setMessage("UNSET App User ID.");
            return false;
        }
        
        if (sClientNm.isEmpty()){
            if (sLastName.isEmpty()){
                setMessage("UNSET Last Name.");
                return false;
            }
            
            if (sFrstName.isEmpty()){
                setMessage("UNSET First Name.");
                return false;
            }
            
            if (sMiddName.isEmpty()){
                setMessage("UNSET Middle Name.");
                return false;
            }
            
            sClientNm = sLastName + ", " + sFrstName + " ";
            if (sSuffixNm.isEmpty()) sClientNm = sClientNm + sSuffixNm + " ";
            sClientNm = sClientNm + sMiddName;
        }
        
        if (cWithCIxx.isEmpty()){
            setMessage("UNSET value if CI was needed.");
            return false;
        }
        
        //process random number 1
        lnRand01 = MiscUtil.getRandom();
        lsRand01 = procRandom(lnRand01);
        
        //process random number 2
        lnRand02 = MiscUtil.getRandom();
        lsRand02 = procRandom(lnRand02);
        
        //process user id
        lsUserxx = procUser(lnRand01);
        
        //process series
        lsSeries = procSeries(lsUserxx, lnRand02);
        
        //process downpayment
        lsDownPy = procDownPayment(lnRand01, lnRand02);
        
        //process client
        lsNameCd = sClientNm;
        if (sClientNm.length() != 3) lsNameCd = MiscUtil.getClientCode(sLastName, sFrstName, sMiddName, sSuffixNm);
        lsNameCd = procName(lsNameCd, lnRand01, lnRand02);
        
        //user(XXXXX) + series(XXXXX) + name(XXXX) + rand1(X) + down(XX) + rand2(X)
        sGOCASNox = lsUserxx + lsSeries + lsNameCd + lsRand01 + lsDownPy + lsRand02;
        
        return true;
    }
    
    public boolean IsValidGOCASNo(String fsTransNox, 
                                    String fsGOCASNox,
                                    String fsLastName,
                                    String fsFrstName,
                                    String fsMiddName,
                                    String fsSuffixNm, 
                                    double fnUnitPrce,
                                    double fnDownPaym){
        
        setMessage("Invalid GOCAS Number Detected.");
        
        if (!Decode(fsGOCASNox)) return false;
        
        if (!sTransNox.equals(fsTransNox)) return false;
        
        //get the client code
        String lsName = MiscUtil.getClientCode(fsLastName, fsFrstName, fsMiddName, fsSuffixNm);
        
        //compare the decoded client code to the client code from the given client name
        if (!sClientNm.equals(lsName)) return false;
        
        double lnDownPaym  = (fnDownPaym / fnUnitPrce) * 100;
        
        return nDownPaym <= lnDownPaym;
    }
                                            
    
    
    private String procRandom(int fnValue){
        return MiscUtil.SerializeNumber(fnValue);
    }
    
    private String procUser(int fnRand01){
        return MiscUtil.SerializeNumber(Long.parseLong(sUserIDxx.substring(4)) + fnRand01);
    }
    
    private String procSeries(String fsUser, int fnRand02){
        return MiscUtil.SerializeNumber(Long.parseLong(sTransNox.substring(fsUser.length())) + fnRand02);
    }
    
    private String procDownPayment(int fnRand01, int fnRand02){
        String lsDownPy = cWithCIxx + StringHelper.prepad(String.valueOf(nDownPaym + fnRand01 + fnRand02), 3, '0');
        
        lsDownPy = MiscUtil.SerializeNumber(Long.valueOf(lsDownPy));
        
        if (lsDownPy.length() < 2) lsDownPy = StringHelper.prepad(lsDownPy, 2, '0');
        
        return lsDownPy;
    }
    
    private String procName(String fsCode, int fnRand01, int fnRand02){
        return MiscUtil.SerializeNumber(MiscUtil.DeSerializeNumber(fsCode) + fnRand01 + fnRand02, 16);
    }
    
    
    private void initValues(){
        sTransNox = "";    
        sUserIDxx = "";
        sClientNm = "";
        cWithCIxx = "";
        sGOCASNox = "";

        sLastName = "";
        sFrstName = "";
        sMiddName = "";
        sSuffixNm = "";

        sWarnMsgx = "";
        
        //default DP
        nDownPaym = 200;   
    }
    
    private String sTransNox;
    private String sUserIDxx;
    private String sClientNm;
    private String cWithCIxx;
    private String sGOCASNox;

    //applicant name         
    private String sLastName;
    private String sFrstName;
    private String sMiddName;
    private String sSuffixNm;
    
    private String sWarnMsgx;

    private long nDownPaym;   

    private final String BRANCHCD = "GAP0";
}



/**
 * @author Michael Cuison 2020.12.23
 */
package org.rmj.appdriver.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rmj.appdriver.iface.iGProperty;

public class GProperty implements iGProperty{
    Properties prop;
    
    String psProductID = "";
    String psMessage = "";
    
    String psDBSrvrMn = null;  // ip address of central server
    String psDBSrvrNm = null;
    String psDBNameXX = null;
    String psDBPassWD = null;
    String psDBUserNm = null;
    String psDBPortNo = null;
    String psDriverNm = null;
    String psClientID = null;
    
    String psCryptTyp = "0";
    
    public GProperty() {
        prop = new Properties();
    }
    
    public GProperty(String fsFile, String fsProdctID){
        prop = new Properties();
      
        try {
            prop.load(new FileInputStream(fsFile));
            psProductID = fsProdctID;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public GProperty(InputStream foValue, String fsProdctID){
        prop = new Properties();
      
        try {
            prop.load(foValue);
            psProductID = fsProdctID;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void setConfigDIR(String fsValue) {
        prop = new Properties();
      
        try {
            prop.load(new FileInputStream(fsValue));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void setProductID(String fsValue) {
        psProductID = fsValue;
    }
    
    @Override
    public boolean loadConfig() {
        try{        
            if(getConfig("-CryptType") != null){
                psCryptTyp = getConfig("-CryptType");
            }
         
            psDBNameXX = getConfig("-Database");
            psDBSrvrNm = getConfig("-ServerName");
            psDBSrvrMn = getConfig("-MainServer");         
            
            if(!getConfig("-UserName").isEmpty()){
                psDBUserNm = getConfig("-UserName");
                psDBPassWD = getConfig("-Password");
            } else{
                psDBUserNm = "";
                psDBPassWD = "";
            }

            psDriverNm = getConfig("-DBDriver");
            psDBPortNo = getConfig("-Port");
            psClientID = getConfig("-ClientID");         
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            setMessage(ex.getMessage());
            return false;
        }
             
        if(psDBNameXX.equals("") ||
            psDBSrvrNm.equals("") ||
            psClientID.equals("")) {
            setMessage("Invalid configuration values!");
            return false;
        }

        if (psDBPortNo == null || psDBPortNo.equals("")){
            psDBPortNo = "3306";
        }

        return true;
    }
    
    @Override
    public String getConfig(String fsValue) {
        return prop.getProperty(psProductID + fsValue);
    }

    @Override
    public void setConfig(String fsConfig, String fsValue) {
        prop.setProperty(psProductID + fsConfig, fsValue);
    }

    @Override
    public void save(String fsFileName) {
        try {
            prop.store(new FileOutputStream(fsFileName), null);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(GProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getClientID() {
        return psClientID;
    }
    
    @Override
    public String getDBDriver() {
        return psDriverNm;
    }
    
    @Override
    public String getDBHost() {
        return psDBSrvrNm;
    }

    @Override
    public String getMainServer() {
        return psDBSrvrMn;
    }    

    @Override
    public String getPort() {
        return psDBPortNo;
    }

    @Override
    public String getDBName() {
        return psDBNameXX;
    }

    @Override
    public String getUser() {
        return psDBUserNm;
    }

    @Override
    public String getPassword() {
        return psDBPassWD;
    }
    
    @Override
    public String getCryptType() {
        return psCryptTyp;
    }

    @Override
    public void setDBHost(String fsValue) {
        psDBSrvrNm = fsValue;
    }
    
    @Override
    public void setDBName(String fsValue) {
        psDBNameXX = fsValue;
    }

    @Override
    public void setPort(String fsValue) {
        psDBPortNo = fsValue;
    }

    @Override
    public String getMessage() {
        return psMessage;
    }

    //added methods
    private void setMessage(String fsValue){
        psMessage = fsValue;
    }
}
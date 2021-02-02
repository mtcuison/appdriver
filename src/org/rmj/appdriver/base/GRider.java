/**
 * @author Michael Cuison 2020.12.23
 */
package org.rmj.appdriver.base;

import java.sql.Date;
import java.sql.ResultSet;
import org.rmj.appdriver.iface.iGConnection;
import org.rmj.appdriver.iface.iGCrypt;
import org.rmj.appdriver.iface.iGRider;

public class GRider implements iGRider{
    iGConnection poConn;
    iGCrypt poCrypt;
    
    Date pdSysDate;

    @Override
    public void setGConnection(iGConnection foValue) {
        poConn = foValue;
    }

    @Override
    public iGConnection getGConnection() {
        return poConn;
    }

    @Override
    public String Encrypt(String fsValue, String fsSalt) {
        return poCrypt.Encrypt(fsValue, fsSalt);
    }

    @Override
    public String Decrypt(String fsValue, String fsSalt) {
        return poCrypt.Decrypt(fsValue, fsSalt);
    }
    
    @Override
    public String EnvStat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SystemDate(Date fdValue) {
        pdSysDate = fdValue;
    }

    @Override
    public Date SystemDate() {
        return pdSysDate;
    }

    @Override
    public String getAppConfig(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBranchConfig(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSysConfig(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getServerDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean lockUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loginUser(String fsProdctID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loginUser(String fsProdctID, String fsUserIDxx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logoutUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean unlockUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean beginTrans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean commitTrans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean rollbackTrans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet executeQuery(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long executeUpdate(String fsValue, String fsTableNme, String fsBranchCd, String fsDestinat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //added methods
    public void setMessage(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

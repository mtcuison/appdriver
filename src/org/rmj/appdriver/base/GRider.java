/**
 * @author Michael Cuison 2020.12.23
 */
package org.rmj.appdriver.base;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.rmj.appdriver.iface.iGConnection;
import org.rmj.appdriver.iface.iGCrypt;
import org.rmj.appdriver.iface.iGRider;
import org.rmj.apprdiver.util.MiscUtil;

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
    public Timestamp getServerDate() {
        ResultSet loRS = null;
        Timestamp loTimeStamp = null;
        String lsSQL = "";

        setMessage("");

        try{
            Connection loConn = poConn.getConnection();
            
            if(loConn == null){
                setMessage("Connection is not set.");
                return loTimeStamp;
            }

            if(loConn.getMetaData().getDriverName().equalsIgnoreCase("SQLite JDBC")){
                lsSQL = "SELECT DATETIME('now','localtime')";
                
                loRS = loConn.createStatement()
                     .executeQuery(lsSQL);
                //position record pointer to the first record
                loRS.next();
                //assigned timestamp

                loTimeStamp = Timestamp.valueOf(loRS.getString(1));
            }else{
                //assume that default database is MySQL ODBC
                lsSQL = "SELECT SYSDATE()";
                
                loRS = loConn.createStatement()
                    .executeQuery(lsSQL);
                //position record pointer to the first record
                loRS.next();
                //assigned timestamp
                loTimeStamp = loRS.getTimestamp(1);
            }            
        }
        catch(SQLException ex){
            ex.printStackTrace();
            setMessage(ex.getSQLState());
        } finally{
            MiscUtil.close(loRS);
        }
        return loTimeStamp;
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
        return poConn.beginTrans();
    }

    @Override
    public boolean commitTrans() {
        return poConn.commitTrans();
    }

    @Override
    public boolean rollbackTrans() {
        return poConn.rollbackTrans();
    }

    @Override
    public ResultSet executeQuery(String fsValue) {
        return poConn.executeQuery(fsValue);
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

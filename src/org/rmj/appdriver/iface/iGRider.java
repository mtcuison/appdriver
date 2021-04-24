/**
 * @author Michael Cuison 2020.12.21
 */
package org.rmj.appdriver.iface;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

public interface iGRider {
    public void setGConnection(iGConnection foValue);
    public iGConnection getGConnection();
    
    public String Encrypt(String fsValue, String fsSalt);
    public String Decrypt(String fsValue, String fsSalt);
    
    public String EnvStat();
    public void SystemDate(Date fdValue);
    public Date SystemDate();
        
    public String getAppConfig(String fsValue);
    public String getBranchConfig(String fsValue);
    public String getSysConfig(String fsValue);
    public Timestamp getServerDate();
    
    public boolean lockUser();
    public boolean loginUser(String fsProdctID);
    public boolean loginUser(String fsProdctID, String fsUserIDxx);
    public boolean logoutUser();
    public boolean unlockUser();
    
    public boolean beginTrans();
    public boolean commitTrans();
    public boolean rollbackTrans();
    public ResultSet executeQuery(String fsValue);
    public long executeUpdate(String fsValue, String fsTableNme, String fsBranchCd, String fsDestinat);
    
    public String getMessage();
}

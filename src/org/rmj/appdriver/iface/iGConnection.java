/**
 * @author Michael Cuison 2020.12.21
 */
package org.rmj.appdriver.iface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.rmj.appdriver.base.GProperty;

public interface iGConnection {
    public void setGProperty(GProperty foValue);
    public void setGAESCrypt(iGCrypt foValue);
    public iGCrypt getGAESCrypt();
    
    public void IsOnline(boolean fbValue);
    public boolean IsOnline();
    
    public void setUserID(String fsValue);
    public String getUserID();
    
    public Timestamp getServerDate();
    
    public boolean connect();
    public boolean beginTrans();
    public boolean commitTrans();
    public boolean rollbackTrans();
    public Connection getConnection();
    public ResultSet executeQuery(String fsValue);
    public long executeUpdate(String fsValue);
   
    public String getMessage();

}
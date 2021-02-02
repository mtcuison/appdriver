import java.sql.Connection;
import java.sql.ResultSet;
import org.rmj.appdriver.base.GConnection;
import org.rmj.appdriver.base.GProperty;
import org.rmj.appdriver.crypt.GCryptFactory;
import org.rmj.appdriver.iface.iGCrypt;
import org.rmj.apprdiver.util.MiscUtil;

public class tesGConnection {
    public static void main (String [] args){
        //initialize server configuration
        GProperty loProp = new GProperty();
        loProp.setProductID("gRider");
        loProp.setConfigDIR("GhostRiderXP.properties");
        if (!loProp.loadConfig()){
            System.exit(1);
        }
        
        //initialize encryption engine
        iGCrypt loCrypt = new GCryptFactory().make(GCryptFactory.CrypType.GCrypt);
        loCrypt.setHexCrypt(Integer.valueOf(loProp.getCryptType()));
        
        GConnection instance = new GConnection();
        instance.setGProperty(loProp);
        instance.setGAESCrypt(loCrypt);
        
        if (instance.connect()){
            Connection loConn = instance.getConnection();
            
            if (loConn == null){
                System.out.println("Olats");
                System.exit(1);
            }
            
            ResultSet loRS = instance.executeQuery("SELECT * FROM Branch;");
            System.out.println(MiscUtil.RecordCount(loRS));
        } else 
            System.err.println(instance.getMessage());
    }
}

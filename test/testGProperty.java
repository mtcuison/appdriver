
import org.rmj.appdriver.base.GProperty;

public class testGProperty {
    public static void main(String [] args){
        GProperty instance = new GProperty();
        instance.setProductID("gRider");
        instance.setConfigDIR("GhostRiderXP.properties");
        if (instance.loadConfig()){
            System.out.println("Config file was loaded successfully.");
            System.out.println(instance.getCryptType());
            System.out.println(instance.getDBDriver());
            System.out.println(instance.getDBHost());
            System.out.println(instance.getUser());
            System.out.println(instance.getPassword());
            System.out.println(instance.getPort());
            
            System.out.println(instance.getMainServer());
            System.out.println(instance.getClientID());
        }
        else
            System.err.println(instance.getMessage());
    }
}

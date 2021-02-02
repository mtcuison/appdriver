
import org.rmj.appdriver.crypt.GCryptFactory;
import org.rmj.appdriver.iface.iGCrypt;

public class testGCrypt {
    public static void main(String [] args){
        iGCrypt instance = new GCryptFactory().make(GCryptFactory.CrypType.GCrypt);
        instance.setHexCrypt(0);
        System.out.println(instance.Decrypt("_Â", "08220326"));
        System.out.println(instance.Decrypt("m×£\"ÎÑr¸>§", "08220326"));
    }
}

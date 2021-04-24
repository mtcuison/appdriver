
import org.rmj.appdriver.crypt.GCryptFactory;
import org.rmj.appdriver.iface.iGCrypt;

public class testGCrypt {
    public static void main(String [] args){
        iGCrypt loCrypt = new GCryptFactory().make(GCryptFactory.CrypType.AESCrypt);
        String lsValue = "Michael Cuison";
        String lsSalt = "2020";
        lsValue = loCrypt.Encrypt(lsValue, lsSalt);
        System.out.println("ENCRYPTED VALUE: " + lsValue);
        lsValue = loCrypt.Decrypt(lsValue, lsSalt);
        System.out.println("DECRYPTED VALUE: " + lsValue);
    }
}

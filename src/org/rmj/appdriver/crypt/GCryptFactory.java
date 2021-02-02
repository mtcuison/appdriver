/**
 * @author Michael Cuison 2020.12.23
 */
package org.rmj.appdriver.crypt;

import org.rmj.appdriver.iface.iGCrypt;

public class GCryptFactory {
    public enum CrypType{
        AESCrypt,
        GCrypt
    }
    
    public static iGCrypt make(GCryptFactory.CrypType foType){
        switch (foType){
            case AESCrypt:
                return new MySQLAES();
            case GCrypt:
                return new GCrypt();
            default:
                return null;
        }
    }
}

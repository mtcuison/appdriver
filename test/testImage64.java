
import org.rmj.apprdiver.util.WebFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mac
 */
public class testImage64 {
    public static void main(String [] args){
        String lsFileIn = "D:/sample.png";
        String lsFileOut = "output.png";
        
        String lsMD5xx = WebFile.md5Hash(lsFileIn);
        String lsImg64 = WebFile.FileToBase64(lsFileIn);
        
        System.out.println(WebFile.Base64ToFile(lsImg64, lsMD5xx, "D:/", lsFileOut));
    }
}

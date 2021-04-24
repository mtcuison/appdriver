/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rmj.apprdiver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mac
 */
public class WebFile {
    private static final String UPLOAD_IMAGE_FILE = "https://webfsgk.guanzongroup.com.ph/x-api/v1.0/edocsys/upload_edoc_file.php";
    private static final String DOWNLOAD_IMAGE_FILE = "https://webfsgk.guanzongroup.com.ph/x-api/v1.0/edocsys/download_edoc_file.php";
    private static final String UPDATE_FILE_OWNER = "https://webfsgk.guanzongroup.com.ph/x-api/v1.0/edocsys/update_edoc_owner.php";
    private static final String CHECK_UPLOADED_FILE = "https://webfsgk.guanzongroup.com.ph/x-api/v1.0/edocsys/check_edoc_file.php";
    
    /**
     * UploadFile
     * 
     * Upload image with its corresponding information.
     * 
     * @param fsAccessCd    Access Token
     * @param fsFileType    File Type
     * @param fsFileOwnr    Universal Identifier for this file (sSerialID)
     * @param fsFileName    File Name
     * @param fsScannerx    Branch/Dept/Employee who owns this file
     * @param fsHashValx    Hash value of file
     * @param fsImage64x    64 bit encoded value of file
     * @param fsSourceCd    Source Code
     * @param fsSourceNo    Source No/Reference No
     * @param fsUniqueVl    Additional Information that will make this file unique
     * 
     * @return {"result":"success","sTransNox":""} / {"result":"success","error":"{"code":0,"message":""}"}
     */
    public static JSONObject UploadFile(
            String fsAccessCd, 
            String fsFileType,
            String fsFileOwnr,
            String fsFileName,
            String fsScannerx,
            String fsHashValx,
            String fsImage64x,
            String fsSourceCd,
            String fsSourceNo,
            String fsUniqueVl){
        
        JSONObject loJSON = new JSONObject();
        loJSON.put("g-edoc-type", fsFileType);
        loJSON.put("g-edoc-ownr", fsFileOwnr);
        loJSON.put("g-edoc-file", fsFileName);
        loJSON.put("g-edoc-scnr", fsScannerx);
        loJSON.put("g-edoc-hash", fsHashValx);
        loJSON.put("g-edoc-imge", fsImage64x);
        loJSON.put("g-edoc-srcd", fsSourceCd);
        loJSON.put("g-edoc-srno", fsSourceNo);
        loJSON.put("g-edoc-unqe", fsUniqueVl);
        
            return sendRequest(UPLOAD_IMAGE_FILE, fsAccessCd, loJSON);
    }
    
    /**
     * DownloadFile
     * 
     * Download image with its corresponding information
     * 
     * @param fsAccessCd    Access Token
     * @param fsFileType    File Type
     * @param fsFileOwnr    Universal Identifier for this file (sSerialID)
     * @param fsFileName    File Name
     * @param fsSourceCd    Source Code
     * @param fsSourceNo    Source No/Reference No
     * @param fsUniqueVl    Additional Information that will make this file unique
     * 
     * @return {"result":"success","payload":"{"filename":"File Name","hash":"Hash value of file","data":"64 bit encoded data"}"} / {"result":"success","error":"{"code":0,"message":""}"}
     */
    public static JSONObject DownloadFile(
            String fsAccessCd,
            String fsFileType,
            String fsFileOwnr,
            String fsFileName,
            String fsSourceCd,
            String fsSourceNo,
            String fsUniqueVl){
        
        JSONObject loJSON = new JSONObject();
        loJSON.put("g-edoc-type", fsFileType);
        loJSON.put("g-edoc-ownr", fsFileOwnr);
        loJSON.put("g-edoc-file", fsFileName);
        loJSON.put("g-edoc-srcd", fsSourceCd);
        loJSON.put("g-edoc-srno", fsSourceNo);
        loJSON.put("g-edoc-unqe", fsUniqueVl);
        
        return sendRequest(DOWNLOAD_IMAGE_FILE, fsAccessCd, loJSON);
    }
    
    /**
     * CheckFile
     * 
     * Check image with its corresponding information
     * 
     * @param fsAccessCd    Access Token
     * @param fsFileType    File Type
     * @param fsFileOwnr    Universal Identifier for this file (sSerialID)
     * @param fsFileName    File Name
     * @param fsSourceCd    Source Code
     * @param fsSourceNo    Source No/Reference No
     * @param fsUniqueVl    Additional Information that will make this file unique
     * 
     * @return {"result":"success","payload":"{"filename":"File Name","hash":"Hash value of file","data":"64 bit encoded data"}"} / {"result":"success","error":"{"code":0,"message":""}"}
     */
    public static JSONObject CheckFile(
            String fsAccessCd,
            String fsFileType,
            String fsBranchCd,
            String fsSourceCd,
            String fsSourceNo){
        
        JSONObject loJSON = new JSONObject();
        loJSON.put("g-edoc-type", fsFileType);
        loJSON.put("g-edoc-ownr", fsBranchCd);
        loJSON.put("g-edoc-srcd", fsSourceCd);
        loJSON.put("g-edoc-srno", fsSourceNo);
        
        return sendRequest(CHECK_UPLOADED_FILE, fsAccessCd, loJSON);
    }
    
    /**
     * UpdateFileOwner
     * 
     * Update the Universal Identifier for file
     * 
     * @param fsAccessCd    Access Token
     * @param fsFileType    File Type
     * @param fsFileOwnr    Universal Identifier for this file (sSerialID)
     * @param fsFileName    File Name
     * @param fsSourceCd    Source Code
     * @param fsSourceNo    Source No/Reference No
     * @param fsUniqueVl    Additional Information that will make this file unique
     * 
     * @return {"result":"success","payload":"{"filename":"File Name","hash":"Hash value of file","data":"64 bit encoded data"}"} / {"result":"success","error":"{"code":0,"message":""}"}
     */
    public static JSONObject UpdateFileOwner(
            String fsAccessCd,
            String fsFileType,
            String fsFileOwnr,
            String fsFileName,
            String fsSourceCd,
            String fsSourceNo,
            String fsUniqueVl){
        
        JSONObject loJSON = new JSONObject();
        loJSON.put("g-edoc-type", fsFileType);
        loJSON.put("g-edoc-ownr", fsFileOwnr);
        loJSON.put("g-edoc-file", fsFileName);
        loJSON.put("g-edoc-srcd", fsSourceCd);
        loJSON.put("g-edoc-srno", fsSourceNo);
        loJSON.put("g-edoc-unqe", fsUniqueVl);
        
        return sendRequest(UPDATE_FILE_OWNER, fsAccessCd, loJSON);
    }
    
    /**
     * FileToBase64
     * 
     * Converts a file to 64 bit encoding.
     * 
     * @param fsFilePath Location and name of the file
     * 
     * @return 64 bit string value of the file.
     */
    public static String FileToBase64(String fsFilePath){
        String lsValue = "";
        
        try {
            File f = new File(fsFilePath);
            FileInputStream fis = new FileInputStream(f);
            byte byteArray[] = new byte[(int)f.length()];
            fis.read(byteArray);
            
            //lsValue = Base64.encodeBase64String(byteArray);
            lsValue = new String(Base64.encodeBase64(byteArray));

            fis.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return lsValue;
    }
    
    
    /**
     * Base64ToFile
     * 
     * Converts the 64 bit data to a file on a specific location.
     * 
     * @param fsImage64x    64 bit encoded data
     * @param fsImgHashx    Hash value of the file
     * @param fsSavePath    Save directory for the file
     * @param fsFileName    Filename
     * 
     * @return true or false
     */
    public static boolean Base64ToFile(String fsImage64x, String fsImgHashx, String fsSavePath, String fsFileName){
        try {            
            //output file
            FileOutputStream fos = new FileOutputStream(fsSavePath + fsImgHashx);
            //byte byteArray[] = Base64.decodeBase64(fsImage64x);
            byte byteArray[] = Base64.decodeBase64(fsImage64x.getBytes());
            
            fos.write(byteArray);
            fos.close();
            
            //validate if hash
            String lsHashx = md5Hash(fsSavePath + fsImgHashx);
            
            File f = new File(fsSavePath + fsImgHashx);
            
            if (fsImgHashx.equals(lsHashx)){
                f.renameTo(new File(fsSavePath + fsFileName));
                return true;
            } else{
                f.delete();
                return false;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * md5Hash
     * 
     * Gets the hash value of a specific file.
     * 
     * @param fsFilePath Location and name of the file
     * @return 
     */
    public static String md5Hash(String fsFilePath){
        String md5 = "";
        try{
            FileInputStream fis = new FileInputStream(new File(fsFilePath));
            //md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
            md5 = new String (Hex.encodeHex(DigestUtils.md5(fis)));
            fis.close();     
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        
        return md5;
    }
    
    private static JSONObject sendRequest(String fsURL, String fsAccessCd, JSONObject foParam){
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("g-access-token", fsAccessCd);
        
        JSONObject loJSON = new JSONObject();
        
        try {
            String response = WebClient.sendHTTP(fsURL, foParam.toJSONString(), (HashMap<String, String>) headers);

            if(response == null){
                JSONObject loErr = new JSONObject();
                loErr.put("code", 500);
                loErr.put("message", "Server has no response.");
                
                loJSON.put("result", "error");
                loJSON.put("error", loErr);
                
                return loJSON;
            } else {
                JSONParser loParser = new JSONParser();
                return (JSONObject) loParser.parse(response);
            }
        } catch (IOException | ParseException ex) {
            JSONObject loErr = new JSONObject();
            loErr.put("code", 0);
            loErr.put("message", ex.getMessage());

            loJSON.put("result", "error");
            loJSON.put("error", loErr);

            return loJSON;
        }
    }
}

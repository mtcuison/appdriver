import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rmj.apprdiver.util.WebClient;
import org.rmj.apprdiver.util.WebFile;

public class testDownloadFile {
    public static void main(String [] args){
        String lsClientToken = "";
        String lsAccessToken = "";
        JSONObject loJSON;
        JSONParser loParser = new JSONParser();
        
        try {
            loJSON = WebClient.RequestClientToken("IntegSys", "GGC_BM001", "GAP0190004");
        
            if (loJSON.get("result").equals("success")){
                loJSON = (JSONObject) loParser.parse(loJSON.get("payload").toString());
                lsClientToken = (String) loJSON.get("token");             
                
                if (lsClientToken.isEmpty()){
                    System.err.println("Client Token is empty.");
                    System.exit(1);
                }
            } else {
                loJSON = (JSONObject) loParser.parse(loJSON.get("error").toString());
                System.err.println(loJSON.get("message") + " " +loJSON.get("code"));
                System.exit(1);
            }
            
            loJSON = WebClient.RequestAccessToken(lsClientToken);

            if ("success".equals((String) loJSON.get("result"))){
                loJSON = (JSONObject) loJSON.get("payload");
                lsAccessToken = (String) loJSON.get("token");
            } else {
                loJSON = (JSONObject) loParser.parse(loJSON.get("error").toString());
                System.err.println(loJSON.get("message") + " " +loJSON.get("code"));
                System.exit(1);
            }
            
            loJSON = WebFile.DownloadFile(lsAccessToken, 
                                            "0024", 
                                            "",
                                            "C0YNQ2100029_3_0024.png", 
                                            "COAD", 
                                            "C0YNQ2100029", 
                                            "");

            if ("success".equals((String) loJSON.get("result"))){
                loJSON = (JSONObject) loParser.parse(loJSON.get("payload").toString());

                if (WebFile.Base64ToFile((String) loJSON.get("data"), (String) loJSON.get("hash"), "D:/", (String) loJSON.get("filename")))
                    System.out.println("File downloaded successfully.");
                else
                    System.out.println("Unable to convert file.");
            } else {
                loJSON = (JSONObject) loParser.parse(loJSON.get("error").toString());
                System.err.println(loJSON.get("message") + " " +loJSON.get("code"));
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

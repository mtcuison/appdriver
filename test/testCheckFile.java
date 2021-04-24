import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.rmj.apprdiver.util.WebClient;
import org.rmj.apprdiver.util.WebFile;

public class testCheckFile {
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
            
            loJSON = WebFile.CheckFile(lsAccessToken,
                                            "0029",
                                            "M001",
                                            "", 
                                            "");

            if ("success".equals((String) loJSON.get("result"))){
                System.out.println("Image exists on the server.");    
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

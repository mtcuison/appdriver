
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.rmj.apprdiver.util.WebClient;

public class tesAccessToken {
    public static void main(String [] args){
        try {
            JSONObject loJSON = WebClient.RequestClientToken("IntegSys", "GGC_BM001", "GAP0190004");
            JSONParser loParser = new JSONParser();
            String lsToken = "";
        
            if (loJSON.get("result").equals("success")){
                loJSON = (JSONObject) loParser.parse(loJSON.get("payload").toString());
                lsToken = (String) loJSON.get("token");             
                
                if (!lsToken.isEmpty()){
                    //todo: get access token
                } else {
                    System.err.println("Client Token is empty.");
                    System.exit(1);
                }
            } else {
                loJSON = (JSONObject) loParser.parse(loJSON.get("error").toString());
                System.err.println(loJSON.get("message") + " " +loJSON.get("code"));
                System.exit(1);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        
        
        
        //lsClientToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJndWFuem9uZ3JvdXAuY29tLnBoIiwiYXVkIjoiR3VhbnpvbiBNZXJjaGFuZGlzaW5nIENvcnAuIChNYWluIE9mZmljZSkiLCJpYXQiOiIxMTEwNTU2ODAwIiwibmJmIjoiMTYxMTMxNDI1NyIsImV4cCI6bnVsbCwiZGF0YSI6ImI5NWJjZjg2YzRlNjJiZmNmNGYzODcyZjljNDI3ZTU0NWU1NWI3ZDFmZWE4NTMzNGVhZmFlZTA5YjI5ZTViMWJiNzViMjkzMWQ1ZWNlZTMxYWIyMzYwYjFhMGFkYTU4NzU2NjQxMTQ2N2Q2YjJiMjMyODUyNGM3ZjdiMDRmNzNkM2M3YWQ0ZTY5YmJmY2EzMjE2ZTk3NDgxY2NmZWUzZWRhNzFhZWQwMTkyNjQ4OWY4ZjIxMjBiZmE2M2U0NTdjNiJ9.NLFt3DPSLRTm8SV295Kmol8sng32mKdDr8L-FK6LNTs";
        
        String lsClientToken = WebClient.RequestAccessToken("").toJSONString();
        //lsClientToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJndWFuem9uZ3JvdXAuY29tLnBoIiwiYXVkIjoiR3VhbnpvbiBNZXJjaGFuZGlzaW5nIENvcnAuIChNYWluIE9mZmljZSkiLCJpYXQiOiIxMTEwNTU2ODAwIiwibmJmIjoiMTYxMTMxNDQwOSIsImV4cCI6IjE2MTEzMTYyMDkiLCJkYXRhIjoiYjk1YmNmODZjNGU2MmJmY2Y0ZjM4NzJmOWM0MjdlNTQ1ZTU1YjdkMWZlYTg1MzM0ZWFmYWVlMDliMjllNWIxYmI3NWIyOTMxZDVlY2VlMzFhYjIzNjBiMWEwYWRhNTg3NTY2NDExNDY3ZDZiMmIyMzI4NTI0YzdmN2IwNGY3M2RiM2YzZWViZTIxYmNkYzgzMmRlOGYzMmVmMjc5MzMxYWYyMDk4NzhkZDhmNGZkNTJjZTBkYmFjODgwN2U1ZTVkMDYyYjE2MDg4ODk4NDU3NjhkNDUzZDU3ZWIzMzIwMzBmMDJjMjhkNWE2NWY1ODAxYmIxMzU1YzA0MmQ1ZDAzYyJ9.KSFEw1k9l9G3Arty0YrgYPLk2S66h5hMEzDWXOvIEw0";
        System.out.println(lsClientToken);
    }
}

package service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class NaverApiService {
    public static String clientId = "AULXiK7lYaMrVt8ezGM0";
    public static String clientSecret = "Gg80r4O1R5";


    public static String searchReturnJson(String searchtext){

        try {
            String text = URLEncoder.encode(searchtext,"UTF-8");
            String apiUrl = "https://openapi.naver.com/v1/search/blog?query="+ text;

            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientId);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = conn.getResponseCode();
            BufferedReader br;


            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();



            return response.toString();

        } catch (Exception e){
            System.out.println(e);
            return "Error";
        }

    }



    public static String translate(String source, String origin, String trans){

        try {
            String text = URLEncoder.encode(trans, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/language/translate";

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request

            /*String postParams = "source=ko&target=ja&text=" + text;*/

            String postParams = "source=" + source
                    + "&target=" + origin + "&text= " + text;

            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br2;

            if (responseCode == 200) { // 정상 호출
                br2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br2 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br2.readLine()) != null) {
                response.append(inputLine);
            }
            br2.close();

            JSONObject obj = new JSONObject(response.toString());

            String res = obj.getJSONObject("message")
                    .getJSONObject("result")
                    .getString("translatedText");

            return res;

        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }

    }
}

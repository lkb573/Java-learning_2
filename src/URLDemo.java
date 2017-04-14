

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class URLDemo {
    public static void main(String[] args){

        List<String> list = new ArrayList<>();

        String developId = "AULXiK7lYaMrVt8ezGM0";
        String developPw = "Gg80r4O1R5";

        try {
            String text = URLEncoder.encode("그린팩토리","UTF-8");
            String apiUrl = "https://openapi.naver.com/v1/search/blog?query="+ text;

            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", developId);
            conn.setRequestProperty("X-Naver-Client-Secret", developPw);
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
            System.out.println(response.toString());

        } catch (Exception e){
            System.out.println(e);
        }



    }
}
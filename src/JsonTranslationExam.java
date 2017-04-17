import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class JsonTranslationExam {
    public static void main(String[] args){

        String clientId = "AULXiK7lYaMrVt8ezGM0";
        String clientSecret = "Gg80r4O1R5";


        try {

            BufferedReader br = new BufferedReader(new FileReader("src\\closer.txt"));

            String line = "";

            while ((line = br.readLine()) != null) {

                if(line.equals(""))
                    continue;

                    try {
                        String text = URLEncoder.encode(line, "UTF-8");
                        String apiURL = "https://openapi.naver.com/v1/language/translate";

                        URL url = new URL(apiURL);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("X-Naver-Client-Id", clientId);
                        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                        // post request

                        String postParams = "source=en&target=ko&text=" + text;

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


                        String jsonData = response.toString();

                        JSONObject obj = new JSONObject(jsonData);


                        String res = obj.getJSONObject("message")
                                .getJSONObject("result")
                                .getString("translatedText");

                        System.out.println(res);

                    } catch (Exception je) {
                        System.out.println(je);
                    }

                }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }




    }
}
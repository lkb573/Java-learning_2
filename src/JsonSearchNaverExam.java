import service.NaverApiService;
import service.NaverSearchApiService;

import java.util.List;
import java.util.Scanner;

public class JsonSearchNaverExam {
    public static void main(String[] args){

        System.out.print("Input String :");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine().trim();

        String jsonlink = NaverSearchApiService.searchReturnJson(search);

        List<String> json = NaverSearchApiService.searchReturnIOOJson(search, 100);

        int count = 1;
        for (String e : json){
            System.out.printf("> %d blogName = " + e + "\n", count);
            count++;
        }

        System.out.println("bloglink : "+jsonlink);


    }
}

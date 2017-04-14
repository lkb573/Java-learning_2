package url;

import java.util.List;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class UrlServiceImpl implements UrlService{

    @Override
    public int countOfchar(List<String> list) {
        int sum =0;

        for (String e : list){

            sum += e.length();

        }

        return sum;
    }
}

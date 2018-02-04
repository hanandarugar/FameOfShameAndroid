package com.kaamchor.android.fameofshame;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * Created by hanandarugar on 04/02/18.
 */

public class CheersNotification {
    private List<String> remarks = Arrays.asList("gjjbrehdfjnx","wefusdgzxjh","trefdsgixz","esdfghfcg");

    private int index;

    public String getRemarks(){
        index = randomNum();
        return remarks.get(index);
    }

    public int randomNum(){
        int max = remarks.size();
        Random random = new Random();
        int number = random.nextInt(max);
        return number;
    }
}


package com.kaamchor.android.fameofshame;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * Created by hanandarugar on 04/02/18.
 */

public class CheersNotification {
    private List<String> remarks = Arrays.asList("First they ignore you… Then they laugh and fight against you… Then you win. I knew you could do it.\n",
            "Your dedication, enthusiasm and insight are really inspiring.\n",
            "Everyone wants success, but it only follows those who make a true approach to get it.\n",
            "The aim of the life lies in pushing your limitation always; you have successfully made this thing possible your achievement is the result of your efforts.\n",
            "With knowledge in your hands and an open heart, you were sure to find success in your efforts.\n",
            "Some are dreamers, some are talented. You are both. May you achieve more success ahead.\n",
            "After a good time of hard work, commitment and diligence. It is time to rest. Congratulations\n",
            "Congratulations for adding yet another feather to your coveted cap.\n",
            "It is inspirational to witness hard work finally give birth to success.\n",
            "We are taught that the sky should always be the limit, but you have showed us that truly we should think beyond the sky.\n");

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


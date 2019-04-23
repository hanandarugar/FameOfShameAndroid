package com.kaamchor.android.fameofshame;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * Created by hanandarugar on 04/02/18.
 */

public class ShameNotification {
    private List<String> remarks = Arrays.asList("Everyone has the right to be stupid, but you are abusing the privilege\n",
            "There are two kinds of people in this world. People who get shit done. Then there's you.\n",
            "Somewhere out there, there is a tree endlessly producing oxygen so that  you can breathe. I think you owe it an apology.\n",
            "I see your IQ test came back negative.\n",
            "I'm sorry for hurting your feelings, but I thought you already knew you were stupid.\n",
            "Are you always this stupid or is today a special occasion?\n",
            "Wow you are even dumber than you look.\n",
            "Were you dropped on your head as a kid?\n",
            "This is a whole new level of moronic, even for you.\n",
            "I would love to insult you, but I'm afraid I wont do as well as nature did.\n",
            "Today I saw something that reminded my of you. But don't worry, I flushed it and everything went back to normal.\n",
            "Be yourself...somewhere else.\n");

    private int index;

    public String getRemarks (){
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

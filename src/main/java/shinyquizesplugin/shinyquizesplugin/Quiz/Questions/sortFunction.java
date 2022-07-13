package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import java.util.List;

public class sortFunction {
    public static <T> void sort(List<T> words){
        for(int i = 0; i<words.size()-1; i++) {
            for (int j = i+1; j<words.size(); j++) {
                if(words.get(i).toString().compareTo(words.get(j).toString())>0) {
                    T temp = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, temp);
                }
            }
        }
    }
}

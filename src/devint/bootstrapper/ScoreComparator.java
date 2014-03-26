package devint.bootstrapper;

import java.util.Comparator;
import java.util.Map;

public class ScoreComparator implements Comparator<String> {
    private Map<String, Integer> scores;//pour garder une copie du Map que l'on souhaite traiter

    public ScoreComparator( Map<String, Integer> scores){
        this.scores = scores; //stocker la copie pour qu'elle soit accessible dans compare()
    }

    @Override
    public int compare(String o1, String o2) {
        return scores.get(o2) - scores.get(o1);
    }
}
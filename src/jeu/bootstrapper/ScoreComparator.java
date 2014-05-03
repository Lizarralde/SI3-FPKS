package jeu.bootstrapper;

import jeu.KeysKeeper;
import jeu.config.Config;
import jeu.model.Themes;
import jeu.utils.ConfigXML;
import sun.management.resources.agent_ko;

import java.util.*;

public class ScoreComparator implements Comparator<String> {
    private Map<String, Integer> scores;

    public static Map<Themes, LinkedHashMap<String, Integer>> result = new HashMap<>();
    public static Map<Themes, List<String>> sortedNN = new HashMap<>();

    public ScoreComparator( Map<String, Integer> scores){
        this.scores = scores;
    }

    @Override
    public int compare(String o1, String o2) {
        return scores.get(o2) - scores.get(o1);
    }

    public static Map<Themes, LinkedHashMap<String, Integer>> getSortedScores(Map<String, Map<Themes, Integer>> scores) {

        for(String keyNN : scores.keySet()) {
            for(Themes t : scores.get(keyNN).keySet()) {
                LinkedHashMap tmp = result.get(t);
                if(tmp == null) tmp = new LinkedHashMap<String, Integer>();
                tmp.put(keyNN, scores.get(keyNN).get(t));
                result.put(t,tmp );
            }
        }

        for(Themes t : result.keySet()) {
            List<String> keysNickNames = new ArrayList<String>(result.get(t).keySet());
            Collections.sort(keysNickNames, new ScoreComparator(result.get(t)));
            sortedNN.put(t, keysNickNames);
       }

        return result;
    }



}
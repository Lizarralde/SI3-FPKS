package devint.bootstrapper;

import devint.KeysKeeper;
import devint.utils.ConfigXML;

import java.security.Key;
import java.util.*;

/**
 * Created by user on 26/03/14.
 */
public class BootStrapperModel {

    private Map<String, Object> profilesDescription = new HashMap<String, Object>();

    private LinkedHashMap<String, String> highscores = new LinkedHashMap<String, String>();

    public BootStrapperModel() {

    }

    public void loadProfiles() {
        profilesDescription = (Map<String, Object>)ConfigXML.load(KeysKeeper.PATH_PROFILES_FILE,
                KeysKeeper.PATH_PROFILES_VERSION);
    }

    public void buildHighScores() {
        if(profilesDescription == null || profilesDescription.size() == 0) {
            return;
        }

        List<String> keysOrdered = new ArrayList<String>();
        
    }
}

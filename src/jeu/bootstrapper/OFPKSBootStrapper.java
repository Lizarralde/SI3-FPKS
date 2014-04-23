package jeu.bootstrapper;

/**
 * Created by user on 26/03/14.
 */
public class OFPKSBootStrapper {
    private BootStrapperView bootStrapperView;
    private BootStrapperModel bootStrapperModel;

    public OFPKSBootStrapper() {
        bootStrapperModel = new BootStrapperModel();
        //bootStrapperView = new BootStrapperView();

        //bootStrapperView.showHighScoresView(bootStrapperModel.getHighscores());
        //TODO DELETE THIS TOKEN
       // bootStrapperModel.storeDefaultProfiles();
    }
}
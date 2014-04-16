package devint.bootstrapper;

/**
 * Created by user on 26/03/14.
 */
public class FPKSBootStrapper {
    private BootStrapperView bootStrapperView;
    private BootStrapperModel bootStrapperModel;

    public FPKSBootStrapper() {
        bootStrapperModel = new BootStrapperModel();
        //bootStrapperView = new BootStrapperView();

        //bootStrapperView.showHighScoresView(bootStrapperModel.getHighscores());
        //TODO DELETE THIS TOKEN
       // bootStrapperModel.storeDefaultProfiles();
    }


    public static void main(String[] arg) {
        new FPKSBootStrapper();
    }
}
package devint.bootstrapper;

/**
 * Created by user on 26/03/14.
 */
public class FPKSBootStrapper {
    private BootStrapperView bootStrapperView;
    private BootStrapperModel bootStrapperModel;

    public FPKSBootStrapper() {
        bootStrapperModel = new BootStrapperModel();
        bootStrapperView = new BootStrapperView();
    }

    public void loadProfiles() {
        bootStrapperModel.loadProfiles();
    }
}
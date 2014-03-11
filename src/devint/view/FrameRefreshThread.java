package devint.view;

import java.awt.*;

public class FrameRefreshThread implements Runnable {
    private Component toRefresh;
    private Long frameDelta;

    public FrameRefreshThread(Component toRefresh, Long frameDelta){
        this.toRefresh = toRefresh;
        this.frameDelta = frameDelta;
    }
    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(frameDelta);
                toRefresh.repaint();
            }
        } catch(Exception e){
            return;
        }
    }
}

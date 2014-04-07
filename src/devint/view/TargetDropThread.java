package devint.view;

import java.awt.*;
import java.util.List;

public class TargetDropThread implements Runnable {
    private List<GameObjectView> gameObjects;
    private Integer frameTime;
    private Dimension frameSize;

    private Integer answerTime;

    public TargetDropThread(List<GameObjectView> gameObjects, Integer frameTime, Dimension frameSize){
        this.answerTime = 10000;
        this.frameTime = frameTime;
        this.frameSize = frameSize;
        this.gameObjects = gameObjects;
    }
    @Override
    public void run() {
        try{
            Double fallSpeed = 0.;
            Integer frames = 0;
            while(fallSpeed < 1){
                this.frameTime+=1;
                frames = this.answerTime / this.frameTime;
                fallSpeed = 1.0 * frameSize.height / frames;
            }
            System.out.println("TargetDropThread starting to drop");
            while(true){
                Thread.sleep(this.frameTime);
                for(GameObjectView gov : this.gameObjects){
                    if(gov instanceof TargetObjectView){
                        gov.setMovement(new Dimension(0, (int) Math.round(fallSpeed)));
                    }
                }
            }
        } catch(InterruptedException e){
            System.out.println("TargetDropThread interuupted and closing");
        }
    }
}

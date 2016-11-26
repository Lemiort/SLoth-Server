package ru.etu.sapr.game;

/**
 * Created by Red on 20.11.2016.
 */
public class GameServer implements Runnable {
    private SimpleCube simpleCube;

    public GameServer()
    {
        simpleCube = new SimpleCube();
    }

    public SimpleCube getSimpleCube(){
        return  simpleCube;
    }

    protected  void  setSimpleCube(SimpleCube cube){
        simpleCube = cube;
    }

    public void run()
    {

        int cycleCounter =0;
        while (true)
        {
            //System.out.println("GameServer cycle №"+cycleCounter);
            simpleCube.getTransformation().position.y = (float) Math.cos(cycleCounter);
            cycleCounter++;
            try {
                Thread.sleep(33);
            }
           catch (InterruptedException ex)
           {

           }
        }
    }
}

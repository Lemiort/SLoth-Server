package ru.etu.sapr;

/**
 * Created by Red on 20.11.2016.
 */
public class Game implements Runnable {
    private SimpleCube simpleCube;

    public  Game()
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
            //System.out.println("Game cycle №"+cycleCounter);
            simpleCube.transformation.position.y = (float) Math.cos(cycleCounter);
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

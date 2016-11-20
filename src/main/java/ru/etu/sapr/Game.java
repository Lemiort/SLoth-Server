package ru.etu.sapr;

/**
 * Created by Red on 20.11.2016.
 */
public class Game implements Runnable {
    public void run()
    {
        int cycleCounter =0;
        while (true)
        {
            System.out.println("Game cycle №"+cycleCounter);
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

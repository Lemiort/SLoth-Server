package ru.etu.sapr.game;

import org.json.simple.JSONObject;
import ru.etu.sapr.net.JsonContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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

    public void setSimpleCube(SimpleCube cube) {
        simpleCube = cube;
    }

    private HashMap<Long,SimpleCube> simpleCubeHashMap;


    private ArrayList<JsonContainer> transactions;
    private ArrayList<JsonContainer> queue;

    public void AddToQueue(JsonContainer jsonContainer)
    {
        queue.add(jsonContainer);
    }

    public int GetCurrentTransactionNum()
    {
        return transactions.size();
    }

    public JsonContainer GetTransaction(int index){
        System.out.println("Getting obj:" + transactions.get(index).toJSONObject().toJSONString());
        return transactions.get(index);
    }

    public void run()
    {
        transactions = new ArrayList<JsonContainer>();
        queue = new ArrayList<JsonContainer>();
        simpleCubeHashMap = new HashMap<Long, SimpleCube>();
        int cycleCounter =0;
        while (true)
        {
            //System.out.println("GameServer cycle №"+cycleCounter);
            //simpleCube.getTransformation().position.y = (float) Math.cos(cycleCounter);
            cycleCounter++;
            try {
                Update();
                Thread.sleep(33);
            }
           catch (InterruptedException ex)
           {

           }
        }
    }

    private void Update()
    {
        for(int i=0; i < queue.size(); i++)
        {
            JsonContainer tempContainer = queue.get(i);
            switch (tempContainer.getContainerType())
            {
                case setPosition:
                    SimpleCube temp = (SimpleCube)tempContainer.getObject(0);
                    simpleCubeHashMap.put(temp.getInstanceID(),temp);

                    /*if(simpleCubeHashMap.get(temp.getInstanceID()).getInstanceID() == cube.getInstanceID())
                    {
                        cube = temp;
                    }
                    this.simpleCube = (SimpleCube)tempContainer.getObject(0);*/
                    this.transactions.add(tempContainer);
                    System.out.println("Added transaction "+ transactions.size());
                    System.out.println("It contains "+ tempContainer.toJSONObject().toJSONString());
                    break;
                default:
                    break;
            }
        }
        queue.clear();
    }
}

package ru.etu.sapr.game;

import ru.etu.sapr.net.JsonContainer;

/**
 * Created by Nikita on 26.11.2016.
 */
public class TransactionBuilder {

    private JsonContainer container;

    private Transaction result;

    TransactionBuilder(JsonContainer container)
    {
        this.container = container;
        result = null;
    }

    public Transaction BuildCreateTransaction(){
        this.result = new CreateTransaction();

        // TODO: как-то собираем его

        return this.result;
    }

    public Transaction BuildDeleteTransaction(){
        this.result = new DeleteTransaction();

        // TODO: как-то собираем его

        return this.result;
    }

    public Transaction BuildMoveTransaction(){
        this.result = new MoveTransaction();

        // TODO: как-то собираем его

        return this.result;
    }
}

package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.infraestructure.utils.RoundInfo;

import java.util.Objects;

public abstract class GameAction {

    private GameAction next;
    RoundInfo roundInfo;

    public GameAction(RoundInfo roundInfo) {
        this.roundInfo = roundInfo;
    }

    public void setNext(GameAction action) {
        this.next = action;
    }

    public abstract void execute();

    protected void executeNext() {
        if (Objects.isNull(next)) return;

        next.execute();
    }
}

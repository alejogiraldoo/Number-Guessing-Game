package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;

import java.util.Objects;

public abstract class GameAction {

    private GameAction next;
    PlayerInfoEntity playerInfo;

    public GameAction(PlayerInfoEntity playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void setNext(GameAction action ) {
        this.next = action;
    };

    public abstract void execute();

    protected void executeNext() {
        if(Objects.isNull(next)) return;

        next.execute();
    }
}

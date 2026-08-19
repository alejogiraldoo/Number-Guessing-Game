package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.entities.PlayerInfo;

import java.util.Objects;

public abstract class GameAction {

    private GameAction next;
    PlayerInfo playerInfo;

    public GameAction(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void setNext(GameAction action) {
        this.next = action;
    }

    ;

    public abstract void execute();

    protected void executeNext() {
        if (Objects.isNull(next)) return;

        next.execute();
    }
}

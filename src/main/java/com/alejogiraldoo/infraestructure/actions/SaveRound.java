package com.alejogiraldoo.infraestructure.actions;

import com.alejogiraldoo.domain.errors.CustomError;
import com.alejogiraldoo.infraestructure.services.RoundService;
import com.alejogiraldoo.infraestructure.utils.RoundInfo;

public class SaveRound extends GameAction {

    private final RoundService roundService;

    public SaveRound(
            RoundService roundService,
            RoundInfo roundInfo
    ) {
        super(roundInfo);
        this.roundService = roundService;
    }

    @Override
    public void execute() {
        try {
            this.roundService.saveRound(roundInfo);
            this.executeNext();
        } catch (CustomError e) {
            System.out.println(e.getMessage());
        }
    }
}

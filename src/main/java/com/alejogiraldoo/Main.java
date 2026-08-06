package com.alejogiraldoo;

import com.alejogiraldoo.domain.entities.PlayerInfoEntity;
import com.alejogiraldoo.presentation.GuessingGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerInfoEntity.Settings settings = new PlayerInfoEntity.Settings( 1, 100 );
        new GuessingGame( settings ).start();
    }
}
// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.mymonopoly.entity;

import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameTheme;

privileged aspect GameChance_Roo_JavaBean {
    
    public String GameChance.getMessage() {
        return this.message;
    }
    
    public void GameChance.setMessage(String message) {
        this.message = message;
    }
    
    public short GameChance.getStopTurn() {
        return this.stopTurn;
    }
    
    public void GameChance.setStopTurn(short stopTurn) {
        this.stopTurn = stopTurn;
    }
    
    public short GameChance.getMoneyDiff() {
        return this.moneyDiff;
    }
    
    public void GameChance.setMoneyDiff(short moneyDiff) {
        this.moneyDiff = moneyDiff;
    }
    
    public short GameChance.getMove() {
        return this.move;
    }
    
    public void GameChance.setMove(short move) {
        this.move = move;
    }
    
    public String GameChance.getMoveToClass() {
        return this.moveToClass;
    }
    
    public void GameChance.setMoveToClass(String moveToClass) {
        this.moveToClass = moveToClass;
    }
    
    public GameTheme GameChance.getTheme() {
        return this.theme;
    }
    
    public void GameChance.setTheme(GameTheme theme) {
        this.theme = theme;
    }
    
}

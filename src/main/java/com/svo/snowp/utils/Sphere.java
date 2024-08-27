package com.svo.snowp.utils;

public class Sphere {
    private final String displayName;
    private final String texture;
    private final double attackBoost;
    private final double healthReduction;
    private final double movementSpeedBoost;
    private final double defenseBoost;

    public Sphere(String displayName, String texture, double attackBoost, double healthReduction, double movementSpeedBoost, double defenseBoost) {
        this.displayName = displayName;
        this.texture = texture;
        this.attackBoost = attackBoost;
        this.healthReduction = healthReduction;
        this.movementSpeedBoost = movementSpeedBoost;
        this.defenseBoost = defenseBoost;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTexture() {
        return texture;
    }

    public double getAttackBoost() {
        return attackBoost;
    }

    public double getHealthReduction() {
        return healthReduction;
    }

    public double getMovementSpeedBoost() {
        return movementSpeedBoost;
    }

    public double getDefenseBoost() {
        return defenseBoost;
    }
}

package com.svo.snowp.utils;

public class Sphere {
    private final String name;
    private final String displayName;
    private final String textureId;
    private final double attackBoost;
    private final double healthReduction;
    private final double movementSpeedBoost;
    private final double defenseBoost;
    private final String description;

    public Sphere(String name, String displayName, String textureId, double attackBoost, double healthReduction, double movementSpeedBoost, double defenseBoost, String description) {
        this.name = name;
        this.displayName = displayName;
        this.textureId = textureId;
        this.attackBoost = attackBoost;
        this.healthReduction = healthReduction;
        this.movementSpeedBoost = movementSpeedBoost;
        this.defenseBoost = defenseBoost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTextureId() {
        return textureId;
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

    public String getDescription() {
        return description;
    }
}

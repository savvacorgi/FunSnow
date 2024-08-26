package com.svo.snowp;

public class Sphere {
    private String name;
    private String headName;
    private String ownerUUID;
    private double damage;
    private double health;
    private double movementSpeed;
    private String description;

    public Sphere(String name, String headName, String ownerUUID, double damage, double health, double movementSpeed, String description) {
        this.name = name;
        this.headName = headName;
        this.ownerUUID = ownerUUID;
        this.damage = damage;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getHeadName() {
        return headName;
    }

    public String getOwnerUUID() {
        return ownerUUID;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public String getDescription() {
        return description;
    }
}

package com.svo.snowp.utils;

public class Sphere {
    private String name;
    private String headName;
    private String id;
    private double damage;
    private double health;
    private double speed;
    private String description;

    public Sphere(String name, String headName, String id, double damage, double health, double speed, String description) {
        this.name = name;
        this.headName = headName;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.speed = speed;
        this.description = description;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadName() { return headName; }
    public void setHeadName(String headName) { this.headName = headName; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getDamage() { return damage; }
    public void setDamage(double damage) { this.damage = damage; }
    public double getHealth() { return health; }
    public void setHealth(double health) { this.health = health; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

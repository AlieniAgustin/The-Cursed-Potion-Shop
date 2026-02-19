package com.magic.academy.cursedpotions.model;

import java.util.Objects;

public class Potion {

    /** The unique identifier of the potion. */
    private Long id;
    /** The name of the potion. */
    private String name;
    /** The effect or description of what the potion does. */
    private String effect;
    /** The risk level of the potion, ranging from 1 to 100. */
    private int levelOfRisk;
    /** Indicates whether the potion is legal or not. */
    private boolean legal;

    public Potion () { }

    public Potion (String name, String effect, int levelOfRisk, boolean legal) {
        this.name = name;
        this.effect = effect;
        this.levelOfRisk = levelOfRisk;
        this.legal = legal;
    }

    public Potion (Long id, String name, String effect, int levelOfRisk, boolean legal) {
        this.id = id;
        this.name = name;
        this.effect = effect;
        this.levelOfRisk = levelOfRisk;
        this.legal = legal;
    }

    public String getName () { return name; }
    public Long getId () { return id; }
    public String getEffect () { return effect; }
    public int getLevelOfRisk () { return levelOfRisk; }
    public boolean isLegal () { return legal; }

    public void setName (String name) { this.name = name; }
    public void setId (Long id) { this.id = id; }
    public void setEffect (String effect) { this.effect = effect; }
    public void setLevelOfRisk (int levelOfRisk) { this.levelOfRisk = levelOfRisk; }
    public void setLegal (boolean legal) { this.legal = legal; }

    /**
     * Compares this potion with another object for equality based on all attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Potion potion = (Potion) o;
        return levelOfRisk == potion.getLevelOfRisk() && legal == potion.isLegal() && Objects.equals(name, potion.getName()) && Objects.equals(id, potion.getId()) && Objects.equals(effect, potion.getEffect());
    }

    /**
     * Generates a hash code based on all potion attributes.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id, effect, levelOfRisk, legal);
    }


    /**
     * Returns a string representation of the potion with its ID, name, and risk level.
     */
    @Override
    public String toString() {
        return "Potion{id=" + id + ", name='" + name + "', risk=" + levelOfRisk + "}";
    }

}
package com.example.ufit.model;

import java.util.Random;

public class Exercise implements Comparable<Exercise>
{
    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
    private String instructions;
    private int sets;


    public Exercise(String name, String type, String muscle, String equipment, String difficulty, String instructions) {
        this.name = name;
        this.type = type;
        this.muscle = muscle;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.instructions = instructions;
        this.sets = new Random().ints(3, 5).findFirst().getAsInt();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getMuscle()
    {
        return muscle;
    }

    public void setMuscle(String muscle)
    {
        this.muscle = muscle;
    }

    public String getEquipment()
    {
        return equipment;
    }

    public void setEquipment(String equipment)
    {
        this.equipment = equipment;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }

    public int getSets()
    {
        return this.sets;
    }

    public void setSets(int sets)
    {
        if(sets > 0)
            this.sets = sets;
    }

    public boolean hasName(String aName)
    {
        return this.name.equals(aName);
    }

    @Override
    public int compareTo(Exercise exercise)
    {
        return this.name.compareTo(exercise.name);
    }
}

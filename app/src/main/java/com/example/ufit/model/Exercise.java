package com.example.ufit.model;

public class Exercise implements Comparable<Exercise>
{
    private String name;
    private int sets;
    private String description;
    private String iconUrl;

    public Exercise(String name, int sets, String description, String iconUrl)
    {
        this.name = name;
        this.sets = sets;
        this.description = description;
        this.iconUrl = iconUrl;
    }

    public boolean hasName(String aName)
    {
        return this.name.equals(aName);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSets()
    {
        return sets;
    }

    public void setSets(int sets)
    {
        this.sets = sets;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    @Override
    public int compareTo(Exercise exercise)
    {
        return this.name.compareTo(exercise.name);
    }
}

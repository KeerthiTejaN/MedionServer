package com.starters.json;

public class Aspects {
	private String rating;

    private String type;

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rating = "+rating+", type = "+type+"]";
    }
}

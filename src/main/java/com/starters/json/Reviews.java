package com.starters.json;

public class Reviews {
	private String time;

    private String text;

    private String author_url;

    private String author_name;

    private Aspects[] aspects;

    private String rating;

    private String language;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getAuthor_url ()
    {
        return author_url;
    }

    public void setAuthor_url (String author_url)
    {
        this.author_url = author_url;
    }

    public String getAuthor_name ()
    {
        return author_name;
    }

    public void setAuthor_name (String author_name)
    {
        this.author_name = author_name;
    }

    public Aspects[] getAspects ()
    {
        return aspects;
    }

    public void setAspects (Aspects[] aspects)
    {
        this.aspects = aspects;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+", text = "+text+", author_url = "+author_url+", author_name = "+author_name+", aspects = "+aspects+", rating = "+rating+", language = "+language+"]";
    }


}

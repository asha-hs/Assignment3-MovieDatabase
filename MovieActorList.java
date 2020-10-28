package movies;

import java.util.ArrayList;

public class MovieActorList {
    // instance variables
    private String name;
    private ArrayList<String> actornames;
    
    // constructor
    public MovieActorList(String name) {
	this.name = name;
	actornames = new ArrayList<String>();
    }
    
    //getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getActornames() {
        return actornames;
    }
    public void setActornames(ArrayList<String> actornames) {
        this.actornames = actornames;
    }

}

package movies;
import java.util.*;
public class Movie {
    //instance variables
    private String name;
    private ArrayList<Actor> actors;
    private double rating;
    
    //constructor
    public Movie(String name) {
	this.name = name;
	this.actors = new ArrayList<Actor>();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Actor> getActors() {
        return actors;
    }
    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
    
    
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void addActor(Actor a) {
	actors.add(a);
    }
    
}

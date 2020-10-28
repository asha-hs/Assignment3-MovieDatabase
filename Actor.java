package movies;
import java.util.*;

public class Actor {
    
    //instance variables
    private String name;
    private ArrayList<Movie> movies;
    public String getName() {
        return name;
    }
    
    public Actor(String name) {
	this.name = name;
	this.movies = new ArrayList<Movie>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    

}

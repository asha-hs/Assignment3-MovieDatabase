package movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class MovieDatabase {
    private ArrayList<Movie> movieList;
    private ArrayList<Actor> actorList;

    private ArrayList<MovieActorList> movieActorList;

    public MovieDatabase() {
	movieList = new ArrayList<Movie>();
	actorList = new ArrayList<Actor>();
	movieActorList = new ArrayList<MovieActorList>();
    }

    public ArrayList<Movie> getMovieList() {
	return movieList;
    }

    public ArrayList<Actor> getActorList() {
	return actorList;
    }

    public void addMovie(String name, String[] actors) {

	boolean isNewMovie = true;
	boolean isNewActor;
	// check if the movie is already exists in movieList
	for(Movie m : movieList) {
	    if(name.equals(m.getName())) {
		isNewMovie = false;
		break;
	    }
	}
	// if its a new movie add to the movieList
	if(isNewMovie) {
	    Movie m = new Movie(name);

	    //check if all the actors exists in actorList
	    for(int i = 0; i < actors.length; i++) {
		// create an Actors object for each actors in actors list
		Actor a = new Actor(actors[i]);
		// add them to movie's actor list
		m.getActors().add(a);

		// If any of actor happen to be new , they will be added to the actorList
		isNewActor = true;
		for(Actor ac : actorList) {
		    if(actors[i].equals(ac.getName())) {
			isNewActor = false;
		    }
		}
		if(isNewActor) {
		    actorList.add(new Actor(actors[i]));

		}
	    }
	    movieList.add(m);
	}
    }

    public void addRating(String name, double rating) {

	for(Movie m : movieList) {
	    if(name.equals(m.getName())) {
		m.setRating(rating);
	    }
	}

    }

    public void updateRating(String name, double newRating) {

	for(Movie m : movieList) {
	    if(name.equals(m.getName())) {
		m.setRating(newRating);
	    }
	}
    }

    /**
     * get movies average rating
     * @return
     */
    public double getMovieAverageRating() {
	double sum = 0.0;
	for(Movie m : movieList) {
	    sum = sum + m.getRating();
	}
	double avg = sum / movieList.size();
	return avg;
    }
    /**
     * get best actor bases on average rating
     * @return
     */
    public String getBestActor() {
	double bestAvgRating = getMovieAverageRating();
	Movie bestRatedMovie = movieList.get(0);		

	for(Movie m : movieList) {

	    if(m.getRating() >= bestAvgRating) {
		bestAvgRating = m.getRating();
		bestRatedMovie = m;
	    }
	}

	return bestRatedMovie.getActors().get(0).getName();
	
    }

    /**
     * get the best movie based on rating
     * @return
     */
    public String getBestMovie() {
	double bestRating = 0.0;
	Movie bestRatedMovie = movieList.get(0);
	for(Movie m : movieList) {
	    if(m.getRating() > bestRating) {
		bestRating = m.getRating();
		bestRatedMovie = m;
	    }
	}

	return bestRatedMovie.getName();
    }
    
    /**
     * Parse Actor name Movie list to movie name actor list using ArrayList of 
    // MovieActorList objects
     * @param list - actor name and movie names
     */
    public void parse(String[] list) {

	boolean isNewMovie;
	for(int i = 1; i < list.length; i++) {
	    isNewMovie = true;
	    for(MovieActorList m : this.movieActorList) {
		if(list[i].equals(m.getName())) {
		    m.getActornames().add(list[0]);
		    isNewMovie = false;
		    break;
		}
	    }
	    if(isNewMovie) {
		MovieActorList mal = new MovieActorList(list[i]);
		mal.getActornames().add(list[0]);
		this.movieActorList.add(mal);
	    }

	}

    }
    public static void main(String args[]) {
	MovieDatabase movieDb = new MovieDatabase();

	// add the movies from movies.text to movie database
	File moviesFile = new File("src/movies/movies.txt");
	try {
	    Scanner sc = new Scanner(moviesFile);
	    while(sc.hasNextLine()) {

		String[] str = sc.nextLine().split(",");
		movieDb.parse(str);

	    }

	    for(MovieActorList m : movieDb.movieActorList) {
		String actnames[]=m.getActornames().toArray(new String[m.getActornames().size()]);
		movieDb.addMovie(m.getName(), actnames);
	    }


	    sc.close();
	}
	catch(FileNotFoundException e) {

	}

	// Add the ratings to each movie in database
	File ratingsFileoviesFile = new File("src/movies/ratings.txt");
	try {
	    System.out.println("rating...");
	    Scanner sc = new Scanner(ratingsFileoviesFile);;
	    String[] movieRatingStr;

	    while(sc.hasNextLine()) {

		movieRatingStr = sc.nextLine().split("\\t");

		for(Movie m : movieDb.movieList) {
		    if(movieRatingStr[0].equalsIgnoreCase(m.getName())) {

			m.setRating(Double.parseDouble(movieRatingStr[1]));
			System.out.println("matching movie "+m.getName()+" rating = "+m.getRating());

		    }
		}

	    }

	    sc.close();
	}
	catch(FileNotFoundException e) {

	}

	// print out movielist to test
	for(Movie m : movieDb.movieList) {
	    System.out.print(m.getName() +" -- ");
	    for(Actor a : m.getActors()) {
		System.out.print(a.getName() +" , ");
	    }
	    System.out.print("rating = "+m.getRating());
	    System.out.println();
	}

	// print best actor
	System.out.println("best Actor is "+ movieDb.getBestActor());
	// print best movie
	System.out.println("best movie is "+movieDb.getBestMovie());

    }
}

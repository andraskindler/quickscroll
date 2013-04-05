package com.andraskindler.quickscrollsample.model;

import java.util.ArrayList;
import java.util.List;

public class MarvelMovie {

	private String Title;
	private int year;

	public MarvelMovie(String title, int year) {
		super();
		Title = title;
		this.year = year;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getIndicator(){
		return Character.toString(Title.charAt(0)) + Character.toString(Character.toLowerCase(Title.charAt(0)));
	}

	public static List<MarvelMovie> getMovies(){
		final List<MarvelMovie> movies = new ArrayList<MarvelMovie>();
		
		movies.add(new MarvelMovie("Blade", 1998));
		movies.add(new MarvelMovie("Blade II", 2002));
		movies.add(new MarvelMovie("Blade: Trinity", 2004));
		movies.add(new MarvelMovie("Captain America: The First Avenger", 2011));
		movies.add(new MarvelMovie("Captain America: Winter Soldier", 2014));
		movies.add(new MarvelMovie("Daredevil", 2003));
		movies.add(new MarvelMovie("Doctor Strange", 2007));
		movies.add(new MarvelMovie("Electra", 2005));
		movies.add(new MarvelMovie("Fantastic Four", 2005));
		movies.add(new MarvelMovie("Fantastic Four: Rise of the Silver Surfer", 2007));
		movies.add(new MarvelMovie("Ghost Rider", 2007));
		movies.add(new MarvelMovie("Ghost Rider: Spirit of Vengeance", 2012));
		movies.add(new MarvelMovie("Howard The Duck", 1986));
		movies.add(new MarvelMovie("Hulk", 2003));
		movies.add(new MarvelMovie("Hulk Vs.", 2009));
		movies.add(new MarvelMovie("Iron Man", 2008));
		movies.add(new MarvelMovie("Iron Man 2", 2010));
		movies.add(new MarvelMovie("Iron Man 3", 2013));
		movies.add(new MarvelMovie("Man-Thing", 2005));
		movies.add(new MarvelMovie("Marvel's Iron Man And Hulk: Heroes United", 2013));
		movies.add(new MarvelMovie("Marvel's The Avengers", 2012));
		movies.add(new MarvelMovie("Planet Hulk", 2010));
		movies.add(new MarvelMovie("Punisher", 2004));
		movies.add(new MarvelMovie("Punisher: War Zone", 2008));
		movies.add(new MarvelMovie("Spider-Man", 2002));
		movies.add(new MarvelMovie("Spider-Man 2", 2004));
		movies.add(new MarvelMovie("Spider-Man 3", 2007));
		movies.add(new MarvelMovie("The Amazing Spider-Man", 2012));
		movies.add(new MarvelMovie("The Incredible Hulk", 2008));
		movies.add(new MarvelMovie("The Wolverine", 2013));
		movies.add(new MarvelMovie("Thor", 2011));
		movies.add(new MarvelMovie("Thor: The Dark World", 2013));
		movies.add(new MarvelMovie("X-Men", 2000));
		movies.add(new MarvelMovie("X-Men Origins: Wolverine", 2009));
		movies.add(new MarvelMovie("X-Men: First Class", 2011));
		movies.add(new MarvelMovie("X2: X-Men United", 2003));
		movies.add(new MarvelMovie("X3: The Last Stand", 2006));
		
		return movies;
	}
	
}

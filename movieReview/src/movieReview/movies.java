//Package for the class.
package movieReview;

//Libraries imported
import java.util.*;

//Class 'movies{}' to store Movie attributes.
class movies
{
	//Class member data and objects.
	protected String name;	//Movie name.
	protected String genre;	//Movie genre.
	protected int releaseYear;	//Release year of a movie.
	protected int id;	//Movie Id.
	protected LinkedList<movies> movieList;	//LinkedList to track all movies.

	//Public member Methods of the class to implement class functionalities.
    //Default Constructor.
    public movies()
    {
        name=null;
        genre=null;
        releaseYear=-1;
        id=-1;
        movieList=null;
    }

    //Method to take Input from console.
    public void addMovie(String name,String genere, int year)
    {
        int check=0;
        if(this.movieList==null)
        	movieList=new LinkedList<movies>();

        movies object=new movies();
       
        object.name=name;
        object.genre=genere;
            
            
                
        //try{} block for handle exceptions.
        try {
            int valid=year;
            if(valid<1990 || valid>2021)
            {
                throw new Exception();
            }   
            object.releaseYear=valid;
                    
            } catch (NumberFormatException e) {
                    System.out.println("Invalid year");
            }catch(Exception e)
            {
                System.out.println("Year should be in between 1990 - 2021.");
            }
            
            object.id=movieList.size()+1;
            this.movieList.add(object);
            
            System.out.println("\nMovie details successfully entered.\n\n"); 
      
    }

    //Method to Print output on the Console.
    public void moviePrint()
    {
    	//for() to iterate through List of Movies.
        for(movies temp : this.movieList)
        {
            System.out.println("\n\nMovie Data : ");
            System.out.print("\nId : "+temp.id);
            System.out.print("\nName : "+temp.name);
            System.out.print("\nGenre : "+temp.genre);
            System.out.print("\nRelease Year : "+temp.releaseYear);
        }
    }
}// end of the class.
    
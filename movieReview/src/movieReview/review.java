//Package for the class.
package movieReview;

//Libraries imported
import java.util.*;

//Class 'users{}' to store Review' attributes.
public class review implements Comparator<review> //Implementation of the interface 'Comparator'.
{
	private int score;	//Review score.
	private int uId;	//User Id.
	private int mId;	//Movie Id.
	private int rYear;	//Movie releasing Year.
	private String uName;	//User Name.
	private String mName;	//Movie Name.
	private String gName;	//Genre Name.
	private movies movieR;	//Object of class 'movies'.
	private users userR;	//Object of the class 'users.
	private HashMap <String, List<review>> scoreCritic;	//HashMap<> to track all reviews by critic order by a user.
	private LinkedList<review> reviewList;	//LinkedList to track all reviews.
	
	//Public member Methods of the class to implement class functionalities.
	//Review class Constructor.
	public review()
	{
		score=-1;
		uId=-1;
		mId=-1;
		rYear=-1;
		movieR=new movies();
		userR=new users();
		scoreCritic= new HashMap<String,List<review>>();
		reviewList=null;
	}
	
	//Abstract method Compare<>() to sort 'review' class object.
	public int compare(review one, review two)
	{
		return one.score - two.score;
	}
	
	//Method to add Review.
	public void addReview(String username, String moviename,int sr)
	{
		//Instantiation of class references.
		if(this.reviewList==null)
			this.reviewList=new LinkedList<review>();
		
			review object=new review();
			
			String uName=username;
			
			//for{} loop to check a user is exist or not.
			for(users temp : this.userR.userList)
			{
				if(temp.name.equalsIgnoreCase(uName))
				{
					object.uId=temp.id;
					object.uName=temp.name;
				}
			}
			
			//Return if user not exist.
			if(object.uId==-1)
			{
				System.out.println("User not exist.\n\n");
				return;
			}
			
			String mName=moviename;
			
			//for{} loop to check a movie is exist or not.
			for(movies temp1 : this.movieR.movieList)
			{
				if(temp1.name.equalsIgnoreCase(mName))
				{
					
					if(temp1.releaseYear>2020)
					{
						System.out.println("Movie not released.\n\n");
						return;
					}
					object.mId=temp1.id;
					object.mName=temp1.name;
					object.rYear=temp1.releaseYear;
					object.gName=temp1.genre;
				}
				
			}
			
			//Return if duplicate movie is trying to review by same user.
			if(checkDuplicate(uName,mName)==false)
			{
				return;
			}
			
			//Return if movie not exist.
			if(object.mId==-1)
			{
				System.out.println("Movie not exist.\n\n");
				return;
			}
			
			//while() block to validate a review score.
			while(true)
			{
				try {
					int score=sr;
					if(score<0 || score>10)
					{
						throw new Exception();
					}
					object.score=score;
					break;
					}catch(Exception e){
						System.out.println("Score should be between 0 to 10.");
					}
			}
			
			//Block to check for a user is upgraded to critic or not.
			for(users temp : this.userR.userList)
			{
				if(temp.id == object.uId)
				{
					if(temp.isCritic())
					{
						object.score *= 2;
						if (!scoreCritic.containsKey(object.gName)) {
							this.scoreCritic.put(object.gName, new ArrayList<review>());
	
						}
						this.scoreCritic.get(object.gName).add(object);
					}
					temp.setNor();
				}
			}
			
			//Add valid review to the LinkedList.
			this.reviewList.add(object);
			
			System.out.println("\nReview successfully recorded.\n\n"); 
			
	}
	
	
	//Method to Check Duplicate Review.
	public boolean checkDuplicate(String userName, String movieName)
	{
		for(review temp : this.reviewList)
		{
			if(temp.uName.equalsIgnoreCase(userName) && temp.mName.equalsIgnoreCase(movieName))
			{
				System.out.println("User can review a movie once.\n\n");
				return false;
			}
		}
		return true;
	}
	
	//method to calculate Average Score by Year.
	public void calAveraveYear(int year)
	{
		//Variables local to the method.
		int valid,count=0,sum=0;
		double averageYear=0;
		boolean flag=false;
		
		while (true) {
            System.out.print("\nEnter Year of Release(1990 - 2021) : ");
            try {
                valid=year;
                if(valid<1990 || valid>2021)
                {
                	throw new Exception();
                }
                    
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year");
            }catch(Exception e)
            {
                System.out.println("\n\nYear should be in between 1990 - 2021.\n");
            }
		}
		for(review temp : this.reviewList)
		{
			if(temp.rYear==valid && temp.score!=-1)
			{
				sum+=temp.score;
				count++;
				flag=true;
			}
		}
		if(flag)
		{
			averageYear=(double)sum/count;
			System.out.println("\n\nAverage Review Score in "+valid+" : "+averageYear+"\n\n");
		}
		else
		{
			System.out.println("\n\nYThere is no movie in the Year : "+valid+"\n\n");
		}
		
	}
	
	//method to calculate Average Score by Year.
	public void calAveraveMovie(String movie)
	{
		int count=0,sum=0;
		double averageMovie=0;
		boolean flag=false;
		String valid;
		valid=movie;
		
		for(review temp : this.reviewList)
		{
			if(temp.mName.equalsIgnoreCase(valid) && temp.score!=-1)
			{
				sum+=temp.score;
				count++;
				flag=true;
			}
		}
		if(flag)
		{
			averageMovie=(double)sum/count;
			System.out.println("\n\nAverage Review Score of movie, "+valid+" : "+averageMovie+"\n\n");
		}
		else
		{
			System.out.println("\n\nThere is no movie by the name, : "+valid+"\n\n");
		}
	}
	//Method to Display N movies by Critics in a particular genre.
	public void printMovieGenre(int nom, String gnm)
	{
		
		String genereName;
		
			
		if(nom<=0)
		{
			System.out.println("Negative or Zero is Invalid.\n\n");
			return;
		}
		if(nom>this.reviewList.size())
		{
			System.out.println("Number is greater than moview recorded.\n\n");
			return;
		}
		
		genereName=gnm;
		if(this.scoreCritic.containsKey(genereName))
		{
			Collections.sort(scoreCritic.get(genereName), new review());
			System.out.print("Top "+nom+" movies by Genre : ");
			for(review tm : scoreCritic.get(genereName))
			{
				System.out.print(tm.mName+" ");
			}
			System.out.println("\n\n");
		}
		else
		{
			System.out.println("No movie revied by a critic of the genre, "+genereName+"\n\n");
		}
		
		
	}
	
	//method for Display
	public void printReview()
	{
		for(review temp : this.reviewList)
		{
			System.out.println("User Name : "+temp.uName);
			System.out.println("Movie Name : "+temp.mName);
			System.out.println("Score : "+temp.score);
		}
	}
	
	//Main Method.
	public static void main(String []args)
	{
		
		
		review object=new review();

        object.movieR.addMovie("Don","Action & Comedy",2006);
        object.movieR.addMovie("Tiger","Drama",2008);
        object.movieR.addMovie("Padmavaat","Comedy",2006);
        object.movieR.addMovie("Lunchbox","Drama",2021);
        object.movieR.addMovie("Guru","Drama",2006);
        object.movieR.addMovie("metro","Romance",2006);


        object.userR.addUser("SRK");
        object.userR.addUser("Salman");
        object.userR.addUser("Deepika");

        
        object.addReview("SRK","Don",2);
        object.addReview("SRK","Padmavaat",8);
        object.addReview("Salman", "Don", 5);
        object.addReview("Deepika","Don",9);
        object.addReview("Deepika","Guru" ,6);
        object.addReview("SRK","Don", 10);
        object.addReview("Deepika","Lunchbox",5);
        object.addReview("SRK", "Tiger", 5);
        object.addReview("SRK", "Metro", 7);

	}//end of main.
}//end of class.
//Package for the class.
package movieReview;

//Libraries imported
import java.util.*;

//Class 'users{}' to store Users' attributes.
public class users
{
	//Class member data and objects.
    protected String name; //User Name.
    protected int id;	//Users' Id.
    protected int nor;	//Number of reviews by a user.
    boolean critic;	//To update user to critic.
    protected LinkedList<users> userList; //LinkedList to track all Users.

    ////Public member Methods of the class to implement class functionalities.
    //Default Constructor.
    public users()
    {
    	name=null;
        id=-1;
        nor=0;
        critic=false;
        userList=null;
        
    }

  //Method to take Input from console.
    void addUser(String name)
    {
    	if(userList==null)
        	userList=new LinkedList<users>();
        users object=new users();

        object.name=name;  
        object.id=userList.size()+1;
        this.userList.add(object);
        
         System.out.println("User successfully created.\n\n");  
    }

    //Method to track number of reviews by a user.
    public void setNor()
    {
    	this.nor++;
    	if(nor>=3)
    	{
    		this.critic=true;
    		System.out.println("\n\nThe user, "+this.name+" has published  3 reviews, is now promoted to 'Critic'.\n\n\n");
    	}
    }
    
    //Method to check for a 'user' to upgrade to 'critic'.
    public boolean isCritic()
    {
    	if(this.critic==true)
    		return true;
    	else
    		return false;
    }
}// end of class.
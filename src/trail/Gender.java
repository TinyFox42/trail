package trail;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import com.google.gson.*;
import java.util.Scanner;

public class Gender {
	/* pretty much, for relationship purposes (who will data who), there are 2 variables. Note that I'm not saying there are only 4 gender, more of that in my observation there are 4 gender groups
	 * 	So there are the 2 classic genders, male and female, who have _as_male and _as_female, respectively, set to true
	 * 	Then there is "classic" nonbinary, with they/them pronouns and in the middle of the male-female gender spectrum. They can be dated by people attracted to men or women without a change in labels, so they have both set to true
	 * 	Then there are the aligned nonbinary genders, where they lean more to male or female, and importantly for this identify as not the one they lean away from. They would have the flag for the gender they lean towards set to true
	 *  But really, it works in this way:
	 *  	If _as_male is set to true, people attracted to men would flirt with you. For men and masculine in nature genders
	 *  	If _as_female is set to true, people attracted to women would flirt with you. For women and feminine in nature genders
	 *  	If both are set to true, everyone who is attracted to people would flirt with you. For the middle nonbinaries
	 *  	If both are set to false, no one will flirt with you, but note that this is more of a degenerate state of the 2 variable system than a real thing this is representing
	 *  And I'm just going to say here that if I need to, I can switch this to a list of tags, which would allow for an arbitrary number of dating groups
	 *  Maybe a better way to do this would be to have a bunch of base identities ("straight man", "lesbian", etc.) and have each of those point to other ones as valid dateables, and just ignore the fact that you can be attracted to someone who
	 *  	by orientation can't be attracted to you. It would make this a lot more inclusive.
	 *  Ok, I'm making that .json file, it's a list of genders+orientations but I'm just calling it genders. The elements of each "gender" is:
	 *  	name: Lable for the gender
	 *  	id: Unique number for other stuff to refer to it
	 *  	pronouns: An array of Strings that are the pronouns, in the same order they are listed below. Sorry, for now it's just one set of pronouns, I'll definitely put it in later (I use multiple pronoun sets)
	 *  	dateables: An array of IDs, referencing only previously created genders. It will be turned into an arraylist, and whenever a future one is dateable to a past one, the past one will add that gender to its list. It can point back to itself
	 *  There is a very good chance I will separate the pronouns and gender+orientation systems.
	 *  Ok, if we separate pronouns and the gender+orientation system, we can cut it down to just the following templates:
	 *  	-Straight "Man"
	 *  	-Straight "Woman"
	 *  	-Gay "Man"
	 *  	-Lesbian
	 *  	-Bi/Pan "Man" (Gay "Man"s and Straight "Woman"s will date them)
	 *  	-Bi/Pan "Woman" (Lesbians and Straight "Man"s will date them)
	 *  	-Bi/Pan Middle(?) (All orientations will date them)
	 *  That's what, 7 different values, instead of however many I will have to make with the old system. Time to implement that
	 */
	private String _name;
	private int _id;
	private ArrayList<Gender> _dateable;
	/**
	 * The base constructor for Genders. Sets the name and id to the inputted variables, and creates an empty ArrayList for the attractions
	 * @param name A string for the name of the Gender
	 * @param id An int for the id of the Gender (which is used to create the network of attractions)
	 */
	public Gender(String name, int id) {
		this._name=name;
		this._id=id;
		this._dateable=new ArrayList<Gender>();
	}
	/**
	 * Adds the given Gender to the list of attractions.
	 * @param g The Gender to be added
	 */
	public void add_attraction(Gender g) {
		this._dateable.add(g);
	}
	/**
	 * Returns the id of the Gender, for creating the network of attractions, mainly
	 * @return The int id of the Gender
	 */
	public int get_id() {
		return this._id;
	}
	public String toString() {
		//This is the simple print, which just returns the name and id, to stop a recursive loop of printing the references to attractions
		String ans=this._name+" (#"+String.valueOf(this._id)+")";
		return ans;
	}
	/**
	 * Please avoid overwriting this, otherwise you could easily get an infinite loop, because the attractions are a complicated network
	 * @return A String that lists all the variables of the Gender, with the attractions ArrayList printing the .toString() output of the Gender, to stop infinite recursion
	 */
	public String get_info() {
		//This is the full print, which should never be called by Genders, as that risks an infinite loop. Thankfully that would be recursive and the stack would overflow after some point
		String ans="";
		ans+="name: "+_name+"\n";
		ans+="ID:   "+String.valueOf(_id)+"\n";//I just remembered we don't always need to use the this keyword
		ans+="Attractions:";
		for (int i=0; i<_dateable.size();i++) {
			Gender g=_dateable.get(i);
			ans+="\n\t"+g.toString();
		}
		return ans;
	}
	//This may be moved to Gender_manager later on
	//TODO: Remove the following function, it was just a proof-of-concept
	public static Gender_manager create_genders(String loc){//May change that to giving us the file directly, instead of having us shuffle around with directories and such
		//honestly I don't even think I need that string for this tester, it should just be at ./genders.json
		String genders_s = "";
		try {
			File genders_f = new File(loc);
			Scanner scan = new Scanner(genders_f);
			while (scan.hasNextLine()) {
				genders_s+=scan.nextLine();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			//File wasn't found
			System.out.println("File was not found");
			e.printStackTrace();
		}
		//Use gson to turn the string genders_s into an array of Gender_base
		Gson gson = new Gson();
		//Ok, apparently something is wrong with what I'm doing here...
		//I think to get this to work I need to create the array and turn it into a json, and then use that as the genders.json, just to learn the right formatting and stuff
		Gender_base[] genders_a = gson.fromJson(genders_s, Gender_base[].class);
		//for(int i=0; i<genders_a.length; i++) {System.out.println(genders_a[i]);}
		//Now we need to do the Gender_manager stuff
		Gender_manager manager = new Gender_manager();
		for (int i=0; i<genders_a.length; i++) {
			manager.add(genders_a[i]);
		}
		return manager;
	}
	public boolean is_attracted(Gender g) {
		for (int i=0; i<_dateable.size(); i++) {
			if(_dateable.get(i).equals(g)) {
				return true;
			}
		}
		return false;
	}
}
class Gender_base{
	public String name;
	public int id;
	public int[] dateable;
	public Gender_base() {
		
	}
	public String toString() {
		//Mainly for debugging purposes
		String ans="";
		ans+="name:     "+this.name+"\n";
		ans+="ID:       "+this.id+"\n";
		ans+="dateable: [";//+this.dateable;//arrays don't have a nice .toString() method, so I have to manually format this
		if (this.dateable.length>0) {
			ans+=String.valueOf(this.dateable[0]);
		}
		for (int i=1; i<this.dateable.length; i++) {
			ans+=", "+String.valueOf(this.dateable[i]);
		}
		ans+="]";
		return ans;
	}
}
class Gender_manager{
	private ArrayList<Gender> genders;
	private int next_id;
	public Gender_manager() {
		this.genders=new ArrayList<Gender>();
		this.next_id=0;
		Gender err=new Gender("GENDER ASSIGN ERROR", -1);//A gender for an error in reading stuff, put in for a default case in the settler stuff
		genders.add(err);
	}
	public Gender add(Gender_base g) {
		//We're just going to assume that genders.json is right. Later on in development we can throw an error if it is wrong
		//TODO: Add a check for redundant Gender initializations. Note that we can have two very similar genders, the unique identifier here is the ID variable
		Gender gender=new Gender(g.name, g.id);
		//add the attractions
		for (int i=0; i<g.dateable.length; i++) {
			int id=g.dateable[i];
			if (id==g.id) {
				//TODO: Make it so that this turns on a flag in the Gender, instead of making a recursive reference
				gender.add_attraction(gender);
				continue;
			}
			Gender attract = this.find(id);
			gender.add_attraction(attract);
			attract.add_attraction(gender);
		}
		this.next_id=g.id+1;
		this.genders.add(gender);
		return gender; //wait, why are we returning the gender?
		//TODO: Find out why we're returning the Gender here, or stop doing it
	}
	//TODO: adder for custon (none Gender_base) genders
	public Gender find(int id) {
		//TODO: Either put this optimization back in and test it, or remove it from the files
		//First, we should check index that we would expect it to be at if everything was right
		/*Gender g=genders.get(id);
		if(g.get_id()==id) {
			return g;
		}*/
		//If that didn't work, go through all of the other ones until we find it
		for (int i=0; i<genders.size(); i++) {
			//if(i==id) {continue;} A side part of the possibly-removed optimization, which was to check the index the ID should be at first, so we can skip it later
			Gender g=genders.get(i);
			if(g.get_id()==id) {
				return g;
			}
		}
		return null; //throw some form of error
	}
	public void initialize_genders(String json) {
		//Note that that json is the /contents/ of the json file, not the file location
		Gson gson = new Gson(); //Maybe we will want to get this from somewhere higher up, so that we aren't initializing gson so many times.
		Gender_base[] gs=gson.fromJson(json, Gender_base[].class);
		for (int i=0; i<gs.length; i++) {
			this.add(gs[i]);
		}
	}
	public String get_info() {
		String ans="";
		ans+="Next ID: "+String.valueOf(next_id);
		ans+="\nGenders:";
		for (int i=0; i<genders.size(); i++) {
			ans+="\n---"+String.valueOf(i)+"---\n";
			ans+=genders.get(i).get_info();
		}
		return ans;
	}
}
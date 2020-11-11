package trail;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.gson.*;

public class Settler {
	public Settler(Char_Name name, int age, Gender g, boolean aro, boolean ace) {
		// TODO Auto-generated constructor stub
		this._name=name;
		this._age=age;
		this._gender=g;
		this._aro=aro;
		this._ace=ace;
	}
	/* Ok, more thinking, now about how I will actually implement this.
	 * Every settler has:
	 * 	-A biography, which is made up of:
	 * 		-Age
	 * 		-Sex, the options of which change depending on the "Race" now that I think about it (humans have 3, 2 for the standards and 1 for intersex, which will probably need an object attached to it to explain the specifics)
	 * 		-"Race"
	 * 		-Gender, which will include male and female, and then to handle other cases we will use objects so that you can define your own gender. Those user-defined genders are the following parts:
	 * 			-Pronouns, which needs to handle 2 weird cases that I can think of: 2+ sets of pronouns, where we will either choose one for the current text or keep track of which pronouns each person uses to refer to their friends, and 
	 * 				genderfluidity, where the player needs to be able to change their current pronouns. Oh, and the case of no pronouns (refer to by name only), which will be fun to code
	 * 			-Dating group, a way to handle the relationship system without going crazy. A simplification of the complicated thing which is gender, where for the relationship system there are only 3 or 4 genders: male-aligned, female-aligned
	 * 				both (nonbinary in a way such that people attracted to both men and women are attracted to you. Nonbinary isn't a 3rd gender, you don't need to change your lables if you date a nonbinary person unless they are aligned with
	 * 				the gender you thought you weren't attracted to), and then neither is suggested by a ven diagram of what this is doing to the space of gender, but I think if you want to be down as neither you should set your 
	 * 				orientation to aroace or turn off relationships for this character or something.
	 * 		-Orientation, which will be the following 3 things:
	 * 			-Allo/ace
	 * 			-Allo/aro
	 * 			-Dating group, which is male-aligned (attracted to men and the like), female-aligned (attracted to women and the like), both (attracted to both men, women, and all other genders), and neither (for non-oriented aroace)
	 * 		-*facepalm* Name, which will be a bit complicated as I need to handle:
	 * 			-Different orders of personal name, family name, and other name parts 
	 * 			-Nicknames (probably can be handled by the personal name/full name system)
	 * 			-Declined names (not sure if that is the proper terminology, names which change depending on part of speech), which I guess I will already need to handle mostly for the pronouns system, I just need to mark the case the name is used in as well
	 * 		-Probably some more stuff, which will make a little prose biography
	 * 	-Ability scores. Do I even need this, or can it be handled by skills and subskills?
	 * 	-A list of skills. 
	 */
	private Gender _gender;
	private Pronouns _pronouns;
	private int _age;//May want to change this to a different data type
	//<Thing> _birthday;//Not sure how I want to handle this, for now I'll just leave it alone
	private Char_Name _name;//May want to make Char_Name inheret some stuff from the same source as Pronouns. Note that this isn't the version that should be directly referenced by outside stuff, Settler.name() will probably return Subject form First/Nickname
	private Biology _biology;//Handles both biological sex and any non-human stuff. Pretty much a box of stuff that I may just pull into here. The Biology object will still exist, I'm not sure if I'll inport the data from the object or save a pointer to the object. Probably save a pointer, and if I need to change something I can make a copy of the template and change the one variable
	private boolean _aro;
	private boolean _ace;
	//TODO: Work in the skills system. And create it, I guess
	
}
class Settler_Manager{
	public Gender_manager genders;
	private ArrayList<Settler> settlers;
	public Settler_Manager() {
		//Initialize Genders
		genders=new Gender_manager();
		String genders_json="";
		try {
			File genders_f = new File("./genders.json");
			Scanner scan = new Scanner(genders_f);
			while (scan.hasNextLine()) {
				genders_json+=scan.nextLine();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			//File wasn't found
			//TODO: Better error reporting, maybe don't crash?
			System.out.println("Genders file was not found");
			e.printStackTrace();
		}
		genders.initialize_genders(genders_json);
		//Create the settlers array
		settlers=new ArrayList<Settler>();
	}
	public void add_settler(Settler s) {
		settlers.add(s);
	}
	public Settler add_settler(Char_Name name, Gender g, int age, boolean aro, boolean ace) {
		Settler s=new Settler(name, age, g, aro, ace);//not sure what order these arguments will be required in
		this.add_settler(s);
		return s;
	}
	public Settler add_settler(Settler_Simple simple) {
		
		return null;
	}
	public void initialize_settlers(String json) {
		//json is the contents of the JSON file
		Gson gson=new Gson();
		Settler_Simple[] simples=gson.fromJson(json, Settler_Simple[].class);
		for (int i=0; i<simples.length; i++) {
			//Turn the simple into a full settler
		}
	}
}
class Settler_Simple{
	public String[] name;
	public int age;
	public int gender_id;
	public boolean aro;
	public boolean ace;
	public Settler_Simple() {
		
	}
}
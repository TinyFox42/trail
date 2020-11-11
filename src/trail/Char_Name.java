package trail;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Char_Name {
	//Oh goodness, this is going to be complicated, isn't it?
	//Ok, for version 1 I'm going to implement this format of a name:
	//		<prefixes> <first name> <middle name> "<nickname>" <last name>, <postfixes>
	//e.g.: Mr. Robert John "Bobby" Smith, Jr.
	//This gives us the following forms we need to be able to decline/transform:
	//	Full name, with titles and stuff
	//	Full name, without titles and stuff
	//saiognawoidn
	//No, let's do this in a way that I will keep my sanity.
	//First off, let's turn this into an interface, so that people can write their own name handlers for names of different cultures
	//Magic numbers for the different parts of speech. English doesn't really use this, but other langauges do
	/**
	 * The form for the subject of the sentence, such as "Bob" in "Bob said 'hi'."
	 */
	public static final int SUBJECT=0;//"Bob said hi"
	/**
	 * The form for the object of the sentence, such as "Bob" in "I greeted Bob."
	 * English doesn't really use this, but I put it in in case someone with a non-English name wanted their name used properly
	 */
	public static final int OBJECT=1;//"I hit Bob"
	/**
	 * The form for showing possession, such as "Bob's" in "Bob's book."
	 */
	public static final int POSSESSIVE=2;//"Bob's thing", one of the few that is different. Mainly just to pass along the whole "Name ending in s" thing
	/*	Considered additions:
	 * 		-Genetive, another form of possession, such as "of Bob"
	 */		
	private Hashtable<Integer, String> subjects;
	private Hashtable<Integer, String> objects;
	private Hashtable<Integer, String> possessives;

	public Char_Name() {
		subjects=new Hashtable<Integer, String>();
		objects=new Hashtable<Integer, String>();
		possessives=new Hashtable<Integer, String>();
	}
	/**
	 * 
	 * @param familiarity An int 0-10, where 0 returns the full formal name and 10 returns the full nickname. Should round to the nearest form
	 * @param form An int for the part of speech, see the variables defined in the Char_Name class
	 * @return A String that can be printed straight out of the box, so 's is added and such.
	 */
	public String get_form(int familiarity, int form){
		//get the right Hashtable for this form
		Hashtable<Integer, String> names=null;
		if(form==SUBJECT) {
			names=subjects;
		}
		else if(form==OBJECT) {
			names=objects;
		}
		else {
			names=possessives;
		}
		//Find the nearest key in the Hashtable
		Integer closest=Integer.MAX_VALUE;//Since we're working with 0-10 and this is like 2 billion, this should work as a starting value
		for(Enumeration<Integer> e=names.keys(); e.hasMoreElements();) {
			Integer i=e.nextElement();
			if(Math.abs(closest-familiarity)>Math.abs(i-familiarity)) {
				closest=i;
			}
		}
		return names.get(closest);
	}
	/**
	 * Sets the name of the given formatlity and form to the given String
	 * @param familiarity An int 0-10, where 0 returns the full formal name and 10 returns the full nickname
	 * @param form form An int for the part of speech, see the variables defined in the Char_Name interface
	 * @param name The String to set the given name form to
	 */
	public void set_form(int familiarity, int form, String name) {
		//get the right Hashtable for this form. Probably should turn this into a function, instead of copying my code...
		Hashtable<Integer, String> names=null;
		if(form==SUBJECT) {
			names=subjects;
		}
		else if(form==OBJECT) {
			names=objects;
		}
		else {
			names=possessives;
		}
		names.put(familiarity, name);
	}
	/**
	 * Takes an array and turns it into a name. Done this way so it can stay generic and not need a separate file for names
	 * (If it read from a json, we would need to make a separate file for the names, because we can only read jsons as a whole file)
	 * @param parts An array of Strings, which the name object needs to be able to build itself from
	 */
	public abstract void setup(String [] parts);
}

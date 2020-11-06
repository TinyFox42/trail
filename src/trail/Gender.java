package trail;
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
	 */
	private boolean _as_male;
	private boolean _as_female;
	/*
	 * Now, the pronouns variables. We may need to double the number of variables to handle capitalization at the start to the sentence, for neopronouns like ".wav" (which I have seen, but have no idea how you handle at the start of a sentence)
	 * Or I may make a pronoun object with functions to get each form and a flag in the input for the capitalization form, and then I can handle it with inheritance. Yeah, that's probably how I should handle it
	 * Oh, I actually need to do that anyway, for genderfluidity and other forms of multiple pronouns.
	 * But for version 1 this will work.
	 * TODO Change out this system for the more inclusive Pronouns objects
	 */
	private String _p_subject; 					//"he", "she", "they", "it"
	private String _p_object; 					//"him", "her", "them", "it"
	private String _p_possessive; 				//"his", "her", "their", "its"
	private String _p_possessive_independent;	//"his", "hers", "theirs", "its"
	private String _p_intensive; 				//"himself", "herself", "themselves"/"themself", "itself". "Bob did it himself"
	private String _p_reflexive;				//"himself", "herself", "themselves"/"theirself", "itself". "Bob gave it to himself"
	
	public Gender() {
		this(false,
				false,
				"they",
				"them",
				"thier",
				"theirs",
				"themselves",
				"themselves");
	}
	public Gender(boolean as_male, boolean as_female, String subject, String object, String possessive, String possessive_independent, String intensive, String reflexive) {
		this._as_male=as_male;
		this._as_female=as_female;
		this._p_subject=subject;
		this._p_object=object;
		this._p_possessive=possessive;
		this._p_possessive_independent=possessive_independent;
		this._p_intensive=intensive;
		this._p_reflexive=reflexive;
	}
	/**
	 * I'm not that sure if this will exist anymore
	 * @param scan The input stream thingy
	 * @return A Gender object represented by the answers the user makes to the questions
	 */
	public static Gender creator(Scanner scan) {
		//"Can a <man/woman> dating you call themselves straight?"
		return new Gender();
	}

}

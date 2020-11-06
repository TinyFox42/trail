package trail;
/**
 * (Put documentation here)
 */
public class Settler {
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
	private int _age;//May want to change this to a different data type
	//<Thing> _birthday;//Not sure how I want to handle this, for now I'll just leave it alone
	private Char_Name _name;//May want to make Char_Name inheret some stuff from the same source as Pronouns. Note that this isn't the version that should be directly referenced by outside stuff, Settler.name() will probably return Subject form First/Nickname
	private Biology _biology;//Handles both biological sex and any non-human stuff. Pretty much a box of stuff that I may just pull into here. The Biology object will still exist, I'm not sure if I'll inport the data from the object or save a pointer to the object. Probably save a pointer, and if I need to change something I can make a copy of the template and change the one variable
	private boolean _aro;
	private boolean _ace;
	private boolean _male_attracted;
	private boolean _female_attracted;//realized that this would make more sense than a magic number, only uses up 2 bits of data rather than <however long an int is>
	//<however I'm handling skills>
	/**
	 * TODO add documentation
	 */
	public Settler(){//this is the one with the least information, and depending on how things go I may require the addition of some game instance to reference back to
		this(Gender.base(),
				0,	//0 years or days old, not sure which system I'm going to use
				Char_Name.base(),
				Biology.base(),
				true, //Not attracted to anyone, as a good default
				true,
				false,
				false);
	}
	/**
	 * TODO add documentation
	 * @param gender
	 * @param age
	 * @param name
	 * @param biology
	 * @param aro
	 * @param ace
	 * @param male_attracted
	 * @param female_attracted
	 */
	public Settler(Gender gender, int age, Char_Name name, Biology biology, boolean aro, boolean ace, boolean male_attracted, boolean female_attracted) {
		this._gender=gender;
		this._age=age;
		this._name=name;
		this._biology=biology;
		this._aro=aro;
		this._ace=ace;
		this._male_attracted=male_attracted;
		this._female_attracted=female_attracted;
	}
	//TODO add a bunch of getters and setters
}

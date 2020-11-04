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
}

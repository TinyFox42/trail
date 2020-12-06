package trail;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.gson.*;

public class Settler {
	private Gender _gender;
	private Pronouns _pronouns;
	private int _age;//May want to change this to a different data type
	//<Thing> _birthday;//Not sure how I want to handle this, for now I'll just leave it alone
	private String _name;//Not using any special object, just a string for the name.//May want to make Char_Name inheret some stuff from the same source as Pronouns. Note that this isn't the version that should be directly referenced by outside stuff, Settler.name() will probably return Subject form First/Nickname
	private Biology _biology;//Handles both biological sex and any non-human stuff. Pretty much a box of stuff that I may just pull into here. The Biology object will still exist, I'm not sure if I'll inport the data from the object or save a pointer to the object. Probably save a pointer, and if I need to change something I can make a copy of the template and change the one variable
	private boolean _aro;
	private boolean _ace;
	//TODO: Work in the skills system. And create it, I guess
	public Settler(String name, int age, Gender g, boolean aro, boolean ace) {
		this._name=name;
		this._age=age;
		this._gender=g;
		this._aro=aro;
		this._ace=ace;
	}
	public Gender get_gender() {
		return _gender;
	}
	public String get_name() {
		return _name;
	}
	public boolean is_aro() {
		return _aro;
	}
	public boolean is_ace() {
		return _ace;
	}
	public boolean is_attracted(Gender g) {
		return _gender.is_attracted(g);
	}
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
	public Settler add_settler(String name, Gender g, int age, boolean aro, boolean ace) {
		Settler s=new Settler(name, age, g, aro, ace);//not sure what order these arguments will be required in
		this.add_settler(s);
		return s;
	}
	public Settler add_settler(Settler_Simple simple) {
		String name=simple.name;
		if (name==null) {
			//TODO: throw some error, for now I'm just going to set the name to an error code
			name="NAME READ ERROR";
		}
		int age=simple.age;
		Gender g=genders.find(simple.gender_id);
		if (g==null) {
			//TODO: throw some error
			g=genders.find(-1);//The error gender
		}
		boolean aro=simple.aro;
		boolean ace=simple.ace;
		return this.add_settler(name, g, age, aro, ace);
	}
	public void initialize_settlers(String json) {
		//json is the contents of the JSON file
		Gson gson=new Gson();
		Settler_Simple[] simples=gson.fromJson(json, Settler_Simple[].class);
		for (int i=0; i<simples.length; i++) {
			Settler_Simple simple=simples[i];
			add_settler(simple);//Oh, I guess we don't need the return value here
		}
	}
}
class Settler_Simple{
	public String name;
	public int age;
	public int gender_id;
	public boolean aro;
	public boolean ace;
	public Settler_Simple() {
		
	}
}
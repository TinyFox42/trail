package trail;
import com.google.gson.*;
public class Tester {
	public static void main(String [] args) {
		System.out.println(Gender.create_genders("./genders.json"));
		/*Gson gson = new Gson();
		Gender_base base = new Gender_base();
		base.name="Straight \"Male\"";
		base.id=0;
		base.dateable= new int[0];
		System.out.println(base);
		String json= gson.toJson(base);
		System.out.println(json);
		Gender_base [] bases= {base};
		String json2=gson.toJson(bases);
		System.out.println(json2);
		Gender_base base_imported=gson.fromJson(json, Gender_base.class);
		System.out.println(base_imported);
		Gender_base [] bases_imported=gson.fromJson(json2, Gender_base[].class);
		for (int i=0; i<bases_imported.length; i++) {
			System.out.println(bases_imported[i]);
		}*/
		
	}
}

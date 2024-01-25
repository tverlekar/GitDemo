package QAMavenProject.Mavenjava;

import java.util.HashMap;
import java.util.Map;

public class HashMapProgram {

	public static void main(String[] args) {
		HashMap<Integer,String> hm= new HashMap();
		hm.put(1, "Tasmeya");
		hm.put(2, "Raj");
		hm.put(3, "Ram");
		hm.put(4, "Rohan");
		hm.put(5, "Rahul");
		hm.put(6, "Rajesh");
		hm.put(105, "New");
		hm.put(3, "Rama");
		
		hm.put(null, "aaaa");
		
		System.out.println(hm);

		for(Map.Entry me: hm.entrySet())
		{
			System.out.println(me.getKey()+" -> "+me.getValue());
		}

	}

}

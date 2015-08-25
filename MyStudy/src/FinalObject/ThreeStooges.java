package FinalObject;

import java.util.HashSet;
import java.util.Set;

public class ThreeStooges {
	private final Set<String> stooges=new HashSet<String>();
	public ThreeStooges(){
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}
	
	public boolean isStooge(String name){
		return stooges.contains(name);
	}
	
	public void addName(String name){
		stooges.add(name);
	}
	
	public void changeFinal()
	{
		Set<String> stooges1=new HashSet<String>();
		//stooges = stooges1;
	}
}

package socket;

public class ArrayMath {
	public ArrayMath(){
		
	}
	
	public int[] addArray(int a[],int b[]){
		int result[]=new int[10];
		for(int s=0;s<result.length;s++){
			result[s]=a[s]+b[s];
		}
		return result;
	}
}

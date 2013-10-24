
public class Debug {

	public static boolean enable = true;
	
	public static void println(String msg){
		if(enable)
			System.out.println(msg);
	}
	
	public static void print(String msg){
		if(enable)
			System.out.print(msg);
	}
	
}

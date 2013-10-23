
public class Debug {

	public static boolean enable = true;
	
	public static void debugPrint(String msg){
		if(enable)
			System.out.println(msg);
	}
	
}

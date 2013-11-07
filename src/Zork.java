import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.PrintStream;

public class Zork {
	
	static private boolean fromFile = false;

	public Zork(String XMLin){
		XMLParser parser = new XMLParser();
		parser.Parse(XMLin);
	}
	
	static public void main(String[] Args){
		String fname = "trigger";
		Debug.enable = false;
		fromFile = false;
		if(fromFile){
			try{
				System.setIn(new FileInputStream("samples/" + fname + "sample.txt"));
				System.setOut(new PrintStream("out.txt"));
				Zork zork = new Zork("samples/triggersample.xml");
				System.out.println(Game.getCurrentRoom().getDescription());
				Game.Play();
			} catch (Exception e){
				System.out.println("Error in initialization");
			}
		} else {
			//Zork zork = new Zork("samples/" + fname + "sample.xml");
			Zork zork = new Zork("sample.xml");
			System.out.println(Game.getCurrentRoom().getDescription());
			Game.Play();
		}
		
		return;
	}
	
	
}


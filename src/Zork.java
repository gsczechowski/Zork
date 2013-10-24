import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;

public class Zork {

	public Zork(String XMLin){
		XMLParser parser = new XMLParser();
		parser.Parse(XMLin);
	}
	
	static public void main(String[] Args){
		Zork zork = new Zork("sample.xml");
	}
	
	
}


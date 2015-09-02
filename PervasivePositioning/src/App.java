import java.util.ArrayList;
import java.util.List;

import pp.collect.TraceParser;
import pp.navigation.NavigationController;
import pp.navigation.NavigationElement;
import pp.test.TestElement;


public class App {

	public static void main(String[] args) {
		
		
		
		// TODO Auto-generated method stub
		List<NavigationElement> elements = new ArrayList<>();
		elements.add(new NavigationElement(TraceParser.class, "Trace Parser"));
		new NavigationController(elements);
	}

}

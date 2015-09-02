import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;

import pp.collect.TraceParser;
import pp.navigation.NavigationController;
import pp.navigation.NavigationElement;
import pp.test.TestElement;


public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TraceParser parser = new TraceParser();
		List<NavigationElement> elements = new ArrayList<>();
		elements.add(new NavigationElement(TestElement.class, "Trace Parser"));
		NavigationController controller = new NavigationController(elements);
		System.out.println(controller.returnDefaultWelcomingString());
		Scanner sc = new Scanner(System.in);
		intiateScannerRequest(sc, controller, parser);
	}

	private static void intiateScannerRequest(Scanner sc, NavigationController controller, TraceParser parser){
		int index = sc.nextInt();
		NavigationElement choosenElement = controller.retrieveNavigationElementByIndex(index);
		if(choosenElement != null){
			Class<?> currentElement = choosenElement.getElement();
			try {
				currentElement.getConstructor(TraceParser.class).newInstance(parser);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println(controller.errorMessage());
			intiateScannerRequest(sc, controller, parser);
		}
	}
}

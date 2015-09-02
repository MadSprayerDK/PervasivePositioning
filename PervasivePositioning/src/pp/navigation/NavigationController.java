package pp.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NavigationController {

	private List<NavigationElement> objects;
	private final String standardInformation = "Select a program by choosen the programs corrisponding number:\n";
	private final String defaultErrorMessage = "No such element exists, try again! \n";
	private Scanner sc = new Scanner(System.in);
	
	public NavigationController(List<NavigationElement> navigationElements){
		this.objects = navigationElements;
		System.out.println(returnDefaultWelcomingString());
		int index = sc.nextInt();
		NavigationElement choosenElement = retrieveNavigationElementByIndex(index);
		if(choosenElement != null){
			choosenElement.getElement();
		}
		else{
			System.out.println(defaultErrorMessage);
		}
		
	}
	
	public String returnDefaultWelcomingString(){
		StringBuilder builder = new StringBuilder();
		builder.append(standardInformation);
		for(int a = 0; a<objects.size(); a++){
			NavigationElement element = objects.get(a);
			builder.append(a + " " + element.getName() + "\n");
		}
		return builder.toString();
	}
	
	
	public NavigationElement retrieveNavigationElementByIndex(int a){
		if(a >= 0 && a < objects.size()){
			return objects.get(a);
		}
		return null;
	}
	
}

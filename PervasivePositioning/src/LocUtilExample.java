

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.SignalStrengthSamples;
import org.pi4.locutil.trace.TraceEntry;

/**
 * Example of how to use LocUtil
 * @author mikkelbk
 */

public class LocUtilExample {

	/**
	 * Execute example
	 * @param args
	 */
	 ArrayList<point> created_world_all_points = new ArrayList<point>();
	 ArrayList<point> all_points = new ArrayList<point>(); // empirical
	 ArrayList<point> all_locations = new ArrayList<point>();
	 
	
	 
		double smallest_sample = 9999.9;
		
		public double calculate_signalStregh(point a, point b){
			
			double cc = -30.0;
			
			double distance = distance_between_2_poits_fysik(a,b);
			//distance = distance * -33;
			
			
		///	System.out.println(distance+"ssss");
			
			//1,6-1,8 and 4-6 = 3
			double temp = Math.abs(distance);
			 temp = Math.log10(temp);
			//System.out.println(distance+"ssss");
			temp = 10*1.6*temp+cc;
			
			//System.out.println(temp+"ssss");
			return(temp);
					
					
			
			
			
		}
		
		public void create_model_vorld(){
			short[] s1 = {1111,1111,1111,1111,1111,1111};
			short[] s2 = {1111,1111,1111,1111,1111,1112};
			short[] s3 = {1111,1111,1111,1111,1111,1113};
			short[] s4 = {1111,1111,1111,1111,1111,1114};
			short[] s5 = {1111,1111,1111,1111,1111,1115};
			short[] s6 = {1111,1111,1111,1111,1111,1116};
			
			
			
			short[] js1 = {0x00,0x14,0xBF,0x3B,0xC7,0xC6};
			MACAddress jaaa = new MACAddress(js1);
		
// theas are the masadress on the wifi signals
			MACAddress aaa = new MACAddress(s1);
			point pa = new point(3.25,20.0,0.0);
			MACAddress bbb = new MACAddress(s2);
			point pb = new point(26.75,20.0,0.0);
			MACAddress ccc = new MACAddress(s1);
			point pc = new point(3.25,0.0,0.0);
			MACAddress ddd = new MACAddress(s2);
			point pd = new point(26.75,0.0,0.0);
			
			// problem med MACADRESSER i test data
			
		//	x = -23 to 30
		//		y = -10 to 10
			int dd= 0;
			for (double x = -23; x < 30; x = x + 0.5){
				
				for (double y = -10; y < 30; y = y +0.5){
				
					point a = new point(x,y,0.0);
					
				if (distance_between_2_poits_fysik(pa,a) < 5 )	a.addSignalStregh(calculate_signalStregh(pa,a), aaa); else a.addSignalStregh(999999.9, aaa);
				if (distance_between_2_poits_fysik(pb,a) < 5)	a.addSignalStregh(calculate_signalStregh(pb,a), bbb); else a.addSignalStregh(999999.9, bbb);
				if (distance_between_2_poits_fysik(pc,a) < 5)	a.addSignalStregh(calculate_signalStregh(pc,a), ccc); else a.addSignalStregh(999999.9, ccc);
				if (distance_between_2_poits_fysik(pd,a) < 5)	a.addSignalStregh(calculate_signalStregh(pd,a), ddd); else a.addSignalStregh(999999.9, ddd);
//8956
					created_world_all_points.add(a);
				dd++;
				
				}
			}
			
		
			
			int i = 0;
			for(point entry: all_points) {
				

			}
			
		//	System.out.println(aaa.equals(bbb));
			
		}
		
	public void fuck_main(){
		

	//	System.out.println(aaa);
		
		short[] js1 = {0x00,0x14,0xBF,0x3B,0xC7,0xC6};
		MACAddress jaaa = new MACAddress(js1);
		
		//System.out.println(jaaa + "starter");
		
		all_points.clear();
		all_locations.clear();
		
		String offlinePath = "data/MU.1.5meters.offline.trace", onlinePath = "data/MU.1.5meters.online.trace";
		
		//Construct parsers
		File offlineFile = new File(offlinePath);
		Parser offlineParser = new Parser(offlineFile);
		System.out.println("Offline File: " +  offlineFile.getAbsoluteFile());
		
		File onlineFile = new File(onlinePath);
		Parser onlineParser = new Parser(onlineFile);
		System.out.println("Online File: " + onlineFile.getAbsoluteFile());
		
		//Construct trace generator
		TraceGenerator tg;
		try {
			int offlineSize = 25;
			int onlineSize = 5;
			tg = new TraceGenerator(offlineParser, onlineParser,offlineSize,onlineSize);
			
			//Generate traces from parsed files
			tg.generate();
			
			//Iterate the trace generated from the offline file
			List<TraceEntry> offlineTrace = tg.getOffline();	
			
			
			
			 
			for(TraceEntry entry: offlineTrace) {
				//Print out coordinates for the collection point and the number of signal strength samples
				
	//			System.out.println(entry.getGeoPosition().toString() + " - " + entry.getSignalStrengthSamples().size());	
		
				// load all x,y koordination over in model
				GeoPosition a = entry.getGeoPosition();
				point temp = new point(a.getX(),a.getY(),0.0);
				
				
				
				
				LinkedList<MACAddress> aa = new  LinkedList<MACAddress>();
				aa = entry.getSignalStrengthSamples().getSortedAccessPoints();
			
			//	int yyy = 0;
				for(MACAddress adr: aa) {
			//	yyy++;
		//	if (jaaa.equals(adr))		
		//		System.out.println(adr);
					
		//			System.out.println(adr + " x,y "+a.getX()+" "+a.getY()+"");
				double temp123 = entry.getSignalStrengthSamples().getAverageSignalStrength(adr);
				//double temp123 = entry.getSignalStrengthSamples().size(adr);
				
				
				
				temp.addSignalStregh(temp123,adr); // for every x,y coordinate load all macadresses and signal stregs over in model
	//			System.out.println(adr); // all macadresses
	//			System.out.println(entry.getSignalStrengthSamples().getAverageSignalStrength(adr)); //average signal stregh one the specifik mac
				}
		//	System.out.println("");
		//	System.out.println("tal "+yyy);
				all_points.add(temp);
				
				//System.out.println(aa);
				
			//	System.out.println(entry.getSignalStrengthSamples().get(1));
				
				
				/*f
				 * 
				 * 	public double getAverageSignalStrength(MACAddress mac) {
					return samples.get(mac).getAverageSignalStrength();
	}
				 * 
				 * 
				 */
				
		//		x = -23 to 30
		//		y = -10 to 10
			}
			
		/*	
			for(point entry: all_points) {
				
				System.out.println(entry.getX());
				System.out.println(entry.GetMac(0)+"");
				System.out.println(entry.getSignalStregh(entry.GetMac(0))+"");
			}
		*/	
		
			//Iterate the trace generated from the online file
			List<TraceEntry> onlineTrace = tg.getOnline();			
			for(TraceEntry entry: onlineTrace) {
				//Print out coordinates for the collection point and the number of signal strength samples
		//System.out.println(entry.getGeoPosition().toString() + " - " + entry.getSignalStrengthSamples().size());
	
		GeoPosition a = entry.getGeoPosition();
		point temp = new point(a.getX(),a.getY(),0.0);
		
		LinkedList<MACAddress> aa = new  LinkedList<MACAddress>();
		aa = entry.getSignalStrengthSamples().getSortedAccessPoints();
		for(MACAddress adr: aa) {
		
		double temp123 = entry.getSignalStrengthSamples().getAverageSignalStrength(adr);
		temp.addSignalStregh(temp123,adr); // for every x,y coordinate load all macadresses and signal stregs over in model
	
		//System.out.println(temp123+""+adr);
		
//			System.out.println(adr); // all macadresses
//			System.out.println(entry.getSignalStrengthSamples().getAverageSignalStrength(adr)); //average signal stregh one the specifik mac
		}
		
		all_locations.add(temp);
		//System.out.println(temp.getX());
		
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// distance between 2 point,så a small distance is good here
	public double distance_between_2_poits_fysik(point a, point b){
		double result;
		//result =  Math.pow((a.getX()-b.getX()),2) - (a.getY()-b.getY()), 2);
		
		double ja,jb;
		
		ja = Math.pow((a.getX()-b.getX()),2);
		jb =  Math.pow(a.getY()-b.getY(), 2);
		
		double jc;
		jc = Math.sqrt(ja+jb);
		/*
		System.out.println("ax "+a.getX());
		System.out.println("bx "+b.getX());
		System.out.println("x"+(a.getX() - b.getX()));
		System.out.println("y"+(a.getY() - b.getY()));
		System.out.println(ja );
		System.out.println(jb );
		System.out.println(jc );
		*/
		return jc;
		
	}
	
// k nearest points, and make a gennemsnit of them
	public point expected(ArrayList<point> a, int k){
		double x = 0;
		double y = 0;
		int i = 0;
		int count = 0;
	//	System.out.println("nyt");
		while (i+1<a.size() && i < k){
			
			x = x + a.get(i).getX();
		//	System.out.println(x+"x");
			y = y + a.get(i).getY();
			
			i++;
			count++;
		}
		x = x / count;
		y = y / count;
		
		point result = new point(x,y,0.0);
		return result;
		
	}
	
	public void run(){
		fuck_main();
		// fuck_main load over the trace data over in my model.

		
		System.out.println("loaded over in oure data model");
		
		// k_point[0] the closet neighboy, k_point[1] the second closed neighboy
		 ArrayList<point> k_point = new ArrayList<point>();
	//	 create_model_vorld();
		
		 // print one result the one for loc 34
		 
		 point test;
		 
		 /*
		 k_point.clear();	 
		k_point.addAll(Test_one_point_against_a_List_of_points(all_locations.get(34), all_points));
		
		
		test = expected(k_point,7);
		System.out.println("From loc "+all_locations.get(34).getX()+" "+all_locations.get(34).getY());
		
		System.out.println(k_point.get(1).getX()+"  x based on closes neighbors ");
		System.out.println(k_point.get(1).getX()+"  y based on closes neighbors");
		
		//System.out.println(k_point.get(1).get_Signal_Stregh().size()+"  siz ");
		
		System.out.println(test.getX()+" expected x based on k neighbors");
		System.out.println(test.getY()+" expected y based on k neighbors");
		
		System.out.println("distance closed neighbor = "+distance_between_2_poits_fysik(all_locations.get(34),k_point.get(0)));
		System.out.println("distance k closed neighbor = "+distance_between_2_poits_fysik(all_locations.get(34),test));
		
		*/
		

	/*
		point test;
		for(point entry: created_world_all_points) {
			System.out.println(entry.get_Signal_Stregh().get(0)+"   ");
			
			
		}
		
	*/
		// point test;
		 

		 
		for(point entry: all_locations) {
	

		 k_point.clear();	 
			k_point.addAll(Test_one_point_against_a_List_of_points(entry, all_points));
			
			
			test = expected(k_point,15);
			System.out.println("From loc ");
			System.out.println(entry.getX()+" "+entry.getY());
			
			System.out.println("x , y based on closes neighbors  ");
			System.out.println(k_point.get(0).getX()+" "+k_point.get(0).getY());

			System.out.println("x , y based on k closes neighbors  ");
			System.out.println(test.getX()+" "+test.getY());
			

			System.out.println("distance closed neighbor = "+distance_between_2_poits_fysik(entry,k_point.get(0)));
			System.out.println("distance k closed neighbor = "+distance_between_2_poits_fysik(entry,test));
			System.out.println("");	
		
				
		}
		


		
		
	}
	
	public static void main(String[] args) {
		LocUtilExample a = new LocUtilExample();
		a.run();
		
		System.out.println("no crash");
		
	}
	
	


	// smallest_sample is the euclise distance that is the smallest

	// smallest_point is the coordinat to the smallest_sample
	point smallest_point = new point(0.0, 0.0, 0.0);
	// ss hvor vi står nu med et målepunkt
	// er et andet målepunkt

	// distance euclise distance between 2 points, first argument is the point
	// we are standing in
	public Double distance_2_Points(point a, point b) {
		Double temp = 0.0;

		int i = 0;
		boolean oneNOtzero = false;
		while (i < a.get_Signal_Stregh().size()) { // go throw all the points in
													// the map

			if (b.getSignalStregh(a.GetMac(i)) != null) { // only look at the
															// ones where we can
															// see them where er
															// are standing now
				temp = temp + Math.pow((a.get_Signal_Stregh().get(i)) - (b.getSignalStregh(a.GetMac(i))), 2);
				
			
				
				double aaa = a.get_Signal_Stregh().get(i);
				if ( aaa == 0.0) {} else {
					oneNOtzero = true;
				}
				
				 aaa = b.getSignalStregh(a.GetMac(i));
				if ( aaa == 0.0) {} else {
					oneNOtzero = true;
				}
				
			}
				
			i++;

		}
	//	if (temp == 0.0)

			
		temp = Math.sqrt(temp);
//System.out.println(temp);
		if (oneNOtzero) {
		
			
//			if (temp == 0.0) {System.out.println("nyt"); distance_2_Points_show(a,b); }
			return temp;
		
		
		}else
			return 9999.9;
	}
	
	
	
	
	public Double distance_2_Points_show(point a, point b) {
		Double temp = 0.0;

		int i = 0;
		boolean oneNOtzero = false;
		while (i < a.get_Signal_Stregh().size()) { // go throw all the points in
													// the map

			System.out.println(a.get_Signal_Stregh().size() +"size()");
			if (b.getSignalStregh(a.GetMac(i)) != null) { // only look at the
															// ones where we can
															// see them where er
															// are standing now
				temp = temp + Math.pow((a.get_Signal_Stregh().get(i)) - (b.getSignalStregh(a.GetMac(i))), 2);
				
			System.out.println(a.get_Signal_Stregh().get(i) +"first");
			System.out.println(b.getSignalStregh(a.GetMac(i)) +"second");	
				double aaa = a.get_Signal_Stregh().get(i);
				if ( aaa == 0.0) {} else {
					oneNOtzero = true;
				}
				
				 aaa = b.getSignalStregh(a.GetMac(i));
				if ( aaa == 0.0) {} else {
					oneNOtzero = true;
				}
				
			}
				
			i++;

		}
	//	if (temp == 0.0)

			
		temp = Math.sqrt(temp);
//System.out.println(temp);
		if (oneNOtzero)
		return temp; else
			return 9999.9;
	}
	
 boolean first_time = true;
 
 ArrayList<point> temp = new  ArrayList<point>();
 
	public ArrayList<point> distance_2_points_akkumulatet(point herervi, point a1) {
		temp.clear();
		//	System.out.println("smallest "+smallest_sample);
		
		double ttt =  distance_2_Points(herervi, a1);
//		System.out.println("tt"+ttt);
		
		Integer yyy = (int) (1000000*ttt);
		
		a1.setdummy(yyy);
		temp.add(a1);
		
	//	smallest_sample = ttt;

		if (ttt < smallest_sample) {
			smallest_sample = distance_2_Points(herervi, a1);
			smallest_point = a1;
		
		// vælg et nyt punkt der har en indre distance
			
		//System.out.println(smallest_sample);
		//System.out.println("distance ="+distance_between_2_poits_fysik(a1,herervi)); // listen det ændres
		}

		// if the distance we have found is smaller what the previosly, remenber
		// that
return temp;
	}

	public ArrayList<point> Test_one_point_against_a_List_of_points(point location, ArrayList<point> AllPoints) {
	// sæt en retur type på et array med nearest nabors
		int i = 0;
		smallest_sample = 9999.9;
	//	k_point_distances.clear();
		
		ArrayList<point> temp = new  ArrayList<point>();
		temp.clear();
		
		while (i + 1 < AllPoints.size()) {
			
			temp.addAll(distance_2_points_akkumulatet(location, AllPoints.get(i)));
		//	temp = 
			i++;
		}
		
		/*
		System.out.println("From loc "+location.getX()+" "+location.getY());
		System.out.println("Smalest distance i radiowave : "+smallest_sample);
		System.out.println(smallest_point.getX() + " " + smallest_point.getY() + " " + smallest_point.getZ());
		System.out.println("distance ="+distance_between_2_poits_fysik(smallest_point,location));
		
		*/
	///	System.out.println("");
		Collections.sort(temp);
		return temp;
	}

	// dummu funktion for testing
	// requres change of MASADRESS
	/*
	 * 
	 * private int id = 0;
	 * 
	 * public void setid(int i){
	 * 
	 * id = i; }
	 * 
	 * 
	 * public boolean equals(MACAddress o) { if (o.getid() != getid()) return
	 * false; if (o == null) return false; if (o.getClass() != this.getClass())
	 * return false; MACAddress ba = (MACAddress) o; for (int i = 0; i < 6; i++)
	 * { if (macAddress[i] != ba.macAddress[i]) return false; } return true; }
	 * 
	 */
	
	/*
	public void run_test_test_one_point_against_a_List_of_points() {
		ArrayList<point> AllPoints = new ArrayList<point>();

		MACAddress bbb = new MACAddress();
		MACAddress ccc = new MACAddress();
		MACAddress ddd = new MACAddress();
		bbb.setid(1);
		ccc.setid(2);
		ddd.setid(3);

		point herervi = new point(0.0, 0.0, 0.0);
		herervi.addSignalStregh(-30.0, bbb);
		// herervi.addSignalStregh(-4.0,ccc);
		// herervi.addSignalStregh(-5.0,ddd);

		point a1 = new point(1.0, 2.0, 0.0);
		a1.addSignalStregh(-25.0, bbb);
		a1.addSignalStregh(-35.0, bbb);
		a1.addSignalStregh(-55.0, bbb);

		point b1 = new point(2.0, 1.0, 1.0);
		b1.addSignalStregh(-3.0, bbb);
		b1.addSignalStregh(-5.0, bbb);
		b1.addSignalStregh(-5.0, bbb);

		point c1 = new point(3.0, 2.0, 0.0);
		c1.addSignalStregh(-25.0, bbb);
		c1.addSignalStregh(-35.0, bbb);
		c1.addSignalStregh(-55.0, bbb);

		point d1 = new point(4.0, 2.0, 0.0);
		d1.addSignalStregh(-25.0, bbb);
		d1.addSignalStregh(-35.0, bbb);
		d1.addSignalStregh(-55.0, bbb);

		AllPoints.add(a1);
		AllPoints.add(b1);
		AllPoints.add(c1);
		AllPoints.add(d1);

		Test_one_point_against_a_List_of_points(herervi, AllPoints);

	}
*/
	public void begin() {

	//	run_test_test_one_point_against_a_List_of_points();

	}

}

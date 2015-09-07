package example;

import java.util.ArrayList;
import java.util.Comparator;

import org.pi4.locutil.MACAddress;

public class point implements Comparator<point>, Comparable<point>{

	private Double x;
	private Double y;
	private Double z;
	private Integer dummudistance;
	
	  // Overriding the compare method to sort the age 
	   public int compare(point d, point d1){
	     return (int) (d.getdummy() - dummudistance);
	   }
	   
	   // Overriding the compareTo method
	   public int compareTo(point d){
	      return (this.dummudistance).compareTo(d.dummudistance);
	   }
	
	
	// private Double sg; // signal strength

	// two array
	// first intry in SignalStregh equal to signal stregh for the macadress in
	// MacAdress
	ArrayList<Double> SignalStregh = new ArrayList<Double>();
	ArrayList<MACAddress> MacAdress = new ArrayList<MACAddress>();

	public point(Double x1, Double y1, Double z1) {

		x = x1;
		y = y1;
		z = z1;
		// sg = sg1;
	}

	public void setdummy(Integer a) {

		dummudistance = a;
	}
	
	
	public double getdummy(){
		double temp;
		temp = dummudistance;
		temp = temp/1000000;
		return temp;
	}
	
	public MACAddress GetMac(int i) {

		return MacAdress.get(i);
	}

	public Double getSignalStregh(MACAddress b) {
		int i = 0;
		while (i + 1 < MacAdress.size()) {

			if (MacAdress.get(i).equals(b))
				return SignalStregh.get(i);
			i++;

		}
		return null;
	}

	public void addSignalStregh(Double a, MACAddress b) {

		SignalStregh.add(a);
		MacAdress.add(b);
	}

	public ArrayList<Double> get_Signal_Stregh() {

		return SignalStregh;
	}

	public Double getX() {

		return x;
	}

	public Double getY() {

		return y;
	}

	public Double getZ() {

		return z;
	}

}

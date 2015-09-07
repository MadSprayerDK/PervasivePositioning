package example;

import java.util.ArrayList;

import org.pi4.locutil.MACAddress;

public class start {

	// smallest_sample is the euclise distance that is the smallest
	double smallest_sample = 99999;
	// smallest_point is the coordinat to the smallest_sample
	point smallest_point = new point(0.0, 0.0, 0.0);
	// ss hvor vi står nu med et målepunkt
	// er et andet målepunkt

	// distance euclise distance between 2 points, first argument is the point
	// we are standing in
	public Double distance_2_Points(point a, point b) {
		Double temp = 0.0;

		int i = 0;
		while (i < a.get_Signal_Stregh().size()) { // go throw all the points in
													// the map

			if (b.getSignalStregh(a.GetMac(i)) != null) { // only look at the
															// ones where we can
															// see them where er
															// are standing now
				temp = temp + Math.pow(a.get_Signal_Stregh().get(i) - b.getSignalStregh(a.GetMac(i)), 2);
			}
			i++;

		}
		temp = Math.sqrt(temp);

		return temp;
	}

	public void distance_2_points_akkumulatet(point herervi, point a1) {
		if (distance_2_Points(herervi, a1) < smallest_sample) {
			smallest_sample = distance_2_Points(herervi, a1);
			smallest_point = a1;
		}

		// if the distance we have found is smaller what the previosly, remenber
		// that

	}

	public void Test_one_point_against_a_List_of_points(point location, ArrayList<point> AllPoints) {

		int i = 0;
		while (i + 1 < AllPoints.size()) {

			distance_2_points_akkumulatet(location, AllPoints.get(i));
			i++;
		}

		System.out.println(smallest_sample);
		System.out.println(smallest_point.getX() + " " + smallest_point.getY() + " " + smallest_point.getZ());
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start a = new start();
		a.begin();
	}

}

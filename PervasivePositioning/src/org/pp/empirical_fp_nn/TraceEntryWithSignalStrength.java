package org.pp.empirical_fp_nn;

import org.pi4.locutil.trace.TraceEntry;

public class TraceEntryWithSignalStrength implements Comparable<TraceEntryWithSignalStrength>{
	 private TraceEntry entry;
	 private double signalStrength;
	 
	 public TraceEntryWithSignalStrength(TraceEntry entry, double signalStrength){
		 this.entry = entry;
		 this.signalStrength = signalStrength;
	 }
	 
	public TraceEntry getEntry() {
		return entry;
	}
	public double getSignalStrength() {
		return signalStrength;
	}

	@Override
	public int compareTo(TraceEntryWithSignalStrength o) {
		// TODO Auto-generated method stub
		int number = Double.compare(this.getSignalStrength(), o.getSignalStrength());;
		return number;
	}
}
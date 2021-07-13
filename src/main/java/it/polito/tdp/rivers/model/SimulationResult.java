package it.polito.tdp.rivers.model;

public class SimulationResult {
	
	int numGiorniNo;
	
	double cAvg;
	
	public SimulationResult(int numGiorniNo, double cAvg) {
		this.cAvg = cAvg;
		this.numGiorniNo = numGiorniNo;
	}

	@Override
	public String toString() {
		return "SimulationResult [numGiorniNo=" + numGiorniNo + ", cAvg=" + cAvg + "]";
	}

	public int getNumGiorniNo() {
		return numGiorniNo;
	}

	public void setNumGiorniNo(int numGiorniNo) {
		this.numGiorniNo = numGiorniNo;
	}

	public double getcAvg() {
		return cAvg;
	}

	public void setcAvg(double cAvg) {
		this.cAvg = cAvg;
	}
	
	

}

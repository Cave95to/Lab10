package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	
	private PriorityQueue<Flow> queue;

	public Model() {
		this.dao = new RiversDAO();
	}

	public List<River> getRivers() {
		
		return this.dao.getAllRivers();
	}

	public List<Flow> getFlows(River r) {
		
		return this.dao.getFlows(r);
	}

	public double calcolaAvg(River r, List<Flow> flows) {
		
		double avg = 0.0;
		
		for(Flow f : flows) {
			avg = avg + f.getFlow();
		}
		
		avg = (avg/flows.size());
		
		r.setFlowAvg(avg);
		
		return avg;
		
	}
	
	public SimulationResult simula(River river, double k) {
		
		this.queue = new PriorityQueue<>();
		this.queue.addAll(river.getFlows());
		
		
		double Q = k*30*this.convert(river.getFlowAvg());
		
		double C = Q/2;
		
		double fOutMin = 0.8*this.convert(river.getFlowAvg());
		
		List<Double> capacity = new ArrayList<>();
		
		int numGiorniNo = 0;
		
		Flow flow;
		while((flow = this.queue.poll())!= null) {
			
			double fOut = fOutMin;
			
			if(Math.random()>0.95) 
				fOut = 10*fOut;
			
			C = C + this.convert(flow.getFlow());
			
			if(C>Q)
				C = Q;
			
			if(C < fOut) {
				numGiorniNo++;
				C = 0;
			}else
				C = C - fOut;
			
			capacity.add(C);		
		}
		
		double cAvg = 0.0;
		
		for(double d : capacity)
			cAvg = cAvg + d;
		
		cAvg = cAvg / capacity.size();
		
		SimulationResult sr = new SimulationResult(numGiorniNo, cAvg);
		
		return sr;
		
		
	}
	
	private double convert (double d) {
		
		return d*60*60*24;
	}
	

}

package slash.slachecker.behaviour;

import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.*;
import slash.dsm.client.DsmClient;
import slash.entity.*;
import java.io.*;
import jade.core.*;
import java.util.*;
import slash.slachecker.util.*;
import slash.util.*;

public class SLACheckerBehaviour extends TickerBehaviour {
	
	private static final long serialVersionUID = -500981357584073158L;
	
	private SLACheckerAgent sc;
	private DsmClient dsmClient;
	
	public SLACheckerBehaviour(SLACheckerAgent sc) {
		super(sc, 2000);
		this.sc = sc;
		this.dsmClient = new DsmClient(sc);
	}
	
	protected void onTick() {
		System.out.println("Sono su "+myAgent.here().getName());
		
		for(int i=0; i<sc.getContractList().size(); i++) {
			SLAContract contract = sc.getContractList().get(i);
			Status status = sc.getStatusTable().get(contract.getPublisher());
			float lat=0, rel=0, req=0;
			if((status!=null) && (((lat=status.getAvgLatency()) > contract.getLatency()) || ((rel=status.getAvgReliability()) > contract.getReliability()) || ((req=status.getAvgReqInterval()) > contract.getReqInterval()))) {
				
				System.out.println("Violated with: "+lat+" > "+contract.getLatency()+", "+rel+" > "+contract.getReliability()+", "+req+" > "+contract.getReqInterval());
				
				dsmClient.out(contract.getPublisher().getLocalName(), "slacontract-violation", contract);
				dsmClient.out(contract.getSubscriber().getLocalName(), "slacontract-violation", contract);
			}
		}
		
		AID best = getBestNode();
		
		AID cm = new AID("cm"+sc.getAssociatedID(), AID.ISLOCALNAME);
		System.out.println("Associated with: "+cm.getLocalName());
		Context context = sc.getContextTable().get(cm);

		float avgCpu, avgRam, avgMemory, avgEnergy;
		if(context!=null) {
			avgCpu = context.getAvgCpu();
			avgRam = context.getAvgRam();
			avgMemory = context.getAvgMemory();
			avgEnergy = context.getAvgEnergy();
			System.out.println("Actual context--> cpu: "+avgCpu+", ram: "+avgRam+", memory: "+avgMemory+", energy: "+avgEnergy+", index: "+context.calcIndex());
			//if(avgCpu >= Context.CPU_LIMIT || avgRam >= Context.RAM_LIMIT || avgMemory >= Context.MEMORY_LIMIT || 
				//	avgEnergy <= Context.ENERGY_LIMIT) {
			if(context.calcIndex() > 52) {
//				AID best = getBestNode();
				if(best!=null) {
					myAgent.doMove(sc.getContextTable().get(best).getLocation());
					int bestN = Integer.parseInt(String.valueOf(best.getLocalName().charAt(best.getLocalName().length()-1)));
					System.out.println("MIGRAZIONE verso "+best.getLocalName());
					System.out.println("BESTN: "+bestN);
					MigrationUtil.notifyMigration(myAgent, sc.getAssociatedID(), bestN);
					sc.setAssociatedID(bestN);
				}
				else
					System.out.println("IMPOSSIBILE MIGRARE!!!");
			}
		}
	}
	
	public AID getBestNode() {
		AID best = null;
		AID next;
		Enumeration<AID> keys = sc.getContextTable().keys();
		Context context;
		float avgCpu, avgRam, avgMemory, avgEnergy;
		float total=500, iter=0;

		while(keys.hasMoreElements()) {
			next = keys.nextElement();
			context = sc.getContextTable().get(next);
			if(context!=null) {
				avgCpu = context.getAvgCpu();
				avgRam = context.getAvgRam();
				avgMemory = context.getAvgMemory();
				avgEnergy = context.getAvgEnergy();
				iter = avgCpu + avgRam + avgMemory + (1-avgEnergy);
				DataWriter.writeData("index"+next.getLocalName().charAt(2), context.calcIndex());
				System.out.println("Context["+next.getLocalName()+"]--> cpu: "+avgCpu+", ram: "+avgRam+", memory: "+avgMemory+", energy: "+avgEnergy+", index: "+context.calcIndex());
				if(avgCpu < Context.CPU_LIMIT && avgRam < Context.RAM_LIMIT && 
						avgMemory < Context.MEMORY_LIMIT && avgEnergy > Context.ENERGY_LIMIT &&
						iter < total) {
					best = next;
					total = iter;
				}
			}
		}

		return best;
	}

}

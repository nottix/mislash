package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;
import slash.entity.*;
import slash.util.*;
import slash.dsm.client.*;

public class CpuBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float cpu;
	
	private AID cmAid;
	private CpuAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public CpuBehaviour(AID cmAid, CpuAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("cpu.tick")));
		this.cmAid = cmAid;
		this.agent = agent;
		
		this.dsmClient = new DsmClient(agent);
	}
	
	private float generate() {
		cpu = (float)((Math.random()*100)%60);
		if(agent.isLocalSC()) {
			//System.out.println("LOCAL: "+myAgent.getLocalName());
			//cpu += (float)((Math.random()*100)%40);
			cpu += 40;
		}
			
		return cpu;
	}
	
	protected void onTick() {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), cpu);
	    	Float cpuStr = cpu;
	    	dsmClient.out(agent.getLocalName(), "cpu", cpuStr);
	}
}

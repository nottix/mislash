package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resource.agent.*;
import slash.util.DataWriter;

public class MemoryBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float memory;
	
	private AID cmAid;
	private MemoryAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public MemoryBehaviour(AID cmAid, MemoryAgent agent) {
		super(agent, 1000);
		this.cmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
		this.memory = (float)((Math.random()*100)%60);
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		if(agent.isLocalSC())
			memory += 0.2*(float)((Math.random()*100)%10);
		else
			memory -= 0.2*(float)((Math.random()*100)%10);
		
		if(memory<0)
			memory = 0;
		
		return memory;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), memory);
    	String memoryStr = String.valueOf(memory);
    	dsmClient.out(agent.getLocalName(), "memory", memoryStr);
	}
}

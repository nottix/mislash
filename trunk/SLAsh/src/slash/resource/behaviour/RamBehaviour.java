package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resource.agent.*;
import slash.util.DataWriter;

public class RamBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float ram;
	
	private AID cmAid;
	private RamAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public RamBehaviour(AID cmAid, RamAgent agent) {
		super(agent, 1000);
		this.cmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		ram = (float)((Math.random()*100)%60);
		if(agent.isLocalSC()) {
			//ram += (float)((Math.random()*100)%40);
			ram += 40;
		}
		
		return ram;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), ram);
    	Float ramStr = ram;
    	dsmClient.out(agent.getLocalName(), "ram", ramStr);
	}
}

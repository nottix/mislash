package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.entity.Context;
import slash.resource.agent.ReliabilityAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class ReliabilityBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reliability;
	
	private AID rmAid;
	private ReliabilityAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public ReliabilityBehaviour(AID cmAid, ReliabilityAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("reliability.tick")));
		this.rmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		reliability = (float)((Math.random()*100)%60);
		if(agent.getNetwork() == Context.WIRED)
			reliability += (float)(((Math.random()*100)*(agent.getBandwidth()/50))%40);
		//if(agent.getNetwork() == Context.WIRED)
		//	reliability *= 2;
		return reliability;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), reliability);
    	Float reliabilityStr = reliability;
    	dsmClient.update(agent.getLocalName(), "reliability", reliabilityStr);
	}
}

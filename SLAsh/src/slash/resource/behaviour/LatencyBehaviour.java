package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.entity.Context;
import slash.resource.agent.LatencyAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class LatencyBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float latency;
	
	private AID rmAid;
	private LatencyAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public LatencyBehaviour(AID cmAid, LatencyAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("latency.tick")));
		this.rmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		latency = (float)((Math.random()*100)%60);
		if(agent.isLocalSC() || agent.getNetwork() == Context.WIRELESS) {
			latency += (float)(((Math.random()*100)/(agent.getBandwidth()/50))%40);
		}
		//latency /= ((float)agent.getBandwidth())*0.2;
		//latency %= 100;
		//if(agent.getNetwork() == Context.WIRED) {
		//	latency /= 2;
		//}
		
		return latency;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), latency);
    	Float latencyStr = latency;
    	dsmClient.update(agent.getLocalName(), "latency", latencyStr);
	}
}

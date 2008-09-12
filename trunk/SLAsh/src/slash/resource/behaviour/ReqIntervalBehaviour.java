package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resource.agent.*;
import slash.util.DataWriter;

public class ReqIntervalBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reqInterval;
	
	private AID rmAid;
	private ReqIntervalAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	private DsmClient dsmClient;
	
	public ReqIntervalBehaviour(AID cmAid, ReqIntervalAgent agent) {
		super(agent, 1000);
		this.rmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		reqInterval = (float)((Math.random()*100)%100);
		return reqInterval;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), reqInterval);
    	String reqIntervalStr = String.valueOf(reqInterval);
    	dsmClient.out(agent.getLocalName(), "reqInterval", reqIntervalStr);
	}
}

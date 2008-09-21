package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.resource.agent.ReqIntervalAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class ReqIntervalBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reqInterval;
	
	private AID rmAid;
	private ReqIntervalAgent agent;
	private DsmClient dsmClient;
	
	public ReqIntervalBehaviour(AID cmAid, ReqIntervalAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("reqinterval.tick")));
		this.rmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	private float generate() {
		reqInterval = (float)((Math.random()*100)%100);
		return reqInterval;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), reqInterval);
    	Float reqIntervalStr = reqInterval;
    	dsmClient.update(agent.getLocalName(), "reqInterval", reqIntervalStr);
	}
}

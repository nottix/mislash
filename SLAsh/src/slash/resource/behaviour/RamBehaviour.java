package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.resource.agent.RamAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class RamBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float ram;
	
	private AID cmAid;
	private RamAgent agent;
	private DsmClient dsmClient;
	
	public RamBehaviour(AID cmAid, RamAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("ram.tick")));
		this.cmAid = cmAid;
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	private float generate() {
		ram = (float)((Math.random()*100)%60);
		if(agent.isLocalSC()) {
			ram += 40;
		}
		
		return ram;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), ram);
    	Float ramStr = ram;
    	dsmClient.update(agent.getLocalName(), "ram", ramStr);
	}
}

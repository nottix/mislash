package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.resource.agent.CpuAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class CpuBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float cpu;
	
	private AID cmAid;
	private CpuAgent agent;
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
			cpu += 40;
		}
			
		return cpu;
	}
	
	protected void onTick() {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), cpu);
	    	Float cpuStr = cpu;
	    	dsmClient.update(agent.getLocalName(), "cpu", cpuStr);
	}
}

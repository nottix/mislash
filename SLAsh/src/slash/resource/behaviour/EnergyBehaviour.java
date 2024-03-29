package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.entity.Context;
import slash.resource.agent.EnergyAgent;
import slash.util.DataWriter;
import slash.util.PropertiesReader;

public class EnergyBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float energy;
	private boolean powerOn;
	
	private AID cmAid;
	private EnergyAgent agent;
	private DsmClient dsmClient;
	
	public EnergyBehaviour(AID cmAid, EnergyAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("energy.tick")));
		this.cmAid = cmAid;
		this.agent = agent;
		this.powerOn = false;
		this.energy = (float)(Math.random()*60)+40;
		this.dsmClient = new DsmClient(agent);
	}
	
	private float generate() {
		
		if(agent.getNetwork() == Context.WIRED)
			energy = 100;
		else {
			if(powerOn) {
				energy += 0.8;
				if(agent.isLocalSC() && (agent.getNetwork() == Context.WIRELESS))
					energy -= 0.2;
			}
			else {
				energy -= 0.2;
				if(agent.isLocalSC() && (agent.getNetwork() == Context.WIRELESS))
					energy -= 0.8;
			}

			if(energy<=10) {
				powerOn = true;
			}

			if(energy>=99)
				powerOn = false;

		}

		return energy;
	}
	
	protected void onTick() {
		generate();
		DataWriter.writeData(myAgent.getLocalName(), energy);
    	Float energyStr = energy;
    	dsmClient.update(agent.getLocalName(), "energy", energyStr);
	}
}

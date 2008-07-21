package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;
import slash.entity.*;

public class EnergyBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float energy;
	private boolean powerOn;
	
	private AID cmAid;
	private EnergyAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public EnergyBehaviour(AID cmAid, EnergyAgent agent) {
		this.cmAid = cmAid;
		this.agent = agent;
		this.powerOn = false;
		this.energy = (float)(Math.random()*60)+40;
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
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
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(energy);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}

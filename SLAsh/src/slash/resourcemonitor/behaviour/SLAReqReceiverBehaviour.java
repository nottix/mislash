package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

import slash.df.DFUtil;
import slash.dsm.client.DsmClient;
import slash.entity.SLAContract;
import slash.resourcemonitor.agent.*;

public class SLAReqReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 1897715821469100876L;

	private DsmClient dsmClient;
	private ResourceMonitorAgent rm;
	
	public SLAReqReceiverBehaviour(ResourceMonitorAgent agent) {
		super(agent, 1000);
		this.rm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	public float genLatency() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReliability() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReqInterval() {
		return (float)(((Math.random()*100))%30)+70;
	}
		
	protected void onTick() {
		String req;
		if((req=(String)dsmClient.in("sc", "slacontract-request"))!=null) {
			AID requester = new AID(req, AID.ISLOCALNAME);
			SLAContract contract = new SLAContract(myAgent.getAID(), new AID("cm"+myAgent.getLocalName().charAt(2), AID.ISLOCALNAME), requester,  new AID("cm"+requester.getLocalName().charAt(2), AID.ISLOCALNAME), this.genLatency(), this.genReliability(), this.genReqInterval());
			dsmClient.out("sc", "slacontract", contract);
		}
	}
}

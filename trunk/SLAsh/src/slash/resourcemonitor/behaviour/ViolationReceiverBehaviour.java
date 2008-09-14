package slash.resourcemonitor.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resourcemonitor.agent.*;

public class ViolationReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -5411307240223204565L;

	private ResourceMonitorAgent rm;
	private DsmClient dsmClient;
	
	public ViolationReceiverBehaviour(ResourceMonitorAgent agent) {
		super(agent, 1000);
		this.rm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		if(dsmClient.in(myAgent.getLocalName(), "slacontract-violation")!=null)
			System.out.println("Contract violated!!!");
	}
}

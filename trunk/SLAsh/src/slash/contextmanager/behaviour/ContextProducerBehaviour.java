package slash.contextmanager.behaviour;

import java.io.IOException;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.contextmanager.agent.*;
import slash.df.DFUtil;
import slash.dsm.client.DsmClient;
import slash.util.*;

public class ContextProducerBehaviour extends TickerBehaviour{

	private static final long serialVersionUID = -884539926477308910L;
	
	private AID scAid;
	private ContextManagerAgent cm;
	private DsmClient dsmClient;
	
	public ContextProducerBehaviour(ContextManagerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("contextproducer.tick")));
		this.cm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
    	dsmClient.out(myAgent.getLocalName(), "context", cm.getContext());
    	//System.out.println("Context produced on "+myAgent.getLocalName()+", context->cpu: "+cm.getContext().getCpu());
	}
	
}

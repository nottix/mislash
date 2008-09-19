/**
 * 
 */
package slash.slachecker.agent;

import jade.core.*;
import jade.lang.acl.ACLMessage;
import slash.slachecker.behaviour.*;
import slash.df.*;

import java.io.IOException;
import java.util.*;
import slash.entity.*;

/**
 * @author Simone Notargiacomo, Lorenzo Tavernese, Ibrahim Khalili
 *
 */
public class SLACheckerAgent extends Agent {

	private static final long serialVersionUID = -7918542991436312908L;
	
	private List<SLAContract> contractList = null;
	private Hashtable<AID, Context> contextTable = null;
	
	private int associatedID;
	
	public void setAssociatedID(int ID) {
		this.associatedID = ID;
	}
	
	public int getAssociatedID() {
		return this.associatedID;
	}
	
	public List<SLAContract> getContractList() {
		return this.contractList;
	}
	
	public Hashtable<AID, Context> getContextTable() {
		return this.contextTable;
	}
	
	protected void setup() {
		this.contractList = new LinkedList<SLAContract>();
		this.contextTable = new Hashtable<AID, Context>();
		
		this.associatedID = 1; //TODO: DA PASSARE COME ARGOMENTO
		
		System.out.println("SlaChecker: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "sla-checker");
		
		this.addBehaviour(new SLAStarterBehaviour());
		this.addBehaviour(new ContextConsumerBehaviour(this));
		this.addBehaviour(new SLAReceiverBehaviour(this));
		this.addBehaviour(new SLACheckerBehaviour(this));
	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "sla-checker");
	}
}

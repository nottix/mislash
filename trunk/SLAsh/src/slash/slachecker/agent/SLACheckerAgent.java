/**
 * 
 */
package slash.slachecker.agent;

import jade.core.*;
import slash.slachecker.behaviour.*;
import slash.df.*;
import java.util.*;
import slash.entity.*;

/**
 * @author Simone Notargiacomo, Lorenzo Tavernese, Ibrahim Khalili
 *
 */
public class SLACheckerAgent extends Agent {

	private static final long serialVersionUID = -7918542991436312908L;
	
	private List<SLAContract> contractList = null;
	private Hashtable<AID, Status> statusTable = null;
	private Hashtable<AID, Context> contextTable = null;
	
	public List<SLAContract> getContractList() {
		return this.contractList;
	}
	
	public Hashtable<AID, Status> getStatusTable() {
		return this.statusTable;
	}
	
	public Hashtable<AID, Context> getContextTable() {
		return this.contextTable;
	}
	
	protected void setup() {
		this.contractList = new LinkedList<SLAContract>();
		this.statusTable = new Hashtable<AID, Status>();
		this.contextTable = new Hashtable<AID, Context>();
		
		System.out.println("SlaChecker: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "sla-checker");
		this.addBehaviour(new ContextReceiverBehaviour(this));
		this.addBehaviour(new ContextRequesterBehaviour(this));
		this.addBehaviour(new SLAReceiverBehaviour(this));
		this.addBehaviour(new StatusRequesterBehaviour(this));
		this.addBehaviour(new StatusReceiverBehaviour(this));
	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "sla-checker");
	}

}
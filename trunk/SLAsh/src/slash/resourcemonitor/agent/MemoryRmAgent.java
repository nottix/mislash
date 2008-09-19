package slash.resourcemonitor.agent;

import jade.core.Agent;
import slash.df.DFUtil;
import slash.resourcemonitor.behaviour.*;
import slash.entity.*;

public class MemoryRmAgent extends Agent {

	private static final long serialVersionUID = 2514514536861766799L;
	
	protected void setup() {
		//this.addBehaviour(new CoreBehaviour(this));
		this.addBehaviour(new MemoryResourceBehaviour(this));
	}
}

package slash.resource.agent;

import jade.core.AID;
import jade.core.Agent;

public class ResourceAgent extends Agent {

	private static final long serialVersionUID = -8360934789152239831L;

	private boolean localSC = false;
	
	protected AID rm;
	
	public AID getRm() {
		return this.rm;
	}
	
	public boolean isLocalSC() {
		return this.localSC;
	}
	
	public void setLocalSC(boolean localSC) {
		this.localSC = localSC;
	}
}

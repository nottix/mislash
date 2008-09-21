package slash.slachecker.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import slash.slachecker.agent.SLACheckerAgent;
import slash.slachecker.util.MigrationUtil;

public class SLAStarterBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -1382758463374415598L;

	private SLACheckerAgent sc;
	
	public void action() {
		MigrationUtil.notifyMigration(myAgent, 0, 1); //Avvertire il primo nodo della presenza dello sc
	}
}

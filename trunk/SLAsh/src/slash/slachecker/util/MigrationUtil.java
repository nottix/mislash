package slash.slachecker.util;

import jade.core.Agent;
import slash.dsm.client.DsmClient;
import slash.entity.Notify;

public class MigrationUtil {
	
	private static DsmClient dsmClient = null; 
	
	public static void notifyMigration(Agent agent, int src, int dest) {
		if(dsmClient==null)
			dsmClient = new DsmClient(agent);
		Notify notify = new Notify(src, dest);

		dsmClient.out("notify", "notify", notify);
	}
}

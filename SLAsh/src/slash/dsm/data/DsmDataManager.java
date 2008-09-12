package slash.dsm.data;

import java.util.*;
import slash.dsm.tuple.*;

public class DsmDataManager {

	Hashtable<String, Hashtable<String, Object>> tupleSpace;
	
	public DsmDataManager() {
		this.tupleSpace = new Hashtable<String, Hashtable<String, Object>>();
	}
	
	public int out(String index, String type, Object value) {
		//Se hashtable per index già esistente
		if(this.tupleSpace.containsKey(index)) {
			Hashtable<String, Object> obj = new Hashtable<String, Object>();
			obj.put(type, value);
			this.tupleSpace.get(index).put(index, obj);
		} //Se hashtable per index non esistente
		else {
			Hashtable<String, Object> obj = new Hashtable<String, Object>();
			obj.put(type, value);
			this.tupleSpace.put(index, obj);
		}
		
		return 0;
	}
	
	public Tuple in(String index, String type) {
		if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Object obj = this.tupleSpace.get(index).get(type);
				this.tupleSpace.get(index).remove(obj); //TODO: rimuove il primo oggetto che trova
				return new Tuple(Tuple.IN, index, type, obj);
			}
		}
		
		return null;
	}
	
	public Object read(String index, String type) {
		if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Object obj = this.tupleSpace.get(index).get(type);
				return obj;
			}
		}
		
		return null;
	}
}

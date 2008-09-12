package slash.dsm.tuple;

import java.io.Serializable;

/**
 * @author Simone Notargiacomo
 *
 * tuple: <operation, type, index, value>
 * ex: <in, Context, node1, object>
 */
public class Tuple implements Serializable {

	private static final long serialVersionUID = -1484825309031979328L;
	
	public static String IN = "in";
	public static String READ = "read";
	public static String OUT = "out";
	public static String UPDATE = "update";
	public static String EVAL = "eval";

	private String operation;
	private String type;
	private String index;
	private Object value;
	
	public Tuple(String operation, String type, String index, Object value) {
		this.operation = operation;
		this.type = type;
		this.index = index;
		this.value = value;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return this.operation;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getIndex() {
		return this.index;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return this.value;
	}
	
}

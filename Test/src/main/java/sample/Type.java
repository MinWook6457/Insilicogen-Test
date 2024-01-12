package sample;

// 사용하지 않음 

public class Type {
	private String type;
	
	
	public Type() {
		//
	}
	
	public Type(String type) {
		this.type =type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
	    return type + " ";
	}
}

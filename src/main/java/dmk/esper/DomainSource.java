package dmk.esper;

/**
 * System sending a web event
 * @author dmknopp
 */
public enum DomainSource {
	SYSTEM1("SYSTEM1"), SYSTEM2("SYSTEM2");
	
	private String name;
	
	DomainSource(String name){
		if(name != null){
			name = name.toUpperCase();
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

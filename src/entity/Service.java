package entity;

public class Service extends AbstractEntity{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Service(String name) {
		super();
		this.name = name;
	}

	public Service() {
		super();
	}
	
}

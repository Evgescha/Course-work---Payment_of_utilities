package entity;

public class Tenant extends AbstractEntity{
	private String fio;

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	@Override
	public String toString() {
		return fio;
	}

	public Tenant(String fio) {
		super();
		this.fio = fio;
	}

	public Tenant() {
		super();
	}
	
}

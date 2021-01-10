package entity;

import java.sql.Date;

public class Payment extends AbstractEntity{
	
	private Date dates;
	private Tenant tenant;
	private Service service;
	private float summ;
	private float fine;
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public float getSumm() {
		return summ;
	}
	public void setSumm(float summ) {
		this.summ = summ;
	}
	public float getFine() {
		return fine;
	}
	public void setFine(float fine) {
		this.fine = fine;
	}
	public Payment(Date dates, Tenant tenant, Service service, float summ, float fine) {
		super();
		this.dates = dates;
		this.tenant = tenant;
		this.service = service;
		this.summ = summ;
		this.fine = fine;
	}
	public Payment() {
		super();
	}
	
	
}

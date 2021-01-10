package entity;

import java.sql.Date;
/**
 * Сущность Учета
 * 
 * @author ya
 *
 */
public class Payment extends AbstractEntity{
	/**
	 * Дата оплаты
	 */
	private Date dates;
	/**
	 * Квартиросъемщик
	 */
	private Tenant tenant;
	/**
	 * Услуга
	 */
	private Service service;
	/**
	 * Сумма оплаты
	 */
	private float summ;
	/**
	 * Пеня
	 */
	private float fine;
	/**
	 * Получение поля
	 * @return dates
	 */
	public Date getDates() {
		return dates;
	}
	/**
	 * Установка поля
	 * @param dates
	 */
	public void setDates(Date dates) {
		this.dates = dates;
	}
	/**
	 * Получение поля
	 * @return dates
	 */
	public Tenant getTenant() {
		return tenant;
	}
	/**
	 * Установка поля
	 * @param tenant
	 */
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	/**
	 * Получение поля
	 * @return service
	 */
	public Service getService() {
		return service;
	}
	/**
	 * Установка поля
	 * @param service
	 */
	public void setService(Service service) {
		this.service = service;
	}
	/**
	 * Получение поля
	 * @return summ
	 */
	public float getSumm() {
		return summ;
	}
	/**
	 * Установка поля
	 * @param summ
	 */
	public void setSumm(float summ) {
		this.summ = summ;
	}
	/**
	 * Получение поля
	 * @return fine
	 */
	public float getFine() {
		return fine;
	}
	/**
	 * Установка поля
	 * @param fine
	 */
	public void setFine(float fine) {
		this.fine = fine;
	}
	/**
	 * Конструктор с параметром
	 * 
	 * @param fio
	 */
	public Payment(Date dates, Tenant tenant, Service service, float summ, float fine) {
		super();
		this.dates = dates;
		this.tenant = tenant;
		this.service = service;
		this.summ = summ;
		this.fine = fine;
	}
	/**
	 * Конструктор без параметров
	 */
	public Payment() {
		super();
	}
	
	
}

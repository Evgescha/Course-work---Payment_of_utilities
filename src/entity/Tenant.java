package entity;

/**
 * Сущность квартиросъемщика
 * 
 * @author ya
 *
 */
public class Tenant extends AbstractEntity {
	/**
	 * fio квартиросъемщика
	 */
	private String fio;

	/**
	 * Получение поля
	 * 
	 * @return fio
	 */
	public String getFio() {
		return fio;
	}

	/**
	 * Установка поля
	 * 
	 * @param fio
	 */
	public void setFio(String fio) {
		this.fio = fio;
	}

	@Override
	public String toString() {
		return fio;
	}

	/**
	 * Конструктор с параметром
	 * 
	 * @param fio
	 */
	public Tenant(String fio) {
		super();
		this.fio = fio;
	}

	/**
	 * Конструктор без параметров
	 */
	public Tenant() {
		super();
	}

}

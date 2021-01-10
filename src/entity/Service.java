package entity;
/**
 * Сущность услуга
 * 
 * @author ya
 *
 */
public class Service extends AbstractEntity{
	/**
	 * Название услуги
	 */
	private String name;
	/**
	 * Получение поля
	 * 
	 * @return наименование
	 */
	public String getName() {
		return name;
	}

	/**
	 * Установка поля
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	/**
	 * Конструктор с параметром
	 * 
	 * @param name
	 */
	public Service(String name) {
		super();
		this.name = name;
	}
	/**
	 * Конструктор без параметров
	 */
	public Service() {
		super();
	}
	
}

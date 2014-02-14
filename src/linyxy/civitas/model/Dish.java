package linyxy.civitas.model;

public class Dish {

	private String name		;//菜名
	private String hungerChange;	//饥饿变化
	private String happyChange	;//快乐变化
	private String healthChange	;//健康变化
	private String energyChange	;//精力变化
	private String luxuryChange	;//奢侈度变化
	private String price		;//价格
	private String remains		;//是否剩余

	public Dish() {
		// TODO Auto-generated constructor stub
	}

	
	public Dish(String name, String hungerChange, String happyChange,
			String healthChange, String energyChange, String luxuryChange,
			String price, String remains) {
		super();
		this.name = name;
		this.hungerChange = hungerChange;
		this.happyChange = happyChange;
		this.healthChange = healthChange;
		this.energyChange = energyChange;
		this.luxuryChange = luxuryChange;
		this.price = price;
		this.remains = remains;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dish [name=" + name + ", hungerChange=" + hungerChange
				+ ", happyChange=" + happyChange + ", healthChange="
				+ healthChange + ", energyChange=" + energyChange
				+ ", luxuryChange=" + luxuryChange + ", price=" + price
				+ ", remains=" + remains + "]";
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hungerChange
	 */
	public String getHungerChange() {
		return hungerChange;
	}

	/**
	 * @param hungerChange the hungerChange to set
	 */
	public void setHungerChange(String hungerChange) {
		this.hungerChange = hungerChange;
	}

	/**
	 * @return the happyChange
	 */
	public String getHappyChange() {
		return happyChange;
	}

	/**
	 * @param happyChange the happyChange to set
	 */
	public void setHappyChange(String happyChange) {
		this.happyChange = happyChange;
	}

	/**
	 * @return the healthChange
	 */
	public String getHealthChange() {
		return healthChange;
	}

	/**
	 * @param healthChange the healthChange to set
	 */
	public void setHealthChange(String healthChange) {
		this.healthChange = healthChange;
	}

	/**
	 * @return the energyChange
	 */
	public String getEnergyChange() {
		return energyChange;
	}

	/**
	 * @param energyChange the energyChange to set
	 */
	public void setEnergyChange(String energyChange) {
		this.energyChange = energyChange;
	}

	/**
	 * @return the luxuryChange
	 */
	public String getLuxuryChange() {
		return luxuryChange;
	}

	/**
	 * @param luxuryChange the luxuryChange to set
	 */
	public void setLuxuryChange(String luxuryChange) {
		this.luxuryChange = luxuryChange;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the remains
	 */
	public String getRemains() {
		return remains;
	}

	/**
	 * @param remains the remains to set
	 */
	public void setRemains(String remains) {
		this.remains = remains;
	}

}

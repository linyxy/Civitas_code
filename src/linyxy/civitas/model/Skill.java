package linyxy.civitas.model;

public class Skill {

	private String skillName;
	private String ExpPercentage;
	private String image;
	
	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(String skillName, String expPercentage) {
		super();
		this.skillName = skillName;
		ExpPercentage = expPercentage;
	}

	public Skill(String skillName, String expPercentage, String image) {
		super();
		this.skillName = skillName;
		ExpPercentage = expPercentage;
		this.image = image;
	}

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/**
	 * @return the expPercentage
	 */
	public String getExpPercentage() {
		return ExpPercentage;
	}

	/**
	 * @param expPercentage the expPercentage to set
	 */
	public void setExpPercentage(String expPercentage) {
		ExpPercentage = expPercentage;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

}

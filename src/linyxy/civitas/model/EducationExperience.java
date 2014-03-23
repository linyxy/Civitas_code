package linyxy.civitas.model;

import java.util.ArrayList;

public class EducationExperience {
	
	private String skillName;		//������
	private String skillLevel;		//���ܵȼ�
	private String comprehension;		//�����
	private String breakingProp;		//ͻ�Ƹ���
	private String description;		//��������
	private ArrayList<Skill> subSkills;		//����ĳ�����������ļ���

	public EducationExperience(String skillName, String skillLevel,
			String comprehension, String breakingProp, String description) {
		super();
		this.skillName = skillName;
		this.skillLevel = skillLevel;
		this.comprehension = comprehension;
		this.breakingProp = breakingProp;
		this.description = description;
	}

	/**
	 * �����µ�educationExperience��������һ������sub Skill��ArrayList
	 * @param skillName
	 * @param skillLevel
	 * @param comprehension
	 * @param breakingProp
	 * @param description
	 * @param subSkills
	 */
	public EducationExperience(String skillName, String skillLevel,
			String comprehension, String breakingProp, String description,
			ArrayList<Skill> subSkills) {
		super();
		this.skillName = skillName;
		this.skillLevel = skillLevel;
		this.comprehension = comprehension;
		this.breakingProp = breakingProp;
		this.description = description;
		this.subSkills = subSkills;
	}


	public EducationExperience() {
		// TODO Auto-generated constructor stub
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
	 * @return the skillLevel
	 */
	public String getSkillLevel() {
		return skillLevel;
	}

	/**
	 * @param skillLevel the skillLevel to set
	 */
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	/**
	 * @return the comprehension
	 */
	public String getComprehension() {
		return comprehension;
	}

	/**
	 * @param comprehension the comprehension to set
	 */
	public void setComprehension(String comprehension) {
		this.comprehension = comprehension;
	}

	/**
	 * @return the breakingProp
	 */
	public String getBreakingProp() {
		return breakingProp;
	}

	/**
	 * @param breakingProp the breakingProp to set
	 */
	public void setBreakingProp(String breakingProp) {
		this.breakingProp = breakingProp;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the subSkills
	 */
	public ArrayList<Skill> getSubSkills() {
		return subSkills;
	}

	/**
	 * @param subSkills the subSkills to set
	 */
	public void setSubSkills(ArrayList<Skill> subSkills) {
		this.subSkills = subSkills;
	}

}

package linyxy.civitas.model;

public class Speech {

	private String image;	//�û�ͷ��
	private String speaker;	//�ݽ��û���
	private String content;	//�ݽ�����
	private String cheer	;//������
	private String attention;	//��ע��
	private String  hooting	;//������
	private String time;	//����ʱ��
	
	
	public Speech() {
		// TODO Auto-generated constructor stub
	}

	




	public Speech(String speaker, String content, String cheer,
			String attention, String hooting, String time) {
		super();
		this.speaker = speaker;
		this.content = content;
		this.cheer = cheer;
		this.attention = attention;
		this.hooting = hooting;
		this.time = time;
	}






	public Speech(String image, String speaker, String content, String cheer,
			String attention, String hooting, String time) {
		super();
		this.image = image;
		this.speaker = speaker;
		this.content = content;
		this.cheer = cheer;
		this.attention = attention;
		this.hooting = hooting;
		this.time = time;
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


	/**
	 * @return the speaker
	 */
	public String getSpeaker() {
		return speaker;
	}


	/**
	 * @param speaker the speaker to set
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the cheer
	 */
	public String getCheer() {
		return cheer;
	}


	/**
	 * @param cheer the cheer to set
	 */
	public void setCheer(String cheer) {
		this.cheer = cheer;
	}


	/**
	 * @return the attention
	 */
	public String getAttention() {
		return attention;
	}


	/**
	 * @param attention the attention to set
	 */
	public void setAttention(String attention) {
		this.attention = attention;
	}


	/**
	 * @return the hooting
	 */
	public String getHooting() {
		return hooting;
	}


	/**
	 * @param hooting the hooting to set
	 */
	public void setHooting(String hooting) {
		this.hooting = hooting;
	}


	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

}

package linyxy.civitas.model;

public class Chat {

	private String targetPerson;
	private String content;
	private int sendDirection;
	
	public static final int SEND_IN = 0;
	public static final int SEND_OUT= 1;
	
	public Chat() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Chat(String targetPerson, String content, int sendDirection) {
		super();
		this.targetPerson = targetPerson;
		this.content = content;
		this.sendDirection = sendDirection;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chat [targetPerson=" + targetPerson + ", content=" + content
				+ ", sendDirection=" + sendDirection + "]";
	}

	/**
	 * @return the targetPerson
	 */
	public String getTargetPerson() {
		return targetPerson;
	}

	/**
	 * @param targetPerson the targetPerson to set
	 */
	public void setTargetPerson(String targetPerson) {
		this.targetPerson = targetPerson;
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
	 * @return the sendDirection
	 */
	public int getSendDirection() {
		return sendDirection;
	}

	/**
	 * @param sendDirection the sendDirection to set
	 */
	public void setSendDirection(int sendDirection) {
		this.sendDirection = sendDirection;
	}

}

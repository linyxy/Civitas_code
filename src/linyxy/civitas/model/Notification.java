package linyxy.civitas.model;

public class Notification {

	private String content;
	private int notificationType;
	
	
	public static final int INDIVIDUAL = 1;
	public static final int FRIENDSHIP = 2;
	public static final int EXCHANGE =3;
	public static final int JOBANDESTATE = 4;
	
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}


	public Notification(String content, int notificationType) {
		super();
		this.content = content;
		this.notificationType = notificationType;
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [content=" + content + ", notificationType="
				+ notificationType + "]";
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
	 * @return the notificationType
	 */
	public int getNotificationType() {
		return notificationType;
	}


	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}
	

}

package linyxy.civitas.model;

import java.util.ArrayList;
import java.util.Map;

public class Notification {

	private String id;
	private boolean is_unread;
	private String message;
	private String related_entity_id;
	private String related_entity_name;
	private Map<String,String> related_links;
	private String comment;
	

	
	public static final String Interactions	 = "Interactions";
	public static final String Relationships = "Relationships";
	public static final String Recipes		 ="Recipes";
	public static final String Occupancies	 = "Occupancies";
	
	public static final String Inventories = "Inventories";
	public static final String Transactions = "Transactions";
	public static final String Estates = "Estates";
	public static final String VolumeTransfer = "VolumeTransfer";
	
	
	public static String[] links = {Interactions,Relationships,Recipes,
		Occupancies,Inventories,Transactions,Estates,VolumeTransfer};

	
	public Notification(String id, boolean is_unread, String message,
			String related_entity_id, String related_entity_name,
			Map<String, String> related_links, String comment) {
		super();
		this.id = id;
		this.is_unread = is_unread;
		this.message = message;
		this.related_entity_id = related_entity_id;
		this.related_entity_name = related_entity_name;
		this.related_links = related_links;
		this.comment = comment;
	}
	
	
	
	@Override
	public String toString() {
		return "Notification [id=" + id + ", is_unread=" + is_unread
				+ ", message=" + message + ", related_entity_id="
				+ related_entity_id + ", related_entity_name="
				+ related_entity_name + ", related_links=" + related_links
				+ ", comment=" + comment + "]";
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isIs_unread() {
		return is_unread;
	}
	public void setIs_unread(boolean is_unread) {
		this.is_unread = is_unread;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRelated_entity_id() {
		return related_entity_id;
	}
	public void setRelated_entity_id(String related_entity_id) {
		this.related_entity_id = related_entity_id;
	}
	public String getRelated_entity_name() {
		return related_entity_name;
	}
	public void setRelated_entity_name(String related_entity_name) {
		this.related_entity_name = related_entity_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Map<String, String> getRelated_links() {
		return related_links;
	}
	

	

}


	
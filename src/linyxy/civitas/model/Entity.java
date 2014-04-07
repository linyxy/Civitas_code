package linyxy.civitas.model;

public class Entity {
	
	private String type;
	private String id;
	private String avatar;
	private String name;
	private String nick;
	private String level;
	private String expNow;
	private String expMax;
	private String relationship;
	private String at;
	
	static final String[] list  = new String[]{"type","id","avatar","name"
		,"nick","level","expNow","expMax","relationship","at"};
	static final String[] types = new String[]{"People","Estates","Unknow"};
	
	
	
	
	public Entity(String type, String id, String avatar, String name,
			String nick, String level, String expNow, String expMax,
			String relationship, String at) {
		super();
		this.type = type;
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.nick = nick;
		this.level = level;
		this.expNow = expNow;
		this.expMax = expMax;
		this.relationship = relationship;
		this.at = at;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getExpNow() {
		return expNow;
	}
	public void setExpNow(String expNow) {
		this.expNow = expNow;
	}
	public String getExpMax() {
		return expMax;
	}
	public void setExpMax(String expMax) {
		this.expMax = expMax;
	}
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
}

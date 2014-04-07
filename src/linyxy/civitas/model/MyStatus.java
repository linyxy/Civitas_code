package linyxy.civitas.model;

import java.util.ArrayList;
import java.util.Map;

import linyxy.civitas.util.DataRequest;

import org.json.JSONException;
import org.json.JSONObject;

import structure.SharedPreferenceUtil;
import android.content.Context;

public class MyStatus {

	private String avatar;
    private String entity_id;
    private String type;
    private String id;
    
    //name
    private String name;
    private String nick;
    
    //4围
    private String lv;//等级
    private String stamina;// 精力
    private String happiness;//快乐
    private String health;//健康
    private String starvation;//饥饿
    private Map<String,String> house;
    private Map<String,String> work;
    private ArrayList<EducationExperience> skill;//基础的技能
    
    private static final String[] keys =new String[]{"avatar","entity_id","type","id","name","nick","lv"
    		,"stamina","happiness","health","starvation"};
    static final String[] house_keys = new String[]{"house_id","house_name"};
    static final String[] work_keys  = new String[]{"work_id","work_name","work_info"};
    
    /**
     * 用于储存MyStatus的函数,
     * 四维存在ShareP的pseronStatus下
     * 具体存入项目可查看此model
     * @param ctx
     * @param obj 储存内容
     * @return true ｜ false
     */
    public static boolean saveMyStatus(Context ctx,JSONObject obj)
    {
    	try {
	    	for(int i=0;i<keys.length;i++)
	    	{
	    		
	    			if(keys[i].equals("name")  || keys[i].equals("nick"))
	    			{
	    				SharedPreferenceUtil.updateSharedPreference(ctx, 
	    						DataRequest.pseronStatus, keys[i], 
	    						obj.getJSONObject("name").getJSONObject("name").optString(keys[i]));
	    				continue;
	    			}
	    		
					SharedPreferenceUtil.updateSharedPreference(ctx, 
							DataRequest.pseronStatus, keys[i], obj.getString(keys[i]));
	    	}
	    	for(int i=0;i<house_keys.length;i++)
	    	{
	    			    		
					SharedPreferenceUtil.updateSharedPreference(ctx, 
							DataRequest.pseronStatus, house_keys[i], 
							obj.getJSONObject("house").getString(house_keys[i]));
	    	}
	    	for(int i=0;i<work_keys.length;i++)
	    	{
	    			    		
					SharedPreferenceUtil.updateSharedPreference(ctx, 
							DataRequest.pseronStatus, work_keys[i],
							obj.getJSONObject("work").getString(work_keys[i]));
	    	}
	    	
	    	
	    	return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

    
	public MyStatus(String avatar, String entity_id, String type, String id,
			String name, String nick, String lv, String stamina,
			String happiness, String health, String starvation,
			Map<String, String> house, Map<String, String> work,
			ArrayList<EducationExperience> skill) {
		super();
		this.avatar = avatar;
		this.entity_id = entity_id;
		this.type = type;
		this.id = id;
		this.name = name;
		this.nick = nick;
		this.lv = lv;
		this.stamina = stamina;
		this.happiness = happiness;
		this.health = health;
		this.starvation = starvation;
		this.house = house;
		this.work = work;
		this.skill = skill;
	}
	
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
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
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getStamina() {
		return stamina;
	}
	public void setStamina(String stamina) {
		this.stamina = stamina;
	}
	public String getHappiness() {
		return happiness;
	}
	public void setHappiness(String happiness) {
		this.happiness = happiness;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getStarvation() {
		return starvation;
	}
	public void setStarvation(String starvation) {
		this.starvation = starvation;
	}
	public Map<String, String> getHouse() {
		return house;
	}
	public void setHouse(Map<String, String> house) {
		this.house = house;
	}
	public Map<String, String> getWork() {
		return work;
	}
	public void setWork(Map<String, String> work) {
		this.work = work;
	}
	public ArrayList<EducationExperience> getSkill() {
		return skill;
	}
	public void setSkill(ArrayList<EducationExperience> skill) {
		this.skill = skill;
	}
    
    
}

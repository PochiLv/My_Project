package com.lml.model;


public class Light {
	private String id;
	private Linght_State state=new Linght_State();
	private String type;
	private String name;
	private String modelid;
	private String manufactureranme;
	private String uniqueid;
	private String swversion;
	
	
	public Light(String id, Linght_State state, String type, String name, String modelid, String manufactureranme,
			String uniqueid, String swversion) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.name = name;
		this.modelid = modelid;
		this.manufactureranme = manufactureranme;
		this.uniqueid = uniqueid;
		this.swversion = swversion;
	}
	public Light() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getManufactureranme() {
		return manufactureranme;
	}
	public void setManufactureranme(String manufactureranme) {
		this.manufactureranme = manufactureranme;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getSwversion() {
		return swversion;
	}
	public void setSwversion(String swversion) {
		this.swversion = swversion;
	}
	public Linght_State getState() {
		return state;
	}
	public void setState(Linght_State state) {
		this.state = state;
	}
	
}

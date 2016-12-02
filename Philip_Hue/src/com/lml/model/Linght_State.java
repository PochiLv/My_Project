package com.lml.model;

public class Linght_State {
	private Boolean on = null;
	private Integer bri = null;
	private Integer hue = null;
	private Integer sat = null;
	private Double[] xy = null;
	private String effect = null;
	private String colormode = null;
	private Boolean reachable = null;
	private String alert = null;
	private Integer ct = null;
	public Boolean getOn() {
		return on;
	}
	public void setOn(Boolean on) {
		this.on = on;
	}
	public Integer getBri() {
		return bri;
	}
	public void setBri(Integer bri) {
		this.bri = bri;
	}
	public Integer getHue() {
		return hue;
	}
	public void setHue(Integer hue) {
		this.hue = hue;
	}
	public Integer getSat() {
		return sat;
	}
	public void setSat(Integer sat) {
		this.sat = sat;
	}
	public Double[] getXy() {
		return xy;
	}
	public void setXy(Double[] xy) {
		this.xy = xy;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getColormode() {
		return colormode;
	}
	public void setColormode(String colormode) {
		this.colormode = colormode;
	}
	public Boolean getReachable() {
		return reachable;
	}
	public void setReachable(Boolean reachable) {
		this.reachable = reachable;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public Integer getCt() {
		return ct;
	}
	public void setCt(Integer ct) {
		this.ct = ct;
	}
	public Linght_State() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Linght_State(Boolean on, Integer bri, Integer hue, Integer sat, Double[] xy, String effect, String colormode,
			Boolean reachable, String alert, Integer ct) {
		super();
		this.on = on;
		this.bri = bri;
		this.hue = hue;
		this.sat = sat;
		this.xy = xy;
		this.effect = effect;
		this.colormode = colormode;
		this.reachable = reachable;
		this.alert = alert;
		this.ct = ct;
	}


}

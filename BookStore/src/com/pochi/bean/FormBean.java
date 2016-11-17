package com.pochi.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormBean {
	private String name;
	private String password;
	private String password2;
	private String birthday; 
	private String email;
	private String cellphone;
	private String id;
	private String address;
	private Map<String, String>errors=new LinkedHashMap<String, String>();
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public FormBean() {
		super();
	}
	
	public FormBean(String name, String password, String password2,
			String birthday, String email, String cellphone, String id,
			String address, Map<String, String> errors) {
		super();
		this.name = name;
		this.password = password;
		this.password2 = password2;
		this.birthday = birthday;
		this.email = email;
		this.cellphone = cellphone;
		this.id = id;
		this.address = address;
		this.errors = errors;
	}
	public boolean isValide(){
		boolean flag=true;
		/*
	     * validate��������У���������
	     * ��������У�����
	     *         private String userName; �û�������Ϊ�գ�����Ҫ��3-8����ĸ���ֺ��»��� abcdABcd 
	     *         private String userPwd; ���벻��Ϊ�գ�����Ҫ��6-16��������ĸ�����»���
	     *         private String confirmPwd; ��������Ҫһ��
	     *         private String email; ����Ϊ�գ���Ϊ��Ҫ��һ���Ϸ������� 
	     *         private String birthday; ����Ϊ�գ���Ϊ��ʱ��Ҫ��һ���Ϸ�������
	     */
		if (this.name==null||this.name.trim().equals("")) {
			flag=false;
			errors.put("username", "�û�������Ϊ��");
		}else if (!this.name.matches("\\w{3,16}")) {
			flag=false;
			errors.put("username", "�û���������3-16λ��ĸ�����ֻ��»���_���");
		}
		if(this.password==null||this.password.trim().equals("")){
			flag=false;
			errors.put("password", "���벻��Ϊ��");
		}else if(!this.password.matches("\\w{6,16}")){
			flag=false;
			errors.put("password", "���������6-16λ��ĸ�����ֻ��»���_���");
		}
		if (!this.password2.equals(this.password)) {
			flag=false;
			errors.put("password2", "���������������һ��");
		}
		if(this.email!=null&&!this.email.trim().equals("")){
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				flag=false;
				errors.put("email", "�������ʽ��ȷ��email");
			}
		}
		if(this.birthday!=null&&!this.birthday.trim().equals("")){
			try{
				DateFormat format=DateFormat.getDateInstance();
				Date da=format.parse(this.birthday);
			}catch(Exception e){
				flag=false;
				errors.put("birthday", "������������ȷ��ʽ������");
			}
		}
		
		if(this.address==null||this.address.trim().equals("")){
			flag=false;
			errors.put("address", "�ջ���ַ����Ϊ��");
		}
		if (this.cellphone==null||this.cellphone.trim().equals("")) {
			flag=false;
			errors.put("cellphone", " ��ϵ�绰����Ϊ��");
		}else if (!this.cellphone.matches("[1]\\d{10}")) {
			flag=false;
			errors.put("cellphone", "��������ȷ���ֻ�����");
		}
		
		return flag;
		
	}
}


package com.qq.common;

public class Message implements java.io.Serializable{
	private String mesType;//1��½�ɹ���2��¼ʧ�ܣ�3��ͨ��Ϣ����4Ҫ�����ߺ��Ѱ���5�������ߺ��ѵİ�
	private String sender;
	private String getter;
	private String con;//��������
	private String sendTime;
	public String getMesType() {
		return mesType;
	}
	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}

package com.qq.common;

public class Message implements java.io.Serializable{
	private String mesType;//1登陆成功，2登录失败，3普通信息包，4要求在线好友包，5返回在线好友的包
	private String sender;
	private String getter;
	private String con;//发送内容
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

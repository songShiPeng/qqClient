package com.qq.client.model;

import com.qq.common.QQUser;



public class QqClientUser {
	public boolean checkUser(QQUser u){
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
}

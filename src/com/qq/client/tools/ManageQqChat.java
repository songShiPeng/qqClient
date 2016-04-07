package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

public class ManageQqChat {
	public static HashMap hm=new HashMap<String,QqChat>();
	public static void addQaChat(String userId,QqChat qqList){
		hm.put(userId, qqList);
	}
	public static QqChat getQqChat(String getterId){
		return (QqChat)hm.get(getterId);
	}
}

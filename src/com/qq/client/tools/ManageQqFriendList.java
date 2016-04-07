package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	private static HashMap hm=new HashMap<String,QqFriendList>();
	public static void addQqFriendList(String userId,QqFriendList qqList){
		hm.put(userId, qqList);
	}
	public static QqFriendList getQqFriendList(String getterId){
		return (QqFriendList)hm.get(getterId);
	}
}

package com.qq.client.tools;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;

public class ClientConServerThread extends Thread{
	private Socket s;
	private String owner;
	public ClientConServerThread(Socket s,String owner){
		this.s=s;
		this.owner=owner;
	}
	public void run(){
		while(true){
			try{
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals("3")){
					QqChat qqChat=null;
					if(ManageQqChat.hm.containsKey(m.getGetter()+" "+m.getSender()))
						qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					else{
						qqChat=new QqChat(m.getGetter(),m.getSender());
						ManageQqChat.addQaChat(m.getGetter()+" "+m.getSender(), qqChat);
					}
					qqChat.showMessage(m);
				}
				else if(m.getMesType().equals("5")){
					String con=m.getCon();
					String friends[] =con.split(" ");
					System.out.print("∫√”—"+friends);
					String getter=m.getGetter();
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					if(qqFriendList!=null){
						qqFriendList.updateFriend(m);
					}
				}
				else if(m.getMesType().equals("6")){
					String con=m.getCon();
					String friends[] =con.split(" ");
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(owner);
					if(qqFriendList!=null){
						qqFriendList.updateFriend2(m);
					}
				}
			}catch(Exception e){
				
			}
		}
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
}

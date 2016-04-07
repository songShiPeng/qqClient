package com.qq.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.QQUser;

public class QqClientConServer {
	public Socket s;
	//发送第一次请求，登录
	public boolean sendLoginInfoToServer(Object o){
		boolean b=false;
		try{
			//s=new Socket("47.88.136.44",9999);
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois =new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			if(ms.getMesType().equals("1")){
				ClientConServerThread ccst=new ClientConServerThread(s,((QQUser)o).getUserId());
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((QQUser)o).getUserId(),ccst);
				b=true;
				new Thread(){
					public void run(){
						while(true){
							try {
								//s=new Socket("47.88.136.44",9999);
								s=new Socket("127.0.0.1",9999);
								ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
								oos.writeObject(new QQUser());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("ddd"+new Date());
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
						}
						}
					}
				}.start();
				
			}
			else{
				s.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
}

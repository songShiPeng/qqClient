package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;

public class QqFriendList extends JFrame implements ActionListener,MouseListener{
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String owner;
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jb1s;
	CardLayout c1;
	public void updateFriend(Message m){
		String onLineFriend[]=m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++){
			jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	public void updateFriend2(Message m) throws IOException{
		for(int i=0;i<jb1s.length;i++){
			jb1s[i].setEnabled(false);
		}
		ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
		Message m2=new Message();
		m.setSender(owner);
		m.setMesType("4");
		oos.writeObject(m);
	}
	public QqFriendList(String ownerId){
		this.owner=ownerId;
		//处理第一张卡片，显示好友列表
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb3=new JButton("黑名单");
		jphy1=new JPanel(new BorderLayout());
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		//给jphy2初始化50个好友
		jb1s=new JLabel[50];
		for(int i=0;i<jb1s.length;i++){
			jb1s[i]=new JLabel(i+1+"",new ImageIcon("image/head.png"),JLabel.LEFT);
			jb1s[i].setEnabled(false);
			if(jb1s[i].getText().equals(ownerId)){
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);
			jphy2.add(jb1s[i]);
		}
		jphy3=new JPanel(new GridLayout(2,1));
		//自己写的
		jsp1=new JScrollPane(jphy2);
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		this.add(jsp1);
		this.setTitle(ownerId);
		this.setSize(350,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy_jb2){
			c1.show(this.getContentPane(), "2");
		}
		else{
			c1.show(this.getContentPane(), "1");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			String no=((JLabel)e.getSource()).getText();
			QqChat qqChat=new QqChat(this.owner,no);
			ManageQqChat.addQaChat(this.owner+" "+no,qqChat);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setOpaque(true);
		j1.setBackground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setForeground(Color.black);
		j1.setOpaque(false);
		j1.setBackground(Color.black);
	}
}

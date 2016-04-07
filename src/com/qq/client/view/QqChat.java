package com.qq.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;

public class QqChat extends JFrame implements ActionListener{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	JScrollPane js1;
	public QqChat(String ownerId,String friend){
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jta.setEditable(false);
		//jta.setEnabled(false);
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		js1=new JScrollPane(jta);
		this.add(js1,"Center");
		this.add(jp,"South");
		this.setTitle("正在对话"+friend);
		this.setIconImage((new ImageIcon("image/head.png").getImage()));
		this.setSize(300,200);
		this.setVisible(true);
		this.setResizable(false);
	}
	public void showMessage(Message m){
		String info=m.getSender()+"说:     "+m.getCon()+"\r\n";
		jta.setForeground(Color.red);
		this.jta.append(info);
		jta.setCaretPosition(jta.getText().length());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			Message m=new Message();
			m.setMesType("3");
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			try{
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				jtf.setText("");
				jta.setForeground(Color.blue);
				this.jta.append("你说："+m.getCon()+"\r\n");
				jta.setCaretPosition(jta.getText().length());
			}catch(Exception e2){
				
			}
		}
	}

}

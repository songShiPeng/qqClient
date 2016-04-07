package com.qq.client.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.QQUser;

public class QqClientLogin extends JFrame implements ActionListener{
	JLabel jbl1;
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp4_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	public static void main(String [] args){
		QqClientLogin qqClientLogin=new QqClientLogin();
	}
	public QqClientLogin(){
		jbl1=new JLabel(new ImageIcon("qq_top.jpg"));
		jp2=new JPanel(new GridLayout(3,3));//网格布局
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jtf=new JTextField(20);
		jp2_jb1=new JButton("登录");
		jp2_jpf=new JPasswordField(20);
		//
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		//jp2.add(jp2_jb1);
		
		jp2.add(jp2_jbl3);
		
		jtp=new JTabbedPane();
		jtp.add("QQ号码",jp2);
		jp3=new JPanel();
		jtp.add("手机号码",jp3);
		jp4=new JPanel();
		jtp.add("电子邮件",jp4);
		//jp1
		jp1=new JPanel();
		jp1_jb1=new JButton("登录");
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton("取消");
		jp1_jb3=new JButton("向导");
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp1_jb1){
			QqClientUser qqClientUser =new QqClientUser();
			QQUser u=new QQUser();
			u.setUserId(jp2_jtf.getText().trim());
			System.out.println("用户"+u.getUserId());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			System.out.println("密码"+u.getPasswd());
			if(qqClientUser.checkUser(u)){
				try{
					//创建qq列表
					QqFriendList qqList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(),qqList);
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					Message m=new Message();
					m.setSender(u.getUserId());
					m.setMesType("4");
					oos.writeObject(m);
				}catch(Exception e2){
					
				}
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(this, "用户名密码错误");
			}
		}
	}
	
}

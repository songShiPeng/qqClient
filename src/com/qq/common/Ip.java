package com.qq.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Ip {
	private static Document document = null;// xmlÎÄµµ¶ÔÏó
	static InputStream ins=null;
	public static String getIp() throws DocumentException{
			try {
				 ins = new FileInputStream("ip.xml");
			} catch (FileNotFoundException e) {
				
			}
		SAXReader reader = new SAXReader();
		document = reader.read(ins);
		List<Element> elements = document.selectNodes("/ip/ipValue");
		String name=((Element) elements.get(0)).attributeValue("driverName");
		System.out.println("name="+name);
		return name;
	}
}

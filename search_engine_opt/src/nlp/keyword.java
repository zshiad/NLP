package nlp;

import java.util.HashMap;
import java.util.Map;

public class keyword {
	private int tags1;
	private int content1;
	private int chapter1;
	private int section1;
	private int sum1;
	private double sumwithinfo1;
	private int tags2;
	private int content2;
	private int chapter2;
	private int section2;
	private int sum2;
	private double sumwithinfo2;
	private int tags3;
	private int content3;
	private int chapter3;
	private int section3;
	private int sum3;
	private double sumwithinfo3;
	private double totalsum;

	public keyword(	int tags1,int content1,int chapter1,int section1,int sum1,double sumwithinfo1,
			int tags2,int content2,int chapter2,int section2,int sum2,double sumwithinfo2,
			int tags3,int content3,int chapter3,int section3,int sum3,double sumwithinfo3,double totalsum){
	this.tags1=tags1;
	this.content1=content1;
	this.chapter1=chapter1;
	this.section1=section1;
	this.sum1=sum1;
	this.sumwithinfo1=sumwithinfo1;
	this.tags1=tags2;
	this.content1=content2;
	this.chapter1=chapter2;
	this.section1=section2;
	this.sum1=sum2;
	this.sumwithinfo1=sumwithinfo2;
	this.tags1=tags3;
	this.content1=content3;
	this.chapter1=chapter3;
	this.section1=section3;
	this.sum1=sum3;
	this.sumwithinfo1=sumwithinfo3;
	}
	public Map geteveryproblem(){
		Map<Integer, keyword> map=new HashMap<Integer,keyword>();
		return map;
		
	}
	
}

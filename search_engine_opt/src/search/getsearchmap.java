package search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import dao.Item;
import dao.connection;

public class getsearchmap {


	
	private String keyword1;
	private String keyword2;
	private String keyword3;
	
	
	private double info_entropy_keyword1;
	private double info_entropy_keyword2;
	private double info_entropy_keyword3;
	
	public  List< dao.Item>  initial_map()
	{
		List<dao.Item>   mymap  = new ArrayList< dao.Item> ();
		Connection conn =   new dao.connection().getDao();
		 
  		// String sql = "select CONCAT(  a.tags ,  _utf8' , ', b.tags   ) as tags, CONCAT(a.chapter_title , _utf8' ', a.section_title,  _utf8' ') as title,   a.content_view , c.content_edit as prerequisite,d.content_edit as solution    from search as a, search as b,    problems_attributes as c  where a.problem_id = b.problem_id  and a.problem_id = c.problem_id and a.problem_id=d.problem_id   and a.type='main' and b.type='sub' and c.type='prerequisite' and d.type='solution'";
		String sql = "select CONCAT(  a.tags ,  _utf8' , ', b.tags   ) as tags, CONCAT(a.chapter_title , _utf8' ', a.section_title,  _utf8' ') as title,  a.problem_id,  a.content_view  as content    from search as a, search as b  where a.problem_id = b.problem_id   and a.type='main' and b.type='sub' ";
		// 这句sql 有问题。。。应该用 full join 来写。
		
 		Statement stm;
		try {
			stm = conn.createStatement();
		    ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()) {
		    	Integer problem_id = rs.getInt("problem_id");
				String content= rs.getString("content").toLowerCase();
				String tags= rs.getString("tags").toLowerCase();
				String title= rs.getString("title").toLowerCase();
		        dao.Item myitem = new dao.Item();
		        myitem.setProblem_id(problem_id);
		        myitem.setContent(content);
		        myitem.setTags(tags);
		        myitem.setTitle(title);
		        mymap.add(myitem);
		         
		    }
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mymap;
	}
	
	public List<dao.Item> calculate_nums(List<dao.Item> mymap)
	{
		this.info_entropy_keyword1 = getinfo_entropy (this.keyword1);
		this.info_entropy_keyword2 = getinfo_entropy (this.keyword2);
		this.info_entropy_keyword3 = getinfo_entropy (this.keyword3);
		List<dao.Item> mymap_new   = new ArrayList<dao.Item> (); 
		
		
		Iterator it = mymap.iterator();
	    while (it.hasNext()) {
 	    //    System.out.println(pair.getKey() + " = " + pair.getValue());
	          dao.Item myitem = new dao.Item();
	          myitem= (Item) it.next();
	    	  Integer problemid = myitem.getProblem_id();
	    	  
	    	  
	    	  
	     // prerequisite & solution
 
	    	  myitem.setKeyword1(this.keyword1);
	    	  Integer tags_num1 = calculate_keyword_num(myitem.getTags(), this.keyword1);
	    	  Integer content_num1 = calculate_keyword_num(myitem.getContent(), this.keyword1);
	    	  Integer title_num1 = calculate_keyword_num(myitem.getTitle(), this.keyword1);
	    	  myitem.setTags_num1(tags_num1);
	    	  myitem.setContent_num1( content_num1);
	    	  myitem.setTitle_num1(title_num1);
	    	  myitem.setInfo_entropy1(this.info_entropy_keyword1);
	    	  
	    	  myitem.setKeyword2(this.keyword2);
	    	  Integer tags_num2 = calculate_keyword_num(myitem.getTags(), this.keyword2);
	    	  Integer content_num2 = calculate_keyword_num(myitem.getContent(), this.keyword2);
	    	  Integer title_num2 = calculate_keyword_num(myitem.getTitle(), this.keyword2);
	    	  myitem.setTags_num2(tags_num2);
	    	  myitem.setContent_num2( content_num2);
	    	  myitem.setTitle_num2(title_num2);
	    	  myitem.setInfo_entropy2(this.info_entropy_keyword2);
	    	  
	    	  myitem.setKeyword3(this.keyword3);
	    	  Integer tags_num3= calculate_keyword_num(myitem.getTags(), this.keyword3);
	    	  Integer content_num3 = calculate_keyword_num(myitem.getContent(), this.keyword3);
	    	  Integer title_num3 = calculate_keyword_num(myitem.getTitle(), this.keyword3);
	    	  myitem.setTags_num3(tags_num3);
	    	  myitem.setContent_num3( content_num3);
	    	  myitem.setTitle_num3(title_num3);
	    	  myitem.setInfo_entropy3(this.info_entropy_keyword3);
	    	  
	    	  
	    	  double score = (tags_num1 + content_num1 +title_num1)* this.info_entropy_keyword1 +  (tags_num2 + content_num2 +title_num2)* this.info_entropy_keyword2 +  (tags_num3 + content_num3 +title_num3)* this.info_entropy_keyword3;
	    	  Integer number =0;
	    	  if ((tags_num1 + content_num1 +title_num1)>0) { number++;}
	    	  if ((tags_num2 + content_num2 +title_num2)>0) { number++;}
	    	  if ((tags_num3 + content_num3 +title_num3)>0) { number++;}
	    	 
	    	  myitem.setScore(score);
	    	  myitem.setNumber(number);
	    	  
	    	  mymap_new.add(myitem);
	        
	    	  it.remove(); // avoids a ConcurrentModificationException
	    }
		return mymap_new;
	}
	 
	
	
	private Integer calculate_keyword_num(String content, String keyword)
	{
				String[] str1 = content.split(keyword);
				 Integer num = str1.length-1;  
				 return num;
	}
	
	
	public double getinfo_entropy (String keyword)
	{
		double info_entropy =1;
		keyword = keyword.trim();
		Connection conn =   new dao.connection().getDao();
		
 		String sql = "select  distinct avg(info_entropy) as mean from info_entropy where keyword like '%"+keyword+"%'";
 		 System.out.println(keyword +"sql: "+sql );
 		Statement stm;
		try {
			stm = conn.createStatement();
		    ResultSet rs = stm.executeQuery(sql);
		    while(rs.next()) {
		    	info_entropy = rs.getDouble("mean");
		    }
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return info_entropy;
		
	}
	
	static class InfoComparator implements Comparator{
		 public int compare(Object object1, Object object2)
		    {
			 dao.Item p1 = (Item) object2;
			 dao.Item p2 = (Item) object1;
		       // if last names are the same compare first names
		       if(p1.getNumber().equals(p2.getNumber()))
		       {
		           return p1.getScore().compareTo(p2.getScore());
		       }
		       return p1.getNumber().compareTo(p2.getNumber());
		    }
	}
	
	public List<Integer>  search_output(String keyword1, String keyword2, String keyword3  )
	{
		this.setKeyword1(keyword1);
		this.setKeyword2(keyword2);
		this.setKeyword3(keyword3);
		List<dao.Item> mymap = initial_map();
		List< dao.Item> mylist = calculate_nums(mymap); 
 		 
		Collections.sort(mylist, new InfoComparator());
		
		List<Integer> problemid_list = new ArrayList<Integer>();
		  for(int i =0; i< mylist.size(); i++)
		  {
			  dao.Item item = mylist.get(i);
			  System.out.println(item.getProblem_id() +": Number:  " +item.getNumber() +" score:"+item.getScore());
		  }
		  
		  for(int i =0; i< mylist.size(); i++)
		  {
			  if (i<=2)
			  {
				  problemid_list.add(mylist.get(i).getProblem_id());
			  }
		  }
		
		  
		  return problemid_list;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getsearchmap mysearch = new getsearchmap();
		List<Integer> problemlist = mysearch.search_output("a", "equal", "cal");
		System.out.println("--------------------------------------------------------");
		  for(int i =0; i< problemlist.size(); i++)
		  {
			  Integer item = problemlist.get(i);
			  System.out.println(item);
		  }
		
	}
	
	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}


}

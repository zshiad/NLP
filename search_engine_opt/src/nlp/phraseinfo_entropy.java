package nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.util.GregorianCalendar;  
import java.util.Iterator;  

import dao.connection;



public class phraseinfo_entropy {
	private java.sql.Connection conn=connection.getDao();
	private ResultSet searchContent;	//Store the information of each problem into a result set
	private static List<String> phrase_list = new ArrayList<String>();
	private static List<phrase> three_phrase_list = new ArrayList<phrase>();
	private static List<Integer> prerequisite_id = new ArrayList<Integer>();
	private static List<phrase> phraseobject = new ArrayList<phrase>();
	static class infoComparator implements Comparator {
		public int compare(Object object1, Object object2) {// 实现接口中的方法  
            phrase p1 = (phrase) object1; // 强制转换  
            phrase p2 = (phrase) object2;  
            return new Double(p1.info_entropy).compareTo(new Double(p2.info_entropy));  
        }
	}


	

	public void get_prerequisite_id() throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "SELECT id FROM problem_prerequsite WHERE prerequsite LIKE ?";
		pstmt = conn.prepareStatement(sql);
		try {
		for(int i=0; i<phrase_list.size();i++){
			pstmt.clearParameters();
			pstmt.setString(1,"%"+phrase_list.get(i)+"%");
			searchContent=pstmt.executeQuery();
			  while(searchContent.next())
              {
				  prerequisite_id.add(searchContent.getInt(1));
               }
			  }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getinfo_entropy() throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "SELECT info_entropy FROM info_entropy WHERE keyword LIKE ?";
		pstmt = conn.prepareStatement(sql);
		try {
		for(int i=0; i<phrase_list.size();i++){
			pstmt.clearParameters();
			pstmt.setString(1,"%"+phrase_list.get(i)+"%");
			searchContent=pstmt.executeQuery();
			if (searchContent.next()){
				phraseobject.add(new phrase(searchContent.getDouble(1),phrase_list.get(i)));
				System.out.print(searchContent.getDouble(1));
			  }
			else{
				phraseobject.add(new phrase(1.0,phrase_list.get(i)));
				
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getthreephrase() {
		 Collections.sort(phraseobject, new infoComparator());
			three_phrase_list.add(phraseobject.get(0));
			three_phrase_list.add(phraseobject.get(1));
			three_phrase_list.add(phraseobject.get(2));
	}

	
	
	
	
	public static void main(String[] args) throws SQLException {
	Final t = new Final();
	phrase_list=t.getkeyword();
	 System.out.print(phrase_list);
	 phraseinfo_entropy s = new phraseinfo_entropy();
	 s.get_prerequisite_id();
	 s.getinfo_entropy();
	 s.getthreephrase();
	 System.out.print(prerequisite_id);
	 System.out.print(phraseobject.get(2).info_entropy);
	 }
}
		
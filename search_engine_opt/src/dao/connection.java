package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class connection {
 
	// ���ݿ�����
	private static final String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/stepdev";// ���ݿ�URL
	private static Connection conn = null;// ���ݿ�����
	private static connection dao = null;
	
	public connection() {
		try {
			//System.out.println("���Լ���");
			Class.forName(driver);
			System.out.println("���������Ѿ��ɹ�");
			conn = DriverManager.getConnection(url,"root","");
			// �������ݿ�ɹ�
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ݿ������쳣�����߱�����Ѿ����С�");
			System.exit(0);
		}
	}
	
	public static Connection getDao() {// ��ȡconnʵ��
		if (dao == null)
			dao = new connection();
		return conn;
	}
}

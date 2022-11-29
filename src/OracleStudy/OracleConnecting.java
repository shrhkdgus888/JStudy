package OracleStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnecting {

	public static void main(String[] args){
		//Connection
		// : SQLDeveloper ������ ���� ������ ����
		String user = "tester";
		String password = "1234";
		//���� url
		//@localhost �κ��� ���� �� ��ǻ�� ���ο� �����ϴ� DB�� �����ϱ⶧���� localhost(�ڱ��ڽ�)��� ������.
		//but, �ٸ���ǻ�Ϳ� �����Ѵٰ� �Ѵٸ�, localhost�� �ƴ� �ٸ� ��ǻ���� ip�� �ʿ��ϴ�.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class�� forName��� method�� Ȯ���ϰ��� �ϴ� Ŭ������ �̸��� �Է��Ͽ�, �����۵��ϴ��� Ȯ��
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Ŭ���� �ε� ����!");
			
			//DriverManger�� ���� getConnection �޼��带 ȣ���Ѵ�.
			//getConnection() : DB�� ������ �ؼ�, ���������� ��� �ִ� ��ü. Connection�� ��ȯ�Ѵ�.
			//��, �ٽø��� con������ ��������(id, pw, url)�� ������ �ִ� ���̸�, Ŀ�ؼ�(���Ӽ���)�� �Ǹ� "�����ͺ��̽� ���� ���������� �ߵ���!)
			
			//getConnection(��������) > ��ȣ���� ���������� ���� ������ �߿���.
			Connection con = DriverManager.getConnection(url, user, password);
			
			System.out.println("�����ͺ��̽� ���� ����!");
		} catch(ClassNotFoundException e)
				{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

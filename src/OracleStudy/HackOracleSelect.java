package OracleStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HackOracleSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
//		PreparedStatement(����:�غ�� ����)�� ���������� ������ ���̰� �ִ�.
//		�Ʒ� pstmt�� Ŀ�ؼ��� ���ؼ� �������� �����ϱ� ���ؼ� sql������ �غ��ϰ� �ȴ�.
		PreparedStatement pstmt = null; 
//		Statement stmt = null;
		ResultSet rs = null;
		

		String user = "tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class�� forName��� method�� Ȯ���ϰ��� �ϴ� Ŭ������ �̸��� �Է��Ͽ�, �����۵��ϴ��� Ȯ��
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Ŭ���� �ε� ����!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"INFO\"");
			
			//��  SQL injection ������ ��  - ��ŷ�� Ȱ��Ǵ� ���ݱ��
			//����ó�� ������� �Է��� ���� �������� ���� ���(EX. �Խ����̳� �۾��̸� ã�� ��)
			//, ������� �Է� ���� �������� �״�� �����ϰԵǸ� ������ ������ ���� ���� �� �ִ�.
			//���� ���, String searchName = "'or 1=1--";��� ���ڿ��� �ִٰ� �˶�,
			
			//String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			//SQL������(����) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"='' or 1=1--'
			//SQL������(����) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"=''(����) or 1=1(��)--'
			
			//�̰��� sql������ Ư¡���� where�� ���ǽ��̱� ������, � ���ǿ� ���缭 Ư�� ���ڵ带 �����ϴ� ��(=���ǿ� �´°͸� �˻��ϴ� ��)�� �ƴ϶�!
			//���� ���ǽ��� ���̸� ��� ���ڵ��� ����� Ȯ���ϰ� �ȴ�.
			//��, ���̸� �׳� where���� ������ �ȴ�.
			//�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�
			//���� �������� ������ ������� �Է°��� �������� �Է��ؾ� �ȴٸ�, String searchName = "'or 1=1--"; �� ���ڿ��� SQL ��������
			//Ȱ��� �� �ִ����� Ȯ��,�����Ҽ� �ִ� �۾��� �ʿ��� �� �ִ�. ������ �����ϴ�.
			//�׷��� pstmt�� ����Ѵ�. 
			String searchName = "'or 1=1--";
			//?�� ���ε� ����
			//�Ʒ�ó�� �������� �غ��ϰ� Ŀ�ؼǿ� ������ ���ؼ� ������ �غ��ϰԲ� ���� ���̴�.
			//�׸��� �׷��� ������� �غ�� ���������� �� ����ǥ�� ���ε� �� ���� �������ش�
			String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"= ? ";
			conn = DriverManager.getConnection(url, user, password);
			
			pstmt = conn.prepareStatement(sql);
			//pstmt�� ��ü���ٰ� ����ǥ�� �� ��(String), ù��° ���ε� ������ searchName�� ���� �����ϰڴ�. (��𿡴ٰ�? ���� �ʷϻ� ������ ����ǥ(?)���ٰ�) 
			pstmt.setString(1, searchName);
//			�������� Ȱ��� �� �ִ� �ο��ȣ���� �ִٸ�("'or 1=1--") �˾Ƽ� escape �����Ұ� �����ϰ� �ĺ����� �ʰԵȴ�.
//			�׸���, executeQuery()�� ������, �̹� �غ�� ������ �������ֱ� ������, ������ �� �ʿ䰡 ��������. 			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {

				System.out.println(rs.getLong(1));
				System.out.println(rs.getString("NAME"));
				System.out.println(rs.getDate(3));
				System.out.println(rs.getString("PHONE"));
				System.out.println();
			}
			
		} catch(ClassNotFoundException e)
				{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//finally ��Ͽ��� �ڿ�(Resource)�� ����.
		finally {
			//rs(=resultset)�� close
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} 
			//stmt�� close
			if(pstmt != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} 
			//conn �� close
			if(conn != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} 
			
		}

	}

}

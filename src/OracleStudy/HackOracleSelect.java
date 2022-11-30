package OracleStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HackOracleSelect {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//Eclipse & OracleDB Connection
		// : SQLDeveloper ������ ���� ������ ����
		String user = "tester";
		String password = "1234";
		//���� url : @localhost �κ��� ���� �� ��ǻ�� ���ο� �����ϴ� DB�� �����ϱ⶧���� localhost(�ڱ��ڽ�)��� ������.
		//but, �ٸ���ǻ�Ϳ� �����Ѵٰ� �Ѵٸ�, localhost�� �ƴ� �ٸ� ��ǻ���� ip�� �ʿ��ϴ�.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class�� forName��� method�� Ȯ���ϰ��� �ϴ� Ŭ������ �̸��� �Է��Ͽ�, �����۵��ϴ��� Ȯ��
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Ŭ���� �ε� ����!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"INFO\"");
			
			//��  SQL injection �� 
			//����ó�� ������� �Է��� ���� �������� ���� ���(EX. �Խ����̳� �۾��̸� ã�� ��)
			//, ������� �Է� ���� �������� �״�� �����ϰԵǸ� ������ ������ ���� ���� �� �ִ�.
			//���� ���, String searchName = "'or 1=1--";��� ���ڿ��� �ִٰ� �˶�,
			
			//String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			//SQL������(����) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"='' or 1=1--'
			//SQL������(����) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"=''(����) or 1=1(��)--'
			
			//�̰��� sql������ Ư¡���� where�� ���ǽ��̱� ������, � ���ǿ� ���缭 Ư�� ���ڵ带 �����ϴ� ��(=���ǿ� �´°͸� �˻��ϴ� ��)�� �ƴ϶�!
			//���� ���ǽ��� ���̸� ��� ���ڵ��� ����� Ȯ���ϰ� �ȴ�.
			//��, ���̸� �׳� where���� ������ �ȴ�.
			
			
			
			
	//�� �Է��� ������ �������� ���Ǵ� ���õ�
//			�Խ��ǿ��� �̸����� �˻��ϰų�, �Խñ� �������� �˻��ϰų� �� ��!
			
//			EX 1��) �÷��� ���������
//					sql.append("select count(*) as \CNT\" from \"INFO\"");

//			EX 2��) �÷� NUM�� 2�� ���ڵ�(��)�� �̾ƺ���
//					int searchNum = 2;
//					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NUM\"=" + searchNum;
			
//			EX 3��) �÷� NAME�� "�Ӳ���"�� ���ڵ�(��)�� �̾ƺ���
//					����! sqldeveloper�� �����ϰ� �Ӳ��� �� ������ Ȧ����ǥ(')�� ���� �ν���
//					���ϱ�) SQL ������������ select "NAME", "NUN", "BIRTH" from "INFO" where "NAME" = '�Ӳ���';
					
//					String searchName = "�Ӳ���";
//					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			
			
			//DriverManger�� ���� getConnection �޼��带 ȣ���Ѵ�.
			//getConnection() : DB�� ������ �ؼ�, ���������� ��� �ִ� ��ü. Connection�� ��ȯ�Ѵ�.
			//��, �ٽø��� con������ ��������(id, pw, url)�� ������ �ִ� ���̸�, Ŀ�ؼ�(���Ӽ���)�� �Ǹ� "�����ͺ��̽� ���� ���������� �ߵ���!)
			
			//getConnection(��������) > ��ȣ���� ���������� ���� ������ �߿���.
			conn = DriverManager.getConnection(url, user, password);
			//createStatement �޼��� ȣ�� �� Statement ��ü ����.
			stmt = conn.createStatement();
			//stmt��ü�� �����ͺ��̽��� SQL�� �����ϴ� ������ �ϸ�, �� SQL������ ������� ������ ������ �ϴµ� executeQuery()��� ȣ���ϸ鼭
			//���� �������Ҵ� SQL����(sql.append("select * from \"INFO\"");)��  �����ϴ°�
			//��, SQL Developer���� SQL>select * from "INFO"; ����! ��� ġ�°Ͱ� ������ ����� ��.
			//rs(Resultset)�̶�� ��ü�� SQL>select * from "INFO"; ����! ��� ĥ�� ������ ���� ������ ���� ������ �ִ� ��.
			rs = stmt.executeQuery(sql.toString());
			
			//���� rs��ü�� ���� ���ڵ�(��)�� �ϳ��ϳ� ��ȸ�ϰ� �ȴ�. ���⼭ rs.next()��� ���� ���� ���ڵ�(��)�� �����ϴ��� Ȯ�����ִ� ����
			//�� rs.next(); ��� ���� ��, ù��° ���ڵ�(��,row)�� �����ϴ����� ã�� �ȴ�. ex) NUM NAME  BRITH    PHONE
			//																   1   ȫ�浿 88/11/03  010-1234-1234
			
			//�����Ѵٸ�, ���ڵ��� ù��° �÷��� ������ 1, �ι�° �÷��� ������ ȫ�浿, ....�� ���(sop)�ϵ��� �Ѵ�. ���ڿ� �÷����� �Ѵ� �ᵵ������, �ڷ����� �����ؼ� �ۼ�
			while(rs.next()) {
//				EX 1��) �÷��� ���� �����
//				System.out.println("���ڵ��� ��:" + rs.getInt("CNT"));
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
			if(stmt != null) {
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

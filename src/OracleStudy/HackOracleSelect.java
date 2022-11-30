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
		// : SQLDeveloper 연결을 위한 정보들 선언
		String user = "tester";
		String password = "1234";
		//접속 url : @localhost 부분은 현재 내 컴퓨터 내부에 존재하는 DB에 접근하기때문에 localhost(자기자신)라고 적어줌.
		//but, 다른컴퓨터에 접속한다고 한다면, localhost가 아닌 다른 컴퓨터의 ip가 필요하다.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class의 forName라는 method의 확인하고자 하는 클래스의 이름을 입력하여, 정상작동하는지 확인
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"INFO\"");
			
			//☆  SQL injection ☆ 
			//지금처럼 사용자의 입력한 값이 쿼리문에 사용될 경우(EX. 게시판이나 글쓴이를 찾을 때)
			//, 사용자의 입력 값을 쿼리문에 그대로 전달하게되면 굉장히 위험한 일이 생길 수 있다.
			//예를 들어, String searchName = "'or 1=1--";라는 문자열이 있다고 알때,
			
			//String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			//SQL쿼리문(원본) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"='' or 1=1--'
			//SQL쿼리문(설명) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"=''(거짓) or 1=1(참)--'
			
			//이것은 sql구문의 특징으로 where은 조건식이긴 하지만, 어떤 조건에 맞춰서 특정 레코드를 선택하는 것(=조건에 맞는것만 검색하는 것)이 아니라!
			//뒤의 조건식이 참이면 결과 레코드의 목록을 확인하게 된다.
			//즉, 참이면 그냥 where문은 수행이 된다.
			
			
			
			
	//※ 입력한 값들이 쿼리문에 사용되는 예시들
//			게시판에서 이름으로 검색하거나, 게시글 내용으로 검색하거나 할 때!
			
//			EX 1번) 컬럼의 갯수세어보기
//					sql.append("select count(*) as \CNT\" from \"INFO\"");

//			EX 2번) 컬럼 NUM이 2인 레코드(행)만 뽑아보기
//					int searchNum = 2;
//					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NUM\"=" + searchNum;
			
//			EX 3번) 컬럼 NAME이 "임꺽정"인 레코드(행)만 뽑아보기
//					주의! sqldeveloper와 동일하게 임꺽정 양 옆에는 홀따옴표(')가 들어가야 인식함
//					비교하기) SQL 쿼리문에서는 select "NAME", "NUN", "BIRTH" from "INFO" where "NAME" = '임꺽정';
					
//					String searchName = "임꺽정";
//					String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			
			
			//DriverManger를 통해 getConnection 메서드를 호출한다.
			//getConnection() : DB에 접속을 해서, 접속정보를 담고 있는 객체. Connection을 반환한다.
			//즉, 다시말해 con변수가 접속정보(id, pw, url)를 가지고 있는 것이며, 커넥션(접속성공)이 되면 "데이터베이스 접속 성공문구가 뜨도록!)
			
			//getConnection(접속정보) > 괄호안의 접속정보에 대한 순서도 중요함.
			conn = DriverManager.getConnection(url, user, password);
			//createStatement 메서드 호출 및 Statement 객체 생성.
			stmt = conn.createStatement();
			//stmt객체는 데이터베이스에 SQL을 전송하는 역할을 하며, 그 SQL전송의 결과물을 얻어오는 역할을 하는데 executeQuery()라고 호출하면서
			//위의 만들어놓았던 SQL구문(sql.append("select * from \"INFO\"");)을  전달하는것
			//즉, SQL Developer에서 SQL>select * from "INFO"; 엔터! 라고 치는것과 동일한 기능을 함.
			//rs(Resultset)이라는 객체는 SQL>select * from "INFO"; 엔터! 라고 칠때 나오는 값과 동일한 값을 가지고 있는 것.
			rs = stmt.executeQuery(sql.toString());
			
			//위의 rs객체로 부터 레코드(행)를 하나하나 순회하게 된다. 여기서 rs.next()라는 것은 현재 레코드(행)가 존재하는지 확인해주는 역할
			//즉 rs.next(); 라고 했을 때, 첫번째 레코드(행,row)가 존재하는지를 찾게 된다. ex) NUM NAME  BRITH    PHONE
			//																   1   홍길동 88/11/03  010-1234-1234
			
			//존재한다면, 레코드의 첫번째 컬럼의 데이터 1, 두번째 컬럼의 데이터 홍길동, ....을 출력(sop)하도록 한다. 숫자와 컬럼네임 둘다 써도되지만, 자료형은 주의해서 작성
			while(rs.next()) {
//				EX 1번) 컬럼의 갯수 세어보기
//				System.out.println("레코드의 수:" + rs.getInt("CNT"));
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
		//finally 블록에서 자원(Resource)을 해제.
		finally {
			//rs(=resultset)을 close
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} 
			//stmt을 close
			if(stmt != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} 
			//conn 을 close
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

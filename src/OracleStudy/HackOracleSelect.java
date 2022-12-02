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
//		PreparedStatement(번역:준비된 문장)는 내부적으로 동작이 차이가 있다.
//		아래 pstmt는 커넥션을 통해서 쿼리문을 전송하기 위해서 sql문장을 준비하게 된다.
		PreparedStatement pstmt = null; 
//		Statement stmt = null;
		ResultSet rs = null;
		

		String user = "tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class의 forName라는 method의 확인하고자 하는 클래스의 이름을 입력하여, 정상작동하는지 확인
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"INFO\"");
			
			//☆  SQL injection 방지법 ☆  - 해킹에 활용되는 공격기법
			//지금처럼 사용자의 입력한 값이 쿼리문에 사용될 경우(EX. 게시판이나 글쓴이를 찾을 때)
			//, 사용자의 입력 값을 쿼리문에 그대로 전달하게되면 굉장히 위험한 일이 생길 수 있다.
			//예를 들어, String searchName = "'or 1=1--";라는 문자열이 있다고 알때,
			
			//String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"='" + searchName + "'";
			//SQL쿼리문(원본) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"='' or 1=1--'
			//SQL쿼리문(설명) : select "NAME", "NUM", "BIRTH" from "INFO" where "NAME"=''(거짓) or 1=1(참)--'
			
			//이것은 sql구문의 특징으로 where은 조건식이긴 하지만, 어떤 조건에 맞춰서 특정 레코드를 선택하는 것(=조건에 맞는것만 검색하는 것)이 아니라!
			//뒤의 조건식이 참이면 결과 레코드의 목록을 확인하게 된다.
			//즉, 참이면 그냥 where문은 수행이 된다.
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			//위의 문제점들 때문에 사용자의 입력값을 쿼리문에 입력해야 된다면, String searchName = "'or 1=1--"; 의 문자열이 SQL 쿼리문에
			//활용될 수 있는지를 확인,검증할수 있는 작업이 필요할 수 있다. 굉장히 불편하다.
			//그래서 pstmt를 사용한다. 
			String searchName = "'or 1=1--";
			//?는 바인딩 변수
			//아래처럼 쿼리문을 준비하고 커넥션에 연결을 통해서 쿼리를 준비하게끔 만들 것이다.
			//그리고 그렇게 만들어진 준비된 쿼리문에는 이 물음표에 바인딩 될 값을 셋팅해준다
			String sql = "select \"NAME\", \"NUM\", \"BIRTH\" from \"INFO\" where \"NAME\"= ? ";
			conn = DriverManager.getConnection(url, user, password);
			
			pstmt = conn.prepareStatement(sql);
			//pstmt의 객체에다가 물음표에 들어갈 값(String), 첫번째 바인딩 변수에 searchName의 값을 셋팅하겠다. (어디에다가? 위의 초록색 쿼리문 물음표(?)에다가) 
			pstmt.setString(1, searchName);
//			쿼리문에 활용될 수 있는 인용부호들이 있다면("'or 1=1--") 알아서 escape 제거할것 제거하고 식별하지 않게된다.
//			그리고, executeQuery()할 때에는, 이미 준비된 문장을 가지고있기 때문에, 쿼리를 줄 필요가 없어진다. 			
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
			if(pstmt != null) {
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

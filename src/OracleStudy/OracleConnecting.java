package OracleStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnecting {

	public static void main(String[] args){
		//Connection
		// : SQLDeveloper 연결을 위한 정보들 선언
		String user = "tester";
		String password = "1234";
		//접속 url
		//@localhost 부분은 현재 내 컴퓨터 내부에 존재하는 DB에 접근하기때문에 localhost(자기자신)라고 적어줌.
		//but, 다른컴퓨터에 접속한다고 한다면, localhost가 아닌 다른 컴퓨터의 ip가 필요하다.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			//DriveLoading
			// : Class의 forName라는 method의 확인하고자 하는 클래스의 이름을 입력하여, 정상작동하는지 확인
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			
			//DriverManger를 통해 getConnection 메서드를 호출한다.
			//getConnection() : DB에 접속을 해서, 접속정보를 담고 있는 객체. Connection을 반환한다.
			//즉, 다시말해 con변수가 접속정보(id, pw, url)를 가지고 있는 것이며, 커넥션(접속성공)이 되면 "데이터베이스 접속 성공문구가 뜨도록!)
			
			//getConnection(접속정보) > 괄호안의 접속정보에 대한 순서도 중요함.
			Connection con = DriverManager.getConnection(url, user, password);
			
			System.out.println("데이터베이스 접속 성공!");
		} catch(ClassNotFoundException e)
				{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

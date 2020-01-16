package com.ryon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ryon.dto.MemberDTO;


public class MemberDAO {
	// 데이터 리스트로 가져오기
	public static ArrayList<MemberDTO> select2() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/jdb?serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "root");

			// 3. 쿼리 수행을 위한 Statement 객체 생성
			String sql = "SELECT * FROM member";
			stmt = conn.prepareStatement(sql);

			// 4. SQL 쿼리 작성
			// 주의사항
			// 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
			// 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다
			// 가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
			// 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
			// 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !!
			

			// 5. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			rs = stmt.executeQuery();

			// 6. 실행결과 출력하기
			while (rs.next()) {
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				MemberDTO dto = new MemberDTO();
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				dto.setEmail(email);
				dto.setPhone(phone);
				
				list.add(dto);
				
				//System.out.println(name + "/ " + pw + "/ " + name+"/ "+email+"/ "+phone);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 데이터 보기
	public static void select() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/jdb?serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "root");

			// 3. 쿼리 수행을 위한 Statement 객체 생성
			String sql = "SELECT * FROM member";
			stmt = conn.prepareStatement(sql);

			// 4. SQL 쿼리 작성
			// 주의사항
			// 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
			// 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다
			// 가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
			// 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
			// 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !!
			

			// 5. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			rs = stmt.executeQuery();

			// 6. 실행결과 출력하기
			while (rs.next()) {
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");

				System.out.println(id + "/ " + pw + "/ " + name+"/ "+email+"/ "+phone);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		}
	
	public static MemberDTO select(String pid, String ppw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/jdb?serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "root");

			// 3. 쿼리 수행을 위한 Statement 객체 생성
			String sql = "SELECT * FROM member where id=? AND pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, ppw);

			// 4. SQL 쿼리 작성
			// 주의사항
			// 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
			// 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다
			// 가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
			// 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
			// 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !!
			

			// 5. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			rs = stmt.executeQuery();

			// 6. 실행결과 출력하기
			// rs.next = 데이터 있을때까지 날림(true 없으면 false)
			if (rs.next()) {
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				dto = new MemberDTO();
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				dto.setEmail(email);
				dto.setPhone(phone);
				
				System.out.println(id + "/ " + pw + "/ " + name+"/ "+email+"/ "+phone);
			} else {
				System.out.println("아이디가 없거나 pw가 틀림");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return dto;
		}

	// 데이터 넣기
	public static int insert(String id, String pw, String name, String email, String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0; // 외부로 보낼 결과값(0이면 실패, 0이 아니면 성공)

		try {
// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

// 2. 연결하기
			String url = "jdbc:mysql://localhost/jdb?serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "root");

// 3. SQL 쿼리 준비
// 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
// 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
// stmt = conn.createStatement(); 를 작성하지 않고
// pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
// 물론 sql 쿼리 내에서 + 연산자로 한 줄로 작성할 수 있지만 가독성이 너무 떨어지게 되므로
// 이 방법을 권합니다.
			String sql = "INSERT INTO member VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

// 4. 데이터 binding
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);

// 5. 쿼리 실행 및 결과 처리
// SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
// ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
// INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
// SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
// @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
			
			count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("데이터 입력 성공");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}

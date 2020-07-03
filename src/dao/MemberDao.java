package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBconnection;
import dto.MenuDto;


public class MemberDao {
	
	private static MemberDao dao = null;
	private String id;
	
	private MemberDao() {}
	// 싱글턴
	public static MemberDao getInstance() {
		if(dao == null){
				dao = new MemberDao();			
		}
		return dao;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	// ID 중복확인 메소드
	public boolean getId(String id) {
		// ID를 입력해서 DB에서 검색 후 출력.
		String sql = " SELECT ID"
				+	 " FROM C_MEMBER "
				+	 " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		// ID를 찾으면 true
		boolean findId = false;
			
		try {
			// db와 연결해서 db의 정보를 받아온다.
			conn = DBconnection.getConnection();
			// 쿼리문을 실행하기 전 상태를 저장.
			psmt = conn.prepareStatement(sql);
			// 쿼리문에 입력값을 대입
			psmt.setString(1, id);
			// 쿼리문을 실행한 후 출력한 데이터를 rs로 받아온다.
			rs = psmt.executeQuery();
			// 받아온 데이터가 있는지 rs가 검색
			if(rs.next()) {
				// id가 있다면 true
				findId = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 모든 실행이 끝난 후 db를 닫아준다.
			DBClose.close(psmt, conn, rs);
		}
		// 찾으면 true, 못찾으면 false
		return findId;
	}
	// 회원의 정보를 DB에 입력하는 메소드
	public boolean addMember(String id, String pwd, String name, String email) {
		// 외부에서 받아온 데이터로 테이블에 입력할 쿼리문, 회원의 데이터를 등록한다.
		String sql = " INSERT INTO C_MEMBER(ID, PWD, NAME, EMAIL) "
				+	 " VALUES(?, ?, ?, ?) ";
						
		Connection conn =null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			// 쿼리문에서 지정한 곳에 데이터를 입력한다
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, email);
					
			// 쿼리문을 적용한 row데이터의 수를 반환.
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, null);
		}
		// 등록이 성공하면 true, 실패하면 false
		return count > 0 ? true : false;
	}
	
	// 로그인할 때 ID와 패스워드 확인하는 메소드
	public int memberCheck(String id, String pwd) {
		// 입력한 id의 패스워드를 찾는 쿼리문
		String sql = " SELECT PWD " 
				+	 " FROM C_MEMBER "
				+	 " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int check = 0;
		
		try {
			
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			String db_pwd = "";
			
			while(rs.next()) {	
				db_pwd = rs.getString("PWD");
			}
			// 만약에 id가 db에 없다면 2
			if(db_pwd.equals("")) {
				check = 2;
			}
			// id가 db에는 있지만 pwd가 틀렸다면 1
			else if(!db_pwd.equals(pwd)){
				check = 1;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		// id,pwd에 문제가 없으면 0을 반환.
		return check;
	}
	// 커피 가격표를 출력
	public List<MenuDto> coffeeMenu(){
		// CHOICE테이블의 데이터를 가격이 높은 순으로 정렬해서 가져온다
		String sql = " SELECT * "
				+	 " FROM CHOICE "
				+	 " ORDER BY C_SHORT DESC";
		
		List<MenuDto> list = new ArrayList<MenuDto>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			String c_type = "";
			int c_short = 0;
			int c_tall = 0;
			int c_grande = 0;
			
			while(rs.next()) {
				c_type = rs.getString("C_TYPE");
				c_short = rs.getInt("C_SHORT");
				c_tall = rs.getInt("C_TALL");
				c_grande = rs.getInt("C_GRANDE");
				
				MenuDto  md = new MenuDto(c_type, c_short, c_tall, c_grande);
				list.add(md);	
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(stmt, conn, rs);
		}
		// 리스트로 반환
		return list;	
	}
	
	// 커피종류의 사이즈에 대응하는 가격을 반환.
	public int priceOut(String c_type, String c_size) {
		
		String sql = "";
		int c_price = 0;
		// 커피의 사이즈로 구분해서 쿼리문을 저장.
		if(c_size.equals("c_short")) {
			sql = " SELECT C_SHORT "
				+ " FROM CHOICE "
				+ " WHERE C_TYPE = ? ";
		}
		else if(c_size.equals("c_tall")) {
			sql = " SELECT C_TALL "
				+ " FROM CHOICE "
				+ " WHERE C_TYPE = ? ";
		}
		else if(c_size.equals("c_grande")) {
			sql = " SELECT C_GRANDE "
				+ " FROM CHOICE "
				+ " WHERE C_TYPE = ? ";
		}
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, c_type);
			rs = psmt.executeQuery();
			
			// 커피의 가격을 가져온다
			while(rs.next()) {
				c_price = rs.getInt(c_size);
			}
				
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}	
		// 커피 가격 반환
		return c_price;	
	}	
}
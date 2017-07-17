package dto;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class LogInDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	String url = "";
	String user = "";
	String password = "";
	String driver = "";
	

	public LogInDAO() {
		InputStream input = this.getClass().getResourceAsStream("../../../db.properties");

		Properties pro = new Properties();

		try {
			pro.load(input);
			url = pro.getProperty("url");
			user = pro.getProperty("id");
			password = pro.getProperty("pw");
			driver = pro.getProperty("driver");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public PersonDTO LogInCheck(String input_email, String input_pw) {//로그인체크

		PersonDTO dto = null;

		try {
			getConnection();
			psmt = conn.prepareStatement("select *from Person where email =? and password=? ");
			
			psmt.setString(1, input_email);
			psmt.setString(2, input_pw);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto = new PersonDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
	}

	public ArrayList<PartyDTO> getMyParty(String person_id) {//로그인한 사람이 만든 파티 찾기
				

		ArrayList<PartyDTO> party = new ArrayList<>();
		
		try {
	
			getConnection();
			psmt = conn.prepareStatement("select * from Party where person_id= ?");
			psmt.setString(1, person_id);			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				PartyDTO partyDTO = new PartyDTO(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4));				
				party.add(partyDTO);
			}
			
			for(PartyDTO dto:party){				
				psmt = conn.prepareStatement("select name from person where id= ?");
				psmt.setString(1, dto.getPerson_id());
				rs = psmt.executeQuery();
				while(rs.next()){
					dto.setPerson_name(rs.getString(1));
				}
				psmt = conn.prepareStatement("select * from person_party where party_id= ?");
				psmt.setString(1, dto.getId());
				rs = psmt.executeQuery();
				int num=0;
				while(rs.next()){
					num++;
				}
				dto.setPerson_num(num);
			}
			
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return party;
	
		
	}

	public ArrayList<PartyDTO> getOtherParty(String person_id) {//로그인한 사람이 들어간 파티 찾기
		ArrayList<PartyDTO> partyList = new ArrayList<>();
		PartyDTO dto = null;
		ArrayList<String> idList = new ArrayList<>(); 
		try {
			getConnection();
			psmt = conn.prepareStatement("select party_id from Person_Party where person_id = ?");
			psmt.setString(1, person_id);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				idList.add(rs.getString(1));				
			}
			
			for(String id:idList){
				psmt = conn.prepareStatement("select * from Party where id = ?");
				psmt.setString(1, id);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new PartyDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					dto.setPerson_num(checkNum(rs.getString(1)));
				}
				
				
				psmt = conn.prepareStatement("select name from person where id= ?");
				psmt.setString(1, rs.getString(3));
				rs = psmt.executeQuery();				
				if(rs.next()){
					dto.setPerson_name(rs.getString(1));
				}
				
				
				partyList.add(dto);
			}

			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partyList;
	
		
		
	}



	public int join(String email, String password, String name) {//회원가입
		
		int state = -1;
		try {
			getConnection();
			psmt = conn.prepareStatement("insert into Person Values(id_num.nextval,?,?,?)");
			psmt.setString(1, email);
			psmt.setString(2, password);
			psmt.setString(3, name);
			
			state = psmt.executeUpdate();		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
		
		
	}

	public String createParty(String title, String search_id) {//파티(클래스)만들기
		String result="";
		try {
			getConnection();
			psmt = conn.prepareStatement("insert into Party values(party_num.nextval,?,?,'')");
			psmt.setString(1, title);
			psmt.setString(2, search_id);
			rs =psmt.executeQuery();
			
			psmt = conn.prepareStatement("select party_num.currval from dual");
			rs =psmt.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}

	public int addPartyPerson(String email, String party_id) { //파티에 사람 추가
		
      int state = -1;
		
		try {
			getConnection();
			psmt = conn.prepareStatement("Insert into Person_Party values((select id from person where email = ?), ?)");
			psmt.setString(1, email);
			psmt.setString(2, party_id);
			
			
			state = psmt.executeUpdate();		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
		
		
	}


	public int checkNum(String party_id) {
		
		int num = 0;
		
		try {

			getConnection();
			ResultSet tempRs;

			psmt = conn.prepareStatement("select person_id from Person_Party where party_id =?");
			psmt.setString(1, party_id);
			tempRs = psmt.executeQuery();
			while(tempRs.next()){
				num++;
			}
			tempRs.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return num;
	}
	
	public int clearDone(String party_id) {
		int state = -1;
		
		try {

			getConnection();

			psmt = conn.prepareStatement("update Person_Party set done = 0 where party_id=?");
			psmt.setString(1, party_id);
			state = psmt.executeUpdate();
	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
		
	}

	public int completeDone(String party_id, String person_id) {
		int state = -1;
		
		try {

			getConnection();

			psmt = conn.prepareStatement("update Person_Party set done = 1 where party_id=? and person_id=?");
			psmt.setString(1, party_id);
			psmt.setString(2, person_id);
			state = psmt.executeUpdate();
	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
		

	}

	public int DeleteParty(String party_id) {
		
		int state = -1;
		
		try {

			getConnection();

			psmt = conn.prepareStatement("delete from Party where id = ?");
			psmt.setString(1, party_id);
			state = psmt.executeUpdate();
	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return state;
	}

	public ArrayList<PartyDTO> getPartyByTitle(String title) {
		ArrayList<PartyDTO> partyList = new ArrayList<>();
		PartyDTO dto = null;
		
		try {
			getConnection();
			psmt = conn.prepareStatement("select *from Party where title like ?");
			psmt.setString(1, "%"+title+"%");
			rs = psmt.executeQuery();
			
			
			
			while(rs.next()){
				dto = new PartyDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				dto.setPerson_num(checkNum(rs.getString(1)));
				
				psmt = conn.prepareStatement("select name from person where id= ?");
				psmt.setString(1, rs.getString(3));
				ResultSet tempRs;
				tempRs = psmt.executeQuery();
				
				if(tempRs.next()){
					dto.setPerson_name(tempRs.getString(1));
				}
				
				tempRs.close();
				partyList.add(dto);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partyList;
	
		
	}

	public int cancelDone(String party_id, String person_id) {
		
		int state = -1;
		
		try {

			getConnection();

			psmt = conn.prepareStatement("update Person_Party set done = 0 where party_id=? and person_id=?");
			psmt.setString(1, party_id);
			psmt.setString(2, person_id);
			state = psmt.executeUpdate();
	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
		
	}
}

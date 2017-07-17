package dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "system";
	private String password = "1234";

	public ArrayList<LectureDTO> getLectureList(String party_id) {
		ArrayList<LectureDTO> lectureList = new ArrayList<LectureDTO>();

		try {
			getConnection();
			psmt = conn.prepareStatement("select * from lecture where party_id=?");
			psmt.setString(1, party_id);

			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용

			while (rs.next()) {
				LectureDTO lecture = new LectureDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

				lectureList.add(lecture);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(LectureDTO lecture : lectureList){
         lecture.setExampleList(getExampleList(lecture.getId()));
		}

		return lectureList;

	}

	public int getStudentNum(String party_id) {
		int num = 0;

		try {
			getConnection();
			psmt = conn.prepareStatement("select * from person_party where party_id=?");
			psmt.setString(1, party_id);

			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용

			while (rs.next()) {

				num++;
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return num;

	}

	private void getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, user, password);
	}

	public ExampleDTO getExample(String example_id) {
		ExampleDTO exampleDTO = null;
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from example where id=?");
			psmt.setString(1, example_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				exampleDTO = new ExampleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return exampleDTO;
	}
	
	public PartyDTO getParty(String party_id) {
		PartyDTO partyDTO = null;
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from party where id=?");
			psmt.setString(1, party_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				partyDTO = new PartyDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return partyDTO;
	}
	
	public LectureDTO getLecture(String lecture_id) {
		LectureDTO lectureDTO = null;
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from lecture where id=?");
			psmt.setString(1, lecture_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				lectureDTO = new LectureDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return lectureDTO;
	}

	public String createLecture(String party_id, String title, String lock) {
		String lecture_id="";
	
		try {
			getConnection();
			psmt = conn.prepareStatement("insert into lecture values(lecture_num.nextval,?,?,?)");
			psmt.setString(1, party_id);
			
			psmt.setString(2, title);
			if(lock==null){
				psmt.setInt(3, 0);
			}else{
				psmt.setInt(3, 1);
			}
			psmt.executeUpdate();
			
			psmt = conn.prepareStatement("select lecture_num.currval from dual");
			rs =psmt.executeQuery();
			
			if(rs.next()){
				lecture_id = rs.getString(1);
			}
			

			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return lecture_id;
	}

	public void update(String title, int lecture_id) {
		try {
			getConnection();
			psmt = conn.prepareStatement("update lecture set title=? where id=?");
			psmt.setString(1, title);
			psmt.setInt(2, lecture_id);
			psmt.executeUpdate();
					

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
	}

	public void updateParty(String title, String party_id, String description) {
		try {
			getConnection();
			psmt=conn.prepareStatement("update party set title=?,description=? where id=?");
			psmt.setString(1, title);
			psmt.setString(2, description);
			psmt.setString(3, party_id);
			psmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	

	}

	public String createExample(String title, String content,int lecture_id) {
		String example_id ="";
		
		try {
			getConnection();
			psmt = conn.prepareStatement("insert into example values(example_num.nextval,?,?,?)");
			psmt.setInt(1, lecture_id);
			psmt.setString(2, title);
			psmt.setString(3, content);

			psmt.executeUpdate();
			
			psmt = conn.prepareStatement("select example_num.currval from dual");
			rs =psmt.executeQuery();
			
			if(rs.next()){
				example_id =rs.getString(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return example_id;
	}

	public void updateLecture(String title, String lecture_id, String lock) {
		try {
			getConnection();
			psmt = conn.prepareStatement("update lecture set title=?, locking=? where id=?");
			psmt.setString(1, title);
			System.out.println(lock);
			if(lock==null){
				psmt.setInt(2, 0);
			}else{
				psmt.setInt(2, 1);
			}
			psmt.setString(3, lecture_id);
			psmt.executeUpdate();
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void updateExample(String example_id, String title, String content) {
		try {
			getConnection();
			psmt = conn.prepareStatement("update example set content=?,title=? where id=?");
			psmt.setString(1, content);
			psmt.setString(2, title);
			psmt.setString(3, example_id);
			psmt.executeUpdate();
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ImageDTO> getImageList(String example_id) {
		ArrayList<ImageDTO> imageList = new ArrayList<ImageDTO>();
		
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from image where example_id=?");
			psmt.setString(1, example_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			while (rs.next()) {
				imageList.add(new ImageDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return imageList;
	}

	public ArrayList<ExampleDTO> getExampleList(String lecture_id) {
		ArrayList<ExampleDTO> exampleList = new ArrayList<ExampleDTO>();
		
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from example where lecture_id=?");
			psmt.setString(1, lecture_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			while (rs.next()) {
				exampleList.add(new ExampleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return exampleList;
	}

	public ArrayList<PersonDTO> getStudentList(String party_id) {
		ArrayList<PersonDTO> personList = new ArrayList<PersonDTO>();
		ArrayList<String> idList = new ArrayList<>();
		try {
			getConnection();
			psmt = conn.prepareStatement("select person_id from person_party where party_id=?");
			psmt.setString(1, party_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			while (rs.next()) {
				idList.add(rs.getString(1));
				
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String id:idList){
			personList.add(getPersonById(id));
		}
		
		
		return personList;
	}

	public PersonDTO getPersonById(String person_id) {
		PersonDTO person=null;
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from person where id=?");
			psmt.setString(1, person_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			if (rs.next()) {
				person = new PersonDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return person;
		
	}

	public String createImage(String example_id, String uploadFile, String description) {
		String image_id="";
		try {
			getConnection();
			psmt = conn.prepareStatement("insert into image values(image_num.nextval,?,?,?)");
			psmt.setString(1, example_id);
			psmt.setString(2, uploadFile);
			psmt.setString(3, description);

			psmt.executeUpdate();
			
			psmt = conn.prepareStatement("select image_num.currval from dual");
			rs = psmt.executeQuery();
			if(rs.next()){
				image_id = rs.getString(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return image_id;
	}

	public void deleteExample(String example_id, String person_id) {
		try {
			getConnection();
			psmt = conn.prepareStatement("delete from example where id=?");
			psmt.setString(1, example_id);

			psmt.executeQuery();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void deleteLecture(String lecture_id, String person_id) {
		try {
			getConnection();
			psmt = conn.prepareStatement("delete from lecture where id=?");
			psmt.setString(1, lecture_id);

			psmt.executeQuery();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void deleteParty(String party_id, String person_id) {
		try {
			getConnection();
			psmt = conn.prepareStatement("delete from party where id=?");
			psmt.setString(1, party_id);

			psmt.executeQuery();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void deleteImage(String image_id, String person_id) {
		try {
			getConnection();
			psmt = conn.prepareStatement("delete from image where id=?");
			psmt.setString(1, image_id);

			psmt.executeQuery();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

	public boolean checkPartyLecture(String party_id, String lecture_id) {
		boolean check=false;
		
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from lecture where id=? AND party_id=?");
			psmt.setString(1, lecture_id);
			psmt.setString(2, party_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			if (rs.next()) {
				check = true;
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}

	public void inviteParty(String party_id, String emails,String person_id) {
		try {
			getConnection();
			
			String[] emailArray = emails.split(";");
			
			for(String email : emailArray){
				psmt = conn.prepareStatement("select id from person where email=?");
				psmt.setString(1, email);
				rs = psmt.executeQuery();
											
				if (rs.next()) {					
					psmt = conn.prepareStatement("insert into person_party values(?,?,0,0,null)");
					psmt.setString(1, rs.getString(1));
					psmt.setString(2, party_id);
					if(rs.getString(1).equals(person_id)){
						
					}else{
						psmt.executeUpdate();
					}
				}
			}
			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public int call(String person_id, String party_id) {
		int state = -1;
		try {
			getConnection();
			psmt = conn.prepareStatement("update Person_Party set call = 1, call_time = SYSTIMESTAMP where person_id =? and party_id =?");
			psmt.setString(1, person_id);
			psmt.setString(2, party_id);
			state = psmt.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return state;
		
	}

	public ArrayList<String[]> getCallPerson(String party_id) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try {
			getConnection();
			psmt = conn.prepareStatement("select person_id, call_time from person_party where party_id=? AND call=1");
			psmt.setString(1, party_id);
			rs = psmt.executeQuery();// executeQuery검색한결과를 물어본다 executeUpdate는
										// 값을수정하거나 입력할때 사용
			
			
			
			
			
			while (rs.next()) {
				String[] arr = new String[2];
				arr[0] = rs.getString(2);
				psmt = conn.prepareStatement("select * from person where id=?");
				psmt.setString(1, rs.getString(1));
				ResultSet tempRs = psmt.executeQuery();
				if(tempRs.next()){
					arr[1] = tempRs.getString(4);
				}
				tempRs.close();				
				list.add(arr);
				
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}

	public void callCancel(String person_id, String party_id) {
		
		try {
			getConnection();
			psmt = conn.prepareStatement("update Person_Party set call = 0 where person_id =? and party_id =?");
			psmt.setString(1, person_id);
			psmt.setString(2, party_id);
			psmt.executeUpdate();
			
		
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public int getCompleteNum(String party_id) {
		int done = 0;
		
		try {

			getConnection();

			psmt = conn.prepareStatement("select person_id from Person_Party where party_id =? and done =1");
			psmt.setString(1, party_id);
			rs = psmt.executeQuery();
			while(rs.next()){
				done++;
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
		return done;
	}
	
	public ArrayList<String> getDoneList(String party_id) {
		ArrayList<String> idList = new ArrayList<String>();
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from person where id=(select person_id from Person_Party where party_id =? and done =1)");
			psmt.setString(1, party_id);
			rs = psmt.executeQuery();
			while(rs.next()){
				idList.add(rs.getString(1));
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
		
		return idList;
	}
	
	public ArrayList<String> getCallList(String party_id) {
		ArrayList<String> idList = new ArrayList<String>();
		try {
			getConnection();
			psmt = conn.prepareStatement("select person_id from Person_Party where party_id =? and call =1");
			psmt.setString(1, party_id);
			rs = psmt.executeQuery();
			while(rs.next()){
				idList.add(rs.getString(1));
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
		
		return idList;
	}

	public void banStudent(String person_id, String party_id) {
		
		try {
			getConnection();
			psmt = conn.prepareStatement("delete from Person_Party where person_id = ? and party_id = ?");
			psmt.setString(1, person_id);
			psmt.setString(2, party_id);
			psmt.executeUpdate();
			
					
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
		
	}

	public ImageDTO getImage(String image_id) {
		ImageDTO image =null;
		try {
			getConnection();
			psmt = conn.prepareStatement("select * from image where id=?");
			psmt.setString(1, image_id);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				image=new ImageDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
		return image;
	}



	public void updateImage(String image_id, String uploadFile, String description) {
		try {
			getConnection();
			
			if(uploadFile==null){
				psmt = conn.prepareStatement("update image set description=? where id=?");
				psmt.setString(1, description);
				psmt.setString(2, image_id);
				psmt.executeUpdate();
			}else{
				psmt = conn.prepareStatement("update image set image=?, description=? where id=?");
				psmt.setString(1, uploadFile);
				psmt.setString(2, description);
				psmt.setString(3, image_id);
				psmt.executeUpdate();
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
		
	}

	
}


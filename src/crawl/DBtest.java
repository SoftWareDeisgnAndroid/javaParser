package crawl;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.StringTokenizer;

public class DBtest {

	public static void main(String[] args) {
		TimeTable t = new TimeTable();

	}

}

class TimeTable{
	private String DBURL = "jdbc:mysql://155.230.52.54:8806/TimeTable";
	private String ID = "root";
	private String PW = "master1592";
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	private String grade; // 학년
	private String classify; // 교과 구분
	private String open_university; // 개설 대학
	private String class_number; // 교과목 번호
	private String class_name; // 과목 이름
	private String credit; // 학점
	private String class_theory; // 강의(이론)
	private String class_practice; // 실습
	private String professor; // 담당 교수
	private String class_time; // 강의 시간
	private String real_class_time; // 강의 시간(실제 시간)
	private String classroom; // 강의실
	private String capacity; // 수강 정원
	private String Enrollment_number; // 수강 신청 수
	private String Enrollment_package_number; // 수강 꾸러미 수
	private String package_available; // 수강 꾸러미 신청 가능 여부
	private String note; // 비고
	private Scanner parser;
	
	public TimeTable(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBURL,ID,PW);
			System.out.println("connection DB complete");
		} catch (Exception e2) {
			System.out.println("ClassNotFoundException");
			e2.printStackTrace();
		}
		try {
			crawl();
		}
		catch (IndexOutOfBoundsException e1) {
			e1.getMessage();
			System.out.println("Index Error");
		}
		catch (Exception e) {
			System.out.println("Error");
			e.getStackTrace();
		}
	}
	
	public void crawl() throws Exception{
		Document doc = Jsoup.connect("http://my.knu.ac.kr/stpo/stpo/cour/listLectPln/list.action?search_subj_area_cde=1A01&search_open_yr_trm=20192").get();
		Elements contents = doc.select("tbody").select("tr");
		
		for (Element content: contents) {
			Elements info = content.select("td");
			if (info.size() <= 0) {
				continue;
			}
			/*for (int i = 0; i <= 16; i++) {
				System.out.print(info.get(i).text().trim());
			}
			System.out.println();
			*/
			grade = info.get(0).text().trim();
			classify = info.get(1).text().trim();
			open_university = info.get(2).text().trim();
			class_number = info.get(3).text().trim();
			class_name = info.get(4).text().trim();
			credit = info.get(5).text().trim();
			class_theory = info.get(6).text().trim();
			//class_practice = info.get(7).text().trim(); 이유를 알 수 없는 오류
			professor = info.get(8).text().trim();
			class_time = info.get(9).text().trim();
			real_class_time = info.get(10).text().trim();
			classroom = info.get(11).text().trim();
			capacity = info.get(12).text().trim();
			Enrollment_number = info.get(13).text().trim();
			Enrollment_package_number = info.get(14).text().trim();
			package_available = info.get(15).text().trim();
			note = info.get(16).text().trim();
			parser = new Scanner(classroom).useDelimiter("-");
			class_time = normalize(class_time);
			this.sendQuery(class_number, grade, class_name, parser.next(),parser.next(),class_time);//파싱해서 따로 따로 보내줘
			parser.close();
			System.out.printf("%s %s %s %s %s %s %s %d %s %s %s %s %s %s %s %s %s\n\n", grade, classify, open_university, class_number, class_name,
					credit, class_theory, class_practice, professor, class_time, real_class_time, classroom, capacity, Enrollment_number,
					Enrollment_package_number, package_available, note);
		}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제

		if(connection != null) try{connection.close();}catch(SQLException sqle){}
	}
	
	private void sendQuery(String lecturenumber,String grade, String lecturename,String lectureBuilding,String lectureRoom,String class_time) {
		try {
			String sql = "INSERT INTO TimeTable VALUES(?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,lecturenumber);
			pstmt.setString(2, grade);
			pstmt.setString(3, lecturename);
			pstmt.setString(4, lectureBuilding);
			pstmt.setString(5, lectureRoom);
			pstmt.setString(6, class_time);
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("추가 실패");
		}
	}
	private String normalize(String time){
		String ret = "";
		String preDay = null;
		StringTokenizer st = new StringTokenizer(time,", ");
		String[] days = {"월","화","수","목","금","토"};
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			if(Arrays.asList(days).contains(temp)){
				preDay = String.valueOf(temp.charAt(0));
				ret.concat(temp);
				ret.concat(" ");
			}
			else{
				ret.concat(preDay);
				ret.concat(temp);
			}
		}
		return ret;
	}
}
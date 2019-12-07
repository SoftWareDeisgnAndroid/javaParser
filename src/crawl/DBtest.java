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
	
	private String grade; // �г�
	private String classify; // ���� ����
	private String open_university; // ���� ����
	private String class_number; // ������ ��ȣ
	private String class_name; // ���� �̸�
	private String credit; // ����
	private String class_theory; // ����(�̷�)
	private String class_practice; // �ǽ�
	private String professor; // ��� ����
	private String class_time; // ���� �ð�
	private String real_class_time; // ���� �ð�(���� �ð�)
	private String classroom; // ���ǽ�
	private String capacity; // ���� ����
	private String Enrollment_number; // ���� ��û ��
	private String Enrollment_package_number; // ���� �ٷ��� ��
	private String package_available; // ���� �ٷ��� ��û ���� ����
	private String note; // ���
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
			//class_practice = info.get(7).text().trim(); ������ �� �� ���� ����
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
			this.sendQuery(class_number, grade, class_name, parser.next(),parser.next(),class_time);//�Ľ��ؼ� ���� ���� ������
			parser.close();
			System.out.printf("%s %s %s %s %s %s %s %d %s %s %s %s %s %s %s %s %s\n\n", grade, classify, open_university, class_number, class_name,
					credit, class_theory, class_practice, professor, class_time, real_class_time, classroom, capacity, Enrollment_number,
					Enrollment_package_number, package_available, note);
		}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement ��ü ����

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
			System.out.println("�߰� ����");
		}
	}
	private String normalize(String time){
		String ret = "";
		String preDay = null;
		StringTokenizer st = new StringTokenizer(time,", ");
		String[] days = {"��","ȭ","��","��","��","��"};
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
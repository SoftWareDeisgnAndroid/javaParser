package parser;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl_test {
   
   public static void main(String[] args) throws Exception {
      final String[] site_list = { "search_subj_area_cde=1B0203&search_open_yr_trm=20192", "search_subj_area_cde=1C&search_open_yr_trm=20192","search_subj_div_cde=07&search_open_yr_trm=20192",
            "search_subj_area_cde=46&search_open_yr_trm=20192", "search_open_crse_cde=300H&search_open_yr_trm=20192", "search_open_crse_cde=1108&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=1101&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=110B&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=1104&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=1103&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=1106&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=1102&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=11&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=1109&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=1105&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=1107&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=110A&sub=11&search_open_yr_trm=20192",
            "search_open_crse_cde=120A&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=1204&sub=12&search_open_yr_trm=20192",
            "search_open_crse_cde=1209&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=120902&sub=12&search_open_yr_trm=20192",
            "search_open_crse_cde=120901&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=1202&sub=12&search_open_yr_trm=20192",
            "search_open_crse_cde=1207&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=120C&sub=12&search_open_yr_trm=20192",
            "search_open_crse_cde=1205&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=1201&sub=12&search_open_yr_trm=20192",
            "search_open_crse_cde=1203&sub=12&search_open_yr_trm=20192", "search_open_crse_cde=130A&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=130705&sub=13&search_open_yr_trm=20192", 
            "search_open_crse_cde=130701&sub=13&search_open_yr_trm=20192", "search_open_crse_cde=1309&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=1301&sub=13&search_open_yr_trm=20192", "search_open_crse_cde=130Q&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=130Q01&sub=13&search_open_yr_trm=20192", "search_open_crse_cde=130Q02&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=130Q03&sub=13&search_open_yr_trm=20192", "search_open_crse_cde=1305&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=1304&sub=13&search_open_yr_trm=20192", "search_open_crse_cde=130S&sub=13&search_open_yr_trm=20192",
            "search_open_crse_cde=1302&sub=13&search_open_yr_trm=20192", 
            "search_open_crse_cde=1403&sub=14&search_open_yr_trm=20192", "search_open_crse_cde=1403001&sub=14&search_open_yr_trm=20192",
            "search_open_crse_cde=1403002&sub=14&search_open_yr_trm=20192", "search_open_crse_cde=1403003&sub=14&search_open_yr_trm=20192",
            "search_open_crse_cde=1404&sub=14&search_open_yr_trm=20192", "search_open_crse_cde=1407&sub=14&search_open_yr_trm=20192",
            "search_open_crse_cde=1508&sub=15&search_open_yr_trm=20192", "search_open_crse_cde=1502&sub=15&search_open_yr_trm=20192",
            "search_open_crse_cde=160I01&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=160I02&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1605&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=16&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1601001&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=1601002&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1601003&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=160101&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=160102&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=1607&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1609001&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=1609002&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=160903&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=160904&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1611&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=161101&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=161102&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=1612001&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1612002&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=161201&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=161202&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=160E&sub=16&search_open_yr_trm=20192",
            "search_open_crse_cde=1606&sub=16&search_open_yr_trm=20192", "search_open_crse_cde=170R&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170A&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170T&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170T01&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170T02&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170Q&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170S&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170S02&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170S01&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170V&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170U&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170P&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170P01&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170P02&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170P03&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170O&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170W&sub=17&search_open_yr_trm=20192", 
            "search_open_crse_cde=170J&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170B&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170B01&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=170B05&sub=17&search_open_yr_trm=20192",
            "search_open_crse_cde=170B04&sub=17&search_open_yr_trm=20192", "search_open_crse_cde=191G&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=191H&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190A&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=190B&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=1901&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=1902&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190H&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=19&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190J&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=1908&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190E&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=1903&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190D01&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=190D02&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=1907&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=190G&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190K&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=190F&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=190C&sub=19&search_open_yr_trm=20192",
            "search_open_crse_cde=190I&sub=19&search_open_yr_trm=20192", "search_open_crse_cde=1803&sub=18&search_open_yr_trm=20192",
            "search_open_crse_cde=1806&sub=18&search_open_yr_trm=20192", "search_open_crse_cde=1804&sub=18&search_open_yr_trm=20192",
            "search_open_crse_cde=1801&sub=18&search_open_yr_trm=20192", "search_open_crse_cde=1F04&sub=1F&search_open_yr_trm=20192",
            "search_open_crse_cde=1F01&sub=1F&search_open_yr_trm=20192", "search_open_crse_cde=1G02&sub=1G&search_open_yr_trm=20192",
            "search_open_crse_cde=1G01&sub=1G&search_open_yr_trm=20192", "search_open_crse_cde=1A02&sub=1A&search_open_yr_trm=20192",
            "search_open_crse_cde=1A01&sub=1A&search_open_yr_trm=20192", "search_open_crse_cde=1B04&sub=1B&search_open_yr_trm=20192",
            "search_open_crse_cde=1B07&sub=1B&search_open_yr_trm=20192", "search_open_crse_cde=1B0701&sub=1B&search_open_yr_trm=20192",
            "search_open_crse_cde=1B0702&sub=1B&search_open_yr_trm=20192", "search_open_crse_cde=1B03&sub=1B&search_open_yr_trm=20192",
            "search_open_crse_cde=1E01&sub=1E&search_open_yr_trm=20192", "search_open_crse_cde=1E02&sub=1E&search_open_yr_trm=20192",
            "search_open_crse_cde=1C01&sub=1C&search_open_yr_trm=20192", "search_open_crse_cde=1O07&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O09&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O06&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O01001&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O03&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O01&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O01002&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O01003&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O01004&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O01005&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O01006&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O01007&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O0101&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O02&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1O0204&sub=1O&search_open_yr_trm=20192",
            "search_open_crse_cde=1O08&sub=1O&search_open_yr_trm=20192", "search_open_crse_cde=1P03&sub=1P&search_open_yr_trm=20192",
            "search_open_crse_cde=1Q01&sub=1Q&search_open_yr_trm=20192", "search_open_crse_cde=1S01&sub=1S&search_open_yr_trm=20192",
            "search_open_crse_cde=1S0101&sub=1S&search_open_yr_trm=20192", "search_open_crse_cde=1S0102&sub=1S&search_open_yr_trm=20192"};
      TimeTable t = new TimeTable();
      for (String str: site_list) {
         try {
            t.crawl(str);
         } 
         catch (IOException e1) {
            e1.printStackTrace();
            pause();
            System.err.println("request error");
         }
         catch (IndexOutOfBoundsException e2) {
            System.err.println("crawled data broken");
         }
      }
   }
   public static void pause() {
        try {
          System.in.read();
        } catch (IOException e) { }
      }     
}   


class TimeTable{

   private String DBURL = "jdbc:mysql://155.230.52.54:8806/TimeTable";
   private String ID = "root";
   private String PW = "master1592";
   private Connection connection = null;
   private PreparedStatement pstmt = null;

   private char preDay;
   
   public TimeTable(){
      /*
      establish connection to DB
      */
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(DBURL,ID,PW);
         System.out.println("connection DB complete");
      } catch (Exception e2) {
         System.err.println("ClassNotFoundException.\n");
         e2.printStackTrace();
      }
   }
   
   public void crawl(String code) throws NoSuchElementException, IOException{
      Response execute = Jsoup.connect("http://my.knu.ac.kr/stpo/stpo/cour/listLectPln/list.action?" + code).execute();
      Document doc = Jsoup.parse(execute.body());
      Elements contents = doc.select("tbody").select("tr");
      TimeTableBuilder timeTableBuilder = new TimeTableBuilder();
      TimeTableMaker timeTableMaker = new TimeTableMaker(timeTableBuilder);
      String classroom;
      for (Element content: contents) {
         Elements info = content.select("td");
         if (info.size() <= 0) {
            continue;
         }
         
         if (info.size() == 17) {
        	 classroom = info.get(11).text().trim();
         }
         else {
        	 classroom = info.get(10).text().trim();
         }
         if (classroom.length() == 0 || classroom.equals("(교외강의실) 현장견학지-")) // 강의실이 없는 경우 처리
         	continue;
      	timeTableMaker.build(info);
      	sendQuery((TimeTableProduct) timeTableBuilder.getProduct());
      }
   }
   private void sendQuery(TimeTableProduct data) {
      try {
         String sql = "INSERT IGNORE INTO TimeTable VALUES(?,?,?,?,?,?)";//DB 내용을 수정할까?
         pstmt = connection.prepareStatement(sql);
         pstmt.setString(1, data.getLecturenumber());
         pstmt.setString(2, data.getGrade());
         pstmt.setString(3, data.getLecturename());
         pstmt.setString(4, data.getLectureBuilding());
         pstmt.setString(5, data.getLectureRoom());
         pstmt.setString(6, normalize(data.getClass_time()));
         
         pstmt.executeUpdate();
      }
      catch(NoSuchElementException e3) {
         System.err.printf("%s failed\n", data.getLecturenumber());
      }
      catch(Exception e) {
         e.printStackTrace();
         System.err.println("추가 실패");
      }
   }
   private String normalize(String time){
		StringBuilder ret = new StringBuilder("");
		StringTokenizer st = new StringTokenizer(time,", ");
		String[] days = {"월","화","수","목","금","토"};
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			if(Arrays.asList(days).contains(temp.charAt(0)+"")){
				preDay = temp.charAt(0);
				ret.append(temp);
				ret.append(" ");
			}
			else{
				ret.append(preDay);
				ret.append(temp);
				ret.append(" ");
			}
		}
		return ret.toString();
	}
}
abstract class Product{
	   String grade; // 학년
	   String lecturenumber; // 교과목 번호
	   String lecturename; // 과목 이름
	   String lectureBuilding; // 강의 건물
	   String lectureRoom; // 강의실 번호
	   String class_time; // 강의 시간
	   abstract void setData(String lecturenumber,String grade, String lecturename,String lectureBuilding,String lectureRoom,String class_time);
	
}

class TimeTableProduct extends Product{
	@Override
	void setData(String lecturenumber,String grade, String lecturename,String lectureBuilding,String lectureRoom,String class_time) {
		this.grade = grade;
		this.lecturenumber = lecturenumber;
		this.lecturename = lecturename;
		this.lectureBuilding = lectureBuilding;
		this.lectureRoom = lectureRoom;
		this.class_time = class_time;
	}
	public String getGrade() {
		return grade;
	}
	public String getLecturenumber() {
		return lecturenumber;
	}
	public String getLecturename() {
		return lecturename;
	}
	public String getLectureBuilding() {
      int idx = lectureBuilding.indexOf("(");
      if(idx == -1)
         return lectureBuilding;
      lectureBuilding = lectureBuilding.substring(0, idx);
      return lectureBuilding;
	}
	public String getLectureRoom() {
		return lectureRoom;
	}
	public String getClass_time() {
		return class_time;
	}
}
abstract class Builder{
	abstract Builder setGrade(String grade);
	abstract Builder setClass_number(String class_number);
	abstract Builder setClass_name(String class_name);
	abstract Builder setClass_room(String class_room);
	abstract Builder setClass_time(String class_time);
	abstract Product getProduct();
	abstract void build();
}

class TimeTableBuilder extends Builder{
	private String grade, class_number, class_name, building, room, class_time, classroom;
	private Scanner parser = null;
	TimeTableProduct product = new TimeTableProduct();
	@Override
	Builder setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	@Override
	Builder setClass_number(String class_number) {
		this.class_number = class_number;
		return this;
	}
	@Override
	Builder setClass_name(String class_name) {
		this.class_name= class_name;
		return this;
	}
	@Override
	Builder setClass_room(String classroom) {
		this.classroom = classroom;
		return this;
	}
	@Override
	Builder setClass_time(String class_time) {
		this.class_time = class_time;
		return this;
	}
	public void build() {
		try{
            parser = new Scanner(classroom).useDelimiter("-");
            building  = parser.next();
            room = parser.next();
            parser.close();
            if(room.length()>6){
               parser = new Scanner(room).useDelimiter(" ");
               room = parser.next();
               parser.close();
            }
   	 	}
         catch(NoSuchElementException e) {
         	System.err.print(classroom.length());
         	System.err.println(" " + class_name + " " + classroom);
         }
		product.setData(class_number, grade, class_name, building, room,class_time);
	}
	public Product getProduct() {
		return this.product;
	}
}

class TimeTableMaker{
	Builder builder;
	public TimeTableMaker(Builder builder) {
		this.builder = builder;
	}
	private String grade = null; // 학년
	private String class_number = null; // 교과목 번호
	private String class_name = null; // 과목 이름
	private String classroom = null; // 강의실 번호
	private String class_time = null; // 강의 시간
	private Scanner parser = null;
	void build(Elements info) {
		if (info.size() == 17) { // 개설 대학이 들어가있는 교양수업의 경우
			grade = info.get(0).text().trim();
	        class_number = info.get(3).text().trim();
	        class_name = info.get(4).text().trim();
	        class_time = info.get(9).text().trim();
	        classroom = info.get(11).text().trim();
        }
        else { // 교양 제외 수업
           class_name = info.get(3).text().trim();
           class_number = info.get(2).text().trim();
           classroom = info.get(10).text().trim();
           class_time = info.get(8).text().trim();
        }
		builder.setClass_name(class_name).setClass_number(class_number).setClass_room(classroom).setClass_time(class_time).setGrade(grade).build();
	}
	public Product getProduct() {
		return builder.getProduct();
	}
}
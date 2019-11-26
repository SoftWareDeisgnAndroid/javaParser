import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl_test {
	
	public static void main(String[] args) throws Exception {
		final String[] site_list = { "search_subj_area_cde=1B0203&search_open_yr_trm=20192", "search_subj_area_cde=1C&search_open_yr_trm=20192","search_subj_div_cde=07&search_open_yr_trm=20192",
				"search_subj_area_cde=46&search_open_yr_trm=20192", "search_open_crse_cde=300H&search_open_yr_trm=20192", "search_open_crse_cde=1108&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1101&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=110B&sub=11&search_open_yr_trm=20192", "search_open_crse_cde=1104&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1103&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1106&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1102&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=11&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1109&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1105&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=1107&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=110A&sub=11&search_open_yr_trm=20192",
				"search_open_crse_cde=120A&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1204&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1209&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=120902&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=120901&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1202&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1207&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=120C&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1205&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1201&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=1203&sub=12&search_open_yr_trm=20192",
				"search_open_crse_cde=130A&sub=13&search_open_yr_trm=20192","search_open_crse_cde=130705&sub=13&search_open_yr_trm=20192", 
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
				System.err.println("request error");
			}
			catch (IndexOutOfBoundsException e2) {
				System.err.println("crawled data broken");
			}

		}
	}
}	


class TimeTable{
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
	

	public void crawl(String code) throws Exception{
		Document doc = Jsoup.connect("http://my.knu.ac.kr/stpo/stpo/cour/listLectPln/list.action?" + code).get();
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
			if (info.size() == 17) { 
				note = info.get(16).text().trim();
				System.out.printf("%s %s %s %s %s %s %s %d %s %s %s %s %s %s %s %s %s\n\n", grade, classify, open_university, class_number, class_name,
						credit, class_theory, class_practice, professor, class_time, real_class_time, classroom, capacity, Enrollment_number,
						Enrollment_package_number, package_available, note);
			}
			else {
				System.out.printf("%s %s %s %s %s %s %s %d %s %s %s %s %s %s %s %s\n\n", grade, classify, open_university, class_number, class_name,
						credit, class_theory, class_practice, professor, class_time, real_class_time, classroom, capacity, Enrollment_number,
						Enrollment_package_number, package_available);
			}
			
			
			
		}
	}
}
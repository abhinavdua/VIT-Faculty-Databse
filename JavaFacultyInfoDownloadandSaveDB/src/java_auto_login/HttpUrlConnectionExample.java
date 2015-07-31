package java_auto_login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

 
public class HttpUrlConnectionExample {
 
 
	public static void main(String[] args) throws Exception
	{
 
		String urlForForm = "https://academics.vit.ac.in/student/stud_login.asp";
		String urlHome= "https://academics.vit.ac.in/student/home.asp";
		String urlCaptcha="https://academics.vit.ac.in/student/captcha.asp";
		String urlForPostSubmit="https://academics.vit.ac.in/student/stud_login_submit.asp";
		String urlForTimeTable="https://academics.vit.ac.in/student/timetable.asp?sem=WS";
		String urlFac="https://academics.vit.ac.in/student/fac_profile.asp";
		String str="A";
		String username="YOUR_REGISTRATION_NUMBER";
		String password="YOUR_STUDENT_LOGIN_PASSWORD";
		DateFormat df = new SimpleDateFormat("E,ddMMMyyyyHH:mm:ss");  
		df.setTimeZone(TimeZone.getTimeZone("UTC"));  
		Date date = new Date();
		String displayDate=df.format(date);
		displayDate+="UTC";
		displayDate.replace(" ","%20");
		System.out.println(displayDate);
		String urlFac1="https://academics.vit.ac.in/student/getfacdet.asp";
		GetPageContent http = new GetPageContent();
		// 1. Send a "GET" request, so that you can extract the form's data.
		
		
		String page = http.asString(urlForForm);
		int  captchaStatus=http.getCaptcha(urlCaptcha,"captcha.bmp");
		String postParams = http.getFormParams(page,username,password);
		// 2. Construct above post's content and then send a POST request for
		// authentication
		http.sendPost(urlForPostSubmit, postParams);
		// 3. success then go to gmail.
		int homepageStatus = http.asFile(urlHome,"home_page.html");
		int facinfo = http.asFile(urlFac,"facInfo.html");
		//int facinfo1 = http.asFile(urlFac1,"facInfo1.html");
		ArrayList<String> facultyId=http.fetchFacultyLink(http.asString(urlFac1));
		System.out.println("faculty length is "+facultyId.size());
		int index=0;
		while(index<facultyId.size())
		{
			
			System.out.println("Fetching info for faculty "+index+1);
			String content=http.asString("https://academics.vit.ac.in/student/official_detail_view.asp?empid="+facultyId.get(index));
			http.parseFacultyInfo(content,facultyId.get(index));
			index++;
			
			
		}
		
	}
	
  
}  
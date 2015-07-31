package java_auto_login;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetPageContent 
{
			String[] actualTimeTable=new String[15];
			private List<String> cookies;
			private HttpsURLConnection conn;
			private final String USER_AGENT = "Mozilla/5.0";
			GetPageContent()
			{
				
				// make sure cookies is turn on
				CookieHandler.setDefault(new CookieManager());
				
			}
			public String makeConnection(String url)
			{
				try
				{
				URL obj = new URL(url);
				conn = (HttpsURLConnection) obj.openConnection();

				
				// default is GET
				conn.setRequestMethod("GET");
			 
				conn.setUseCaches(false);
			 
				// act like a browser
				conn.setRequestProperty("User-Agent", USER_AGENT);
				conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
				if (cookies != null) 
				{
					for (String cookie : this.cookies) {
						conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
					}
				}
				int responseCode = conn.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);
				System.out.println("Fetching page");
				//getting the input stream and reading frm it
				BufferedReader in = 
									new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				//storing the content received from input stream into a string
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				System.out.println("page fetched");
				in.close();
				setCookies(conn.getHeaderFields().get("Set-Cookie"));
				//return the string of contents 
				return response.toString();
				}
				catch(MalformedURLException e)
				{
					System.out.println("Please Enter the Write Url");
					e.printStackTrace();
					return "-1";
				}
				catch(IOException e)
				{
					System.out.println("Couldnt Open the Stream");
					e.printStackTrace();
					return "-1";
				}
			}
			public void setCookies(List<String> cookies)
			{
				this.cookies = cookies;
			}
			public String asString(String url)
			{
				return makeConnection(url);
				
			}
			public ArrayList<String> fetchFacultyLink(String info)
			{
				ArrayList<String> facultyId=new ArrayList<String>();
				int index=0;
				while(index<=info.length())
				{
					index=info.indexOf("empid=",index+1);
					if(index==-1)
						break;
					int index2=info.indexOf("\"", index);
					String empId=info.substring(index+6, index2);
					System.out.println("emp id is "+empId+" index is "+index);
					facultyId.add(empId);
					
					
				}
				return facultyId;
				
			}
			
			public void generateData(String timetable)
			{
				int i=0;
				
				
				for(int j=0;j<3;j++)
				{
					i=timetable.indexOf("table", i)+1;
				//	System.out.println("Index is "+i);
				
				}
				timetable=timetable.substring(0,i+5);
				i=0;
				for(int k=0;k<3;k++)
				{
					i=timetable.indexOf("tr",i)+1;
					//System.out.println("Index is "+i);
					
					
				}
				timetable=timetable.substring(i+2, timetable.length());
				//System.out.println("Priting timetable /n"+timetable);
				boolean r=true;
				int trIndex=0;
				int trCloseIndex=0;
				int trFinalIndex=timetable.lastIndexOf("</tr>", timetable.length());
				//System.out.println("Final Index of </tr> is "+trFinalIndex);
				int count=0;
				while(r&& count<10)
				{
					trIndex=timetable.indexOf("<tr", trIndex)+2;
					trCloseIndex=timetable.indexOf("</tr>", trIndex);
					//System.out.println("Close Index of </tr> is "+trCloseIndex);
					if(trCloseIndex==trFinalIndex)
						break;
					int tdIndex=trIndex;
					for(int j=0;j<9;j++)
					{
						tdIndex=timetable.indexOf("<td", tdIndex)+3;
						
					}
					int fontIndex=timetable.indexOf("n'" , tdIndex)+3;
					int fontEndIndex=timetable.indexOf("<", fontIndex);
					actualTimeTable[count]=timetable.substring(fontIndex, fontEndIndex);
					System.out.println(actualTimeTable[count]);
					count++;
				}
				//System.out.println("EM done");
			}
			public int  asFile(String url,String filename)
			{
				try
				{
					String content=makeConnection(url);
					if(filename.contentEquals("timetable.html"))
					{
					generateData(content);
					
					}
					File outputFile=new File(filename);
					FileOutputStream file=new FileOutputStream(outputFile);
					byte[] byteArrayOfContent=content.getBytes();
					file.write(byteArrayOfContent, 0, byteArrayOfContent.length);
					return 1;
				} 
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
					
				}
				catch(IOException e)
				{
					
					e.printStackTrace();
					return -1;
				}
				
				
			}
			public int getCaptcha(String url,String filename)
			{
				try
				{
					DateFormat df = new SimpleDateFormat("E,ddMMMyyyyHH:mm:ss");  
					df.setTimeZone(TimeZone.getTimeZone("UTC"));  
					Date date = new Date();
					String displayDate=df.format(date);
					displayDate+="UTC";
					displayDate.replace(" ","%20");
					System.out.println(displayDate);
					url=url+"?x="+displayDate;
					System.out.println(url);
					URL url_obj=new URL(url);	
					InputStream io=url_obj.openStream();
					byte byte1;
					byte buf[]=new byte[5000];
					File outputfile=new File(filename);
					FileOutputStream file=new FileOutputStream(outputfile);
					int pos = 0;
					while(true)
					{
						  pos=0;
						  int count = io.read(buf, pos, buf.length);
						  
						  if (count <= 0) 
						  {
						    break;
						  }
						  file.write(buf, pos, count);
						  
					}
					io.close();
					file.close();
					
					
					return 1;
				} 
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
					
				}
				catch(IOException e)
				{
					
					e.printStackTrace();
					return -1;
				}
			
			}
			public String getFormParams(String html, String username, String password)
					throws UnsupportedEncodingException
			{
			 
					System.out.println("Extracting form's data...");
					Document doc = Jsoup.parse(html);
					//getting the element with tag form ..this site has only one form element
					Elements loginformList = doc.getElementsByTag("form");
					
					Element loginform=null;
					//reading captcha value from the user
					Scanner obj=new Scanner(System.in);
					String captcha=obj.nextLine();
					//looping over the forms obtained to get the form with form name attribute as parent_login
					for(Element a:loginformList)
					{
						String key=a.attr("name");
						if(key.equals("stud_login"))
						{
							//if form name is equal to parent login we store that form element in the login form 
							loginform=a;
						
						}
					}
					//now extracting individual contents of the form i.e tags of type input
					Elements inputElements = loginform.getElementsByTag("input");
					List<String> paramList = new ArrayList<String>();
					for (Element inputElement : inputElements)
					{
						String key = inputElement.attr("name");
						String value = inputElement.attr("value");
						//wdregno is the name of the input element
						if (key.equals("regno"))
							value = username;
						//wdpswd if the name of password field in the form
						else if (key.equals("passwd"))
							value = password;
						//vrfcd is the name of captcha field in the form
						else if (key.equals("vrfcd"))
							value=captcha;
						//on observing the post url these two were not used so skipping
						else if(value.equals("Login")||value.equals("Reset"))
							continue;
						//creating the post url
						paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
					}

					// build parameters list
					StringBuilder result = new StringBuilder();
					for (String param : paramList)
					{
						if (result.length() == 0)
						{
							result.append(param);
						}
						else
						{
							//appending an & as it was seen the url uses this separator as terminating character
							result.append("&" + param);
						}
					}
					System.out.println(result.substring(0, result.lastIndexOf("&")));
					String s=result.substring(0, result.lastIndexOf("&"));
					//return the parameters list
					return s;
			  }
			 
			  public List<String> getCookies() 
			  {
				return cookies;
			  }
			  public void sendPost(String url, String postParams) throws Exception
			  {
				  
					URL obj = new URL(url);
					conn = (HttpsURLConnection) obj.openConnection();
				 
					// Acts like a browser
					conn.setUseCaches(false);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Host","academics.vit.ac.in");
					conn.setRequestProperty("User-Agent", USER_AGENT);
					conn.setRequestProperty("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
					conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
					conn.setRequestProperty("Connection", "keep-alive");
					conn.setRequestProperty("Referer", "https://academics.vit.ac.in/parent/parent_login.asp");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					//conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
				 
					conn.setDoOutput(true);
					conn.setDoInput(true);
				 
					// Send post request
					DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
					wr.writeBytes(postParams);
					wr.flush();
					wr.close();
				 
					int responseCode = conn.getResponseCode();
					System.out.println("\nSending 'POST' request to URL : " + url);
					System.out.println("Post parameters : " + postParams);
					System.out.println("Response Code : " + responseCode);
				 
					BufferedReader in = 
				             new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
				 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					System.out.println(response.toString());
				 
				 }
			public void parseFacultyInfo(String content,String empId)
			{
				// TODO Auto-generated method stub
				 dbConnection dbConn;
			     Connection handle;
			     dbConn=new dbConnection();
			    try
			    {
			    	System.out.println("inserting id");
			    	Connection c=dbConn.connToDb();
	                System.out.println("Creating statement..."); 
	                PreparedStatement stmt; 
	                String sql;
	                //fetching users who have their notifications enabled
	                sql = "insert into facultyInfo (empId) values (?)";
	                
	                stmt = c.prepareStatement(sql);
	                stmt.setString(1,empId);
	                stmt.executeUpdate();
	                c.close();	
	                System.out.println("insertd id");
			    
			    
				System.out.println("Parsing faculty info");
				int beginindex=0;
				int endindex=0;
				String faculty="";
				int count=0;
				while(true)
				{
					beginindex=content.indexOf("<td width=\"660\">",beginindex+1);
					if(beginindex==-1)
						break;
					endindex=content.indexOf("<", beginindex+1);
					
					String info=content.substring(beginindex+16, endindex);
					if(count==0)
					{
						System.out.println("inserting name");
						c=dbConn.connToDb();
		                System.out.println("Creating statement..."); 
		                
		                //fetching users who have their notifications enabled
		                sql = "update facultyInfo set empName=? where empId=?";
		                
		                stmt = c.prepareStatement(sql);
		                stmt.setString(1, info);
		                
		                stmt.setString(2,empId);
		                stmt.executeUpdate();
		                c.close();	
				    	count++;
				    	System.out.println("insertd name");
				    	continue;
					}
					if(count==1)
					{
						System.out.println("inserting school");
						c=dbConn.connToDb();
		                System.out.println("Creating statement..."); 
		                
		                //fetching users who have their notifications enabled
		                sql = "update facultyInfo set school=? where empId=?";
		                
		                stmt = c.prepareStatement(sql);
		                stmt.setString(1, info);
		                
		                stmt.setString(2,empId);
		                stmt.executeUpdate();
		                c.close();	
				    	count++;
				    	System.out.println("insertd school");
				    	continue;
					}
					if(count==2)
					{
						System.out.println("inserting design");
						c=dbConn.connToDb();
		                System.out.println("Creating statement..."); 
		                
		                //fetching users who have their notifications enabled
		                sql = "update facultyInfo set designation=? where empId=?";
		                
		                stmt = c.prepareStatement(sql);
		                stmt.setString(1, info);
		                
		                stmt.setString(2,empId);
		                stmt.executeUpdate();
		                c.close();	
				    	count++;
				    	System.out.println("insertd design");
				    	continue;
					}
					if(count==3)
					{
						System.out.println("inserting cabinNo");
						c=dbConn.connToDb();
		                System.out.println("Creating statement..."); 
		                
		                //fetching users who have their notifications enabled
		                sql = "update facultyInfo set cabinNo=? where empId=?";
		                
		                stmt = c.prepareStatement(sql);
		                stmt.setString(1, info);
		                
		                stmt.setString(2,empId);
		                stmt.executeUpdate();
		                c.close();	
				    	count++;
				    	System.out.println("insertd cabinno");
				    	continue;
					}
					if(count==4)
					{
						count++;
						continue;
					}
					if(count==5)
					{
						System.out.println("inserting email");
						c=dbConn.connToDb();
						
						
		                System.out.println("Creating statement..."); 
		                
		                //fetching users who have their notifications enabled
		                sql = "update facultyInfo set emailId=? where empId=?";
		                
		                stmt = c.prepareStatement(sql);
		                stmt.setString(1, info);
		                
		                stmt.setString(2,empId);
		                stmt.executeUpdate();
		                c.close();	
		                System.out.println("insertd mail");
		                continue;
					}
					
					faculty+=" "+info;
					
				}
				System.out.println("Faculty info is "+faculty);
			    }
			    catch(SQLException ex)
			    {
			    	
			    	
			    	
			    }
				
			}


}

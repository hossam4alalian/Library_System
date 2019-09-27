

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.database;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		showUsers(request, response);
		showMedia(request, response);
		addMedia(request, response);
		borrowMedia(request, response);
		addUsers(request, response);
		borrowed(request, response);
		returnMedia(request, response);
		
		expirationDate();
		
		doGet(request, response);
	}
	
	public void showUsers(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
			response.setContentType("text/html");
			database db=new database("localhost", "root", "","pär");
			PrintWriter out = response.getWriter();
			
			Object[][]users = db.getData("select * from användare");
			
			if(request.getParameter("submit").equals("Show users")) {
				//för att visa users
				for(int ii=0; ii<users.length; ii++) {
					out.println("User: "+users[ii][1]+" "+users[ii][2]+" --- Personal number: "+users[ii][3]+" <br><br>");
				}
					
					out.print("<form action = \"main.jsp\">\r\n" + 
							"     	\r\n" + 
							"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
							"          \r\n" + 
							"          \r\n" + 
							"     </form>");
			}	
	}
	
	public void showMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		response.setContentType("text/html");
		database db=new database("localhost", "root", "","pär");
		PrintWriter out = response.getWriter();
		
		
		Object[][]böcker = db.getData("select * from böcker");
		Object[][]cd = db.getData("select * from cd");
		Object[][]dvd = db.getData("select * from dvd");
		
		
		Object[][]lånadeBöcker = db.getData("select * from lånadeböcker");
		Object[][]lånadeCD = db.getData("select * from lånadecd");
		Object[][]lånadeDVD = db.getData("select * from lånadedvd");
		
		request.getSession().removeAttribute("borrowedBooks");
		request.getSession().removeAttribute("borrowedCD");
		request.getSession().removeAttribute("borrowedDVD");
		
		
		if(request.getParameter("submit").equals("Show books")) {
			//för att visa lånade böcker och vem lånade dem
			
			for(int ii=0; ii<böcker.length; ii++) {
				boolean check=false;
				for(int i=0; i<lånadeBöcker.length; i++) {
					System.out.println(lånadeBöcker[i][1]+"----"+(böcker[ii][2]));
					if(!lånadeBöcker[i][1].equals(böcker[ii][2])) {
						System.out.println("1");
						//out.println("Book: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"<br><br>");
					}
					
					else if(lånadeBöcker[i][1].equals(böcker[ii][2])) {
						System.out.println("2");
						//out.println("böcker: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"  (Borrowed)"+"<br><br>");
						check=true;
						break;
					}
					System.out.println(" -------- ");
				}
				if (check) {
					out.println("böcker: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+" Categori: "+böcker[ii][3]+"  (Borrowed)"+"<br><br>");
				}
				else if (check==false) {
					out.println("Book: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+" Categori: "+böcker[ii][3]+"<br><br>");
				}
					
			}
				out.print("<form action = \"main.jsp\">\r\n" + 
						"     	\r\n" + 
						"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
						"          \r\n" + 
						"          \r\n" + 
						"     </form>");
		}
			//för att visa lånade cd och vem lånade dem
		if(request.getParameter("submit").equals("Show CDs")) {
			for(int ii=0; ii<cd.length; ii++) {
				boolean check=false;
				for(int i=0; i<lånadeCD.length; i++) {
					
					if(!lånadeCD[i][1].equals(cd[ii][2])) {
						
						//out.println("Book: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"<br><br>");
					}
					
					else if(lånadeCD[i][1].equals(cd[ii][2])) {
						
						//out.println("böcker: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"  (Borrowed)"+"<br><br>");
						check=true;
						break;
					}
					
				}
				if (check) {
					out.println("CD: "+cd[ii][1]+" ------ Serial number: "+cd[ii][2]+" Categori: "+cd[ii][3]+"  (Borrowed)"+"<br><br>");
				}
				else if (check==false) {
					out.println("CD: "+cd[ii][1]+" ------ Serial number: "+cd[ii][2]+" Categori: "+cd[ii][3]+"<br><br>");
				}
					
			}
				out.print("<form action = \"main.jsp\">\r\n" + 
						"     	\r\n" + 
						"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
						"          \r\n" + 
						"          \r\n" + 
						"     </form>");
		}
		if(request.getParameter("submit").equals("Show DVDs")) {
			//för att visa lånade dvd och vem lånade dem
		
			for(int ii=0; ii<dvd.length; ii++) {
				boolean check=false;
				for(int i=0; i<lånadeDVD.length; i++) {
					
					if(!lånadeDVD[i][1].equals(dvd[ii][2])) {
						
						//out.println("Book: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"<br><br>");
					}
					
					else if(lånadeDVD[i][1].equals(dvd[ii][2])) {
						
						//out.println("böcker: "+böcker[ii][1]+" ------ Serial number: "+böcker[ii][2]+"  (Borrowed)"+"<br><br>");
						check=true;
						break;
					}
					
				}
				if (check) {
					out.println("DVD: "+dvd[ii][1]+" ------ Serial number: "+dvd[ii][2]+" Categori: "+dvd[ii][3]+"  (Borrowed)"+"<br><br>");
				}
				else if (check==false) {
					out.println("DVD: "+dvd[ii][1]+" ------ Serial number: "+dvd[ii][2]+" Categori: "+dvd[ii][3]+"<br><br>");
				}
					
			}
				
		
			out.print("<form action = \"main.jsp\">\r\n" + 
					"     	\r\n" + 
					"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
					"          \r\n" + 
					"          \r\n" + 
					"     </form>");
		}
	}
	
	public void addMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","pär");
		
		request.getSession().removeAttribute("bookerror");
		request.getSession().removeAttribute("cderror");
		request.getSession().removeAttribute("dvderror");
		
		if(request.getParameter("submit").equals("Add book")) {
			String name, snum,bookCat;
			name=request.getParameter("bname");
			snum= request.getParameter("bsnum");
			bookCat= request.getParameter("bokCat");
			
			Object[][]bookData = db.getData("select * from böcker WHERE serialNummer="+snum+"");
			
			if(bookData.length==0) {
				String SQL=String.format("INSERT INTO böcker(namn,serialNummer,kategori) "+"VALUES ('%s','%s','%s');",name,snum,bookCat);
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("bookerror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("bookerror", "Book is already registered");
			}
			

		}
		
		if(request.getParameter("submit").equals("Add CD")) {
			String name, snum,cdCat;
			name=request.getParameter("cname");
			snum= request.getParameter("csnum");
			cdCat= request.getParameter("cdCat");
			
			Object[][]cdData = db.getData("select * from cd WHERE serialNummer="+snum+"");

			if(cdData.length==0) {
				String SQL=String.format("INSERT INTO cd(namn,serialNummer,kategori) "+"VALUES ('%s','%s','%s');",name,snum,cdCat);
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("cderror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("cderror", "CD is already registered");
			
			}
			
		}
		
		if(request.getParameter("submit").equals("Add DVD")) {
			String name, snum,dvdCat;
			name=request.getParameter("dname");
			snum= request.getParameter("dsnum");
			dvdCat= request.getParameter("dvdCat");

			
			Object[][]dvdData = db.getData("select * from dvd WHERE serialNummer="+snum+"");
			
			if(dvdData.length==0) {
				String SQL=String.format("INSERT INTO dvd(namn,serialNummer,kategori) "+"VALUES ('%s','%s','%s');",name,snum,dvdCat);
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("dvderror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("dvderror", "DVD is already registered");
			}
			
		}
	}
	
	public void borrowMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","pär");
		
		request.getSession().removeAttribute("errorMessage");
		request.getSession().removeAttribute("errorMessage2");
		request.getSession().removeAttribute("errorMessage3");
		
		//det här för att låna en bok.
		if(request.getParameter("submit").equals("Borrow book")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("booksnum");
			
			
			Object[][]bookData = db.getData("select * from böcker WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			Object[][]lånadeBöcker = db.getData("select * from lånadeBöcker WHERE mediaID="+mediaNum+"");
			
					try {
						if(userData.length==1 &&bookData.length==1 && lånadeBöcker.length==0) {
							String SQL=String.format("INSERT INTO lånadeböcker(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",bookData[0][2],userData[0][3],currentDate());
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							response.sendRedirect(request.getContextPath() + "/main.jsp");
						}
						else {
							response.sendRedirect(request.getContextPath() + "/main.jsp");
							request.getSession().setAttribute("errorMessage", "The person or the media isn't registered/The media is already borrowed");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					
		}
		//det här för att låna en CD.
		if(request.getParameter("submit").equals("Borrow CD")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("cdsnum");
			
			Object[][]cdData = db.getData("select * from cd WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			Object[][]lånadeCD = db.getData("select * from lånadecd WHERE mediaID="+mediaNum+"");
					
				try {
						if(userData.length==1  &&cdData.length==1 && lånadeCD.length==0) {
							String SQL=String.format("INSERT INTO lånadecd(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",cdData[0][2],userData[0][3],currentDate());
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							response.sendRedirect(request.getContextPath() + "/main.jsp");
						}
						else {
							response.sendRedirect(request.getContextPath() + "/main.jsp");
							request.getSession().setAttribute("errorMessage2", "The person or the media isn't registered/The media is already borrowed");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					
		}
		//det här för att låna en DVD.
		if(request.getParameter("submit").equals("Borrow DVD")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("dvdsnum");
			
			Object[][]dvdData = db.getData("select * from dvd WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			Object[][]lånadeDVD = db.getData("select * from lånadedvd WHERE mediaID="+mediaNum+"");
					
				try {
						if(userData.length==1  &&dvdData.length==1 && lånadeDVD.length==0) {
							String SQL=String.format("INSERT INTO lånadedvd(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",dvdData[0][2],userData[0][3],currentDate());
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							response.sendRedirect(request.getContextPath() + "/main.jsp");
						}
						else {
							response.sendRedirect(request.getContextPath() + "/main.jsp");
							request.getSession().setAttribute("errorMessage3", "The person or the media isn't registered/The media is already borrowed");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					
		}
			
	}
	
	public void addUsers(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","pär");
		String personNummer;
		personNummer= request.getParameter("pnum");
		
		Object[][]userData = db.getData("select * from användare WHERE personNummer="+personNummer+"");
		request.getSession().removeAttribute("usererror");
		
		
		if(request.getParameter("submit").equals("Add user")) {
			String fname, ename, pnum;
			fname=request.getParameter("fname");
			ename= request.getParameter("ename");
			pnum= request.getParameter("pnum");
			if(userData.length!=1) {
				String SQL=String.format("INSERT INTO användare(fnamn,enamn,personNummer) "+"VALUES ('%s','%s','%s');",fname,ename,pnum);
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("usererror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("usererror", "The personal number is already used");
			}
			
			

		}
	}
	
	public String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		
		
		return dtf.format(now); 
	}
	
	public void expirationDate() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now(); 
		LocalDateTime newTime = now.plusSeconds(10);
		if(now.isAfter(newTime)) {
			System.out.println("expired");
		}
	}
	
	public void borrowed(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		response.setContentType("text/html");
		database db=new database("localhost", "root", "","pär");
		PrintWriter out = response.getWriter();
		
		Object[][]lånadeböcker = db.getData("select * from lånadeböcker");
		Object[][]lånadecd = db.getData("select * from lånadecd");
		Object[][]lånadedvd = db.getData("select * from lånadedvd");
		
		Object[][]böcker = db.getData("select * from böcker");
		Object[][]cd = db.getData("select * from cd");
		Object[][]dvd = db.getData("select * from dvd");
		
		Object[][]user = db.getData("select * from användare");
		
		request.getSession().removeAttribute("borrowedBooks");
		request.getSession().removeAttribute("borrowedCD");
		request.getSession().removeAttribute("borrowedDVD");
		
		
		if(request.getParameter("submit").equals("Show Borrowed Media")) {
			//för att visa lånade böcker och vem lånade dem
			for(int i=0; i<lånadeböcker.length; i++) {
				for(int ii=0; ii<böcker.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(lånadeböcker[i][1].equals(böcker[ii][2]) && lånadeböcker[i][2].equals(user[iii][3])) {
							out.println("Book: "+böcker[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+lånadeböcker[i][3]+"<br><br>");
						}
					}
						
				}
			}
			//för att visa lånade cd och vem lånade dem
			for(int i=0; i<lånadecd.length; i++) {
				for(int ii=0; ii<cd.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(lånadecd[i][1].equals(cd[ii][2]) && lånadecd[i][2].equals(user[iii][3])) {
							out.println("CD: "+cd[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+lånadecd[i][3]+"<br><br>");
						}
					}
				}
			}
				
			//för att visa lånade dvd och vem lånade dem
			for(int i=0; i<lånadedvd.length; i++) {
				for(int ii=0; ii<dvd.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(lånadedvd[i][1].equals(dvd[ii][2]) && lånadedvd[i][2].equals(user[iii][3])) {
							out.println("DVD: "+dvd[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+lånadedvd[i][3]+"<br><br>");
						}
					}
				}
			}
			out.print("<form action = \"main.jsp\">\r\n" + 
					"     	\r\n" + 
					"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
					"          \r\n" + 
					"          \r\n" + 
					"     </form>");
			
		}
		
		
		
	}
	
	public void returnMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","pär");
		request.getSession().removeAttribute("returnbookerror");
		
		if(request.getParameter("submit").equals("Return book")) {
			String bokSnum;
			bokSnum=request.getParameter("bokSnum");
			Object[][]lånadeböcker = db.getData("select * from lånadeböcker WHERE mediaID="+bokSnum+"");
			//när man returnar en bok
			if(lånadeböcker.length==1) {
				String SQL=("DELETE FROM lånadeböcker WHERE mediaID ="+bokSnum+"");
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("returnbookerror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("returnbookerror", "This book doesn't belong here");
			}
		}
		//när man returnar en CD
		if(request.getParameter("submit").equals("Return CD")) {
			String cdSnum;
			cdSnum=request.getParameter("cdSnum");
			Object[][]lånadecd = db.getData("select * from lånadecd WHERE mediaID="+cdSnum+"");
			
			if(lånadecd.length==1) {
				String SQL=("DELETE FROM lånadecd WHERE mediaID ="+cdSnum+"");
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("returncderror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("returncderror", "This CD doesn't belong here");
			}
		}
		//när man returnar en DVD
		if(request.getParameter("submit").equals("Return DVD")) {
			String dvdSnum;
			dvdSnum=request.getParameter("dvdSnum");
			Object[][]lånadedvd = db.getData("select * from lånadedvd WHERE mediaID="+dvdSnum+"");
			
			if(lånadedvd.length==1) {
				String SQL=("DELETE FROM lånadedvd WHERE mediaID ="+dvdSnum+"");
				try {
					db.execute(SQL);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().removeAttribute("returndvderror");
				response.sendRedirect(request.getContextPath() + "/main.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/main.jsp");
				request.getSession().setAttribute("returndvderror", "This DVD doesn't belong here");
			}
		}
	}

}



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
			database db=new database("localhost", "root", "","p�r");
			PrintWriter out = response.getWriter();
			
			Object[][]users = db.getData("select * from anv�ndare");
			
			if(request.getParameter("submit").equals("Show users")) {
				//f�r att visa users
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
		database db=new database("localhost", "root", "","p�r");
		PrintWriter out = response.getWriter();
		
		
		Object[][]b�cker = db.getData("select * from b�cker");
		Object[][]cd = db.getData("select * from cd");
		Object[][]dvd = db.getData("select * from dvd");
		
		
		Object[][]l�nadeB�cker = db.getData("select * from l�nadeb�cker");
		Object[][]l�nadeCD = db.getData("select * from l�nadecd");
		Object[][]l�nadeDVD = db.getData("select * from l�nadedvd");
		
		request.getSession().removeAttribute("borrowedBooks");
		request.getSession().removeAttribute("borrowedCD");
		request.getSession().removeAttribute("borrowedDVD");
		
		
		if(request.getParameter("submit").equals("Show books")) {
			//f�r att visa l�nade b�cker och vem l�nade dem
			
			for(int ii=0; ii<b�cker.length; ii++) {
				boolean check=false;
				for(int i=0; i<l�nadeB�cker.length; i++) {
					System.out.println(l�nadeB�cker[i][1]+"----"+(b�cker[ii][2]));
					if(!l�nadeB�cker[i][1].equals(b�cker[ii][2])) {
						System.out.println("1");
						//out.println("Book: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"<br><br>");
					}
					
					else if(l�nadeB�cker[i][1].equals(b�cker[ii][2])) {
						System.out.println("2");
						//out.println("b�cker: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"  (Borrowed)"+"<br><br>");
						check=true;
						break;
					}
					System.out.println(" -------- ");
				}
				if (check) {
					out.println("b�cker: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+" Categori: "+b�cker[ii][3]+"  (Borrowed)"+"<br><br>");
				}
				else if (check==false) {
					out.println("Book: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+" Categori: "+b�cker[ii][3]+"<br><br>");
				}
					
			}
				out.print("<form action = \"main.jsp\">\r\n" + 
						"     	\r\n" + 
						"         <input type = \"submit\"  value = \"Back To Home\" />\r\n" + 
						"          \r\n" + 
						"          \r\n" + 
						"     </form>");
		}
			//f�r att visa l�nade cd och vem l�nade dem
		if(request.getParameter("submit").equals("Show CDs")) {
			for(int ii=0; ii<cd.length; ii++) {
				boolean check=false;
				for(int i=0; i<l�nadeCD.length; i++) {
					
					if(!l�nadeCD[i][1].equals(cd[ii][2])) {
						
						//out.println("Book: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"<br><br>");
					}
					
					else if(l�nadeCD[i][1].equals(cd[ii][2])) {
						
						//out.println("b�cker: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"  (Borrowed)"+"<br><br>");
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
			//f�r att visa l�nade dvd och vem l�nade dem
		
			for(int ii=0; ii<dvd.length; ii++) {
				boolean check=false;
				for(int i=0; i<l�nadeDVD.length; i++) {
					
					if(!l�nadeDVD[i][1].equals(dvd[ii][2])) {
						
						//out.println("Book: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"<br><br>");
					}
					
					else if(l�nadeDVD[i][1].equals(dvd[ii][2])) {
						
						//out.println("b�cker: "+b�cker[ii][1]+" ------ Serial number: "+b�cker[ii][2]+"  (Borrowed)"+"<br><br>");
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
		database db=new database("localhost", "root", "","p�r");
		
		request.getSession().removeAttribute("bookerror");
		request.getSession().removeAttribute("cderror");
		request.getSession().removeAttribute("dvderror");
		
		if(request.getParameter("submit").equals("Add book")) {
			String name, snum,bookCat;
			name=request.getParameter("bname");
			snum= request.getParameter("bsnum");
			bookCat= request.getParameter("bokCat");
			
			Object[][]bookData = db.getData("select * from b�cker WHERE serialNummer="+snum+"");
			
			if(bookData.length==0) {
				String SQL=String.format("INSERT INTO b�cker(namn,serialNummer,kategori) "+"VALUES ('%s','%s','%s');",name,snum,bookCat);
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
		database db=new database("localhost", "root", "","p�r");
		
		request.getSession().removeAttribute("errorMessage");
		request.getSession().removeAttribute("errorMessage2");
		request.getSession().removeAttribute("errorMessage3");
		
		//det h�r f�r att l�na en bok.
		if(request.getParameter("submit").equals("Borrow book")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("booksnum");
			
			
			Object[][]bookData = db.getData("select * from b�cker WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from anv�ndare WHERE personNummer="+borrowsnum+"");
			Object[][]l�nadeB�cker = db.getData("select * from l�nadeB�cker WHERE mediaID="+mediaNum+"");
			
					try {
						if(userData.length==1 &&bookData.length==1 && l�nadeB�cker.length==0) {
							String SQL=String.format("INSERT INTO l�nadeb�cker(mediaID,anv�ndareID,datumn) "+"VALUES ('%s','%s','%s');",bookData[0][2],userData[0][3],currentDate());
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
		//det h�r f�r att l�na en CD.
		if(request.getParameter("submit").equals("Borrow CD")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("cdsnum");
			
			Object[][]cdData = db.getData("select * from cd WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from anv�ndare WHERE personNummer="+borrowsnum+"");
			Object[][]l�nadeCD = db.getData("select * from l�nadecd WHERE mediaID="+mediaNum+"");
					
				try {
						if(userData.length==1  &&cdData.length==1 && l�nadeCD.length==0) {
							String SQL=String.format("INSERT INTO l�nadecd(mediaID,anv�ndareID,datumn) "+"VALUES ('%s','%s','%s');",cdData[0][2],userData[0][3],currentDate());
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
		//det h�r f�r att l�na en DVD.
		if(request.getParameter("submit").equals("Borrow DVD")) {
			String borrowsnum,mediaNum;
			borrowsnum= request.getParameter("borrowsnum");
			mediaNum= request.getParameter("dvdsnum");
			
			Object[][]dvdData = db.getData("select * from dvd WHERE serialNummer= "+mediaNum+"");
			Object[][]userData = db.getData("select * from anv�ndare WHERE personNummer="+borrowsnum+"");
			Object[][]l�nadeDVD = db.getData("select * from l�nadedvd WHERE mediaID="+mediaNum+"");
					
				try {
						if(userData.length==1  &&dvdData.length==1 && l�nadeDVD.length==0) {
							String SQL=String.format("INSERT INTO l�nadedvd(mediaID,anv�ndareID,datumn) "+"VALUES ('%s','%s','%s');",dvdData[0][2],userData[0][3],currentDate());
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
		database db=new database("localhost", "root", "","p�r");
		String personNummer;
		personNummer= request.getParameter("pnum");
		
		Object[][]userData = db.getData("select * from anv�ndare WHERE personNummer="+personNummer+"");
		request.getSession().removeAttribute("usererror");
		
		
		if(request.getParameter("submit").equals("Add user")) {
			String fname, ename, pnum;
			fname=request.getParameter("fname");
			ename= request.getParameter("ename");
			pnum= request.getParameter("pnum");
			if(userData.length!=1) {
				String SQL=String.format("INSERT INTO anv�ndare(fnamn,enamn,personNummer) "+"VALUES ('%s','%s','%s');",fname,ename,pnum);
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
		database db=new database("localhost", "root", "","p�r");
		PrintWriter out = response.getWriter();
		
		Object[][]l�nadeb�cker = db.getData("select * from l�nadeb�cker");
		Object[][]l�nadecd = db.getData("select * from l�nadecd");
		Object[][]l�nadedvd = db.getData("select * from l�nadedvd");
		
		Object[][]b�cker = db.getData("select * from b�cker");
		Object[][]cd = db.getData("select * from cd");
		Object[][]dvd = db.getData("select * from dvd");
		
		Object[][]user = db.getData("select * from anv�ndare");
		
		request.getSession().removeAttribute("borrowedBooks");
		request.getSession().removeAttribute("borrowedCD");
		request.getSession().removeAttribute("borrowedDVD");
		
		
		if(request.getParameter("submit").equals("Show Borrowed Media")) {
			//f�r att visa l�nade b�cker och vem l�nade dem
			for(int i=0; i<l�nadeb�cker.length; i++) {
				for(int ii=0; ii<b�cker.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(l�nadeb�cker[i][1].equals(b�cker[ii][2]) && l�nadeb�cker[i][2].equals(user[iii][3])) {
							out.println("Book: "+b�cker[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+l�nadeb�cker[i][3]+"<br><br>");
						}
					}
						
				}
			}
			//f�r att visa l�nade cd och vem l�nade dem
			for(int i=0; i<l�nadecd.length; i++) {
				for(int ii=0; ii<cd.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(l�nadecd[i][1].equals(cd[ii][2]) && l�nadecd[i][2].equals(user[iii][3])) {
							out.println("CD: "+cd[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+l�nadecd[i][3]+"<br><br>");
						}
					}
				}
			}
				
			//f�r att visa l�nade dvd och vem l�nade dem
			for(int i=0; i<l�nadedvd.length; i++) {
				for(int ii=0; ii<dvd.length; ii++) {
					for(int iii=0; iii<user.length; iii++) {
						if(l�nadedvd[i][1].equals(dvd[ii][2]) && l�nadedvd[i][2].equals(user[iii][3])) {
							out.println("DVD: "+dvd[ii][1]+" borrowed of "+ user[iii][1]+" "+user[iii][2]+" in "+l�nadedvd[i][3]+"<br><br>");
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
		database db=new database("localhost", "root", "","p�r");
		request.getSession().removeAttribute("returnbookerror");
		
		if(request.getParameter("submit").equals("Return book")) {
			String bokSnum;
			bokSnum=request.getParameter("bokSnum");
			Object[][]l�nadeb�cker = db.getData("select * from l�nadeb�cker WHERE mediaID="+bokSnum+"");
			//n�r man returnar en bok
			if(l�nadeb�cker.length==1) {
				String SQL=("DELETE FROM l�nadeb�cker WHERE mediaID ="+bokSnum+"");
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
		//n�r man returnar en CD
		if(request.getParameter("submit").equals("Return CD")) {
			String cdSnum;
			cdSnum=request.getParameter("cdSnum");
			Object[][]l�nadecd = db.getData("select * from l�nadecd WHERE mediaID="+cdSnum+"");
			
			if(l�nadecd.length==1) {
				String SQL=("DELETE FROM l�nadecd WHERE mediaID ="+cdSnum+"");
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
		//n�r man returnar en DVD
		if(request.getParameter("submit").equals("Return DVD")) {
			String dvdSnum;
			dvdSnum=request.getParameter("dvdSnum");
			Object[][]l�nadedvd = db.getData("select * from l�nadedvd WHERE mediaID="+dvdSnum+"");
			
			if(l�nadedvd.length==1) {
				String SQL=("DELETE FROM l�nadedvd WHERE mediaID ="+dvdSnum+"");
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

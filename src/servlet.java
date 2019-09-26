

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		
		addMedia(request, response);
		borrowMedia(request, response);
		addUsers(request, response);
		
		doGet(request, response);
	}
	
	public void addMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","p�r");
		if(request.getParameter("submit").equals("Add book")) {
			String name, snum;
			name=request.getParameter("bname");
			snum= request.getParameter("bsnum");
			
			String SQL=String.format("INSERT INTO b�cker(namn,serialNummer) "+"VALUES ('%s','%s');",name,snum);
			try {
				db.execute(SQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
		
		if(request.getParameter("submit").equals("Add CD")) {
			String name, snum;
			name=request.getParameter("cname");
			snum= request.getParameter("csnum");
			
			String SQL=String.format("INSERT INTO cd(namn,serialNummer) "+"VALUES ('%s','%s');",name,snum);
			try {
				db.execute(SQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
		
		if(request.getParameter("submit").equals("Add DVD")) {
			String name, snum;
			name=request.getParameter("dname");
			snum= request.getParameter("dsnum");
			
			String SQL=String.format("INSERT INTO dvd(namn,serialNummer) "+"VALUES ('%s','%s');",name,snum);
			try {
				db.execute(SQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
	}
	
	public void borrowMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","p�r");
		PrintWriter out = response.getWriter();
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
			
					try {
						if(userData.length==1 &&bookData.length==1) {
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
							request.getSession().setAttribute("errorMessage", "The person or the media isn't registered");
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
			
					try {
						if(userData.length==1  &&cdData.length==1) {
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
							request.getSession().setAttribute("errorMessage2", "The person or the media isn't registered");
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
			
					try {
						if(userData.length==1  &&dvdData.length==1) {
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
							request.getSession().setAttribute("errorMessage3", "The person or the media isn't registered");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					
		}
			
	}
	
	public void addUsers(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","p�r");
		if(request.getParameter("submit").equals("Add user")) {
			String fname, ename, pnum;
			fname=request.getParameter("fname");
			ename= request.getParameter("ename");
			pnum= request.getParameter("pnum");
			
			String SQL=String.format("INSERT INTO anv�ndare(fnamn,enamn,personNummer) "+"VALUES ('%s','%s','%s');",fname,ename,pnum);
			try {
				db.execute(SQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
	}
	
	public String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		
		return dtf.format(now); 
	}

}

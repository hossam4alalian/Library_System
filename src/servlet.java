

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		addMedia(request, response);
		borrowMedia(request, response);
		
		doGet(request, response);
	}
	
	public void addMedia(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		database db=new database("localhost", "root", "","pär");
		if(request.getParameter("submit").equals("Add book")) {
			String name, snum;
			name=request.getParameter("bname");
			snum= request.getParameter("bsnum");
			
			String SQL=String.format("INSERT INTO böcker(namn,serialNummer) "+"VALUES ('%s','%s');",name,snum);
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
		database db=new database("localhost", "root", "","pär");
		
		
		//det här för att låna en bok.
		if(request.getParameter("submit").equals("Borrow book")) {
			String borrowsnum,bsname;
			borrowsnum= request.getParameter("borrowsnum");
			bsname= request.getParameter("booksnum");
			
			Object[][]bookData = db.getData("select * from böcker WHERE serialNummer= "+bsname+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			
					try {
						if(userData.length==1 &&bookData.length==1) {
							String SQL=String.format("INSERT INTO lånadeböcker(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",bookData[0][2],userData[0][3],"2019-05-07*");
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
						else {
							System.out.println("media or user doesn't seem to be registered");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
		
		if(request.getParameter("submit").equals("Borrow CD")) {
			String borrowsnum,bsname;
			borrowsnum= request.getParameter("borrowsnum");
			bsname= request.getParameter("cdsnum");
			
			Object[][]cdData = db.getData("select * from cd WHERE serialNummer= "+bsname+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			
					try {
						if(userData.length==1  &&cdData.length==1) {
							String SQL=String.format("INSERT INTO lånadecd(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",cdData[0][2],userData[0][3],"2019-05-07*");
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
						else {
							System.out.println("media or user doesn't seem to be registered");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
		
		if(request.getParameter("submit").equals("Borrow DVD")) {
			String borrowsnum,bsname;
			borrowsnum= request.getParameter("borrowsnum");
			bsname= request.getParameter("dvdsnum");
			
			Object[][]dvdData = db.getData("select * from dvd WHERE serialNummer= "+bsname+"");
			Object[][]userData = db.getData("select * from användare WHERE personNummer="+borrowsnum+"");
			
					try {
						if(userData.length==1  &&dvdData.length==1) {
							String SQL=String.format("INSERT INTO lånadedvd(mediaID,användareID,datumn) "+"VALUES ('%s','%s','%s');",dvdData[0][2],userData[0][3],"2019-05-07*");
							try {
								db.execute(SQL);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
						else {
							System.out.println("media or user doesn't seem to be registered");
						}
					}
					catch (Exception missfall) {
						System.out.println("Error"+ missfall);
					}
					response.sendRedirect(request.getContextPath() + "/main.jsp");
		}
			
	}
	
	public void addUsers() {
		
	}

}

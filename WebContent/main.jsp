<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bibliotek</title>
</head>
<body>
	<h1>Add Media</h1>
	<form action = "servlet" method = "POST">
         Book's name: <input type = "text" name = "bname">
         <br />
         Book's serial number: <input type = "text" name = "bsnum" /><br />
         <input type = "submit" name= "submit" value = "Add book" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         CD's name: <input type = "text" name = "cname">
         <br />
         CD's serial number: <input type = "text" name = "csnum" /><br />
         <input type = "submit" name= "submit" value = "Add CD" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         DVD's name: <input type = "text" name = "dname">
         <br />
         DVD's serial number: <input type = "text" name = "dsnum" /><br />
         <input type = "submit" name= "submit" value = "Add DVD" />
     </form>
     
     
     <h1>Borrow Media</h1>
     
     <form action = "servlet" method = "POST">
     	Borrower's personal number: <input type = "text" name = "borrowsnum" /><br />
        Book's Serial number: <input type = "text" name = "booksnum" /><br />
         <input type = "submit" name= "submit" value = "Borrow book" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
     	Borrower's personal number: <input type = "text" name = "borrowsnum" /><br />
        CD's Serial number: <input type = "text" name = "cdsnum" /><br />
         <input type = "submit" name= "submit" value = "Borrow CD" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
     	Borrower's personal number: <input type = "text" name = "borrowsnum" /><br />
        DVD's Serial number: <input type = "text" name = "dvdsnum" /><br />
         <input type = "submit" name= "submit" value = "Borrow DVD" />
     </form>
     
     <h1>Add Users</h1>
     
     <form action = "servlet" method = "POST">
     	First name: <input type = "text" name = "borrowsnum" /><br />
        Last name: <input type = "text" name = "dvdsnum" /><br />
        Personal number: <input type = "text" name = "dvdsnum" /><br />
         <input type = "submit" name= "submit" value = "Add user" />
     </form>
     
     <h1>Borrowed Media</h1>
     
     
</body>
</html>
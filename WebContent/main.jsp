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
         Book's serial number: <input type = "text" name = "bsnum" maxlength = "11"  onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add book" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         CD's name: <input type = "text" name = "cname">
         <br />
         CD's serial number: <input type = "text" name = "csnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add CD" />
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         DVD's name: <input type = "text" name = "dname">
         <br />
         DVD's serial number: <input type = "text" name = "dsnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add DVD" />
     </form>
     
     
     <h1>Borrow Media</h1>
     
     <form action = "servlet" method = "POST">
     	
     	Borrower's personal number: <input type = "text" name = "borrowsnum" maxlength = "10" onkeypress="isInputNumber(event)"/><br />
        Book's Serial number: <input type = "text" name = "booksnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Borrow book" />
         <div style="color: #FF0000;">${errorMessage}</div>
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
     	Borrower's personal number: <input type = "text" name = "borrowsnum" maxlength = "10" onkeypress="isInputNumber(event)"/><br />
        CD's Serial number: <input type = "text" name = "cdsnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Borrow CD" />
         <div style="color: #FF0000;">${errorMessage2}</div>
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
     	Borrower's personal number: <input type = "text" name = "borrowsnum" maxlength = "10" onkeypress="isInputNumber(event)"/><br />
        DVD's Serial number: <input type = "text" name = "dvdsnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Borrow DVD" />
         <div style="color: #FF0000;">${errorMessage3}</div>
     </form>
     
     <h1>Add Users</h1>
     
     <form action = "servlet" method = "POST">
     	First name: <input type = "text" name = "fname" /><br />
        Last name: <input type = "text" name = "ename" /><br />
        Personal number: <input type = "text" name = "pnum" maxlength = "10" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add user" />
     </form>
     
     <h1>Borrowed Media</h1>
     
     
     <form action = "servlet" method = "POST">
     	
         <input type = "submit" name= "submit" value = "Show Borrowed Media" />
          
          
     </form>
     
     
     
     	<script>
            
            function isInputNumber(evt){
                
                var ch = String.fromCharCode(evt.which);
                
                if(!(/[0-9]/.test(ch))){
                    evt.preventDefault();
                }
                
            }
            
            
            
       </script>
     
</body>
</html>
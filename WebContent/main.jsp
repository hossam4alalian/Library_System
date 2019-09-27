<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bibliotek</title>
</head>
<body>

	<h1>Users</h1>
	<form action = "servlet" method = "POST">
         
         <input type = "submit" name= "submit" value = "Show users" /><br><br>
     </form>
	

	<h1>Media in the Library</h1>
	
	<form action = "servlet" method = "POST">
         
         <input type = "submit" name= "submit" value = "Show books" /><br><br>
         <input type = "submit" name= "submit" value = "Show CDs" /><br><br>
         <input type = "submit" name= "submit" value = "Show DVDs" /><br><br>
     </form>
	
	<h1>Add Media</h1>
	<form action = "servlet" method = "POST">
         Book's name: <input type = "text" name = "bname">
         <br />
         Book's serial number: <input type = "text" name = "bsnum" maxlength = "11"  onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add book" />
         <div style="color: #FF0000;">${bookerror}</div>
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         CD's name: <input type = "text" name = "cname">
         <br />
         CD's serial number: <input type = "text" name = "csnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add CD" />
         <div style="color: #FF0000;">${cderror}</div>
     </form>
     <br /><br />
     <form action = "servlet" method = "POST">
         DVD's name: <input type = "text" name = "dname">
         <br />
         DVD's serial number: <input type = "text" name = "dsnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Add DVD" />
         <div style="color: #FF0000;">${dvderror}</div>
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
         <div style="color: #FF0000;">${usererror}</div>
     </form>
     
     <h1>Borrowed Media</h1>
     
     
     <form action = "servlet" method = "POST">
     	
         <input type = "submit" name= "submit" value = "Show Borrowed Media" />
          
          
     </form>
     
     <h1>Return Media</h1>
     
     <form action = "servlet" method = "POST">
     	
        Book's Serial Number: <input type = "text" name = "bokSnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Return book" />
         <div style="color: #FF0000;">${returnbookerror}</div>
         <br><br>
         CD's Serial Number: <input type = "text" name = "cdSnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Return CD" />
         <div style="color: #FF0000;">${returncderror}</div>
         <br><br>
         DVD's Serial Number: <input type = "text" name = "dvdSnum" maxlength = "11" onkeypress="isInputNumber(event)"/><br />
         <input type = "submit" name= "submit" value = "Return DVD" />
         <div style="color: #FF0000;">${returndvderror}</div>
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
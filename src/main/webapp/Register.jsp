<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
function validate()
{
     var firstName = document.form.firstName.value;
     var lastName = document.form.lastName.value;
     var city = document.form.city.value;
     var state = document.form.state.value;
     var country = document.form.country.value;
     var zip =document.form.zip.value;
     var phone = document.form.phone.value;
     var password = document.form.password.value;

     if (firstName==null || firstName=="")
     {
     alert(" FirstName can't be blank");
     return false;
     }
     else if (lastName==null || lastName=="")
     {
     alert("LastName can't be blank");
     return false;
     }
     else if(password.length<6)
     {
     alert("Password must be at least 6 characters long.");
     return false;
     }
 }
</script>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: grey;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-grey;
  border: none;
  background: #9cb1a1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #9cb1a1;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #9cb1a1;
  margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
  background-color: #9cb1a1;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #9cb1a1;
  text-align: center;
}

</style>
</head>
<body>
<form name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
        <table align="center">
         <tr>
         <td>First Name</td>
         <td><input type="text" name="firstName" placeholder="Enter FirstName"/></td>
         </tr>
         <tr>
         <td>Last Name</td>
         <td><input type="text" name="lastName" placeholder="Enter LastName"/></td>
         </tr>
         <tr>
         <td>City</td>
         <td><input type="text" name="city" placeholder="Enter City"/></td>
         </tr>
         <tr>
         <td>State</td>
         <td><input type="text" name="state" placeholder="Enter State"/></td>
         </tr>
         <tr>
         <td>Country</td>
         <td><input type="text" name="country" placeholder="Enter Country" /></td>
         </tr>
         <tr>
         <td>Zip</td>
         <td><input type="text" name="zip" placeholder="Enter Zip"/></td>
         </tr>
         <tr>
         <td>Phone</td>
         <td><input type="text" name="phone" placeholder="Enter Phone Number"/></td>
         </tr>
         <tr>
         <td>Email</td>
         <td><input type="text" name="email" placeholder="Enter Email Address" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password"  placeholder="Enter Password must be at least 6 characters long"/></td>
         </tr>
         <td><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Register"></input>
         <p>Already have an account? <a href="Login.jsp">Login</a>.</p>
         </tr>
        </table>
    </form>
</body>
</html>
</body>
</html>
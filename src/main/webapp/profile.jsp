<%@page import="com.Connection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>


<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link
  	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
  	rel="stylesheet"
  	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
  	crossorigin="anonymous">
  </head>
  <title></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">PROFILE PAGE</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      <div>
             <li class="nav-item">
                <a class="btn btn-primary" href="Delete.jsp" role="button">Delete</a>
               </li>
               </div>
              <br>
              <div>
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                Update
              </button>
              </div>

          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>

      </ul>
       <li class="nav-item">
       <a class="btn btn-primary" href="LogoutServlet" role="button">Logout</a>
       </li>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit" <a href="Search.jsp"></a>Search</button>
      </form>
    </div>
  </div>
</nav>
<%
String email=(String) session.getAttribute("email");
String password = (String) session.getAttribute("password");
String firstName="";
String lastName="";
String city="";
String zip="";
String state="";
String country="";
String phone="";
String partyId="";

     try {
                     Connection con = DBConnection.getConnection();
                      String qr = "select userLoginId, password from UserLogin";
                      Statement st = con.createStatement();
                      ResultSet rs = st.executeQuery(qr);

                while(rs.next()){
                        if(rs.getString("userLoginId").equalsIgnoreCase(email)
                        && rs.getString("password").equals(password))
                        {

                             String qr1 = "select * from Party natural join UserLogin where userLoginId = ?";
                             PreparedStatement ps = con.prepareStatement(qr1);
                             ps.setString(1, email);
                             ResultSet rs1 = ps.executeQuery();
                              if(rs1.next())
                              {
                                   firstName = rs1.getString("firstName");
                                   lastName = rs1.getString("lastName");
                                   city = rs1.getString("city");
                                   zip = rs1.getString("zip");
                                   state = rs1.getString("state");
                                   country = rs1.getString("country");
                                   phone = rs1.getString("phone");
                                   partyId = rs1.getString("partyId");
                              }
                               request.getSession();
                               session.setAttribute("email", email);
                               session.setAttribute("password", password);
                               session.setAttribute("firstName", firstName);
                               session.setAttribute("lastName", lastName);
                               session.setAttribute("city", city);
                               session.setAttribute("zip", zip);
                               session.setAttribute("state", state);
                               session.setAttribute("country", country);
                               session.setAttribute("phone", phone);
                               session.setAttribute("partyId", partyId);
                               System.out.println(firstName);
                            }
                              }
                         } catch (ClassNotFoundException | SQLException e) {
                                           e.printStackTrace();
                                       }

%>
<table class="table">
  <thead>
    <tr>
      <th scope="col">partyId</th>
      <th scope="col">FirstName</th>
      <th scope="col">LastName</th>
      <th scope="col">City</th>
      <th scope="col">Zip</th>
      <th scope="col">State</th>
      <th scope="col">Country</th>
      <th scope="col">phone</th>
      <th scope="col">Email</th>



    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"><%=partyId%></th>
      <td><%=firstName%></td>
      <td><%=lastName%></td>
      <td><%=city%></td>
      <td><%=zip%></td>
      <td><%=state%></td>
      <td><%=country%></td>
      <td><%=phone%></td>
      <td><%=email%></td>

    </tr>
  </tbody>
</table>
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="UpdateProfile" method="get">
          <div class="container">
            <h1>User Update Profile</h1>
            <p>Please fill in this form to  an Update.</p>
            <hr>
             <tr>
             <label for="firstName"><b>First Name</b></label>
            <input type="text" placeholder="Enter your first name" name="firstName"  id="firstName" value=<%=firstName%> required><br>
             </tr>
             <tr>
             <label for="LastName"><b>Last name</b></label>
            <input type="text" placeholder="Enter your last name" name="lastName" id="LastName" value=<%=lastName%> required><br>
             </tr>
             <tr>
             <label for="City"><b>City</b></label>
            <input type="text" placeholder="Enter city" name="city" id="City" value=<%=city%> required ><br>
            </tr>

             <label for="State"><b>State</b></label>
            <input type="text" placeholder="Enter State" name="state" id="State" value=<%=state%> required><br>

             <label for="Country"><b>Country</b></label>
            <input type="text" placeholder="Enter country" name="country" id="Country" value=<%=country%> required><br>

            <label for="Zip"><b>Zip</b></label>
            <input type="text" placeholder="Enter Zip" name="zip" id="Zip" value=<%=zip%> required><br>

             <label for="Phone"><b>Phone</b></label>
            <input type="text" placeholder="Enter Phone" name="phone" id="Phone" value=<%=phone%> required><br><br>

            <a class="btn btn-primary"> <button type="submit" role="button">Update </button></a>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
      </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
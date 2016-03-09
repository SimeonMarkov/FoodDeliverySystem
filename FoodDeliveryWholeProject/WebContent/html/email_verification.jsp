<%@page import="model.dao.DBUserDAO"%>
<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="model.dao.DBUserDAO" %>
<%
   String result = null;
   // Recipient's email ID needs to be mentioned.
   final String from = "simeon.markov1994@gmail.com";

   // Sender's email ID needs to be mentioned
   String to = ((User)session.getAttribute("failLog")).getEmail();

        final String password = "BadjanakA;34889413"; 
        final String newUserPassword = UUID.randomUUID().toString().substring(0,UUID.randomUUID().toString().indexOf("-"));
        DBUserDAO.getInstance().updateUser((User)session.getAttribute("failLog"), newUserPassword, to);

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
		
        Session ses = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(ses);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Testing Subject");
            message.setText("Здравейте," + ((User) session.getAttribute("failLog")).getUsername() + ".\nНовата Ви парола е " + newUserPassword);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");
            result = "An email with a new password has been sent to " + ((User)session.getAttribute("failLog")).getEmail();

        } catch (MessagingException e) {
            e.printStackTrace();
            out.println("Oops,something went wrong!");
        }
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8" />
<link href="../CSS/mainBody.css" rel="stylesheet" type="text/css" />
<link href="../CSS/mainButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menuButtons.css" rel="stylesheet" type="text/css" />
<link href="../CSS/regFields.css" rel="stylesheet" type="text/css"/>
<meta charset="utf-8">
<title>FoodDelivery Profile</title>
<style type="text/css">


</style>
</head>

<body>

<div id="Container">
  <div id="Header"></div>
	
   <div id="Buttons">
    <ul style="float:right">
        <li><a href="home.html">Home</a></li>
		<li><a href="search2.html">Search</a></li>
		<li><a href="faq.html">Guide</a></li>		
		<li><a href="sign_up.html">Register</a></li>
		<li><a href="login.html">Login</a></li>
		</ul>            
    </div>           
     
	<div id="Menu" style="min-height:950px"></div>
	<div id="MainBody">
		<p><% out.println(result); %></p>
	</div>
     <div id="Footer"></div>

</div>
   
</body>
</html>
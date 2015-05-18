<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <title>CHAT</title>
</head>
<body onload="getContacts();">
    <script>
        setInterval(getContacts,10000);
    </script>
    <%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
    for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
    }
    }
    if(userName == null) response.sendRedirect("index.html");
    %>
    <h3>Hi <%=userName %>, Welcome to chat!</h3>
    
    <div class="container chat-wrapper">
                    <div id="chatik" style="height:200px; overflow:auto;">
                        <table id="response" class="table table-bordered"></table>
                    </div>
        <form id="do-chat">
                    <fieldset>
                        <legend>Enter your message..</legend>
			<div class="controls">
                            <div class="control-group">
                                <div class="controls">
                                    <input id="mesChat" type="text" class="input-block-level" placeholder="Your message..." name="mesText" style="height:60px"/>
                                </div>
                            </div> 
                        </div>
                        <div>
                            <button onclick="addBtn();" class="btn btn-primary">Отправить</button>
                        </div>
                    </fieldset>
	</form>
</div>
    
    <form action="LogoutServlet" method="post">
        <input class="btn-primary" type="submit" value="Logout" >
    </form>
</body>
</html>

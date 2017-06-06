<%@ page pageEncoding="UTF-8" %>
<!doctype html>
<%
  ServletContext sc = request.getServletContext();
%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!--                                                               -->
    <!-- Consider inlining CSS to reduce the number of requested files -->
    <!--                                                               -->
    <link type="text/css" rel="stylesheet" href="BD.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto" rel="stylesheet">
    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Project UI fro BD</title>

    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="BD/BD.nocache.js"></script>
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>
  <div id="statusList" style="display: none"><%= sc.getAttribute("statusList").toString() %></div>
  <div id="objectType" style="display: none"><%= sc.getAttribute("objectType").toString() %></div>
  <script type="text/javascript">
    window.objectType = <%= sc.getAttribute("objectType").toString() %> ;
    window.statusList = <%= sc.getAttribute("statusList").toString() %> ;
  </script>
    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>

    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <section id="global">
      <header>
        <nav id="menu">
          <div class="buttons" id="menu-buttons">
          </div>
          <ul class="breadscrumb">
          </ul>
        </nav>
      </header>
      <main>
        <section id="main">

        </section>
      </main>
      <footer></footer>
    </section>

    <!--table align="center">
      <tr>
        <td colspan="2" style="font-weight:bold;"></td>
      </tr>
      <tr>
        <td id="nameFieldContainer"></td>
        <td id="sendButtonContainer"></td>
      </tr>
      <tr>
        <td colspan="2" style="color:red;" id="errorLabelContainer"></td>
      </tr>
    </table-->
  </body>
</html>
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
    <style>
      #preloader {
        width: 100vw;
        height: 100vh;
        display: flex;
        left: 0;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        position: fixed;
        z-index: 2000001;
        background-color: #0074D9;
        pointer-events: none;
        top: 0;
      }
      #preloader .text
      {
        color: white;
        text-transform: uppercase;
        font-size: 25px;
        margin-bottom: 25px;
      }

      #preloader > #circle
      {
        width: 1px;
        height: 1px;
        border-radius: 50%;
        display: block;
        position: relative;
        background-color: white;
        border: 99px solid #00e6ff;
        border-right-color: #0074D9;
        border-left-color: #0074D9;

        animation: -1s preloaderRotation 2s infinite linear;
      }

      #preloader.none
      {
        display: none;
      }
      #preloader .downlayer
      {
        width: 100%;
        height: 100vh;
        position: absolute;
        display: flex;
        align-items: center;
        flex-direction: column;
        align-items: center;
        pointer-events: auto;
        opacity: 0;
      }
      #preloader.opacity
      {
        opacity: 0.5;
        background-color: darkslategrey;
      }
      @keyframes preloaderRotation {
        from
        {
          transform: rotateZ(0deg);
        }
        to
        {
          transform: rotateZ(360deg);
        }
      }
    </style>
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
          <ul id="breadcrumbs">
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
  <div id="preloader">
    <div class="downlayer"></div>
    <div class="text">Loading...</div>
    <div id="circle"></div>
  </div>
  </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <% String passo = (String) request.getAttribute("passo"); %>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema de Apoio à Tomada de decisão</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilos.css" rel="stylesheet">
    </head>
    <body>
        <div class="well nonFooter">
            <div class="row">
                <h1 class="text-center">Sistema de Apoio à Tomada de Decisão</h1>
                <hr>
            </div>
            <div class="row">
                <div class="well col-sm-6">
                    <div class="container">
                        <% if (passo == null){%>
                            <%@include file="viewEscolherArquivo.jsp"%>
                        <%}%>
                    </div>
                </div>
                <div class="col-sm-6" style="padding: 0px 30px;">                    
                    <div class="row">                        
                        <%if (passo == null){%>
                        <%@include file="viewInfoEscolherArquivo.jsp"%>
                        <%}%>
                    </div>

                </div>
            </div>
            <div id="push"></div>
        </div>
        <footer class="stickyFooter">
            <div class="container">
                <div class="row center-block text-right">
                    Desenvolvido por Alisson Renan Svaigen, Lailla Milainny Siqueira Bine e Filipe Gomes Genu
                </div>
            </div>
        </footer>
    </body>
</html>
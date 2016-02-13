<%@page import="classes.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <% Integer passo = (Integer) request.getAttribute("passo");
        Relacao relacao = (Relacao) session.getAttribute("relacao");
        String arqEntrada = (String) session.getAttribute("arqEntrada");
    %>
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
            <%if (passo != null) {%>
            <div class="row">
                <p><strong>Informações</strong></p>
                <p><strong>Arquivo de entrada:</strong> <%=arqEntrada%>
                    <strong>Número de atributos:</strong> <%=relacao.getAtributos().size()%>
                    <strong>Número de tuplas:</strong> <%=relacao.getTuplas().size()%>
                </p>
            </div>
            <hr>
            <%}%>
            <div class="row">
                <div class="well col-sm-6">
                    <div class="container">
                        <% if (passo == null) {%>
                        <%@include file="viewEscolherArquivo.jsp"%>
                        <%} else {
                            switch (passo) {
                                case 1:
                        %>
                        <%@include file="viewEscolherArquivo.jsp"%>
                        <%break;
                            case 2:%>
                        <%@include file="viewNominarAtributos.jsp"%>
                        <%break;
                            case 3:%>
                        <%@include file="viewPrepararClassificacao.jsp"%>
                        <%break;
                            case 4:%>
                        <%@include file="viewFinalizado.jsp"%>
                        <%}
                            }%>
                    </div>
                </div>
                <div class="col-sm-6" style="padding: 0px 30px;">                    
                    <div class="row">                        
                        <% if (passo == null) {%>
                        <%@include file="viewInfoEscolherArquivo.jsp"%>
                        <%} else {
                            switch (passo) {
                                case 1:
                        %>
                        <%@include file="viewInfoEscolherArquivo.jsp"%>
                        <%break;
                            case 2:%>
                        <%@include file="viewInfoNominarAtributos.jsp"%>
                        <%break;
                            case 3:%>
                        <%@include file="viewInfoPrepararClassificacao.jsp"%>
                        <%break;
                            case 4:%>
                        <%@include file="viewInfoFinalizado.jsp"%>
                        <%}
                            }%>
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
        <script src="js/jquery-2.2.0.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/validaForm.js"></script>
    </body>
</html>
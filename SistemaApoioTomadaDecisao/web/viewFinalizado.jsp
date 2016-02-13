<div class="row">
    <div class="col-sm-5">
        Visualize e salve os arquivos gerados:
        <ul>
            <li><a href="relacao?p=download&caminho=<%=(String) request.getAttribute("caminhoTreinamento")%>">Arquivo de treinamento</a></li>
            <li><a href="relacao?p=download&caminho=<%=(String) request.getAttribute("caminhoClassificacao")%>">Arquivo de classificação</a></li>
                <%
                    if (request.getAttribute("caminhoArvore") != null) {%>
            <li><a href="relacao?p=download&caminho=<%=(String) request.getAttribute("caminhoArvore")%>">Arquivo do algoritmo de árvore de decisão</a></li>
                <%}
                    if (request.getAttribute("caminhoPerceptron") != null) {%>
            <li><a href="relacao?p=download&caminho=<%=(String) request.getAttribute("caminhoPerceptron")%>">Arquivo do algoritmo Perceptron multilayer</a></li>
                <%}%>
        </ul>
    </div>
</div>        
<div class="row"    >
    <div class="form-group">
        <a href="relacao?p=preparar" class="btn btn-danger">Voltar ao passo anterior</a>
        <a href="relacao?p=arquivo" class="btn btn-success">Finalizar e voltar ao início</a>

    </div>
</div>
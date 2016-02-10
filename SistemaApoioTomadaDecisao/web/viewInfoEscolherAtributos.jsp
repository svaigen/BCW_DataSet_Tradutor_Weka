<%@page import="classes.Tupla" %>
<%t = relacao.getTupla(0);%>
<h4>Terceiro passo: Classificar os n�veis de treinamento, classifica��o e utilidade dos atributos</h4>
<p>
    Nessa etapa voc� deve indicar qual a porcentagem da base de dados que ser� utilizada para treinamento e
    para classifica��o. � importante lembrar que ambas devem completar 100%, ou seja, caso seja informado
    70% para treinamento, os outros 30% ser�o para classifica��o.
</p>
<p>
    Quanto aos atributos, deve-se marcar qual a sua utilidade:
<ul>
    <li>Treino: Dados utilizados para o algoritmo de treinamento;</li>
    <li>Decis�o: Dado utilizado para a classifica��o ap�s o treinamento. Apenas um atributo
    pode estar marcado como de decis�o.</li>
    <li>Excluir: Excluir o atributo do processamento.</li>
</ul>
</p>

<hr>
<p>Progresso:</p>
<div class="progress backgroundWhite">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="3" style="width: 50%;">
    </div>
</div>
<hr>
<p>
    Veja um exemplo de uma tupla da sua rela��o:
</p>
<table class="table table-responsive">
    <tr>
        <th>Atributo</th>
        <th>Valor</th>
    </tr>
    <%        
        for(int i=0; i<t.getNumeroAtributos(); i++){%>
    <tr>
        <td><%=relacao.getAtributos().get(i).getRotulo()%></td>
        <td><%=t.showDado(i) %></td>
    </tr>  
    <%}
    %>
</table>
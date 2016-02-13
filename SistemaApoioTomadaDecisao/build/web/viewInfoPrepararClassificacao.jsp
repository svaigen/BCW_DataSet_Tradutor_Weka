<%@page import="classes.Tupla" %>
<%t = relacao.getTupla(0);%>
<h4>Terceiro passo: Preparação dos dados e dos algoritmos realizados</h4>
<p>
    Nessa etapa você deve indicar quais algoritmos deseja utilizar para a classificação da base de dados.
    Também deve informar qual a porcentagem da base de dados que será utilizada para treinamento e
    para classificação de cada algoritmo. É importante lembrar que ambas devem completar 100%, ou seja, caso seja informado
    70% para treinamento, os outros 30% serão para classificação.
</p>
<p>
    Quanto aos atributos, deve-se marcar qual a sua utilidade:
<ul>
    <li>Geral: Dados utilizados no geral para o algoritmo no treinamento e na classificação;</li>
    <li>Decisão: Dado utilizado para a classificação após o treinamento. Apenas um atributo
    pode estar marcado como de decisão.</li>
    <li>Excluir: Excluir o atributo do processamento.</li>
</ul>
</p>

<hr>
<p>Progresso:</p>
<div class="progress backgroundWhite">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="3" style="width: 75%;">
    </div>
</div>
<hr>
<p>
    Veja um exemplo de uma tupla da sua relação:
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
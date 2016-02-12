<%@page import="classes.Tupla" %>
<%t = relacao.getTupla(0);%>
<h4>Terceiro passo: Prepara��o dos dados e dos algoritmos realizados</h4>
<p>
    Nessa etapa voc� deve indicar quais algoritmos deseja utilizar para a classifica��o da base de dados.
    Tamb�m deve informar qual a porcentagem da base de dados que ser� utilizada para treinamento e
    para classifica��o de cada algoritmo. � importante lembrar que ambas devem completar 100%, ou seja, caso seja informado
    70% para treinamento, os outros 30% ser�o para classifica��o.
</p>
<p>
    Quanto aos atributos, deve-se marcar qual a sua utilidade:
<ul>
    <li>Geral: Dados utilizados no geral para o algoritmo no treinamento e na classifica��o;</li>
    <li>Decis�o: Dado utilizado para a classifica��o ap�s o treinamento. Apenas um atributo
    pode estar marcado como de decis�o.</li>
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
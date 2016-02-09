<%@page import="classes.Tupla" %>
<%t = relacao.getTupla(0);%>
<h4>Terceiro passo: Escolher os atributos a serem processados</h4>
<p>
    Como em toda análise, pode haver dados que não são necessários para o processamento.
    Portanto, nessa etapa, você deve manter selecionado apenas os atributos que julgar pertinentes
    para o processamento a ser realizado.
</p>

<hr>
<p>Progresso:</p>
<div class="progress backgroundWhite">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="3" style="width: 50%;">
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
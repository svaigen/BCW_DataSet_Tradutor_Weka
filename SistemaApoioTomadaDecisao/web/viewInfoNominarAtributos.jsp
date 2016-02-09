<%@page import="classes.Tupla" %>
<%Tupla t = relacao.getTupla(0);%>
<h4>Segundo passo: Nomine a relação e os atributos da sua base de dados</h4>
<p>
    Nessa etapa o seu arquivo de entrada já foi lido e aceito!
</p>
<p>
    Agora, é necessário que você dê um nome para a sua relação e para todos os 
    <%=t.getNumeroAtributos()%> atributos detectados.
</p>
<p>
    Nominando os atributos e a relação, você conseguirá realizar uma análise de dados de maneira
    mais prática.
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
            <td><%="Atributo "+(i+1)%></td>
            <td><%=t.showDado(i) %></td>
        </tr>  
        <%}
    %>
</table>
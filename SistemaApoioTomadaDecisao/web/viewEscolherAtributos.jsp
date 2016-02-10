<form action="relacao" method="post" onsubmit="return valida()">        
    <div class="form-group">
        <label for="porcTrein">Treinamento (porcentagem)</label>
        <div class="row">
            <div class="col-sm-5">                
                <input id="trein" type="number" min="0" max="100" required="required" 
                       name="porcTrein" value="70" onchange="alteraTreinamento()" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="porcClass">Classificação (porcentagem)</label>
        <div class="row">
            <div class="col-sm-5">
                <input id="class" type="number" min="0" max="100" required="required" 
                       name="porcClass" value="30" onchange="alteraClassificacao()" />
            </div>
        </div>
    </div>
    <div class="form-group">    
        <div class="row">
            <div class="col-sm-5">
                <table class="table table-responsive table-striped">
                    <tr>
                        <th>Atributo</th>
                        <th>Treino</th>
                        <th>Decisão</th>
                        <th>Excluir</th>
                    </tr>
                    <% for (int i = 0; i < relacao.getAtributos().size(); i++) {%>
                    <tr>
                        <td>
                            <%=relacao.getAtributos().get(i).getRotulo()%>
                        </td>
                        <td>
                            <input type="radio" name="<%=i%>" value="treino" checked="checked"/>
                        </td>
                        <td>
                            <input type="radio" name="<%=i%>" class="decisao" value="decisao"/>
                        </td>
                        <td>
                            <input type="radio" name="<%=i%>" value="excluir"/>
                        </td>
                    </tr>            

                    <%}
                    %>
                </table>
            </div>
        </div>
    </div>    
    <div class="form-group">
        <button type="submit" name="action" value="escolher"
                class="btn btn-primary">
            Enviar
        </button>
    </div>
</form>
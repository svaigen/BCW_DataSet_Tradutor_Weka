<form action="relacao" method="post">    
    <h4>Deixe selecionados apenas os atributos a serem utilizados no processamento. </h4>
    <div class="form-group">        
        <% for (int i = 0; i < relacao.getAtributos().size(); i++) {%>        
        <div class="row">
            <div class="col-sm-5 checkbox-inline">
                <label><input type="checkbox" value="" checked="checked"/><%=relacao.getAtributos().get(i).getRotulo()%></label>
            </div>            
        </div>
        <%}
        %>
    </div>
    <div class="form-group">
        <button type="submit" name="action" value="escolher"
                class="btn btn-primary">
            Enviar
        </button>
    </div>
</form>
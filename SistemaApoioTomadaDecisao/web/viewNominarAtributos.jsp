<form action="relacao" method="post">
    <div class="form-group">
        <label for="nome">Nome da relação</label>
        <div class="row">
            <div class="col-sm-5">
                <input type="text" required="required" class="form-control" name="relacao" size="chars" placeholder="<%=relacao.getRotulo()%>"/>
            </div>            
        </div>        
    </div>
    <h4>Foram detectados <%=relacao.getAtributos().size()%> atributos. Por favor, nomine-os a seguir: </h4>
    <div class="form-group">
        <% for (int i = 0; i < relacao.getAtributos().size(); i++) {%>
        <label for="<%="atributo" + (i + 1)%>">Atributo <%=i + 1%></label>
        <div class="row">
            <div class="col-sm-5">
                <input type="text" required="required" class="form-control" name="atributo<%=i%>" size="chars" placeholder="atributo<%=i + 1%>"/>
            </div>            
        </div>
        <br/>
        <%}
        %>
    </div>
    <div class="form-group">
        <button type="submit" name="action" value="nominar"
                class="btn btn-primary">
            Enviar
        </button>
    </div>
</form>
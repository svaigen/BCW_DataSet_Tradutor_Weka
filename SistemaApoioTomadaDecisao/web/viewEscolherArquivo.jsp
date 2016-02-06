<form action="arquivo" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="nome">Escolha o arquivo de entrada da base de dados: </label>
        <div class="row">
            <div class="col-sm-5">
                <input class="btn btn-xs" type="file" name="file-upload" size="chars"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" name="action" value="escolheArquivo"
                class="btn btn-primary">
            Enviar
        </button>
    </div>
</form>
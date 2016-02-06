package upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Classe com métodos que auxiliam o upload de arquivos
 * @author svaigen
 */

public class Upload {

    private String path;

    public Upload(String path) {
        this.path = path;

        if (!this.isDirectoryExists(path)) {
            createDirectory(path);
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * Testa se o paramÃªtro caminho (path) existe.
     */
    private boolean isDirectoryExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     *
     * Cria o diretorio baseado no paramÃªtro caminho (path).
     */
    private void createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void saveFile(String path, String fileName, FileItem fileItem) {
        
        File file = new File(path, fileName);
        try {
            fileItem.write(file);
        } catch (Exception ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna em um Map os campos do formulÃ¡rio exceto o campo da imagem.
     *
     * @param l
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public Map<String, String> getFormValues(List l) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        Iterator iter = l.iterator();

        while (iter.hasNext()) {

            FileItem item = (FileItem) iter.next();

            if (item.isFormField()) {
                map.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                if (item.getName().length() > 0) {
                    saveFile(getPath(), getNameFile(item), item);
                    map.put(item.getFieldName(), getNameFile(item));
                    item.delete();
                }
            }
        }
        return (map.size() > 0) ? map : null;
    }

    private String getNameFile(FileItem fileItem) {
        String name = fileItem.getName();        
        String arq[] = name.split("\\\\");
        for (String arq1 : arq) {
            name = arq1;
        }
        return name;
    }

    public List processRequest(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            return upload.parseRequest(req);
        } catch (FileUploadException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public String[] getAllImages() {
        File dir = new File(this.getPath());
        return dir.list();
    }
}
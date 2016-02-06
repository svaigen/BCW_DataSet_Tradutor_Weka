/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Relacao;
import classes.Utils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upload.Upload;

/**
 *
 * @author 'Alisson
 */
@WebServlet(name = "ControllerPagina", urlPatterns = {"/arquivo"})
public class ControllerArquivo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerPagina</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerPagina at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String diretorio = "/files";
        String realPath = getServletContext().getRealPath(diretorio);
        Upload upload = new Upload(realPath);
        List list = upload.processRequest(request);
        Map<String, String> map = upload.getFormValues(list);
        String action = map.get("action");
        String fileName = map.get("file-upload");
        switch (action) {
            case "escolheArquivo":
                System.out.println(realPath+File.separator+fileName);
                new File(realPath+File.separator+fileName);
                String textoArquivo = null;
                Relacao relacao;
                try {
                    textoArquivo = Utils.lerArquivo(realPath+File.separator+fileName);
                    //System.out.println(textoArquivo);
                } catch (IOException ex) {
                    System.err.println("Erro ao ler o arquivo!");
                }
                relacao = Utils.tabulaDados(textoArquivo);
                System.out.println(relacao.toString());
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

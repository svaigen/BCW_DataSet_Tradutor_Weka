/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Relacao;
import classes.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 'Alisson
 */
@WebServlet(name = "ControllerRelacao", urlPatterns = {"/relacao"})
public class ControllerRelacao extends HttpServlet {

    RequestDispatcher rd;
    HttpSession session;

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
            out.println("<title>Servlet ControllerRelacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerRelacao at " + request.getContextPath() + "</h1>");
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
        session = request.getSession();
        Relacao relacao = (Relacao) session.getAttribute("relacao");
        String action = request.getParameter("action");
        switch(action){
            case "nominar":
                relacao.setRotulo(request.getParameter("relacao"));
                for(int i=0; i<relacao.getAtributos().size(); i++){
                    relacao.updateAtributo(i, request.getParameter("atributo"+i));
                }
                session.setAttribute("relacao", relacao);
                request.setAttribute("passo", 3);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
            case "escolher":
                Relacao relTreinamento = new Relacao(relacao.getRotulo());
                Relacao relDecisao = new Relacao(relacao.getRotulo());
                int atributos[] = new int[relacao.getAtributos().size()];
                for (int i = 0; i < atributos.length; i++) {
                    switch(request.getParameter(""+i)){ //i é referente ao nome do radiogroup de cada atributo
                        case "treino":
                            atributos[i] = Utils.ATRIBUTO_TREINO;
                            break;
                        case "decisao":
                            atributos[i] = Utils.ATRIBUTO_DECISAO;
                            break;
                        case "excluir":
                            atributos[i] = Utils.ATRIBUTO_REMOVIDO;
                            break;
                    }
                }
                Utils.geraRelacoesTreinamentoClassificacao(relacao,relTreinamento,relDecisao,atributos);               
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

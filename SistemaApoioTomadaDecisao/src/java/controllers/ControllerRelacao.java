/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Relacao;
import classes.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        session = request.getSession();
        String p = request.getParameter("p");
        switch(p){
            case "download":
                 String fileName = request.getParameter("caminho");

                //String dir = "/data/";
                //String path = getServletContext().getRealPath(dir);

                //File file = new File(path + "/" + fileName);
                File file = new File(fileName);
                response.setContentType("text/txt");
                response.addHeader("Content-Disposition", "attachment; filename=" + 
                        (!(fileName.contains("\\"))?
                        fileName.substring(fileName.lastIndexOf("/")):
                        fileName.substring(fileName.lastIndexOf("\\")))
                        );
                response.setContentLength((int) file.length());

                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
                break;
                
            case "arquivo":                
                session.removeAttribute("relacao");
                request.setAttribute("passo", null);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
                
            case "nominar":
                request.setAttribute("passo", 2);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
            case "preparar":
                request.setAttribute("passo", 3);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
        }
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
        switch (action) {
            case "nominar":
                relacao.setRotulo(request.getParameter("relacao"));
                for (int i = 0; i < relacao.getAtributos().size(); i++) {
                    relacao.updateAtributo(i, request.getParameter("atributo" + i));
                }
                session.setAttribute("relacao", relacao);
                request.setAttribute("passo", 3);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
            case "escolher":
                Relacao relTreinamento = new Relacao(relacao.getRotulo());
                Relacao relDecisao = new Relacao(relacao.getRotulo());
                int porcentagemTreinamento = Integer.parseInt(request.getParameter("porcTrein"));
                int porcentagemClassificacao = Integer.parseInt(request.getParameter("porcClass"));
                boolean algArvDec = (request.getParameter("arvDec") != null);
                boolean algPerceptron = (request.getParameter("perceptron") != null);
                int atributos[] = new int[relacao.getAtributos().size()];
                classificaAtributos(atributos, request);

                Utils.geraRelacoesTreinamentoClassificacao(relacao, relTreinamento, relDecisao, atributos, porcentagemTreinamento, porcentagemClassificacao);

                String diretorio = "/files";
                String realPath = getServletContext().getRealPath(diretorio);
                String caminhoArquivoTreinamento = realPath + File.separator + "treinamento_" + (String) session.getAttribute("arqEntrada") + ".arff";
                String caminhoArquivoClassificacao = realPath + File.separator + "classificacao_" + (String) session.getAttribute("arqEntrada") + ".arff";
                Utils.geraArff(atributos, relTreinamento, caminhoArquivoTreinamento);
                Utils.geraArff(atributos, relDecisao, caminhoArquivoClassificacao);
                if (algArvDec) {
                    String caminhoArvoreDecisao = realPath + File.separator + "arvoreDecisao_" + (String) session.getAttribute("arqEntrada");
                    try {
                        Utils.ArvoreDecisao(caminhoArquivoTreinamento, caminhoArquivoClassificacao, caminhoArvoreDecisao);
                    } catch (Exception ex) {
                        System.out.println("Falha ao realizar o algoritmo J48.");
                    }
                    request.setAttribute("caminhoArvore", caminhoArvoreDecisao);
                }
                if(algPerceptron){
                    String caminhoPerceptron = realPath + File.separator + "perceptron_" + (String) session.getAttribute("arqEntrada");
                    try {
                        Utils.Perceptron(caminhoArquivoTreinamento, caminhoArquivoClassificacao, caminhoPerceptron);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    request.setAttribute("caminhoPerceptron", caminhoPerceptron);
                }
                request.setAttribute("caminhoTreinamento", caminhoArquivoTreinamento);
                request.setAttribute("caminhoClassificacao", caminhoArquivoClassificacao);
                request.setAttribute("passo", 4);
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
        }
    }

    public void classificaAtributos(int[] atributos, HttpServletRequest request) {
        for (int i = 0; i < atributos.length; i++) {
            switch (request.getParameter("" + i)) { //i é referente ao nome do radiogroup de cada atributo
                case "treino":
                    atributos[i] = Utils.ATRIBUTO_GERAL;
                    break;
                case "decisao":
                    atributos[i] = Utils.ATRIBUTO_DECISAO;
                    break;
                case "excluir":
                    atributos[i] = Utils.ATRIBUTO_REMOVIDO;
                    break;
            }
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

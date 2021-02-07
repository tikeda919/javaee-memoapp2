package jp.example.www;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.example.www.dao.MemoappDao;
import jp.example.www.dao.MemoappDaoImpl;

/**
 * Servlet implementation class MainServlet
 */
public class MemoAppMain extends HttpServlet {
    private static final long serialVersionUID = 1L;
    MemoappDao dao = new MemoappDaoImpl();


    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoAppMain() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // レコード取り出し２
        request.setAttribute("memo_list", dao.getMemos());

        String view = "/WEB-INF/jsp/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        MemoBean memo = new MemoBean();
        memo.setTitle(request.getParameter("title"));
        memo.setMemo(request.getParameter("memo"));
        System.out.println("post:title: " + memo.getTitle());
        System.out.println("post:memo: " + memo.getMemo());

        dao.save(memo);

        response.sendRedirect(".");
    }

}

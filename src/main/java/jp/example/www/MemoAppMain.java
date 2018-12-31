package jp.example.www;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
public class MemoAppMain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String url = "jdbc:mysql://localhost:3306/memoapp_db";
    String user = "memoapp";
    String pass = "memoapp";

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
        // レコード取り出し
        Connection con = null;
        Statement smt = null;
        ArrayList<HashMap<String, String>> record_list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            smt = con.createStatement();

            String select_memo = "select title, memo, modified_date from memo_data;";

            ResultSet result = smt.executeQuery(select_memo);
            while (result.next()) {
                HashMap<String, String> record = new HashMap<>();
                System.out.println("title: " + result.getString("title"));
                System.out.println("memo: " + result.getString("memo"));
                System.out.println("modify: " + result.getString("modified_date"));
                record.put("title", result.getString("title"));
                record.put("memo", result.getString("memo"));
                record.put("modified_date", result.getString("modified_date"));
                record_list.add(record);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("record_list", record_list);

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
        System.out.println("title maven: " + request.getParameter("title"));
        System.out.println("memo: " + request.getParameter("memo"));

        // -- ここにDBへ保存処理 --
        Connection con = null;
        Statement smt = null;

        try {
            //System.out.println(Driver.class.getName());
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            smt = con.createStatement();
            System.out.println("smt: " + smt);

            String create_table = "create table if not exists memo_data (" +
                    "memo_id INT(11) auto_increment not null comment 'ID'," +
                    "category INT(11) comment 'カテゴリ'," +
                    "title VARCHAR(64) comment 'タイトル'," +
                    "memo TEXT comment 'メモ'," +
                    "create_date DATETIME comment '作成日'," +
                    "modified_date DATETIME comment '更新日'," +
                    "primary key (memo_id)" + ")";
            // create table
            smt.executeUpdate(create_table);

            String form_title = request.getParameter("title");
            String form_memo = request.getParameter("memo");
            System.out.println("title: " + form_title);
            System.out.println("text: " + form_memo);
            String insert_memo = "insert into memo_data (" +
                    "category, title, memo, create_date, modified_date" +
                    ") values (" +
                    "0," +
                    "'" + form_title + "'," +
                    "'" + form_memo + "'," +
                    "cast(now() as datetime)," +
                    "cast(now() as datetime) " +
                    ");";
            System.out.println("sql: " + insert_memo);
            smt.executeUpdate(insert_memo);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // -- ここまでDB処理 --

        response.sendRedirect(".");
    }

}

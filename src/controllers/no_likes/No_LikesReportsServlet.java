package controllers.no_likes;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class No_LikesServlet
 */
@WebServlet("/reports/no_likes")
public class No_LikesReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public No_LikesReportsServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  --- 前準備 ---
        EntityManager em = DBUtil.createEntityManager();                                                // DBUtilからエンティティを作成
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));     // 日報の番号を元に、Reportオブジェクト作成

        //  --- いいね数のカウントアップ  ---
        // いいね数の変数作成し、いいね数をカウントアップさせる
        int no_likes = r.getNo_likes() + 1;      // rオブジェクトからno_likesをgetで値を取得し、+1と合算。その後、変数に合算値を代入
        r.setNo_likes(no_likes);                       // setで合算値の値を設定

        //  --- DBに保存  ---
        em.getTransaction().begin();              // トランザクション処理の開始
        em.getTransaction().commit();           // データの新規登録を確定(コミット)
        em.close();                                        // トランザクション処理の終了

        //  --- 後処理  ---
        // フラッシュメッセージの表示
       request.getSession().setAttribute("flush", "いいねしました。");
       // ページ遷移
       response.sendRedirect(request.getContextPath() + "/reports/index");      //日報 一覧(目次)に戻る

    }
}

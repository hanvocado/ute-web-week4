package webhan.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webhan.models.User;
import webhan.services.impl.UserService;
import webhan.utils.Constant;

import java.io.IOException;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaitingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(Constant.SESSION_USERNAME);
		if (session != null && userId != null) {
			User u = new UserService().findById(userId);
			request.setAttribute("email", u.getEmail());
			if (u.getRoleId() == 1) {
				response.sendRedirect(request.getContextPath() + "/admin/home");
			} else if (u.getRoleId() == 2) {
				response.sendRedirect(request.getContextPath() + "/manager/home");
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

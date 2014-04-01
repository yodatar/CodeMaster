package sk.AjaxRequestServlet;

import sk.BusinessLogic.DatabaseRequestHandlers;
import sk.BusinessLogic.entities.UsersEntity;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pipo
 * Date: 7.12.2013
 * Time: 20:16
 */


public class GetAllUsersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatabaseRequestHandlers databaseRequestHandlers = new DatabaseRequestHandlers();
		List<UsersEntity> list = databaseRequestHandlers.getAllUsers();
		PrintWriter out = response.getWriter();
		out.print(list);
	}
}
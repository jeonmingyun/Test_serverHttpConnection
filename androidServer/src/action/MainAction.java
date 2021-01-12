package action;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MainAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/main.jsp";
	}

}

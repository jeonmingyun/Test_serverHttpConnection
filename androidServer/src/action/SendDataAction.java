package action;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;

public class SendDataAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "철수/"+name);
		map.put("comment",  "hello world/"+comment);
		
		JSONObject jObject = new JSONObject(map);
		String jsonText = jObject.toJSONString();
		
		System.out.println("hello" + jsonText);
		
		return jsonText;
	}
	
}

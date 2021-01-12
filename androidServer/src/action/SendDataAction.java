package action;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class SendDataAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
	                (ServletInputStream) request.getInputStream(), "utf-8"));;
			StringBuffer strBuffer = new StringBuffer();
			String strLine = null;
			JSONObject jObj;
			String name, comment;

			while ((strLine = bufferReader.readLine()) != null) {
				strBuffer.append(strLine);
			}
			
			jObj = (JSONObject) new JSONParser().parse(strBuffer.toString());
			
			name = jObj.get("name").toString();
			comment = jObj.get("comment").toString();
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("name", "철수:" + name);
			map.put("comment", "hello world:" + comment);

			JSONObject jObject = new JSONObject(map);
			String jsonText = jObject.toJSONString();

			System.out.println("hello" + jsonText);

			return jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fail";
	}

}

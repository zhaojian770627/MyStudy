package studyweb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myweb")
public class MainController {
	@RequestMapping(value = "/getData", method = GET)
	public void getData(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
		JSONObject retObject = new JSONObject();
		retObject.put("name", "main");
		writeJsonToResponse(request, response, retObject.toString());
	}

	@RequestMapping(value = "/saveData", method = POST)
	public void saveData(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
		Map reqMap = request.getParameterMap();
		String[] aryNames = (String[]) reqMap.get("name");
		System.out.println(aryNames[0]);
	}

	private void writeJsonToResponse(HttpServletRequest request, HttpServletResponse response, String result)
			throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String callback = request.getParameter("callback");
		String jsonResult = result;
		if (callback == null || callback.equals(""))
			response.getWriter().write(jsonResult);
		else
			response.getWriter().write(callback + "(" + jsonResult + ")");
		response.flushBuffer();
	}

}

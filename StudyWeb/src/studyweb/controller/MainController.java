package studyweb.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
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
		JSONArray retAry = new JSONArray();

		JSONObject ret1 = new JSONObject();
		ret1.put("id", 1);
		ret1.put("name", "word");
		ret1.put("ext", ".doc");
		retAry.put(ret1);

		JSONObject ret2 = new JSONObject();
		ret2.put("id", 2);
		ret2.put("name", "word");
		ret2.put("ext", ".doc");
		retAry.put(ret2);

		retObject.put("asset", retAry);
		writeJsonToResponse(request, response, retObject.toString());
	}

	@RequestMapping(value = "/saveData", method = POST)
	public void saveData(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
		Map reqMap = request.getParameterMap();
		String[] aryNames = (String[]) reqMap.get("name");

		JSONObject retObject = new JSONObject();
		retObject.put("result", true);
		writeJsonToResponse(request, response, retObject.toString());
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

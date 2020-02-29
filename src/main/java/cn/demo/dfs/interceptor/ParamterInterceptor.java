package cn.demo.dfs.interceptor;

import cn.demo.dfs.params.BaseStaticParams;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


public class ParamterInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    JSONObject jsonObject = changRequestParamToJSON(request);
		request.setAttribute(BaseStaticParams.REQUST_PARAMTE_JSON, jsonObject);
		request.setAttribute(HandlerMapping.MATRIX_VARIABLES_ATTRIBUTE,jsonObject);
		return true;
	}

	private JSONObject changRequestParamToJSON(HttpServletRequest request) {
		JSONObject result = null;
		String contentType = request.getContentType();
		try {
			StringBuilder sb = new StringBuilder();
			//contentType 可以是  application/json 或者是application/json; charset=UTF-8
			if (StringUtils.contains(contentType,ContentType.APPLICATION_JSON.getMimeType())) {
				ServletInputStream input = request.getInputStream();
 				if (input != null) {
					//window 默认是gbk，这里强制用utf8
					List<String> lines = IOUtils.readLines(input,"utf-8");
					for (String tmpStr : lines) {
						sb.append(tmpStr);
					}

					result = JSONObject.parseObject(sb.toString());

				}
			}
			if (result == null) {
				result = new JSONObject();
			}

			JSONObject queryStrJSON = new JSONObject();
			Enumeration<String> names = request.getParameterNames();

			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String[] v = request.getParameterValues(name);
				if (v.length == 1) {
					queryStrJSON.put(name, v[0]);
				} else {
					queryStrJSON.put(name, Arrays.asList(v));
				}
			}
			if (StringUtils.isBlank(sb.toString())) {
				result = queryStrJSON;
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = new JSONObject();
		}
		String uri = request.getRequestURI();
		for(String key : result.keySet()){
			if(null!=result.getString(key)){
				result.put(key,result.getString(key).trim());
			}
		}

		String requestSTR = result.toJSONString();
		logger.info("请求地址:{},请求参数:{}", uri, requestSTR);

		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}
}

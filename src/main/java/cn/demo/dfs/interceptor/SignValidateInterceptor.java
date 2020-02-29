package cn.demo.dfs.interceptor;


import cn.demo.dfs.params.BaseStaticParams;
import cn.demo.dfs.utils.SignUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by majunjie
 */
public class SignValidateInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());

	private Pattern urlPattern;

	private boolean urlPaatternFlag = false;



	public SignValidateInterceptor() {
	}

	public SignValidateInterceptor(boolean patternFlag, String urlPattern) {
		logger.info("urlPattern:{},patternFlag:{}", urlPattern, patternFlag);
		if (StringUtils.isNotBlank(urlPattern)) {
			this.urlPattern = Pattern.compile(urlPattern);
		}
		this.urlPaatternFlag = patternFlag;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(StringUtils.isNotBlank(request.getHeader(BaseStaticParams.REQUST_FEGIN_HEADER))){
			return true;
		}
		String uri = request.getRequestURI();
		if ((urlPaatternFlag == true && urlPattern != null && urlPattern.matcher(uri).matches()) || urlPaatternFlag == false) {
			boolean result = validate(request, response);
			logger.debug("url:{},sign validate result:{}", uri, result);
			return result;
		}

		return true;
	}

	public boolean validate(HttpServletRequest request, HttpServletResponse response) {
//		boolean isMobile = false;
//				try {
//					isMobile = DeviceUtils.check(request,response);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

		JSONObject jsonObject = (JSONObject) request.getAttribute(BaseStaticParams.REQUST_PARAMTE_JSON);
		if (jsonObject == null) {
			jsonObject = new JSONObject();
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String[] v = request.getParameterValues(name);
				if (v.length == 1) {
					jsonObject.put(name, v[0]);
				} else {
					jsonObject.put(name, Arrays.asList(v));
				}
			}
		}


		Map<String, String> params = new HashMap<>();

		//需要手动排序一下
		JSONObject j2 = JSONObject.parseObject(jsonObject.toJSONString(), Feature.OrderedField);

		for (String key : j2.keySet()) {
			params.put(key, j2.getString(key));
		}
		try {
			boolean result = true;
			if (!params.containsKey("sign")) {
				logger.error("缺少sign 参数");
				result = false;
			} else {
				result = SignUtils.checkSig(params, BaseStaticParams.key);
			}
			if (!result) {
				logger.error("sign 校验不通过");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("校验error", e);
		}
		return false;
	}

}

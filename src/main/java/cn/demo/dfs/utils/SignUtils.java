package cn.demo.dfs.utils;

import cn.demo.dfs.params.BaseStaticParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 接口加解密
 *
 * @author majunjie
 */
public class SignUtils {

    public static Logger logger = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 请求url的所有参数拼接成字符串
     *
     * @param map
     * @return
     */
    public static String createLinkString(Map<String, String> map) {
        StringBuffer res = new StringBuffer();
        Set<String> swapSet = map.keySet();
        String[] p = new String[swapSet.size()];
        swapSet.toArray(p);
        Arrays.sort(p, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < p.length; i++) {
            String name = p[i];
            String value = map.get(name);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            String dilimiter = res.length() < 1 ? "" : "&";
            res.append(dilimiter + name + "=" + value);
        }
        logger.debug("加密字符String:{}", res.toString());
        return res.toString();
    }

    /**
     * 请求url所有参数名先排序后再参数name=value&生成sig
     *
     * @param map
     * @param key
     * @return
     */
    public static String getSig(Map<String, String> map, String key) {
        String s = DigestUtils.md5Hex( createLinkString(map)+key);
         logger.debug("加密sign:{}", s);
        return s;
    }

    /**
     * 验签
     *
     * @param map
     * @return
     */
    public static boolean checkSig(Map<String, String> map, String key) {
        if (map == null) {
            return false;
        }
        String sig = map.get("sign");

        if(map.containsKey("sign")){
            map.remove("sign"); // _sig除外
        }
//        if(map.containsKey("callback")){
//            map.remove("callback"); //_callback除外
//        }
        if (!sig.toLowerCase().equals(getSig(map, key))) {
            return false;
        }
        return true;
    }





    //    @SuppressWarnings("unchecked")
    	public static void main(String[] args) {
            HttpUtils httpUtils = HttpUtils.getInstance();
            Map jsonObject = new HashMap();//参数
            String sign = getSig(jsonObject, BaseStaticParams.key);
            jsonObject.put("sign",sign);
            String str = httpUtils.post("http://prefinancial.mumzone.cn/receipt/receiptApplication",JSONObject.parseObject(JSON.toJSONString(jsonObject)));
            System.out.println(str);
     	}

}

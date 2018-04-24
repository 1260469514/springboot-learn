package com.hackdog.springbootlearn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置信息接口
 */
public class Param {
    private static Logger logger = LoggerFactory.getLogger(Param.class);

    public static Map<String, String> paramMap = new ConcurrentHashMap<>();
    // 获取配置信息
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Param.class.getClassLoader().getResourceAsStream("param.properties"));// 获取properties文件
        } catch (IOException e) {
            logger.error("载入配置文件异常", e);
        }
    }

    // 获取配置参数值
    public static String getValueByFile(String key) {
        return (String) properties.get(key);
    }

/*	public static String getParamByDb(String key) {
		String value = null;
		if (paramMap.containsKey(key)) {
			value = paramMap.get(key);
			return value;
		} else {
			try {
				WsProfileService wsProfileService = SpringContextsUtil.getBean(WsProfileService.class);
				WsProfile wsProfile = wsProfileService.getBykeyCode(key);
				if (wsProfile != null) {
					paramMap.put(key, wsProfile.getKeyValue());
					return wsProfile.getKeyValue();
				}
			} catch (Exception e) {
				logger.error("无效加载参数" + key,e);
			}
		}
		logger.error("参数加载错误" + key);
		return null;
	}*/

    /**
     * 获取出初页码 方法名：pageIndex
     *
     * @return Integer
     * @author：Mryang
     * @createTime：2016年7月9日-上午10:50:42
     * @since 1.0.0
     */
    public static Integer pageIndex() {
        return Integer.parseInt(getValueByFile("pageIndex"));
    }

    /**
     * 获取初始页大小 方法名：pageSize
     *
     * @return Integer
     * @author：Mryang
     * @createTime：2016年7月9日-上午10:50:18
     * @since 1.0.0
     */
    public static Integer pageSize() {
        return Integer.parseInt(getValueByFile("pageSize"));
    }

}
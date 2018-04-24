package com.hackdog.springbootlearn.util;

import com.hackdog.springbootlearn.keygen.DES;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * APP接口返回数据封装
 *
 * @author hackdog
 * @version 1.0
 * @date 2016年6月29日
 */
public class AppUtil {

    /**
     * app接口返回DES加密后的json数据 （成功）
     *
     * @param mData 内层数据
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMap(Map<String, Object> mData) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", "处理成功");
        map.put("result_data", mData);
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据
     *
     * @param mData   内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMap(Map<String, Object> mData, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        try {
            map.put("result_data", mData);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的List格式JSON数据
     *
     * @param list    内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMap(List<Map<String, Object>> list, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> dataMap = list.get(i);
            JSONObject json = new JSONObject(dataMap);
            jsons.add(json);
        }
        try {
            map.put("result_data", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    /**
     * 返回正确的List格式JSON数据
     *
     * @param list    内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapError(List<Map<String, Object>> list, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "1");
        map.put("message", message);
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> dataMap = list.get(i);
            JSONObject json = new JSONObject(dataMap);
            jsons.add(json);
        }
        try {
            map.put("result_data", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【纯Map】
     *
     * @param mData 内层数据
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES(Map<String, Object> mData) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", "处理成功");
        JSONObject json = new JSONObject(mData);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                map.put("result_data", DES.encryptDES(json.toString()));
            } else {
                map.put("result_data", mData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【含Message】
     *
     * @param mData   内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES(Map<String, Object> mData, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        JSONObject json = new JSONObject(mData);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                map.put("result_data", DES.encryptDES(json.toString()));
            } else {
                map.put("result_data", mData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【含Message】
     *
     * @param jsonData 内层数据
     * @param message  提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES_Obj(String jsonData, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                map.put("result_data", DES.encryptDES(jsonData));
            } else {
                map.put("result_data", jsonData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的List格式JSON数据【含Message】
     *
     * @param list    内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES_map(List<Map<String, Object>> list, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (Map<String, Object> dataMap : list) {
            JSONObject json = new JSONObject(dataMap);
            jsons.add(json);
        }
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                String data = jsons.toString();
                map.put("result_data", DES.encryptDES(data));
            } else {
                map.put("result_data", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    public static <E> Map<String, Object> getMapDESList(List<E> list, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject json = new JSONObject(list.get(i));
            jsons.add(json);
        }
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                String data = jsons.toString();
                map.put("result_data", DES.encryptDES(data));
            } else {
                map.put("result_data", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    /**
     * 返回正确的List格式JSON数据【含Message】
     *
     * @param list    内层数据
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES_obj(List<?> list, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (Object object : list) {
            JSONObject json = new JSONObject(object);
            jsons.add(json);
        }
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                String data = jsons.toString();
                map.put("result_data", DES.encryptDES(data));
            } else {
                map.put("result_data", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【只含Message】
     *
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        map.put("result_data", new HashMap<>());
        return map;
    }

    /**
     * 返回正确的List格式JSON数据【只含Message】
     *
     * @param message
     * @return Map<String                               ,                                                               Object>
     * @author TaoNingBo
     */
    public static Map<String, Object> getListDES(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", message);
        map.put("result_data", new ArrayList<>());
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【自定义code,message,map】
     *
     * @param code    状态码
     * @param message 提示内容
     * @param mData   内层数据
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES(String code, String message, Map<String, Object> mData) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", code);
        map.put("message", message);
        JSONObject json = new JSONObject(mData);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                map.put("result_data", DES.encryptDES(json.toString()));
            } else {
                map.put("result_data", mData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据【自定义code,message】
     *
     * @param code    状态码
     * @param message 提示内容
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapDES(String code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", code);
        map.put("message", message);
        map.put("result_data", new HashMap<>());
        return map;
    }

    /**
     * 返回错误的Map格式JSON数据【只含Map】
     *
     * @param mData 内层数据
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapErrorDES(Map<String, Object> mData) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "1");
        JSONObject json = new JSONObject(mData);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                map.put("result_data", DES.encryptDES(json.toString()));
            } else {
                map.put("result_data", mData);
            }
        } catch (Exception e) {
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回错误的Map格式JSON数据【只含Message】
     *
     * @param message 失败提示
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapErrorDES(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "1");
        map.put("message", message);
        map.put("result_data", new HashMap<>());
        return map;
    }

    /**
     * 返回错误的List格式JSON数据【只含Message】
     *
     * @param message
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getListErrorDES(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "1");
        map.put("message", message);
        map.put("result_data", new ArrayList<>());
        return map;
    }

    /**
     * 返回错误的Map格式JSON数据【含返回数据Map,Message】
     *
     * @param mData   内层数据
     * @param message 失败提示
     * @return Map<String                               ,                                                               Object>
     */
    public static Map<String, Object> getMapErrorDES(Map<String, Object> mData, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "1");
        map.put("message", message);
        JSONObject json = new JSONObject(mData);
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                String data = json.toString();
                map.put("result_data", DES.encryptDES(data));
            } else {
                map.put("result_data", mData);
            }
        } catch (Exception e) {
            map.put("result_data", new HashMap<>());
        }
        return map;
    }

    /**
     * 返回正确的Map格式JSON数据
     *
     * @param list
     * @return Map<String                               ,                                                               Object>
     * @author TaoNingBo
     */
    public static Map<String, Object> getMapList(List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", "0");
        map.put("message", "success");
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (Object object : list) {
            JSONObject json = new JSONObject(object);
            jsons.add(json);
        }
        try {
            if ("true".equals(Param.getValueByFile("control_des"))) {
                String data = jsons.toString();
                map.put("result_data", DES.encryptDES(data));
            } else {
                map.put("result_data", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result_data", new ArrayList<>());
        }
        return map;
    }

    public static boolean isSucceed(Map<String, Object> paramMap) {
        boolean result = false;
        if (null != paramMap && paramMap.containsKey("result_code")) {
            result = StringUtils.equals("0", paramMap.get("result_code").toString());
        }
        return result;
    }
}

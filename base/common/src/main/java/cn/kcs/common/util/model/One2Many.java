package cn.kcs.common.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public class One2Many extends JSONObject {
    private Set<String> roleList;
    private Set<String> menuList;
    private Set<String> permissionList;
    private Set<Integer> permissionIds;
    private List<JSONObject> picList;
    private List<JSONObject> menus;
    private List<JSONObject> users;
    private List<JSONObject> permissions;
}

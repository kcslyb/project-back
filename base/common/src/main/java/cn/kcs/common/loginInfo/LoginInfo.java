package cn.kcs.common.loginInfo;

import cn.kcs.common.util.constants.Constants;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @description: login info
 * @author: kcs
 * @create: 2019-01-14 16:53
 **/
public class LoginInfo {

    public static String getUserId() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return jsonUser == null ? "" : jsonUser.getString("userId");
    }

    public static String getUserName() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return jsonUser == null ? "" : jsonUser.getString("userName");
    }

    public static String getUserRoleName() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return jsonUser == null ? "" : jsonUser.getString("roleName");
    }

    public static String getUserMenus() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return jsonUser == null ? "" : jsonUser.getString("menus");
    }

    public static String getUserPermission() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return jsonUser == null ? "" : jsonUser.getString("permissions");
    }
}

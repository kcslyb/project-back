package cn.kcs.note.entity;

import java.io.Serializable;

/**
 * (TPermissionMenu)实体类
 *
 * @author makejava
 * @since 2019-03-08 09:55:53
 */
public class TPermissionMenu implements Serializable {
    private static final long serialVersionUID = -45025290116484792L;

    private String permid;

    private String permtype;

    private String permvalue;

    private String permparent;

    private String permchild;

    private String permkey;

    private String permpath;


    public String getPermid() {
        return permid;
    }

    public void setPermid(String permid) {
        this.permid = permid;
    }

    public String getPermtype() {
        return permtype;
    }

    public void setPermtype(String permtype) {
        this.permtype = permtype;
    }

    public String getPermvalue() {
        return permvalue;
    }

    public void setPermvalue(String permvalue) {
        this.permvalue = permvalue;
    }

    public String getPermparent() {
        return permparent;
    }

    public void setPermparent(String permparent) {
        this.permparent = permparent;
    }

    public String getPermchild() {
        return permchild;
    }

    public void setPermchild(String permchild) {
        this.permchild = permchild;
    }

    public String getPermkey() {
        return permkey;
    }

    public void setPermkey(String permkey) {
        this.permkey = permkey;
    }

    public String getPermpath() {
        return permpath;
    }

    public void setPermpath(String permpath) {
        this.permpath = permpath;
    }

    @Override
    public String toString() {
        return "TPermissionMenu{" +
                "permid='" + permid + '\'' +
                ", permtype='" + permtype + '\'' +
                ", permvalue='" + permvalue + '\'' +
                ", permparent='" + permparent + '\'' +
                ", permchild='" + permchild + '\'' +
                ", permkey='" + permkey + '\'' +
                ", permpath='" + permpath + '\'' +
                '}';
    }
}
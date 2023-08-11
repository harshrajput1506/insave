package com.hn.insave.utils;

import android.util.Log;

import java.io.Serializable;

public class Cookies implements Serializable {
    private String csrftoken;
    private String ds_user_id;
    private String mcd;
    private String mid;
    private String sessionid;
    private String urlgen;

    private static class CookiesInstance {
        private static final Cookies INSTANCE = new Cookies();

        private CookiesInstance() {

        }
    }

    public static Cookies getInstance() {
        return CookiesInstance.INSTANCE;
    }

    public static void setCook(String str) {
        String[] split = str.split(";");
        Cookies instance = getInstance();
        for (String str2 : split) {
            Log.e("----cook----", str2);
            String str22 = str2.trim();
            if (str22.contains("mcd=")) {
                instance.setMcd(str22.substring(4));
            } else if (str22.contains("mid=")) {
                instance.setMid(str22.substring(4));
            } else if (str22.contains("csrftoken=")) {
                instance.setCsrftoken(str22.substring(10));
            } else if (str22.contains("ds_user_id=")) {
                instance.setDs_user_id(str22.substring(11));
            } else if (str22.contains("sessionid=")) {
                instance.setSessionid(str22.substring(10));
            } else if (str22.contains("urlgen=")) {
                instance.setUrlgen(str22.substring(7));
            }
        }
    }

    private Cookies() {

    }

    public String getCsrftoken() {
        return this.csrftoken;
    }

    public String getDs_user_id() {
        return this.ds_user_id;
    }

    public String getMcd() {
        return this.mcd;
    }

    public String getMid() {
        return this.mid;
    }


    public String getSessionid() {
        return this.sessionid;
    }

    public String getUrlgen() {
        return this.urlgen;
    }

    private void setCsrftoken(String str) {
        this.csrftoken = str;
    }

    private void setDs_user_id(String str) {
        this.ds_user_id = str;
    }

    private void setMcd(String str) {
        this.mcd = str;
    }

    private void setMid(String str) {
        this.mid = str;
    }

    private void setSessionid(String str) {
        this.sessionid = str;
    }

    private void setUrlgen(String str) {
        this.urlgen = str;
    }


}

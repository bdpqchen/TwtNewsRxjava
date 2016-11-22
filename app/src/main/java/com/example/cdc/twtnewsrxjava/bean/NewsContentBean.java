package com.example.cdc.twtnewsrxjava.bean;

import java.util.List;

/**
 * Created by cdc on 16-11-22.
 */

public class NewsContentBean {

    public int index;
    public String subject;
    public String content;
    public String newscome;
    public String gonggao;
    public String sheying;
    public String shengao;
    public int    visitcount;
    public List<Comments> Comments;

    public static class Comments{
        public int nid;
        public int cid;
        public String ccontent;
        public String cuser;
        public String ctime;

    }



}

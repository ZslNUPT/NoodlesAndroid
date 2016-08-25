package com.njupt.sniper.testretrofit.entity;

import java.io.Serializable;


public class StaticsEntity implements Serializable {
    public NearbyInfo nearby_info;
    public ResumeRank resume_rank;
    public DeliveryNum delivery_num;

    public class NearbyInfo {
        public String num;
    }

    public class ResumeRank {
        public int num;
        public String url;
        public String share_title;
        public String share_content;
    }

    public class DeliveryNum {
        public int num;
    }

}

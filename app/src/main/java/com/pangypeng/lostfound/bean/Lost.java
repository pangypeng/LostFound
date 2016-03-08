package com.pangypeng.lostfound.bean;

import cn.bmob.v3.BmobObject;

/**
 * 作者 pang
 * 时间 2016/3/7 0007 13:46
 * 文件 LostFound
 * 描述
 */
public class Lost extends BmobObject {

    private String title;
    private String description;
    private String phone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Lost{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

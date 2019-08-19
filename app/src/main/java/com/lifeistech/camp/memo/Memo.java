package com.lifeistech.camp.memo;

import io.realm.RealmObject;

public class Memo extends RealmObject {
    //title
    public String title;
    //日付
    public String updateDate;
//    content
    public String content;
    //wether_status
    public Integer wether_status_realm;
}

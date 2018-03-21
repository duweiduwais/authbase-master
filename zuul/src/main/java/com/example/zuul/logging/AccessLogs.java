package com.example.zuul.logging;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "DW_AccessLogs")
public class AccessLogs extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private String context;

    private String accessUser;

    public AccessLogs() {
    }

    public AccessLogs(String context, String accessUser) {
        this.createDate = new Date();
        this.context = context;
        this.accessUser = accessUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAccessUser() {
        return accessUser;
    }

    public void setAccessUser(String accessUser) {
        this.accessUser = accessUser;
    }
}



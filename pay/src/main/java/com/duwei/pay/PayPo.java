package com.duwei.pay;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PAY_PAY")
public class PayPo extends AbstractEntity {

    private String payName;

    private BigDecimal payMoney;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    public PayPo() {
        this("",BigDecimal.ZERO);
    }

    public PayPo(String payName, BigDecimal payMoney) {
        this.payName = payName;
        this.payMoney = payMoney;
        this.createDate = new Date();
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

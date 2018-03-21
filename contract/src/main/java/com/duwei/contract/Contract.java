package com.duwei.contract;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CR_CONTRACT")
public class Contract extends AbstractEntity{
    private String contractName;

    public Contract(String contractName) {
        this.contractName = contractName;
    }

    public Contract() {
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
}

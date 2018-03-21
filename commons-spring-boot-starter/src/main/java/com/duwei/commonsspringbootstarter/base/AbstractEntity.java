package com.duwei.commonsspringbootstarter.base;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable{

    private static final long serialVersionUID = -9074839163569747694L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String  desc;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    //	 @PrePersist
//	 public void prePersist() {
//		 AbstractEntity et = this;
//	  }
//
//	  @PreUpdate
//	  public void preUpdate() {
//		  AbstractEntity et = this;
//	  }
//
//	  @PreRemove
//	  public void preRemove() {
//		  AbstractEntity et = this;
//	  }
//
//	  @PostPersist
//	  public void postPersist() {
//		  AbstractEntity et = this;
//	  }
//
//	  @PostUpdate
//	  public void postUpdate() {
//		  AbstractEntity et = this;
//	  }
//
//	  @PostRemove
//	  public void postRemove() {
//		  AbstractEntity et = this;
//	  }
//	  @PostLoad
//	  public void postLoad() {
//		  AbstractEntity et = this;
//	  }

}

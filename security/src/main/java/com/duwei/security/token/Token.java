package com.duwei.security.token;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RBS_TOKEN")
@Data
@NoArgsConstructor
public class Token extends AbstractEntity {

   private String token;

   private Long createDate;

   private String userName;

   public Token(String token){
       this.token = token;
       this.createDate = System.currentTimeMillis();
   }

}

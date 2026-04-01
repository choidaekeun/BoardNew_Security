package com.study.domain.auth.vo;

import java.security.Timestamp;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoVO implements UserDetails{

   @NotBlank
   private String userId;
   private String userNm;
   private String userPwd;
   private String telno;
   private String hpNo;
   private String email;
   private int compId;
   private int deptId;
   private int workplaceId;
   private int positionId;
   private int roleId;
   private String confirmYn;
   private String userStat;
   private String indictYn;
   private String bank;
   private String bank_accountNum;
   private Timestamp hireDt;
   private Timestamp resignationDt;
   private String profileNm;
   private String profileLc;
   private String profileType;
   private int profileSize;
   private int policyId;
   private String regId;
   private Timestamp regDt;
   private String updId;
   private Timestamp updDt;
   private String roleNm;
   private String roleSecurity;
   private int roleLevel;
   private int upperRoleId;
   private String roleDesc;
   private String statusFlag;
   private String userEmpNmb;
   private String delYn;

   private List<UserInfoAuth> securityAuthList;

   /* DB에서 조회시 사용자 권한을 담을 List */
   private List<RoleVO> roleVOs;

   private String role;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO:roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getName()));
		}

		return authorities;
   }

   @Override
   public String getPassword() {
      return userPwd;
   }

   @Override
   public String getUsername() {
      return userId;
   }

   @Override
   public boolean isAccountNonLocked() {
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       return true;
   }

   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @Override
   public boolean isEnabled() {
       return true;
   }

}

package com.study.user.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
public class UserDTO {

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


}

package com.study.domain.auth;

import lombok.Data;

@Data
public class RoleHierarchyVO {
    private int roleId;
    private String roleSecurity;
    private int roleLevel;
    private int upperRoleId;
    private String upperRoleSecurity;
}

package com.study.domain.auth.vo;

import lombok.Data;

@Data
public class MenuVO {
    private String menuId;
    private String menuNm;
    private String upperMenuId;
    private String menuGubun;
    private String baseMenuYn;
    private int menuOrder;
    private String offerYn;
    private String menuDesc;
    private String menuLc;        // URL 경로
    private int roleId;           // JOIN 결과 (system_menu_role.role_id)
    private String roleNm;        // JOIN 결과 (role_info.role_nm)
    private String roleSecurity;  // JOIN 결과 (role_info.role_security)
}

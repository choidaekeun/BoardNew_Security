package com.study.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.study.domain.member.UserInfoVO;

public class CommonController {
	
	/**
	 * 
	 * @Author	: Admin
	 * @Date	: 2024. 10. 31.
	 * @Method	: getUserInfo
	 * @return
	 * @Description : 세션의 사용자정보
	 */
	public UserInfoVO getUserInfo() {
		UserInfoVO result = new UserInfoVO();
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		result = (UserInfoVO)obj;
		return result;
	}
	
	/**
	 * 
	 * @Author	: Admin
	 * @Date	: 2024. 10. 31.
	 * @Method	: getUserId
	 * @return
	 * @Description : 사용자 id
	 */
	public String getUserId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;
		return uVo.getUserId();
	}
	
	/**
	 * ghl
	 * @Author	: Admin
	 * @Date	: 2024. 10. 31.
	 * @Method	: getUserId
	 * @return
	 * @Description : 회사 id
	 */
	public int getCompId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;
		return uVo.getCompId();
	}
	
	/**
	 * 
	 * @Author	: Admin
	 * @Date	: 2024. 10. 31.
	 * @Method	: getDeptId
	 * @return
	 * @Description : 부서 id
	 */
	public int getDeptId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;
		return uVo.getDeptId();
	}
	
	
	/**
	 * 
	 * @Author	: Admin
	 * @Date	: 2024. 10. 31.
	 * @Method	: getWorkspaceId
	 * @return
	 * @Description : 근무지 id
	 */
	public int getWorkspaceId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;
		return uVo.getWorkplaceId();
	}
	
	
	/**
	 * 
	 * @Author	: Admin
	 * @Date	: 2025. 02. 14.
	 * @Method	: getUserEmpNmb
	 * @return
	 * @Description : 사원번호 id
	 */
	public String getUserEmpNmb() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfoVO uVo = (UserInfoVO)obj;
		return uVo.getUserEmpNmb();
	}
	

}

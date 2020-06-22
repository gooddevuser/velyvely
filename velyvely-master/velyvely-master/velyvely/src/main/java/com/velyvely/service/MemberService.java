package com.velyvely.service;

import java.util.List;

import com.velyvely.vo.Member;

public interface MemberService {

	Member selectMemberBymemberidAndpasswd(Member member);

	List<Member> selectMembers();

	void insertMember(Member member);

}

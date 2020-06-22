package com.velyvely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velyvely.mapper.MemberMapper;
import com.velyvely.vo.Member;
import com.velyvely.vo.MemberFile;
import com.velyvely.vo.ProductFile;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public Member selectMemberBymemberidAndpasswd(Member member) {
		Member member2 = memberMapper.selectMemberBymemberidAndpasswd(member);
		return member2;
	}

	@Override
	public List<Member> selectMembers() {
		List<Member> members = memberMapper.selectMembers();
		return members;
	}

	@Override
	public void insertMember(Member member) {
		memberMapper.insertMember(member);		
		
		for (MemberFile f : member.getFileList()) {
			//자동 증가로 만들어진 글번호를 파일 VO에 적용
			f.setMemberid(member.getMemberid());
		}
		memberMapper.insertMemberFileList(member.getFileList());
		
	}
	
	
}

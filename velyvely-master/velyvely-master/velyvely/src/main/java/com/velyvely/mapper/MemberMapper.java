package com.velyvely.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.velyvely.vo.Member;
import com.velyvely.vo.MemberFile;

@Mapper
public interface MemberMapper {

	Member selectMemberBymemberidAndpasswd(Member member);

	List<Member> selectMembers();

	void insertMember(Member member);

	void insertMemberFileList(List<MemberFile> fileList);

}

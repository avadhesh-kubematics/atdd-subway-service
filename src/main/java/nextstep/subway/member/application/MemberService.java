package nextstep.subway.member.application;

import org.springframework.stereotype.Service;

import nextstep.subway.member.domain.Member;
import nextstep.subway.member.domain.MemberRepository;
import nextstep.subway.member.dto.MemberRequest;
import nextstep.subway.member.dto.MemberResponse;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponse createMember(MemberRequest request) {
		Member member = memberRepository.save(request.toMember());
		return MemberResponse.of(member);
	}

	public MemberResponse findMember(Long id) {
		Member member = findMemberById(id);
		return MemberResponse.of(member);
	}

	public void updateMember(Long id, MemberRequest param) {
		Member member = findMemberById(id);
		member.update(param.toMember());
	}

	public Member findMemberById(Long id){
		return memberRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}
}

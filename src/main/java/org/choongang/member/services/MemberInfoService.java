package org.choongang.member.services;

import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.services.FileInfoService;
import org.choongang.member.MemberInfo;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Authorities;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final FileInfoService fileInfoService;
    private final MemberRepository memberRepository;

    // 스프링을 통해 권한 기능을 가공
    // 가공의 이유 => 인가 기능에 대한 구현을 위해
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<Authorities> tmp = Objects.requireNonNullElse(member.getAuthorities(),List.of(Authorities.builder().member(member).authority(Authority.USER).build()));

        List<SimpleGrantedAuthority> authorities = tmp.stream().map(a -> new SimpleGrantedAuthority(a.getAuthority().name())).toList();

        // 추가 데이터 처리
        addMemberInfo(member);

        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .member(member)
                .authorities(authorities)
                .build();

    }

    /**
     * 회원 추가 데이터 처리
     *
     * @param member
     */
    public void addMemberInfo(Member member) {
        String gid = member.getGid();
        List<FileInfo> items = fileInfoService.getList(gid);
        if (items != null && !items.isEmpty()) {
            member.setProfileImage(items.get(0));
        }
    }
}

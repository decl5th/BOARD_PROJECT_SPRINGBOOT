package org.choongang.member;

import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Authorities;
import org.choongang.member.entities.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberUtil {
    public boolean isLogin() {
        return getMember() != null;
    }

    public boolean isAdmin() {
        if(isLogin()) {
            Member member = getMember();
            List<Authorities> authorities = member.getAuthorities();
            return authorities.stream().anyMatch(s -> s.getAuthority() == Authority.ADMIN);
            // 만약 권한이 더 있다면 or로 추가하면 됨
        }
        return false;
    }

    public Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof MemberInfo) {
            // MemberInfo도 한번 더 객체 검증
            MemberInfo memberInfo = (MemberInfo) authentication.getPrincipal();

            return memberInfo.getMember();
        }
        return null;
    }
}

package org.choongang.member;


import lombok.Builder;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Member member;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비번이 만료됐는지 아닌지
        // false 값이면 비번 초기화하면 페이지로 이동할 수 있게 구현 가능
        return false;
    }

    @Override
    public boolean isEnabled() {
        // 회원 탈퇴여부 false: 탈퇴 -> DB에서 지우진 않고, 권한을 안주는 식으로 일정 기간 지나면 DB에서 삭제
        return true;
    }
}

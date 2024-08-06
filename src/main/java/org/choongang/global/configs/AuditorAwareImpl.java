package org.choongang.global.configs;

import lombok.RequiredArgsConstructor;
import org.choongang.member.MemberUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // @Bean으로 감지할 수 있도록
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {
    // 자동으로 로그인한 회원의 이메일 정보 가져올 수 있도록 설정

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        String email = memberUtil.isLogin() ? memberUtil.getMember().getEmail(): null;

        return Optional.ofNullable(email);
    }
}

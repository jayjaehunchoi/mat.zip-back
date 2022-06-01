package com.woowacourse.auth.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.woowacourse.auth.application.dto.TokenResponse;
import com.woowacourse.matzip.domain.member.Member;
import com.woowacourse.matzip.domain.member.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Transactional
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 토큰을_반환한다() {
        TokenResponse tokenResponse = authService.createToken("1");
        assertThat(tokenResponse.getAccessToken()).isNotNull();
    }

    @Test
    void 토큰을_요청하면_멤버를_생성한다() {
        authService.createToken("1");
        List<Member> members = memberRepository.findAll();
        assertThat(members).hasSize(1);
    }

}

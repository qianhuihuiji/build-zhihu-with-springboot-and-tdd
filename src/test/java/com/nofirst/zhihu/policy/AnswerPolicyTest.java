package com.nofirst.zhihu.policy;

import com.nofirst.zhihu.exception.AnswerNotExistedException;
import com.nofirst.zhihu.factory.AnswerFactory;
import com.nofirst.zhihu.factory.UserFactory;
import com.nofirst.zhihu.mbg.mapper.AnswerMapper;
import com.nofirst.zhihu.mbg.model.Answer;
import com.nofirst.zhihu.security.AccountUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AnswerPolicyTest {

    @InjectMocks
    private AnswerPolicy answerPolicy;

    @Mock
    private AnswerMapper answerMapper;

    @Test
    void judge_can_delete_answer() {
        // given
        Answer answer = AnswerFactory.createAnswer(1L);
        given(answerMapper.selectByPrimaryKey(answer.getId())).willReturn(answer);

        // when
        AccountUser accountUser = UserFactory.createAccountUser();
        // 这个时候 answer 的 userId 为 1
        boolean canDelete1 = answerPolicy.canDelete(1L, accountUser);

        // then
        assertThat(canDelete1).isTrue();

        // given
        answer.setUserId(100);
        given(answerMapper.selectByPrimaryKey(answer.getId())).willReturn(answer);

        // when
        boolean canDelete2 = answerPolicy.canDelete(1L, accountUser);

        // then
        assertThat(canDelete2).isFalse();
    }

    @Test
    void answer_is_valid_when_judge() {
        // given
        AccountUser accountUser = UserFactory.createAccountUser();
        given(answerMapper.selectByPrimaryKey(anyLong())).willReturn(null);

        // then
        assertThatThrownBy(() -> {
            // when
            answerPolicy.canDelete(1L, accountUser);
        }).isInstanceOf(AnswerNotExistedException.class)
                .hasMessageContaining("answer not exist");
    }
}
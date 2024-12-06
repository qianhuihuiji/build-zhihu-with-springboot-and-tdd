package com.nofirst.zhihu.controller;

import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.factory.QuestionFactory;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.mbg.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ViewDraftsTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        questionMapper.deleteByExample(null);
    }

    @Test
    void guests_may_not_view_drafts() throws Exception {
        this.mockMvc.perform(post("/drafts"))
                .andDo(print())
                .andExpect(status().is(401));
    }


    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void user_can_view_drafts() throws Exception {
        // given
        Question question = QuestionFactory.createUnpublishedQuestion();
        question.setUserId(2);
        questionMapper.insert(question);

        // when
        MvcResult result = this.mockMvc.perform(get("/drafts"))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(question.getTitle());
        assertThat(json).contains(question.getContent());
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void only_the_creator_can_view_it() throws Exception {
        // given
        Question questionOfJane = QuestionFactory.createUnpublishedQuestion();
        questionOfJane.setUserId(1);
        questionOfJane.setTitle("Jane question");
        questionMapper.insert(questionOfJane);
        Question questionOfJohn = QuestionFactory.createUnpublishedQuestion();
        questionOfJohn.setUserId(2);
        questionOfJohn.setTitle("John question");
        questionMapper.insert(questionOfJohn);

        // when
        MvcResult result = this.mockMvc.perform(get("/drafts", 1))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).contains(questionOfJohn.getTitle());
        assertThat(json).doesNotContain(questionOfJane.getTitle());
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void can_not_see_a_published_question_in_drafts() throws Exception {
        // given
        Question questionOfJohn = QuestionFactory.createPublishedQuestion();
        questionOfJohn.setUserId(2);
        questionOfJohn.setTitle("John question");
        questionMapper.insert(questionOfJohn);

        // when
        MvcResult result = this.mockMvc.perform(get("/drafts", 1))
                .andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();

        // then
        assertThat(json).doesNotContain(questionOfJohn.getTitle());
    }

}

package com.nofirst.zhihu.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.nofirst.zhihu.BaseContainerTest;
import com.nofirst.zhihu.common.CommonResult;
import com.nofirst.zhihu.common.ResultCode;
import com.nofirst.zhihu.mbg.mapper.ActivityMapper;
import com.nofirst.zhihu.mbg.mapper.QuestionMapper;
import com.nofirst.zhihu.model.vo.ProfileVo;
import com.nofirst.zhihu.model.vo.UserVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserProfileTest extends BaseContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private ActivityMapper activityMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails(value = "John", userDetailsServiceBeanName = "accountUserDetailsService")
    void a_user_can_see_his_profile_page() throws Exception {
        // given

        // when
        MvcResult result = this.mockMvc.perform(get("/profiles/{userId}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCode.SUCCESS.getCode()))
                .andReturn();

        // then
        String json = result.getResponse().getContentAsString();
        CommonResult<ProfileVo> commonResult = JSONUtil.toBean(json, new TypeReference<>() {
        }, false);
        UserVo user = commonResult.getData().getUser();
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getName()).isEqualTo("John");
    }


}

package com.bishe.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bishe.core.controller.BaseController;
import com.bishe.redis.entity.Member;
import com.bishe.redis.service.MemberService;

/**
 * Redis 测试Controller
 * @author 杜飞
 *
 */
@Controller
@RequestMapping("/rtest")
public class RedisTestController extends BaseController{

	private MemberService mService = new MemberService();
	
	@RequestMapping("/queryKv")
	public String queryKv() {
        Member member = new Member();
        member.setId("130100");
        member.setNickname("shijiazhuang");
        mService.add(member);
        
        Member mm = mService.get("130100");
        System.out.println(mm.getNickname());
        return "";
	}
	
}

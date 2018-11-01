package com.bishe.core.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.bishe.base.security.user.service.UserService;

/**
 * 初始化完成之后执行
 */
@Service
public class InitService implements ApplicationListener<ContextRefreshedEvent > {
	
	@Autowired
	private UserService userService;

	/**
	 * 当初始化完成后，将触发事件，调用以下代码。SpringMVC所以与Spring初始化完成后都会调用，所以增加判断
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null){
			userService.initAdminUser();
		}
	}
}

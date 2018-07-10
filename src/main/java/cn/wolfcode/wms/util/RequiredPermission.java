package cn.wolfcode.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@author WangZhe
 *@time	  2018年6月8日上午12:04:21
 *@mail   wzzst310@163.com
 *@desp
 */
@Target(ElementType.METHOD)//注解只能贴在方法上
@Retention(RetentionPolicy.RUNTIME)//当前注解能保存到运行时期
public @interface RequiredPermission {
	//使用注解传递参数,为权限设置名称
	//如果传递参数的时候不指定参数,此时需要将参数设置为value
	String value();
	//可以直接写@RequiredPermission("权限加载")
	//String name();
	//写@RequiredPermission(name="权限加载")

}

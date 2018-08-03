package cn.edu.lingnan.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context=invocation.getInvocationContext();
		Boolean isadmin=(Boolean)context.getSession().get("isadmin");
		if(isadmin!=null&&isadmin){
			return invocation.invoke();
		}
		else{
			context.getContext().put("msg", "账号权限不足，请登录拥有对应权限的账号");
			return "users";
		}
	}

}

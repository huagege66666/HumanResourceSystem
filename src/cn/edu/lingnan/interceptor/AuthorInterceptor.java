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
			context.getContext().put("msg", "�˺�Ȩ�޲��㣬���¼ӵ�ж�ӦȨ�޵��˺�");
			return "users";
		}
	}

}

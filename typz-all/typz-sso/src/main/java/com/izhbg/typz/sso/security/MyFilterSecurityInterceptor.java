package com.izhbg.typz.sso.security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/**
 * 
* @ClassName: MyFilterSecurityInterceptor 
* @Description: 
* @author caixl 
* @date 2016-4-27 下午2:01:44 
*
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor  implements Filter
{
	private FilterInvocationSecurityMetadataSource securityMetadataSource;  
	   
	 public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain)  
	 throws IOException, ServletException{  
	    
	  FilterInvocation fi = new FilterInvocation( request, response, chain );  
	  invoke(fi);  
	    
	 }  
	   
	 public FilterInvocationSecurityMetadataSource getSecurityMetadataSource(){  
	  return this.securityMetadataSource;  
	 }  
	   
	 public Class<? extends Object> getSecureObjectClass(){  
	  return FilterInvocation.class;  
	 }  
	  
	   
	 public void invoke( FilterInvocation fi ) throws IOException, ServletException{  
	    
	  InterceptorStatusToken  token = super.beforeInvocation(fi);  
	    
	  try{  
	   fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
	  }finally{  
	   super.afterInvocation(token, null);  
	  }  
	    
	 }  
	    
	   
	 @Override  
	 public SecurityMetadataSource obtainSecurityMetadataSource(){  
	  return this.securityMetadataSource;  
	 }  
	   
	   
	 public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource){  
	  this.securityMetadataSource = securityMetadataSource;  
	 }  
	   
	   
	 public void destroy(){  
	    
	 }  
	   
	 public void init( FilterConfig filterconfig ) throws ServletException{  
	    
	 }
}
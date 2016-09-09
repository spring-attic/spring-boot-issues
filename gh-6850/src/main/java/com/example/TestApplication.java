package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestApplication {
	
	@RequestMapping("/")
	public String home() {
		return "Hello";
	}
	
	@Bean
	public FilterRegistrationBean oneFilterRegistration(OneFilter filter) {
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		bean.setOrder(1);
		return bean;
	}

	@Bean
	public FilterRegistrationBean twoFilterRegistration(TwoFilter filter) {
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.setOrder(2);
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}

@Component
class Tracer {
	private List<String> list = Collections.synchronizedList(new ArrayList<>());
	public void reset() {
		list.clear();
	}
	public void trace(String item) {
		list.add(item);
	}
	public List<String> getTraces() {
		return list;
	}
}

abstract class BaseFilter implements Filter, BeanNameAware {
	
	private final Tracer tracer;
	private String label;

	public BaseFilter(Tracer tracer) {
		this.tracer = tracer;
	}
	
	@Override
	public void setBeanName(String name) {
		this.label = name;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		tracer.trace("before:" + label);
		chain.doFilter(request, response);
		tracer.trace("after:" + label);
	}

	@Override
	public void destroy() {
	}
	
}

@Component
class OneFilter extends BaseFilter {

	public OneFilter(Tracer tracer) {
		super(tracer);
	}
	
}
@Component
class TwoFilter extends BaseFilter {

	public TwoFilter(Tracer tracer) {
		super(tracer);
	}
	
}

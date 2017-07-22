package com.klindziuk.mob.log.model;

import java.util.List;

import net.lightbody.bmp.core.har.HarCookie;
import net.lightbody.bmp.core.har.HarNameValuePair;

public class BaseLog {
	protected static final String SEPARATOR = System.getProperty("line.separator");
	protected long bodySize;
	protected long headersSize;
	protected String comment;
	protected String httpVersion;
	protected List<HarCookie> cookies;
	protected List<HarNameValuePair> headers;
	
	public BaseLog(long bodySize, long headersSize, String comment, String httpVersion,
			List<HarCookie> cookies, List<HarNameValuePair> headers) {
		
		this.bodySize = bodySize;
		this.headersSize = headersSize;
		this.comment = comment;
		this.httpVersion = httpVersion;
		this.cookies = cookies;
		this.headers = headers;
	}
	
	public long getBodySize() {
		return bodySize;
	}

	public void setBodySize(long bodySize) {
		this.bodySize = bodySize;
	}

	public long getHeadersSize() {
		return headersSize;
	}

	public void setHeadersSize(long headersSize) {
		this.headersSize = headersSize;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}
	
	public List<HarCookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<HarCookie> cookies) {
		this.cookies = cookies;
	}

	public List<HarNameValuePair> getHeaders() {
		return headers;
	}

	public void setHeaders(List<HarNameValuePair> headers) {
		this.headers = headers;
	}
	
	protected String printCookies(){
		StringBuilder builder = new StringBuilder();
		for(HarCookie cookie : this.getCookies()){
			builder.append("cookie [ ");
			builder.append(cookie.getName());
			builder.append(" : ");
			builder.append(cookie.getPath());
			builder.append(" : ");
			builder.append(cookie.getValue());
			builder.append(" : ");
			builder.append(cookie.getDomain());
			builder.append(" ] ");
		}
		return builder.toString();
	}
	
	protected String printHeaders(){
		StringBuilder builder = new StringBuilder();
		for(HarNameValuePair pair : this.getHeaders()) {
			builder.append(SEPARATOR);
			builder.append("\t");
			builder.append("header [ ");
			builder.append(pair.getName());
			builder.append(" : ");
			builder.append(pair.getValue());
			builder.append(" ] ");
		}
		return builder.toString();
	}
}

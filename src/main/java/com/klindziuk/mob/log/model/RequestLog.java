package com.klindziuk.mob.log.model;

import java.util.List;

import net.lightbody.bmp.core.har.HarNameValuePair;

public class RequestLog extends BaseLog {
	private String method;
	private String url;
	private List<HarNameValuePair> queries;

	public RequestLog() {
		
	}

	public List<HarNameValuePair> getQueries() {
		return queries;
	}

	public void setQueries(List<HarNameValuePair> queries) {
		this.queries = queries;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((queries == null) ? 0 : queries.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestLog other = (RequestLog) obj;
		if (queries == null) {
			if (other.queries != null)
				return false;
		} else if (!queries.equals(other.queries))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  SEPARATOR + " -= REQUEST =-" + SEPARATOR + "[ bodySize=" + bodySize + " ]" + SEPARATOR + "[ headersSize=" + headersSize
				+ " ]" + SEPARATOR + "[ comment=" + comment + " ]" + SEPARATOR + "[ httpVersion=" + httpVersion + " ]"
				+ SEPARATOR + "[ method=" + method + " ]" + SEPARATOR + "[ url=" + url + " ]" + SEPARATOR + "[ cookies="
				+ printCookies() + " ]" + SEPARATOR + "[ headers=" + printHeaders() + " ]" + SEPARATOR + "[ queries : "
				+ printQueries() + "]" + SEPARATOR + "-= END OF REQUEST =-" + SEPARATOR;
	}

	private String printQueries() {
		StringBuilder builder = new StringBuilder();
		for (HarNameValuePair pair : this.getQueries()) {
			builder.append(SEPARATOR);
			builder.append("\t");
			builder.append("query [ ");
			builder.append(pair.getName());
			builder.append(" : ");
			builder.append(pair.getValue());
			builder.append(" ] ");
		}
		return builder.toString();
	}
}

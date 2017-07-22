package com.klindziuk.mob.log.model;

import java.util.List;

import net.lightbody.bmp.core.har.HarCookie;
import net.lightbody.bmp.core.har.HarNameValuePair;

public class ResponseLog extends BaseLog {
	private int status;
	private String statusText;
	private String content;
	private String redirectedUrl;

	public ResponseLog(long bodySize, long headersSize, String comment, String httpVersion, List<HarCookie> cookies,
			List<HarNameValuePair> headers, int status, String statusText, String content, String redirectedUrl) {
		super(bodySize, headersSize, comment, httpVersion, cookies, headers);
		this.status = status;
		this.statusText = statusText;
		this.content = content;
		this.redirectedUrl = redirectedUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRedirectedUrl() {
		return redirectedUrl;
	}

	public void setRedirectedUrl(String redirectedUrl) {
		this.redirectedUrl = redirectedUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((redirectedUrl == null) ? 0 : redirectedUrl.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusText == null) ? 0 : statusText.hashCode());
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
		ResponseLog other = (ResponseLog) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (redirectedUrl == null) {
			if (other.redirectedUrl != null)
				return false;
		} else if (!redirectedUrl.equals(other.redirectedUrl))
			return false;
		if (status != other.status)
			return false;
		if (statusText == null) {
			if (other.statusText != null)
				return false;
		} else if (!statusText.equals(other.statusText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return SEPARATOR + " -= RESPONSE =-" + SEPARATOR + "[ status=" + status + " ]" + SEPARATOR + "[ statusText=" + statusText
				+ " ]" + SEPARATOR + "[ content =" + content + " ]" + SEPARATOR + "[ status=" + redirectedUrl + " ]"
				+ SEPARATOR + "[ bodySize=" + bodySize + " ]" + SEPARATOR + "[ headersSize=" + headersSize + " ]"
				+ SEPARATOR + "[ comment=" + comment + " ]" + SEPARATOR + "[ httpVersion=" + httpVersion + " ]"
				+ SEPARATOR + "[ cookies=" + printCookies() + " ]" + SEPARATOR + "[ headers=" + printHeaders() + " ]"
				+ SEPARATOR + "-= END OF RESPONSE =-" + SEPARATOR;
	}
}

package com.bing.lan.core.base.domain;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionaryQueryObject extends QueryObject {
	private String keyword;
	private Long parentId;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}
}

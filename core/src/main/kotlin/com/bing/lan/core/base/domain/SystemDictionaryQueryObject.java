package com.bing.lan.core.base.domain;

import com.bing.lan.core.base.query.QueryObject;

import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("SystemDictionaryQueryObject")
public class SystemDictionaryQueryObject extends QueryObject {
	private String keyword;
	private Long parentId;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}
}

package com.bing.lan.core.base.mapper;

import com.bing.lan.core.base.domain.EmailActive;

import java.util.List;

public interface EmailActiveMapper {
	int deleteByPrimaryKey(Long id);

	int insert(EmailActive record);

	EmailActive selectByPrimaryKey(Long id);

	List<EmailActive> selectAll();

	int updateByPrimaryKey(EmailActive record);
	
	EmailActive selectByCode(String code);
}
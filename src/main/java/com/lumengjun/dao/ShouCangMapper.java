package com.lumengjun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lumengjun.entity.ShouCang;

public interface ShouCangMapper {

	@Insert("INSERT INTO cms_shouchang VALUES(NULL,#{test},#{url},#{user_id},NOW())")
	int addShouCang(ShouCang shouCang);

	
	@Delete("DELETE FROM cms_shouchang WHERE id=#{value}")
	int delShouCangById(Integer id);

	@Select("SELECT * FROM cms_shouchang WHERE user_id=#{value} ORDER BY created DESC")
	List<ShouCang> findShouCang(Integer id);

}

package com.asiainfotest.demo.mapper;

import com.asiainfotest.demo.entity.TdParamConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HallowMapper {

    TdParamConfig qryParamConfigByParanId(@Param("paramId") String paramId);
}

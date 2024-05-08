package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);
}

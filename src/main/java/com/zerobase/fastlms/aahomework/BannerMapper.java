package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);
}

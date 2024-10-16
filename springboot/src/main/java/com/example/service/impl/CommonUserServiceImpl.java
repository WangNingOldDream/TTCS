package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Admin;
import com.example.entity.CommonUser;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.service.CommonUserService;
import com.example.mapper.CommonUserMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 天道酬勤hsy
* @description 针对表【common_user】的数据库操作Service实现
* @createDate 2024-10-14 10:56:27
*/
@Service
public class CommonUserServiceImpl extends ServiceImpl<CommonUserMapper, CommonUser>
    implements CommonUserService{
    @Resource
    private CommonUserMapper commonUserMapper;

    /**
     * 新增新用户
     */
    public void add(CommonUser commonUser) {
//      CommonUser dbCu = commonUserMapper.selectByAccountName(commonUser.getAccountName());
//        if (ObjectUtil.isNotNull(dbCu)) {
//            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
//        }
        if (ObjectUtil.isEmpty(commonUser.getAccountPassword())) {
            commonUser.setAccountPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(commonUser.getAccountName())) {
            commonUser.setAccountName(commonUser.getUserName());
        }
        //admin.setRole(RoleEnum.ADMIN.name());
        commonUserMapper.insert(commonUser);
    }

    public void deleteById(Integer id) {
        commonUserMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
    }

    public CommonUser selectById(Integer id) {
        CommonUser commonUser=null;

        return commonUser;
    }

    public List<CommonUser> selectAll(CommonUser commonUser) {
        List<CommonUser> commonUsers=new ArrayList<>();

        return commonUsers;
    }

    public PageInfo<CommonUser> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageInfo<CommonUser>  pageInfo=null;

        return pageInfo;
    }

    @Override
    public boolean updateById(CommonUser commonUser) {

        return false;
    }

    public CommonUser login(CommonUser commonUser){
        CommonUser dbCommonUser=null;

        return dbCommonUser;
    }

    public void register(CommonUser commonUser){

    }

    public void updatePassword(CommonUser commonUser){

    }
}





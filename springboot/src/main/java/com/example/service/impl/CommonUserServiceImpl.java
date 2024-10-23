package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Admin;
import com.example.entity.CommonUser;
import com.example.entity.SimpleUserInfo;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.service.CommonUserService;
import com.example.mapper.CommonUserMapper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.javassist.Loader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean add(CommonUser commonUser) {
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
        int result = commonUserMapper.insert(commonUser);
        return result > 0;
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

    // 实现用户登录的查询方法（ax）
    public CommonUser selectByAccount(String userName, String password) {
        return commonUserMapper.selectByAccount(userName, password);
    }

    @Autowired
    private JavaMailSender mailSender;

    // 生成并发送验证码到邮箱（ax）
    public boolean sendVerificationCode(String email) {
        try {
            // 生成随机验证码 (这里简单生成4位数的数字验证码)
            String verificationCode = String.valueOf((int) (Math.random() * 9000 + 1000));

            // 创建邮件内容
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("验证码");
            message.setText("您的验证码是：" + verificationCode);

            // 发送邮件
            mailSender.send(message);

            // 在实际应用中，可以将验证码保存到数据库或缓存中以便后续验证
            // saveVerificationCodeToDB(email, verificationCode);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //验证码来源？（ax）
    public boolean verifyCode(String email, String vCode) {
        // 假设你在某个缓存或数据库中保存了验证码
        String correctCode = getStoredVerificationCode(email); // 从缓存或数据库中获取验证码
        return correctCode != null && correctCode.equals(vCode);
    }

    // 这个方法是示例，实际中可能会根据业务逻辑从缓存或数据库中获取验证码
    private String getStoredVerificationCode(String email) {
        // 假设你使用了某种缓存机制存储验证码，或者从数据库中获取
        // 这里返回一个模拟的验证码以示例
        return "1234"; // 示例代码，实际代码应查询数据库或缓存
    }

    //查询用户是否存在（ax）
    public boolean checkIfUserExists(String accountName) {
        CommonUser existingUser = commonUserMapper.selectByAccountName(accountName);
        return existingUser != null;
    }


    @Override
    public boolean updateById(CommonUser commonUser) {
        return super.updateById(commonUser);
    }

    public CommonUser login(CommonUser commonUser){
        CommonUser dbCommonUser=null;

        return dbCommonUser;
    }

    public void register(CommonUser commonUser){

    }

    public void updatePassword(CommonUser commonUser){

    }



    public List<SimpleUserInfo> selectAllByCompId(Integer compId) {
        List<SimpleUserInfo> userInfos=new ArrayList<>();
        userInfos= commonUserMapper.selectAllInComp(compId);
        return userInfos;
    }


}





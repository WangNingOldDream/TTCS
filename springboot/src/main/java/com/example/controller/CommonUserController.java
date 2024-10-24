package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.CommonUser;
import com.example.entity.SimpleUserInfo;
import com.example.service.impl.CommonUserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static com.example.common.enums.ResultCodeEnum.PARAM_ERROR;
import static com.example.common.enums.ResultCodeEnum.SUCCESS;

@RestController
@RequestMapping("/common-user")
public class CommonUserController {
    @Resource
    private CommonUserServiceImpl commonUserService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody CommonUser commonUser) {
        commonUserService.add(commonUser);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commonUserService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commonUserService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody CommonUser commonUser) {
        commonUserService.updateById(commonUser);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        CommonUser commonUser = commonUserService.selectById(id);
        return Result.success(commonUser);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(CommonUser commonUser ) {
        List<CommonUser> list = commonUserService.selectAll(commonUser);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<CommonUser> page = commonUserService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectAllUserInComp/{compId}")
    public Result selectAllByCompId(@PathVariable Integer compId) {
        List<SimpleUserInfo> simpleUserInfo = commonUserService.selectAllByCompId(compId);
        if(simpleUserInfo==null||simpleUserInfo.isEmpty()){
            return Result.error(PARAM_ERROR);
        }
        HashMap<String,Object> data=new HashMap<String,Object>();
        Result result=new Result();
        data.put("data",simpleUserInfo);
        data.put("flag",true);
        result.setCode(SUCCESS.code);
        result.setMsg("查找赛事相关用户信息成功");
        result.setData(data);
        return result;
    }

    //登录验证（ax）
    @GetMapping("/login")
    public Result login(@RequestParam String userName, @RequestParam String password) {
        // 调用 service 层方法
        CommonUser user = commonUserService.selectByAccount(userName, password);
        if (user != null) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("userId", user.getUserid());
            data.put("flag", true);
            return Result.success(data);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    //发送验证码（ax）
    @GetMapping("/sendVCode")
    public Result sendVCode(@RequestParam String email) {
        boolean isSent = commonUserService.sendVerificationCode(email);
        if (isSent) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("flag", true);
            return Result.success("验证码发送成功", data);
        } else {
            return Result.error("验证码发送失败");
        }
    }

    //注册用户(ax)
    @PostMapping("/register")
    public Result register(@RequestBody CommonUser commonUser, @RequestParam String vCode) {
        boolean isCorrectCode = commonUserService.verifyCode(commonUser.getMail(), vCode);
        if (!isCorrectCode) {
            return Result.error("验证码错误");
        }

        boolean userExists = commonUserService.checkIfUserExists(commonUser.getAccountName());
        if (userExists) {
            return Result.error("用户已存在");
        }

        boolean isRegistered = commonUserService.add(commonUser);
        if (isRegistered) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("userId", commonUser.getUserid());
            data.put("flag", true);
            return Result.success("注册成功", data);
        } else {
            return Result.error("注册失败");
        }
    }


    //密码更改(ax)
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestParam String userName, @RequestParam String oldPassword, @RequestParam String newPassword) {
        CommonUser user = commonUserService.selectByAccount(userName, oldPassword);
        if (user == null) {
            return Result.error("旧密码错误");
        }

        user.setAccountPassword(newPassword);
        boolean isUpdated = commonUserService.updateById(user);
        if (isUpdated) {
            return Result.success("密码修改成功");
        } else {
            return Result.error("密码修改失败");
        }
    }

     //获取用户信息(ax)
     @GetMapping("/userInfo/{userId}")
     public Result getUserInfo(@PathVariable Integer userId) {
         CommonUser user = commonUserService.selectById(userId);
         if (user != null) {
             HashMap<String, Object> data = new HashMap<>();
             data.put("userId", user.getUserid());
             data.put("userName", user.getAccountName());
             data.put("password", "******"); // 隐藏密码
             data.put("name", user.getUserName());
             data.put("sex", user.getUserSex());
             data.put("birthday", user.getBirthday());
             data.put("birthPlace", user.getBirthPlace());
             data.put("TTL", user.getTtcLevel());
             data.put("email", user.getMail());
             return Result.success(data);
         } else {
             return Result.error("用户不存在");
         }
     }

    /**
     * 更新用户信息(ax)
     */
    @PutMapping("/updateInfo")
    public Result updateUserInfo(@RequestBody CommonUser commonUser) {
        boolean isUpdated = commonUserService.updateById(commonUser);
        if (isUpdated) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

}

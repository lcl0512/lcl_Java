package com.lcl.donation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lcl.donation.service.vo.request.RequestChangePasswordVo;
import com.lcl.donation.service.vo.request.RequestUserListVo;
import com.lcl.donation.utils.FormatUtils;
import com.lcl.donation.utils.Md5Utils;
import com.lcl.donation.common.Result;
import com.lcl.donation.common.StringConst;
import com.lcl.donation.entity.User;
import com.lcl.donation.service.IUserService;
import com.lcl.donation.service.vo.request.RequestLoginVo;
import com.lcl.donation.service.vo.request.RequestUserVo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Producer producer;
    //获取验证码接口：
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        //验证码内容
        String text = producer.createText();
        //得到验证码图片
        BufferedImage image = producer.createImage(text);
        //保存到 redis
        ValueOperations forValue = redisTemplate.opsForValue();
        forValue.set(Constants.KAPTCHA_SESSION_KEY,text);
        redisTemplate.expire(Constants.KAPTCHA_SESSION_KEY, 60, TimeUnit. SECONDS);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    @Autowired
    IUserService userService;
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody RequestLoginVo requestLoginVo){
        String telephone = requestLoginVo.getTelephone();
        boolean isPhoneNumber = FormatUtils.isMobile(telephone);
        String password = requestLoginVo.getPassword();
        String captcha = requestLoginVo.getCaptcha();
        password = Md5Utils.hash(password);
        // 通过redis获取验证码
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String kaptcha = (String) valueOperations.get(Constants.KAPTCHA_SESSION_KEY);
        if(kaptcha.equalsIgnoreCase(captcha)){
//            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//            userQueryWrapper.eq("telephone",telephone).eq("password",password);
            Map<String,Object> map = new HashMap<>();
            map.put("telephone",telephone);
//            map.put("password",password);

            User user = userService.queryByTelephone(telephone);
            if(user==null||!(user.getPassword().equals(password))){
                return Result.getFailure().setMsg(StringConst.USERNAME_OR_PASSWORD_ERROR);
            }else{
                Map<String,Object> userMap = new HashMap<>();
                userMap.put("userId",user.getId());
                if (user.getType() == 1){
                    userMap.put("isAdmin",true);
                }
                else {
                    userMap.put("isAdmin",false);
                }
                return Result.getSuccess().setData(userMap);
            }

        }else{
            return Result.getFailure().setMsg(StringConst.CAPTCHA_ERROR);
        }
    }
    @PostMapping("/register")
    public Result register(@RequestBody RequestUserVo requestUserVo) {
        User user = new User();
        user.setUsername(requestUserVo.getUsername());
        user.setSex(requestUserVo.getSex());
        user.setAge(requestUserVo.getAge());
        String password = Md5Utils.hash(requestUserVo.getPassword());
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        String phone = requestUserVo.getTelephone();
        if(!FormatUtils.isMobile(requestUserVo.getTelephone())){
            return Result.getFailure().setMsg(StringConst.PHONE_NUMBER_ERROR);
        }
        user.setTelephone(phone);
        user.setProvince(requestUserVo.getProvince());
        user.setEmail(requestUserVo.getEmail());
        if(userService.insertUser(user)){
            return Result.getSuccess().setMsg(StringConst.REGISTER_SUCCESS);
        }else{
            return Result.getFailure().setMsg(StringConst.REGISTER_FAILE);
        }
    }

    @RequestMapping("/getUserInfoById/{id}")
    public Result getUserInfoById(HttpServletRequest res,@PathVariable Integer id){
        User user = userService.getUserInfoById(id);
        if(user!=null){
            return Result.getSuccess().setData(user);
        }
        return Result.getFailure().setMsg("错误id");
    }
    @PostMapping("/list")
    public Result getUserList(@RequestBody RequestUserListVo userListVo){
        IPage<User> userIPage = userService.querryPage(userListVo);
//        System.out.println(userIPage.getRecords().get(0).getCreatTime());
        return Result.getSuccess().setData(userIPage);
    }
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody RequestChangePasswordVo changePasswordVo){
        User user = userService.getUserInfoById(changePasswordVo.getId());
        String password = Md5Utils.hash(changePasswordVo.getPassword());
        System.out.println(user);
        System.out.println("password:"+password);
        if(user.getPassword().equals(password)){
            user.setPassword(password);
            userService.save(user);
            return Result.getSuccess().setMsg("修改密码成功");
        }else{
            return Result.getFailure().setMsg("原密码错误");
        }
    }
    @PostMapping("update")
    public Result update(@RequestBody RequestUserVo requestUserVo){
        User user = new User();
        BeanUtils.copyProperties(requestUserVo,user);
        return userService.updateById(user) ? Result.getSuccess() : Result.getFailure();
    }
}

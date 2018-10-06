package com.magicli.web.controller;

import com.magicli.ioc.dao.UserDao;
import com.magicli.ioc.domain.User;
import com.magicli.web.controller.exceptions.DuplicateDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by gaonl on 2018/10/6.
 */
@Controller
@RequestMapping({"/user"})
public class UserInfoController {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public String showUserInfo(@PathVariable("userId") Integer userId, Model model) {

//        if(System.currentTimeMillis() >100){
//            throw new DuplicateDomainException();
//        }

        User user = userDao.getById(userId);
        model.addAttribute("user", user);
        return "user/info";
    }

    /**
     * 也可以使用javax.servlet.http.Part
     * 如果使用javax.servlet.http.Part，就可以不用配置MultipartResolver了，方便快捷（不过只能在servlet3.0中使用）
     */
    @RequestMapping(path = "/profile/upload", method = RequestMethod.POST)
    public String profileUpload(Integer userId, String title, @RequestPart MultipartFile profile, Model model) {
        File baseDir = new File("F:\\my\\my_projects\\spring_word_template_files\\upload");

        String originalFilename = profile.getOriginalFilename();
        String name = profile.getName();
        long size = profile.getSize();


        System.out.println("originalFilename: " + originalFilename);
        System.out.println("name: " + name);
        System.out.println("size: " + size);
        System.out.println("userId: " + userId);
        System.out.println("title: " + title);

        File dest = new File(baseDir,originalFilename);

        try {
            profile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.addAttribute("userId",userId);
        model.addAttribute("title",title);

        //url重定向，spring会吧model里面的参数放到URL下面去（不要用字符串拼接的，用占位符）
        //假设userId=1 title='china' 如下的URL会重定向到  /user/1?title=china
        return "redirect:/user/{userId}";

    }

    /**
     * 当这个controller抛出DuplicateDomainException异常时，调用这个方法，不知道能不能带请求参数，算了，不试了
     * @return
     */
//    @ExceptionHandler(DuplicateDomainException.class)
//    public String exceptionHandler(){
//        return "error/500";
//    }

}

package com.magicli.web.controller;

import com.magicli.ioc.dao.UserDao;
import com.magicli.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
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
        User user = userDao.getById(userId);
        model.addAttribute("user", user);
        return "user/info";
    }

    @RequestMapping(path = "/profile/upload", method = RequestMethod.POST)
    public String profileUpload(Integer userId, String title, @RequestPart MultipartFile profile) {
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

        return "redirect:/user/" + userId;

    }

}

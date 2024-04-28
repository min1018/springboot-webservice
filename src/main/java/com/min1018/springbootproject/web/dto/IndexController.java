package com.min1018.springbootproject.web.dto;

import com.min1018.springbootproject.config.auth.LoginUser;
import com.min1018.springbootproject.service.PostsService;
import com.min1018.springbootproject.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
//        System.out.println("check");
//        System.out.println(dto.getAuthor());
//        System.out.println(dto.getTitle());
//        System.out.println(dto.getContent());
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

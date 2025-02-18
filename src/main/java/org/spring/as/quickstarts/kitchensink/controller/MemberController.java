package org.spring.as.quickstarts.kitchensink.controller;

import org.spring.as.quickstarts.kitchensink.model.Member;
import org.spring.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

    @Autowired
    private MemberRegistration memberRegistration;


    @GetMapping("/")
    public String showIndexPage(Model model) {
        if (!model.containsAttribute("newMember")) {
            model.addAttribute("newMember", new Member());
        }
        model.addAttribute("members", memberRegistration.findAllMembers());

        model.addAttribute("content", "index :: content");
        return "default";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("newMember") Member newMember, RedirectAttributes redirectAttributes) {
        try {
            memberRegistration.register(newMember);
            redirectAttributes.addFlashAttribute("message", "Registered successfully!");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("newMember", newMember);
            return "redirect:/";
        }
    }
}

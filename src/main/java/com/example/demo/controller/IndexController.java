package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleValidator;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/")
    public String index(Model model){
        List<Role> lsRole = roleService.getAll();
        model.addAttribute("lsRole",lsRole);
        return "index";
    }

    @RequestMapping("/add")
    public String addRole(Model model){
        model.addAttribute("role",new Role());
        return "addRole";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(@Validated @ModelAttribute("role") Role role, BindingResult bindingResult){
        new RoleValidator().validate(role,bindingResult);
        if(bindingResult.hasErrors()){
            return "addRole";
        }
        else {
            roleService.saveRole(role);
            return "redirect:/";
        }
    }

    @RequestMapping("/edit")
    public String editUser(@RequestParam("id") int roleId, Model model){
        Optional<Role> roleEdit = roleService.findRoleById(roleId);
        roleEdit.ifPresent(role->model.addAttribute("role",role));
        return "editRole";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") int roleId, Model model){
        roleService.deleteRole(roleId);
        return "redirect:/";
    }
}

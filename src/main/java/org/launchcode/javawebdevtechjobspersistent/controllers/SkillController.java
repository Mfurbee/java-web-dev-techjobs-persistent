package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    // Added index method to return a list of Employers
    @RequestMapping("")
    public String index(Model model) {

        // Add the title attribute to the model, plus a list of all employers from the EmployerRepository
        model.addAttribute("title", "My Employers");
        Iterable<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);

        // return the resources/templates/employers/index.html view
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                    Errors errors, Model model) {
// Add call to Save method to store new Employer
        skillRepository.save(newSkill);

        if (errors.hasErrors()) {
            return "skills/add";
        }

        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        //Calls respository and tells it to get a single entry from the database
        // by passing the employer ID from the url

        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}

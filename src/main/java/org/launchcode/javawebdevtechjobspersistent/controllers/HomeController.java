package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //Added new field, employerRepository
    @Autowired
    private EmployerRepository employerRepository;

    // Added skill repository
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        Iterable<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "index";
    }
//Added list of employers to the model.
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers", employers);

        //Added skills to the model to display the checklist
        Iterable<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors,
                                    Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        //Used employerId param passed from the form to get the employer

        Optional<Employer> employer = employerRepository.findById(employerId);
        newJob.setEmployer(employer.get());

        //Retrieved list of skills submitted by the form
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);
        if(optJob.isPresent()) {
            Job job = optJob.get();
            model.addAttribute(job);
            return "view";
        } else {
            return "redirect:../";
        }
    }


}

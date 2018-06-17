package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.commons.Semester;
import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.faculty.Faculty;
import com.victoria.fargutu.unibook.repository.model.schedule.Schedule;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.service.faculty.FacultyService;
import com.victoria.fargutu.unibook.service.schedule.ScheduleService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private AuthService authService;
    private AuthManager authManager;
    private ScheduleService scheduleService;
    private FacultyService facultyService;

    @Autowired
    IndexController(AuthService authService, AuthManager authManager, FacultyService facultyService, ScheduleService scheduleService) {
        this.authService = authService;
        this.authManager = authManager;
        this.facultyService = facultyService;
        this.scheduleService = scheduleService;
    }

    @HasRole(UserRole.ADMIN)
    @GetMapping
    public String openIndexPage(ModelMap modelMap) {
        User user = authManager.getLoggedInUser();
        modelMap.put("loggedUser", user);

        Semester[] semesters = Semester.values();
        List<Faculty> faculties = facultyService.getAllFaculties();
        modelMap.put("faculties", faculties);
        modelMap.put("semesters", semesters);

        return "index/index";
    }

    @HasRole(UserRole.ADMIN)
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public String createSchedule(@ModelAttribute Schedule schedule, RedirectAttributes redirectAttributes) {
        Schedule schedule1 = scheduleService.createSchedule(schedule);
        redirectAttributes.addFlashAttribute("schedule", scheduleService.getSchedule(schedule1));
        return "redirect:/schedule/cell";
    }

    @HasRole(UserRole.ADMIN)
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {
        authService.logout();
        return "redirect:/";
    }

}
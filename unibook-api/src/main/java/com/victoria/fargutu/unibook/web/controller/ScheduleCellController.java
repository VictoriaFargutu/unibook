package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.commons.*;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.ScheduleMap;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.classroom.ClassroomService;
import com.victoria.fargutu.unibook.service.course.CourseService;
import com.victoria.fargutu.unibook.service.group.StudentsGroupService;
import com.victoria.fargutu.unibook.service.schedule.ScheduleService;
import com.victoria.fargutu.unibook.service.scheduleCell.ScheduleCellService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
import com.victoria.fargutu.unibook.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/scheduleCell/cell")
public class ScheduleCellController {
    private AuthManager authManager;
    private UserService userService;
    private ScheduleCellService scheduleCellService;
    private ClassroomService classroomService;
    private ScheduleService scheduleService;
    private CourseService courseService;
    private StudentsGroupService studentsGroupService;

    @Autowired
    public ScheduleCellController(AuthManager authManager, UserService userService, ScheduleCellService scheduleCellService, ClassroomService classroomService, ScheduleService scheduleService, CourseService courseService, StudentsGroupService studentsGroupService) {
        this.authManager = authManager;
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.scheduleCellService = scheduleCellService;
        this.classroomService = classroomService;
        this.courseService = courseService;
        this.studentsGroupService = studentsGroupService;
    }

    @HasRole(UserRole.USER)
    @GetMapping
    public String openScheduleCellPage(@ModelAttribute("notification") FlashMessage notification, ModelMap modelMap) {
        User user = authManager.getLoggedInUser();
        if (user == null) {
            modelMap.put("loggedUser", new User());
        } else {
            modelMap.put("loggedUser", user);
        }
        if (notification != null) {
            modelMap.addAttribute("error", notification.getMessage());
        }
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        List<Course> courses = courseService.getCourses();
        List<User> users = userService.getAllUsers();
        WeekType[] weekTypes = WeekType.values();
        Day[] days = Day.values();
        List<String> hours = new ArrayList<>();
        hours.add(Hour.EIGHT_TO_TEN);
        hours.add(Hour.TEN_TO_TWELVE);
        hours.add(Hour.TWELVE_TO_FOURTEEN);
        hours.add(Hour.FOURTEEN_TO_SIXTEEN);
        hours.add(Hour.SIXTEEN_TO_EIGHTEEN);
        hours.add(Hour.EIGHTEEN_TO_TWENTY);
        hours.add(Hour.TWENTY_TO_TWENTYTWO);
        List<StudentsGroup> studentsGroups = studentsGroupService.getStudentsGroups();
        Subgroup[] subgroups = Subgroup.values();
        modelMap.put("classrooms", classrooms);
        modelMap.put("courses", courses);
        modelMap.put("users", users);
        modelMap.put("weekTypes", weekTypes);
        modelMap.put("days", days);
        modelMap.put("hours", hours);
        modelMap.put("studentsGroups", studentsGroups);
        modelMap.put("subgroups", subgroups);

        return "/schedule";
    }


    @HasRole(UserRole.USER)
    @PostMapping
    public String createScheduleCell(@ModelAttribute ScheduleMap scheduleMap) {
        scheduleCellService.createScheduleCell(scheduleMap);
        return "redirect:/";
    }
}

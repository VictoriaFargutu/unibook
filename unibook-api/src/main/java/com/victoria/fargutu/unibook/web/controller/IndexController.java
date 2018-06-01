package com.victoria.fargutu.unibook.web.controller;

import com.victoria.fargutu.unibook.repository.commons.*;
import com.victoria.fargutu.unibook.repository.model.AuthManager;
import com.victoria.fargutu.unibook.repository.model.ScheduleMap;
import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.course.Course;
import com.victoria.fargutu.unibook.repository.model.faculty.Faculty;
import com.victoria.fargutu.unibook.repository.model.studentsGroup.StudentsGroup;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.auth.AuthService;
import com.victoria.fargutu.unibook.service.classroom.ClassroomService;
import com.victoria.fargutu.unibook.service.course.CourseService;
import com.victoria.fargutu.unibook.service.faculty.FacultyService;
import com.victoria.fargutu.unibook.service.free_option.cell.FreeOptionCellService;
import com.victoria.fargutu.unibook.service.group.StudentsGroupService;
import com.victoria.fargutu.unibook.service.scheduleCell.ScheduleCellService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private AuthService authService;
    private AuthManager authManager;
    private UserService userService;
    private ClassroomService classroomService;
    private CourseService courseService;
    private StudentsGroupService studentsGroupService;
    private ScheduleCellService scheduleCellService;
    private FacultyService facultyService;
    private FreeOptionCellService freeOptionCellService;

    @Autowired
    IndexController(AuthService authService, AuthManager authManager, UserService userService, ClassroomService classroomService, CourseService courseService, StudentsGroupService studentsGroupService, ScheduleCellService scheduleCellService, FacultyService facultyService, FreeOptionCellService freeOptionCellService) {
        this.authService = authService;
        this.authManager = authManager;
        this.userService = userService;
        this.classroomService = classroomService;
        this.courseService = courseService;
        this.studentsGroupService = studentsGroupService;
        this.scheduleCellService = scheduleCellService;
        this.facultyService = facultyService;
        this.freeOptionCellService = freeOptionCellService;
    }

    @HasRole(UserRole.USER)
    @GetMapping
    public String openIndexPage(ModelMap modelMap) {
        User user = authManager.getLoggedInUser();
        modelMap.put("loggedUser", user);

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
        Semester[] semesters = Semester.values();
        List<Faculty> faculties = facultyService.getAllFaculties();
        modelMap.put("faculties", faculties);
        modelMap.put("semesters", semesters);
        modelMap.put("classrooms", classrooms);
        modelMap.put("courses", courses);
        modelMap.put("users", users);
        modelMap.put("weekTypes", weekTypes);
        modelMap.put("days", days);
        modelMap.put("hours", hours);
        modelMap.put("studentsGroups", studentsGroups);
        modelMap.put("subgroups", subgroups);

        return "index/index";
    }

    @HasRole(UserRole.USER)
    @RequestMapping(value = "/scheduleCell", method = RequestMethod.POST)
    public String createScheduleCell(@ModelAttribute ScheduleMap scheduleMap) {
        scheduleCellService.createScheduleCell(scheduleMap);
        freeOptionCellService.addFreeOptioncells();
        return "redirect:/";
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {
        authService.logout();
        return "redirect:/";
    }

}

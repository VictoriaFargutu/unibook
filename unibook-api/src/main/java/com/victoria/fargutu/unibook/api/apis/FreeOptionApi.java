package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.classroom.Classroom;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import com.victoria.fargutu.unibook.service.free_option.FreeOptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/freeOptions")
public class FreeOptionApi {
    private FreeOptionService freeOptionService;

    public FreeOptionApi(FreeOptionService freeOptionService) {
        this.freeOptionService = freeOptionService;
    }

    @GetMapping("/classrooms")
    public List<FreeOption> getAllFreeOptionsByClassroom(@RequestBody Classroom classroom) {
        return freeOptionService.getAllFreeOptionsByClassroom(classroom);
    }
}

package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import com.victoria.fargutu.unibook.service.free_option.FreeOptionService;
import com.victoria.fargutu.unibook.service.free_option.cell.FreeOptionCellService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/freeOptions")
public class FreeOptionApi {
    private FreeOptionService freeOptionService;
    private FreeOptionCellService freeOptionCellService;

    public FreeOptionApi(FreeOptionService freeOptionService, FreeOptionCellService freeOptionCellService) {
        this.freeOptionService = freeOptionService;
        this.freeOptionCellService = freeOptionCellService;
    }

    @GetMapping("/classrooms")
    public List<FreeOption> getAllFreeOptionsByClassroom(@RequestParam("classroomId") Long classroomId) {
       // freeOptionCellService.addFreeOptioncells();
        return freeOptionService.getAllFreeOptionsByClassroom(classroomId);
    }
}

package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.Filter;
import com.victoria.fargutu.unibook.repository.model.free_option.FreeOption;
import com.victoria.fargutu.unibook.service.free_option.FreeOptionService;
import com.victoria.fargutu.unibook.service.free_option.cell.FreeOptionCellService;
import com.victoria.fargutu.unibook.service.security.HasRole;
import org.springframework.web.bind.annotation.*;

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

    @HasRole(UserRole.USER)
    @GetMapping("/classrooms")
    public List<FreeOption> getAllFreeOptionsByClassroom(@RequestParam("classroomId") Long classroomId) {
//        freeOptionCellService.addFreeOptioncells();
         return freeOptionService.getAllFreeOptionsByClassroom(classroomId);
    }

    @HasRole(UserRole.USER)
    @PostMapping("/filters")
    public List<FreeOption> getAllByFilters(@RequestBody Filter filter) {
//        List<FreeOption> freeOptions = new ArrayList<>();
        List<FreeOption> freeOptions = freeOptionService.getAllFreeOptionsByFilter(filter);
        return freeOptions;
    }

    @HasRole(UserRole.USER)
    @GetMapping("/all")
    public List<FreeOption> getAll() {
        return freeOptionService.getAllFreeOptions();
    }

}

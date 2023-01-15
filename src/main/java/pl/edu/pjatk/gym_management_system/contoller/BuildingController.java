package pl.edu.pjatk.gym_management_system.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.gym_management_system.model.Building;
import pl.edu.pjatk.gym_management_system.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {

    BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings(){
        return ResponseEntity.ok(buildingService.getAllBuildings());
    }

    @PostMapping
    public ResponseEntity<Building> createBuilding(@RequestBody Building building){
        return ResponseEntity.ok(buildingService.createBuilding(building));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuidlingById(@PathVariable("id")Long id){
        return ResponseEntity.ok(buildingService.getBuildingById(id));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteBuildingById(@RequestParam("id") Long id){
        buildingService.deleteBuildingById(id);
        return ResponseEntity.ok("ok");
    }




}

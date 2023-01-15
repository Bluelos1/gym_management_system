package pl.edu.pjatk.gym_management_system.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.gym_management_system.model.Equipment;
import pl.edu.pjatk.gym_management_system.model.enums.EquipmentType;
import pl.edu.pjatk.gym_management_system.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment(){
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment){
        return ResponseEntity.ok(equipmentService.createEquipment(equipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Equipment> getEquipmentByIdRequestParam(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEquipmentById(@RequestParam("id") Long id){
        equipmentService.deleteEquipmentById(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/type")
    public ResponseEntity<List<Equipment>> findEquipmentByBookType(@RequestParam(name = "type") EquipmentType equipmentType){
        return ResponseEntity.ok(equipmentService.findEquipmentByEquipmentType(equipmentType));
    }

}



package pl.edu.pjatk.gym_management_system.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.gym_management_system.model.Trainer;
import pl.edu.pjatk.gym_management_system.service.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers(){
        return ResponseEntity.ok(trainerService.getAllTrainer());
    }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer){
        return ResponseEntity.ok(trainerService.createTrainer(trainer));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Trainer> getTrainerByIdRequestParam(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTrainerById(@PathVariable("id") Long id){
        trainerService.deleteTrainerById(id);
        return ResponseEntity.ok("ok");
    }

}


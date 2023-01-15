package pl.edu.pjatk.gym_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.gym_management_system.model.Client;
import pl.edu.pjatk.gym_management_system.model.Trainer;
import pl.edu.pjatk.gym_management_system.repository.ClientRepository;
import pl.edu.pjatk.gym_management_system.repository.TrainerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    TrainerService(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }

    public Trainer createTrainer(Trainer trainer){
        return trainerRepository.save(trainer);
    }

    public Trainer findByNameAndLastName(String firstName, String lastName){
        Optional<Trainer> trainer = trainerRepository.findTrainerByFirstNameAndLastName(firstName, lastName);
        if(trainer.isPresent()){
            return trainer.get();
        }
        throw new IllegalArgumentException();
    }


    public List<Trainer> getAllTrainer(){
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Long id){
        return trainerRepository.getReferenceById(id);
    }

    public void deleteTrainerById(Long id){
        trainerRepository.deleteById(id);
    }


}

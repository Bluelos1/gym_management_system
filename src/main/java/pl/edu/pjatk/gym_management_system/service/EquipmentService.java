package pl.edu.pjatk.gym_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.gym_management_system.model.Client;
import pl.edu.pjatk.gym_management_system.model.Equipment;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.model.enums.EquipmentType;
import pl.edu.pjatk.gym_management_system.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    @Autowired
    EquipmentService(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment createEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id){
        Optional<Equipment> a = equipmentRepository.findById(id);
        if (a.isPresent()){
            return a.get();
        }
        throw new IllegalArgumentException();
    }

    public void deleteEquipmentById(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> findEquipmentByEquipmentType(EquipmentType equipmentType) {
        return equipmentRepository.findEquipmentByEquipmentType(equipmentType);
    }
}

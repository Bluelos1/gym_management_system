package pl.edu.pjatk.gym_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.gym_management_system.model.Equipment;
import pl.edu.pjatk.gym_management_system.model.enums.EquipmentType;

import java.util.List;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    List<Equipment> findEquipmentByEquipmentType(EquipmentType equipmentType);

}

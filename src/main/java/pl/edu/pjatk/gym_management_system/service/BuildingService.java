package pl.edu.pjatk.gym_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.gym_management_system.model.Building;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.repository.BuildingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository){
    this.buildingRepository = buildingRepository;
  }
    public List<Building> getAllBuildings(){
    return buildingRepository.findAll();
  }

  public Building createBuilding(Building building) {
    return buildingRepository.save(building);
  }

  public Building getBuildingById(Long id) {
    {
      Optional<Building> a = buildingRepository.findById(id);
      if (a.isPresent()) {
        return a.get();
      }
      throw new IllegalArgumentException();
    }
  }

  public void deleteBuildingById(Long id){
    buildingRepository.deleteById(id);
  }

}

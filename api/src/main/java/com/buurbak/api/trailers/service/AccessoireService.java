package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.AccessoireNameDTO;
import com.buurbak.api.trailers.exeption.TrailerAccessoireNotFoundException;
import com.buurbak.api.trailers.model.Accessoire;
import com.buurbak.api.trailers.repository.AccessoireRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccessoireService {
    private AccessoireRepository accessoireRepository;
//    public void saveAccessoire(AccessoireDTO accessoireDTO) {
//        //TODO add null function on accessoirName
////        boolean accesoireNameExists = accessoireRepository.exists(accessoireDTO.getAccessoireName());
////        if(accesoireNameExists == false){
////            accessoireRepository.save(new Accessoire(accessoireDTO.getAccessoireName()));
////        }
//    }

    public void saveAccessoire(AccessoireNameDTO accessoireNameDTO) {
        accessoireRepository.save(new Accessoire(accessoireNameDTO.getName()));
    }

    public List<AccessoireNameDTO> getAllAccossoire(){
        return accessoireRepository.findAllNames();
    }

    public void deleteAccessoire(String identifier) throws TrailerAccessoireNotFoundException {
            boolean exists =  accessoireRepository.existsById(identifier);
            if(!exists){
                throw new TrailerAccessoireNotFoundException("Trailer accesoire by id: " + identifier + "not found!");
            }
            accessoireRepository.deleteById(identifier);
    }
}

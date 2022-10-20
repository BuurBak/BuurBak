package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.AccessoireNameDTO;
import com.buurbak.api.trailers.exeption.TrailerAccessoireAlreadyExistsException;
import com.buurbak.api.trailers.exeption.TrailerAccessoireNotFoundException;
import com.buurbak.api.trailers.model.Accessoire;
import com.buurbak.api.trailers.repository.AccessoireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccessoireService {
    private final AccessoireRepository accessoireRepository;
//    public void saveAccessoire(AccessoireDTO accessoireDTO) {
//        //TODO add null function on accessoirName
////        boolean accesoireNameExists = accessoireRepository.exists(accessoireDTO.getAccessoireName());
////        if(accesoireNameExists == false){
////            accessoireRepository.save(new Accessoire(accessoireDTO.getAccessoireName()));
////        }
//    }

    public void saveAccessoire(AccessoireNameDTO accessoireNameDTO) throws TrailerAccessoireAlreadyExistsException{
        boolean exists =  accessoireRepository.existsById(accessoireNameDTO.getName());
        if(exists){
            throw new TrailerAccessoireAlreadyExistsException("Trailer accesoire by id: " + accessoireNameDTO.getName() + " already exists!");
        }
        accessoireRepository.save(new Accessoire(accessoireNameDTO.getName()));
    }

    public List<AccessoireNameDTO> getAllAccossoire(){
        return accessoireRepository.findAllNames();
    }

    public void deleteAccessoire(String identifier) throws TrailerAccessoireNotFoundException {
            boolean exists =  accessoireRepository.existsById(identifier);
            if(!exists){
                throw new TrailerAccessoireNotFoundException("Trailer accesoire by id: " + identifier + " not found!");
            }
            accessoireRepository.deleteById(identifier);
    }
}

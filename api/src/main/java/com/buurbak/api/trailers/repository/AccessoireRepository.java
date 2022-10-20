package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.dto.AccessoireNameDTO;
import com.buurbak.api.trailers.model.Accessoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoireRepository extends JpaRepository<Accessoire, String> {
//    @Query("DELETE FROM TrailerOfferAccessoire t WHERE t.trailerId = :trailerId AND t.accessoireName = :accessoireName")
//    void deleteAccessoire(UUID trailerId, String accessoireName);

//    boolean exists(String accessoireName);
//    void deleteByIdAndName(UUID trailerID, String accessoireName);

    @Query("SELECT new com.buurbak.api.trailers.dto.AccessoireNameDTO(a.name) FROM Accessoire a")
    List<AccessoireNameDTO> findAllNames();
}

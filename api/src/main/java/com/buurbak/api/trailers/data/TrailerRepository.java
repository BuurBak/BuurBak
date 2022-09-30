package com.buurbak.api.trailers.data;

import com.buurbak.api.trailers.domain.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrailerRepository<T extends Trailer> extends JpaRepository<T, UUID> {
//    @Query("SELECT t, TrailerType.name FROM Trailer t JOIN t.trailerType TrailerType")
    @Query("SELECT t FROM Trailer t")
    List<Trailer> getAllTrailers();

    @Query("SELECT t FROM Trailer t WHERE t.id = ?1")
    Trailer getOneTrailer(UUID id);
}

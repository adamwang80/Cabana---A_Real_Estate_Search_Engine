package com.aic.cabana.spring.files.excel.repository;

import com.aic.cabana.spring.files.excel.model.Cabana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CabanaRepository extends JpaRepository<Cabana, Long> {

    @Query("SELECT c FROM Cabana c WHERE (c.latitude) > (:startLat) and (c.latitude) <= (:endLat) and (c.longitude) > (:startLng) and (c.longitude) <= (:endLng)")
    List<Cabana> retrieveByCoordinateRange(@Param("startLat") Double startLat,
                                           @Param("startLng") Double startLng,
                                           @Param("endLat") Double endLat,
                                           @Param("endLng") Double endLng);

    @Query("SELECT t FROM Cabana t")
    List<Cabana> findAll();

    /*@Query("SELECT c FROM Cabana c WHERE (c.latitude) > (:startLat) and (c.latitude) <= (:endLat) and (c.longitude) > (:startLng) and (c.longitude) <= (:endLng)")
    List<Cabana> retrieveByRoomType(@Param("startLat") Double startLa);*/
}

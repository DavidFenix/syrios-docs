package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Oferta;

import java.util.List;

@Dao
public interface OfertaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Oferta o);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Oferta> ofertas);

    @Query("SELECT * FROM oferta WHERE professor_id = :professorId")
    List<Oferta> findByProfessor(Long professorId);

    @Query("SELECT * FROM oferta WHERE school_id = :schoolId")
    List<Oferta> findBySchool(Long schoolId);

    @Query("SELECT * FROM oferta WHERE id = :id")
    Oferta findById(Long id);

    @Query("DELETE FROM oferta")
    void clearTable();
}

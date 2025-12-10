package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.ModeloMotivo;

import java.util.List;

@Dao
public interface ModeloMotivoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModeloMotivo m);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ModeloMotivo> motivos);

    @Query("SELECT * FROM modelo_motivo WHERE school_id = :schoolId")
    List<ModeloMotivo> findBySchool(Long schoolId);

    @Query("DELETE FROM modelo_motivo")
    void clearTable();
}

package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.OcorrenciaMotivo;

import java.util.List;

@Dao
public interface OcorrenciaMotivoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OcorrenciaMotivo m);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<OcorrenciaMotivo> motivos);

    @Query("SELECT * FROM ocorrencia_motivo WHERE ocorrencia_id = :ocorrenciaId")
    List<OcorrenciaMotivo> findByOcorrencia(Long ocorrenciaId);

    @Query("DELETE FROM ocorrencia_motivo")
    void clearTable();
}

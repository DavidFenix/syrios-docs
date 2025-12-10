package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import br.com.syrios.mobile.data.entities.Ocorrencia;

import java.util.List;

@Dao
public interface OcorrenciaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ocorrencia o);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Ocorrencia> ocorrencias);

    @Update
    void update(Ocorrencia o);

    @Query("SELECT * FROM ocorrencia WHERE professor_id = :professorId")
    List<Ocorrencia> findByProfessor(Long professorId);

    @Query("SELECT * FROM ocorrencia WHERE aluno_id = :alunoId")
    List<Ocorrencia> findByAluno(Long alunoId);

    @Query("SELECT * FROM ocorrencia WHERE sync = 0")
    List<Ocorrencia> findPendingSync();

    @Query("DELETE FROM ocorrencia")
    void clearTable();
}

package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Aluno a);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Aluno> alunos);

    @Query("SELECT * FROM aluno WHERE school_id = :schoolId")
    List<Aluno> findBySchool(Long schoolId);

    @Query("SELECT * FROM aluno WHERE id = :id")
    Aluno findById(Long id);

    @Query("SELECT * FROM aluno WHERE matricula = :matricula")
    Aluno findByMatricula(String matricula);

    @Query("DELETE FROM aluno")
    void clearTable();
}

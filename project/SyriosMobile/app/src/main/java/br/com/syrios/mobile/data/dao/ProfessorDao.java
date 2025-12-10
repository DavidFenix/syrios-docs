package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Professor;

import java.util.List;

@Dao
public interface ProfessorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Professor professor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Professor> professores);

    @Query("SELECT * FROM professor WHERE usuario_id = :usuarioId")
    List<Professor> findByUsuario(Long usuarioId);

    @Query("SELECT * FROM professor WHERE school_id = :schoolId")
    List<Professor> findBySchool(Long schoolId);

    @Query("DELETE FROM professor")
    void clearTable();
}

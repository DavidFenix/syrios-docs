package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Turma;

import java.util.List;

@Dao
public interface TurmaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Turma t);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Turma> turmas);

    @Query("SELECT * FROM turma WHERE school_id = :schoolId")
    List<Turma> findBySchool(Long schoolId);

    @Query("SELECT * FROM turma WHERE id = :id")
    Turma findById(Long id);

    @Query("DELETE FROM turma")
    void clearTable();
}

package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Disciplina;

import java.util.List;

@Dao
public interface DisciplinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Disciplina d);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Disciplina> disciplinas);

    @Query("SELECT * FROM disciplina WHERE school_id = :schoolId")
    List<Disciplina> findBySchool(Long schoolId);

    @Query("SELECT * FROM disciplina WHERE id = :id")
    Disciplina findById(Long id);

    @Query("DELETE FROM disciplina")
    void clearTable();
}

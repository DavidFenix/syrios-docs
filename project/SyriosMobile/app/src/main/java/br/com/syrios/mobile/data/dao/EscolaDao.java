package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Escola;

import java.util.List;

@Dao
public interface EscolaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Escola e);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Escola> escolas);

    @Query("SELECT * FROM escola WHERE id = :id")
    Escola findById(Long id);

    @Query("SELECT * FROM escola")
    List<Escola> getAll();

    @Query("DELETE FROM escola")
    void clearTable();
}

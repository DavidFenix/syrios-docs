package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Enturmacao;

import java.util.List;

@Dao
public interface EnturmacaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Enturmacao e);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Enturmacao> enturmacoes);

    @Query("SELECT * FROM enturmacao WHERE aluno_id = :alunoId")
    List<Enturmacao> findByAluno(Long alunoId);

    @Query("SELECT * FROM enturmacao WHERE turma_id = :turmaId")
    List<Enturmacao> findByTurma(Long turmaId);

    @Query("DELETE FROM enturmacao")
    void clearTable();
}

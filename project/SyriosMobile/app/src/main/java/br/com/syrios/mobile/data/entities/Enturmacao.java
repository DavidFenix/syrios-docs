package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "enturmacao",
        indices = {
                @Index({"school_id"}),
                @Index({"aluno_id"}),
                @Index({"turma_id"})
        }
)
public class Enturmacao {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    @ColumnInfo(name = "ano_letivo")
    public int anoLetivo;

    public boolean vigente;

    @ColumnInfo(name = "aluno_id")
    public Long alunoId;

    @ColumnInfo(name = "turma_id")
    public Long turmaId;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Enturmacao() {}
}

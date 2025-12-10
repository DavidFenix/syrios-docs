package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "oferta",
        indices = {
                @Index({"school_id"}),
                @Index({"professor_id"}),
                @Index({"turma_id"}),
                @Index({"disciplina_id"})
        }
)
public class Oferta {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    @ColumnInfo(name = "ano_letivo")
    public int anoLetivo;

    public boolean vigente;

    @ColumnInfo(name = "turma_id")
    public Long turmaId;

    @ColumnInfo(name = "disciplina_id")
    public Long disciplinaId;

    @ColumnInfo(name = "professor_id")
    public Long professorId;

    public int status;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Oferta() {}
}

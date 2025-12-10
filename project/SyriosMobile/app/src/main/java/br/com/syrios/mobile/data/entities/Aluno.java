package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "aluno",
        indices = {
                @Index({"school_id"}),
                @Index({"matricula"})
        }
)
public class Aluno {

    @PrimaryKey
    public Long id;

    public String matricula;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Aluno() {}
}

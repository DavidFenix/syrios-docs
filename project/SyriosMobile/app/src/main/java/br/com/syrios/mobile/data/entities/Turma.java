package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "turma",
        indices = {
                @Index(value = {"school_id"}),
                @Index(value = {"serie_turma"})
        }
)
public class Turma {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    @ColumnInfo(name = "serie_turma")
    public String serieTurma;

    public String turno;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Turma() {}
}

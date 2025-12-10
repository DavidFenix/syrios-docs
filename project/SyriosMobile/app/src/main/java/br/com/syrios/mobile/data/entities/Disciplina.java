package br.com.syrios.mobile.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Representa uma disciplina ofertada por uma escola.
 * Exemplo: MAT, BIO, ART...
 */
@Entity(
        tableName = "disciplina",
        indices = {
                @Index(value = {"school_id"}),
                @Index(value = {"abr", "school_id"}, unique = false)
        }
)
public class Disciplina {

    @PrimaryKey
    @NonNull
    public Long id;                // Igual ao servidor (syrios_disciplina.id)

    @ColumnInfo(name = "school_id")
    public Long schoolId;          // Escola da disciplina

    @ColumnInfo(name = "abr")
    @NonNull
    public String abreviacao;      // Ex.: "MAT"

    @ColumnInfo(name = "descr_d")
    public String descricao;       // Nome completo: "Matemática"

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;    // Timestamp da última sincronização

    public Disciplina() {}
}

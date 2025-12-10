package br.com.syrios.mobile.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Representa o vínculo do usuário como professor em uma escola.
 * Um usuário pode ser professor em múltiplas escolas.
 */
@Entity(
        tableName = "professor",
        indices = {
                @Index(value = {"usuario_id"}),
                @Index(value = {"school_id"}),
                @Index(value = {"usuario_id", "school_id"}, unique = true)
        }
)
public class Professor {

    @PrimaryKey
    @NonNull
    public Long id;                // Igual ao servidor (syrios_professor.id)

    @ColumnInfo(name = "usuario_id")
    public Long usuarioId;         // ID do usuário (syrios_usuario.id)

    @ColumnInfo(name = "school_id")
    public Long schoolId;          // Escola deste vínculo

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;    // Timestamp da última sincronização (opcional)

    public Professor() {}
}

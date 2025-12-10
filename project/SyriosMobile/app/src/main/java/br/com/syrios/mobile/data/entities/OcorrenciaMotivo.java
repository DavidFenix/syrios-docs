package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "ocorrencia_motivo",
        indices = {
                @Index({"ocorrencia_id"}),
                @Index({"modelo_motivo_id"})
        }
)
public class OcorrenciaMotivo {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "ocorrencia_id")
    public Long ocorrenciaId;

    @ColumnInfo(name = "modelo_motivo_id")
    public Long modeloMotivoId;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public OcorrenciaMotivo() {}
}

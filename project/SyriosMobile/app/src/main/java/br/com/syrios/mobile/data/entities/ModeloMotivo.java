package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "modelo_motivo")
public class ModeloMotivo {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    public String descricao;
    public String categoria;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public ModeloMotivo() {}
}

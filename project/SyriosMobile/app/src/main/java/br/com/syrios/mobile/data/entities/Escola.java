package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "escola")
public class Escola {

    @PrimaryKey
    public Long id;

    public String nome;

    @ColumnInfo(name = "frase_efeito")
    public String fraseEfeito;

    @ColumnInfo(name = "logo_path")
    public String logoPath;

    public String cidade;
    public String estado;

    @ColumnInfo(name = "secretaria_id")
    public Long secretariaId;

    @ColumnInfo(name = "is_master")
    public boolean isMaster;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Escola() {}
}

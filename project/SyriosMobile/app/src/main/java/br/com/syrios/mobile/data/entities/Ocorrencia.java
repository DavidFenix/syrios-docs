package br.com.syrios.mobile.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "ocorrencia",
        indices = {
                @Index({"school_id"}),
                @Index({"aluno_id"}),
                @Index({"professor_id"}),
                @Index({"oferta_id"})
        }
)
public class Ocorrencia {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "school_id")
    public Long schoolId;

    @ColumnInfo(name = "ano_letivo")
    public int anoLetivo;

    public boolean vigente;

    @ColumnInfo(name = "aluno_id")
    public Long alunoId;

    @ColumnInfo(name = "professor_id")
    public Long professorId;

    @ColumnInfo(name = "oferta_id")
    public Long ofertaId;

    public String descricao;
    public String local;
    public String atitude;

    @ColumnInfo(name = "outra_atitude")
    public String outraAtitude;

    public String comportamento;
    public String sugestao;

    public int status;

    @ColumnInfo(name = "nivel_gravidade")
    public int nivelGravidade;

    public int sync;  // 0 = pendente, 1 = sincronizado

    @ColumnInfo(name = "recebido_em")
    public Long recebidoEm;

    @ColumnInfo(name = "encaminhamentos")
    public String encaminhamentos;

    @ColumnInfo(name = "sincronizado_em")
    public Long sincronizadoEm;

    public Ocorrencia() {}
}

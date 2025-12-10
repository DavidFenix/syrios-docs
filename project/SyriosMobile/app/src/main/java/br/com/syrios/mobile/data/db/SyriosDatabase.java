package br.com.syrios.mobile.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// ENTITIES
import br.com.syrios.mobile.data.entities.Usuario;
import br.com.syrios.mobile.data.entities.Professor;
import br.com.syrios.mobile.data.entities.Escola;
import br.com.syrios.mobile.data.entities.Turma;
import br.com.syrios.mobile.data.entities.Disciplina;
import br.com.syrios.mobile.data.entities.Oferta;
import br.com.syrios.mobile.data.entities.Aluno;
import br.com.syrios.mobile.data.entities.Enturmacao;
import br.com.syrios.mobile.data.entities.ModeloMotivo;
import br.com.syrios.mobile.data.entities.Ocorrencia;
import br.com.syrios.mobile.data.entities.OcorrenciaMotivo;

// DAOs
import br.com.syrios.mobile.data.dao.UsuarioDao;
import br.com.syrios.mobile.data.dao.ProfessorDao;
import br.com.syrios.mobile.data.dao.EscolaDao;
import br.com.syrios.mobile.data.dao.TurmaDao;
import br.com.syrios.mobile.data.dao.DisciplinaDao;
import br.com.syrios.mobile.data.dao.OfertaDao;
import br.com.syrios.mobile.data.dao.AlunoDao;
import br.com.syrios.mobile.data.dao.EnturmacaoDao;
import br.com.syrios.mobile.data.dao.ModeloMotivoDao;
import br.com.syrios.mobile.data.dao.OcorrenciaDao;
import br.com.syrios.mobile.data.dao.OcorrenciaMotivoDao;

@Database(
        entities = {
                Usuario.class,
                Professor.class,
                Escola.class,
                Turma.class,
                Disciplina.class,
                Oferta.class,
                Aluno.class,
                Enturmacao.class,
                ModeloMotivo.class,
                Ocorrencia.class,
                OcorrenciaMotivo.class
        },
        version = 1,
        exportSchema = false
)
public abstract class SyriosDatabase extends RoomDatabase {

    // -------------------------
    // DAOs expostos
    // -------------------------
    public abstract UsuarioDao usuarioDao();
    public abstract ProfessorDao professorDao();
    public abstract EscolaDao escolaDao();
    public abstract TurmaDao turmaDao();
    public abstract DisciplinaDao disciplinaDao();
    public abstract OfertaDao ofertaDao();
    public abstract AlunoDao alunoDao();
    public abstract EnturmacaoDao enturmacaoDao();
    public abstract ModeloMotivoDao modeloMotivoDao();
    public abstract OcorrenciaDao ocorrenciaDao();
    public abstract OcorrenciaMotivoDao ocorrenciaMotivoDao();

    // -------------------------
    // Singleton (instância única)
    // -------------------------
    private static volatile SyriosDatabase INSTANCE;

    public static SyriosDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SyriosDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    SyriosDatabase.class,
                                    "syrios.db"
                            )
                            .fallbackToDestructiveMigration()  // se versão mudar e não houver migração
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

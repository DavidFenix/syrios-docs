package br.com.syrios.mobile.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import br.com.syrios.mobile.data.dao.AlunoDao;
import br.com.syrios.mobile.data.dao.AlunoDao_Impl;
import br.com.syrios.mobile.data.dao.DisciplinaDao;
import br.com.syrios.mobile.data.dao.DisciplinaDao_Impl;
import br.com.syrios.mobile.data.dao.EnturmacaoDao;
import br.com.syrios.mobile.data.dao.EnturmacaoDao_Impl;
import br.com.syrios.mobile.data.dao.EscolaDao;
import br.com.syrios.mobile.data.dao.EscolaDao_Impl;
import br.com.syrios.mobile.data.dao.ModeloMotivoDao;
import br.com.syrios.mobile.data.dao.ModeloMotivoDao_Impl;
import br.com.syrios.mobile.data.dao.OcorrenciaDao;
import br.com.syrios.mobile.data.dao.OcorrenciaDao_Impl;
import br.com.syrios.mobile.data.dao.OcorrenciaMotivoDao;
import br.com.syrios.mobile.data.dao.OcorrenciaMotivoDao_Impl;
import br.com.syrios.mobile.data.dao.OfertaDao;
import br.com.syrios.mobile.data.dao.OfertaDao_Impl;
import br.com.syrios.mobile.data.dao.ProfessorDao;
import br.com.syrios.mobile.data.dao.ProfessorDao_Impl;
import br.com.syrios.mobile.data.dao.TurmaDao;
import br.com.syrios.mobile.data.dao.TurmaDao_Impl;
import br.com.syrios.mobile.data.dao.UsuarioDao;
import br.com.syrios.mobile.data.dao.UsuarioDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SyriosDatabase_Impl extends SyriosDatabase {
  private volatile UsuarioDao _usuarioDao;

  private volatile ProfessorDao _professorDao;

  private volatile EscolaDao _escolaDao;

  private volatile TurmaDao _turmaDao;

  private volatile DisciplinaDao _disciplinaDao;

  private volatile OfertaDao _ofertaDao;

  private volatile AlunoDao _alunoDao;

  private volatile EnturmacaoDao _enturmacaoDao;

  private volatile ModeloMotivoDao _modeloMotivoDao;

  private volatile OcorrenciaDao _ocorrenciaDao;

  private volatile OcorrenciaMotivoDao _ocorrenciaMotivoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuario` (`id` INTEGER NOT NULL, `school_id` INTEGER, `cpf` TEXT NOT NULL, `nome_u` TEXT, `nascimento` TEXT, `status` INTEGER, `senha_local_hash` TEXT, `ultimo_login` INTEGER, `foto_url` TEXT, `roles_json` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usuario_cpf_school_id` ON `usuario` (`cpf`, `school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_usuario_school_id` ON `usuario` (`school_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `professor` (`id` INTEGER NOT NULL, `usuario_id` INTEGER, `school_id` INTEGER, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_professor_usuario_id` ON `professor` (`usuario_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_professor_school_id` ON `professor` (`school_id`)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_professor_usuario_id_school_id` ON `professor` (`usuario_id`, `school_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `escola` (`id` INTEGER, `nome` TEXT, `frase_efeito` TEXT, `logo_path` TEXT, `cidade` TEXT, `estado` TEXT, `secretaria_id` INTEGER, `is_master` INTEGER NOT NULL, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `turma` (`id` INTEGER, `school_id` INTEGER, `serie_turma` TEXT, `turno` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_turma_school_id` ON `turma` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_turma_serie_turma` ON `turma` (`serie_turma`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `disciplina` (`id` INTEGER NOT NULL, `school_id` INTEGER, `abr` TEXT NOT NULL, `descr_d` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_disciplina_school_id` ON `disciplina` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_disciplina_abr_school_id` ON `disciplina` (`abr`, `school_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `oferta` (`id` INTEGER, `school_id` INTEGER, `ano_letivo` INTEGER NOT NULL, `vigente` INTEGER NOT NULL, `turma_id` INTEGER, `disciplina_id` INTEGER, `professor_id` INTEGER, `status` INTEGER NOT NULL, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_oferta_school_id` ON `oferta` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_oferta_professor_id` ON `oferta` (`professor_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_oferta_turma_id` ON `oferta` (`turma_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_oferta_disciplina_id` ON `oferta` (`disciplina_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `aluno` (`id` INTEGER, `matricula` TEXT, `school_id` INTEGER, `nome` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_aluno_school_id` ON `aluno` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_aluno_matricula` ON `aluno` (`matricula`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `enturmacao` (`id` INTEGER, `school_id` INTEGER, `ano_letivo` INTEGER NOT NULL, `vigente` INTEGER NOT NULL, `aluno_id` INTEGER, `turma_id` INTEGER, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_enturmacao_school_id` ON `enturmacao` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_enturmacao_aluno_id` ON `enturmacao` (`aluno_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_enturmacao_turma_id` ON `enturmacao` (`turma_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `modelo_motivo` (`id` INTEGER, `school_id` INTEGER, `descricao` TEXT, `categoria` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ocorrencia` (`id` INTEGER, `school_id` INTEGER, `ano_letivo` INTEGER NOT NULL, `vigente` INTEGER NOT NULL, `aluno_id` INTEGER, `professor_id` INTEGER, `oferta_id` INTEGER, `descricao` TEXT, `local` TEXT, `atitude` TEXT, `outra_atitude` TEXT, `comportamento` TEXT, `sugestao` TEXT, `status` INTEGER NOT NULL, `nivel_gravidade` INTEGER NOT NULL, `sync` INTEGER NOT NULL, `recebido_em` INTEGER, `encaminhamentos` TEXT, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_school_id` ON `ocorrencia` (`school_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_aluno_id` ON `ocorrencia` (`aluno_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_professor_id` ON `ocorrencia` (`professor_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_oferta_id` ON `ocorrencia` (`oferta_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ocorrencia_motivo` (`id` INTEGER, `ocorrencia_id` INTEGER, `modelo_motivo_id` INTEGER, `sincronizado_em` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_motivo_ocorrencia_id` ON `ocorrencia_motivo` (`ocorrencia_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ocorrencia_motivo_modelo_motivo_id` ON `ocorrencia_motivo` (`modelo_motivo_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f93fb10d02f5fe62305ea45f0e22b8fe')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuario`");
        db.execSQL("DROP TABLE IF EXISTS `professor`");
        db.execSQL("DROP TABLE IF EXISTS `escola`");
        db.execSQL("DROP TABLE IF EXISTS `turma`");
        db.execSQL("DROP TABLE IF EXISTS `disciplina`");
        db.execSQL("DROP TABLE IF EXISTS `oferta`");
        db.execSQL("DROP TABLE IF EXISTS `aluno`");
        db.execSQL("DROP TABLE IF EXISTS `enturmacao`");
        db.execSQL("DROP TABLE IF EXISTS `modelo_motivo`");
        db.execSQL("DROP TABLE IF EXISTS `ocorrencia`");
        db.execSQL("DROP TABLE IF EXISTS `ocorrencia_motivo`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuario = new HashMap<String, TableInfo.Column>(11);
        _columnsUsuario.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("cpf", new TableInfo.Column("cpf", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("nome_u", new TableInfo.Column("nome_u", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("nascimento", new TableInfo.Column("nascimento", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("status", new TableInfo.Column("status", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("senha_local_hash", new TableInfo.Column("senha_local_hash", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("ultimo_login", new TableInfo.Column("ultimo_login", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("foto_url", new TableInfo.Column("foto_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("roles_json", new TableInfo.Column("roles_json", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuario = new HashSet<TableInfo.Index>(2);
        _indicesUsuario.add(new TableInfo.Index("index_usuario_cpf_school_id", false, Arrays.asList("cpf", "school_id"), Arrays.asList("ASC", "ASC")));
        _indicesUsuario.add(new TableInfo.Index("index_usuario_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        final TableInfo _infoUsuario = new TableInfo("usuario", _columnsUsuario, _foreignKeysUsuario, _indicesUsuario);
        final TableInfo _existingUsuario = TableInfo.read(db, "usuario");
        if (!_infoUsuario.equals(_existingUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "usuario(br.com.syrios.mobile.data.entities.Usuario).\n"
                  + " Expected:\n" + _infoUsuario + "\n"
                  + " Found:\n" + _existingUsuario);
        }
        final HashMap<String, TableInfo.Column> _columnsProfessor = new HashMap<String, TableInfo.Column>(4);
        _columnsProfessor.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfessor.put("usuario_id", new TableInfo.Column("usuario_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfessor.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfessor.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfessor = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProfessor = new HashSet<TableInfo.Index>(3);
        _indicesProfessor.add(new TableInfo.Index("index_professor_usuario_id", false, Arrays.asList("usuario_id"), Arrays.asList("ASC")));
        _indicesProfessor.add(new TableInfo.Index("index_professor_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesProfessor.add(new TableInfo.Index("index_professor_usuario_id_school_id", true, Arrays.asList("usuario_id", "school_id"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoProfessor = new TableInfo("professor", _columnsProfessor, _foreignKeysProfessor, _indicesProfessor);
        final TableInfo _existingProfessor = TableInfo.read(db, "professor");
        if (!_infoProfessor.equals(_existingProfessor)) {
          return new RoomOpenHelper.ValidationResult(false, "professor(br.com.syrios.mobile.data.entities.Professor).\n"
                  + " Expected:\n" + _infoProfessor + "\n"
                  + " Found:\n" + _existingProfessor);
        }
        final HashMap<String, TableInfo.Column> _columnsEscola = new HashMap<String, TableInfo.Column>(9);
        _columnsEscola.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("nome", new TableInfo.Column("nome", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("frase_efeito", new TableInfo.Column("frase_efeito", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("logo_path", new TableInfo.Column("logo_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("cidade", new TableInfo.Column("cidade", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("estado", new TableInfo.Column("estado", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("secretaria_id", new TableInfo.Column("secretaria_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("is_master", new TableInfo.Column("is_master", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEscola.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEscola = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEscola = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEscola = new TableInfo("escola", _columnsEscola, _foreignKeysEscola, _indicesEscola);
        final TableInfo _existingEscola = TableInfo.read(db, "escola");
        if (!_infoEscola.equals(_existingEscola)) {
          return new RoomOpenHelper.ValidationResult(false, "escola(br.com.syrios.mobile.data.entities.Escola).\n"
                  + " Expected:\n" + _infoEscola + "\n"
                  + " Found:\n" + _existingEscola);
        }
        final HashMap<String, TableInfo.Column> _columnsTurma = new HashMap<String, TableInfo.Column>(5);
        _columnsTurma.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTurma.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTurma.put("serie_turma", new TableInfo.Column("serie_turma", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTurma.put("turno", new TableInfo.Column("turno", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTurma.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTurma = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTurma = new HashSet<TableInfo.Index>(2);
        _indicesTurma.add(new TableInfo.Index("index_turma_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesTurma.add(new TableInfo.Index("index_turma_serie_turma", false, Arrays.asList("serie_turma"), Arrays.asList("ASC")));
        final TableInfo _infoTurma = new TableInfo("turma", _columnsTurma, _foreignKeysTurma, _indicesTurma);
        final TableInfo _existingTurma = TableInfo.read(db, "turma");
        if (!_infoTurma.equals(_existingTurma)) {
          return new RoomOpenHelper.ValidationResult(false, "turma(br.com.syrios.mobile.data.entities.Turma).\n"
                  + " Expected:\n" + _infoTurma + "\n"
                  + " Found:\n" + _existingTurma);
        }
        final HashMap<String, TableInfo.Column> _columnsDisciplina = new HashMap<String, TableInfo.Column>(5);
        _columnsDisciplina.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisciplina.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisciplina.put("abr", new TableInfo.Column("abr", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisciplina.put("descr_d", new TableInfo.Column("descr_d", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDisciplina.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDisciplina = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDisciplina = new HashSet<TableInfo.Index>(2);
        _indicesDisciplina.add(new TableInfo.Index("index_disciplina_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesDisciplina.add(new TableInfo.Index("index_disciplina_abr_school_id", false, Arrays.asList("abr", "school_id"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoDisciplina = new TableInfo("disciplina", _columnsDisciplina, _foreignKeysDisciplina, _indicesDisciplina);
        final TableInfo _existingDisciplina = TableInfo.read(db, "disciplina");
        if (!_infoDisciplina.equals(_existingDisciplina)) {
          return new RoomOpenHelper.ValidationResult(false, "disciplina(br.com.syrios.mobile.data.entities.Disciplina).\n"
                  + " Expected:\n" + _infoDisciplina + "\n"
                  + " Found:\n" + _existingDisciplina);
        }
        final HashMap<String, TableInfo.Column> _columnsOferta = new HashMap<String, TableInfo.Column>(9);
        _columnsOferta.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("ano_letivo", new TableInfo.Column("ano_letivo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("vigente", new TableInfo.Column("vigente", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("turma_id", new TableInfo.Column("turma_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("disciplina_id", new TableInfo.Column("disciplina_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("professor_id", new TableInfo.Column("professor_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOferta.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOferta = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOferta = new HashSet<TableInfo.Index>(4);
        _indicesOferta.add(new TableInfo.Index("index_oferta_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesOferta.add(new TableInfo.Index("index_oferta_professor_id", false, Arrays.asList("professor_id"), Arrays.asList("ASC")));
        _indicesOferta.add(new TableInfo.Index("index_oferta_turma_id", false, Arrays.asList("turma_id"), Arrays.asList("ASC")));
        _indicesOferta.add(new TableInfo.Index("index_oferta_disciplina_id", false, Arrays.asList("disciplina_id"), Arrays.asList("ASC")));
        final TableInfo _infoOferta = new TableInfo("oferta", _columnsOferta, _foreignKeysOferta, _indicesOferta);
        final TableInfo _existingOferta = TableInfo.read(db, "oferta");
        if (!_infoOferta.equals(_existingOferta)) {
          return new RoomOpenHelper.ValidationResult(false, "oferta(br.com.syrios.mobile.data.entities.Oferta).\n"
                  + " Expected:\n" + _infoOferta + "\n"
                  + " Found:\n" + _existingOferta);
        }
        final HashMap<String, TableInfo.Column> _columnsAluno = new HashMap<String, TableInfo.Column>(5);
        _columnsAluno.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAluno.put("matricula", new TableInfo.Column("matricula", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAluno.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAluno.put("nome", new TableInfo.Column("nome", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAluno.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAluno = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAluno = new HashSet<TableInfo.Index>(2);
        _indicesAluno.add(new TableInfo.Index("index_aluno_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesAluno.add(new TableInfo.Index("index_aluno_matricula", false, Arrays.asList("matricula"), Arrays.asList("ASC")));
        final TableInfo _infoAluno = new TableInfo("aluno", _columnsAluno, _foreignKeysAluno, _indicesAluno);
        final TableInfo _existingAluno = TableInfo.read(db, "aluno");
        if (!_infoAluno.equals(_existingAluno)) {
          return new RoomOpenHelper.ValidationResult(false, "aluno(br.com.syrios.mobile.data.entities.Aluno).\n"
                  + " Expected:\n" + _infoAluno + "\n"
                  + " Found:\n" + _existingAluno);
        }
        final HashMap<String, TableInfo.Column> _columnsEnturmacao = new HashMap<String, TableInfo.Column>(7);
        _columnsEnturmacao.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("ano_letivo", new TableInfo.Column("ano_letivo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("vigente", new TableInfo.Column("vigente", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("aluno_id", new TableInfo.Column("aluno_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("turma_id", new TableInfo.Column("turma_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnturmacao.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEnturmacao = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEnturmacao = new HashSet<TableInfo.Index>(3);
        _indicesEnturmacao.add(new TableInfo.Index("index_enturmacao_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesEnturmacao.add(new TableInfo.Index("index_enturmacao_aluno_id", false, Arrays.asList("aluno_id"), Arrays.asList("ASC")));
        _indicesEnturmacao.add(new TableInfo.Index("index_enturmacao_turma_id", false, Arrays.asList("turma_id"), Arrays.asList("ASC")));
        final TableInfo _infoEnturmacao = new TableInfo("enturmacao", _columnsEnturmacao, _foreignKeysEnturmacao, _indicesEnturmacao);
        final TableInfo _existingEnturmacao = TableInfo.read(db, "enturmacao");
        if (!_infoEnturmacao.equals(_existingEnturmacao)) {
          return new RoomOpenHelper.ValidationResult(false, "enturmacao(br.com.syrios.mobile.data.entities.Enturmacao).\n"
                  + " Expected:\n" + _infoEnturmacao + "\n"
                  + " Found:\n" + _existingEnturmacao);
        }
        final HashMap<String, TableInfo.Column> _columnsModeloMotivo = new HashMap<String, TableInfo.Column>(5);
        _columnsModeloMotivo.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsModeloMotivo.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsModeloMotivo.put("descricao", new TableInfo.Column("descricao", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsModeloMotivo.put("categoria", new TableInfo.Column("categoria", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsModeloMotivo.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysModeloMotivo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesModeloMotivo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoModeloMotivo = new TableInfo("modelo_motivo", _columnsModeloMotivo, _foreignKeysModeloMotivo, _indicesModeloMotivo);
        final TableInfo _existingModeloMotivo = TableInfo.read(db, "modelo_motivo");
        if (!_infoModeloMotivo.equals(_existingModeloMotivo)) {
          return new RoomOpenHelper.ValidationResult(false, "modelo_motivo(br.com.syrios.mobile.data.entities.ModeloMotivo).\n"
                  + " Expected:\n" + _infoModeloMotivo + "\n"
                  + " Found:\n" + _existingModeloMotivo);
        }
        final HashMap<String, TableInfo.Column> _columnsOcorrencia = new HashMap<String, TableInfo.Column>(19);
        _columnsOcorrencia.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("school_id", new TableInfo.Column("school_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("ano_letivo", new TableInfo.Column("ano_letivo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("vigente", new TableInfo.Column("vigente", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("aluno_id", new TableInfo.Column("aluno_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("professor_id", new TableInfo.Column("professor_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("oferta_id", new TableInfo.Column("oferta_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("descricao", new TableInfo.Column("descricao", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("local", new TableInfo.Column("local", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("atitude", new TableInfo.Column("atitude", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("outra_atitude", new TableInfo.Column("outra_atitude", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("comportamento", new TableInfo.Column("comportamento", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("sugestao", new TableInfo.Column("sugestao", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("nivel_gravidade", new TableInfo.Column("nivel_gravidade", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("sync", new TableInfo.Column("sync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("recebido_em", new TableInfo.Column("recebido_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("encaminhamentos", new TableInfo.Column("encaminhamentos", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrencia.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOcorrencia = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOcorrencia = new HashSet<TableInfo.Index>(4);
        _indicesOcorrencia.add(new TableInfo.Index("index_ocorrencia_school_id", false, Arrays.asList("school_id"), Arrays.asList("ASC")));
        _indicesOcorrencia.add(new TableInfo.Index("index_ocorrencia_aluno_id", false, Arrays.asList("aluno_id"), Arrays.asList("ASC")));
        _indicesOcorrencia.add(new TableInfo.Index("index_ocorrencia_professor_id", false, Arrays.asList("professor_id"), Arrays.asList("ASC")));
        _indicesOcorrencia.add(new TableInfo.Index("index_ocorrencia_oferta_id", false, Arrays.asList("oferta_id"), Arrays.asList("ASC")));
        final TableInfo _infoOcorrencia = new TableInfo("ocorrencia", _columnsOcorrencia, _foreignKeysOcorrencia, _indicesOcorrencia);
        final TableInfo _existingOcorrencia = TableInfo.read(db, "ocorrencia");
        if (!_infoOcorrencia.equals(_existingOcorrencia)) {
          return new RoomOpenHelper.ValidationResult(false, "ocorrencia(br.com.syrios.mobile.data.entities.Ocorrencia).\n"
                  + " Expected:\n" + _infoOcorrencia + "\n"
                  + " Found:\n" + _existingOcorrencia);
        }
        final HashMap<String, TableInfo.Column> _columnsOcorrenciaMotivo = new HashMap<String, TableInfo.Column>(4);
        _columnsOcorrenciaMotivo.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrenciaMotivo.put("ocorrencia_id", new TableInfo.Column("ocorrencia_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrenciaMotivo.put("modelo_motivo_id", new TableInfo.Column("modelo_motivo_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOcorrenciaMotivo.put("sincronizado_em", new TableInfo.Column("sincronizado_em", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOcorrenciaMotivo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOcorrenciaMotivo = new HashSet<TableInfo.Index>(2);
        _indicesOcorrenciaMotivo.add(new TableInfo.Index("index_ocorrencia_motivo_ocorrencia_id", false, Arrays.asList("ocorrencia_id"), Arrays.asList("ASC")));
        _indicesOcorrenciaMotivo.add(new TableInfo.Index("index_ocorrencia_motivo_modelo_motivo_id", false, Arrays.asList("modelo_motivo_id"), Arrays.asList("ASC")));
        final TableInfo _infoOcorrenciaMotivo = new TableInfo("ocorrencia_motivo", _columnsOcorrenciaMotivo, _foreignKeysOcorrenciaMotivo, _indicesOcorrenciaMotivo);
        final TableInfo _existingOcorrenciaMotivo = TableInfo.read(db, "ocorrencia_motivo");
        if (!_infoOcorrenciaMotivo.equals(_existingOcorrenciaMotivo)) {
          return new RoomOpenHelper.ValidationResult(false, "ocorrencia_motivo(br.com.syrios.mobile.data.entities.OcorrenciaMotivo).\n"
                  + " Expected:\n" + _infoOcorrenciaMotivo + "\n"
                  + " Found:\n" + _existingOcorrenciaMotivo);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f93fb10d02f5fe62305ea45f0e22b8fe", "8473d30da09df4253d47db8d980c9760");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuario","professor","escola","turma","disciplina","oferta","aluno","enturmacao","modelo_motivo","ocorrencia","ocorrencia_motivo");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `usuario`");
      _db.execSQL("DELETE FROM `professor`");
      _db.execSQL("DELETE FROM `escola`");
      _db.execSQL("DELETE FROM `turma`");
      _db.execSQL("DELETE FROM `disciplina`");
      _db.execSQL("DELETE FROM `oferta`");
      _db.execSQL("DELETE FROM `aluno`");
      _db.execSQL("DELETE FROM `enturmacao`");
      _db.execSQL("DELETE FROM `modelo_motivo`");
      _db.execSQL("DELETE FROM `ocorrencia`");
      _db.execSQL("DELETE FROM `ocorrencia_motivo`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProfessorDao.class, ProfessorDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EscolaDao.class, EscolaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TurmaDao.class, TurmaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DisciplinaDao.class, DisciplinaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OfertaDao.class, OfertaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlunoDao.class, AlunoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EnturmacaoDao.class, EnturmacaoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ModeloMotivoDao.class, ModeloMotivoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OcorrenciaDao.class, OcorrenciaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OcorrenciaMotivoDao.class, OcorrenciaMotivoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public ProfessorDao professorDao() {
    if (_professorDao != null) {
      return _professorDao;
    } else {
      synchronized(this) {
        if(_professorDao == null) {
          _professorDao = new ProfessorDao_Impl(this);
        }
        return _professorDao;
      }
    }
  }

  @Override
  public EscolaDao escolaDao() {
    if (_escolaDao != null) {
      return _escolaDao;
    } else {
      synchronized(this) {
        if(_escolaDao == null) {
          _escolaDao = new EscolaDao_Impl(this);
        }
        return _escolaDao;
      }
    }
  }

  @Override
  public TurmaDao turmaDao() {
    if (_turmaDao != null) {
      return _turmaDao;
    } else {
      synchronized(this) {
        if(_turmaDao == null) {
          _turmaDao = new TurmaDao_Impl(this);
        }
        return _turmaDao;
      }
    }
  }

  @Override
  public DisciplinaDao disciplinaDao() {
    if (_disciplinaDao != null) {
      return _disciplinaDao;
    } else {
      synchronized(this) {
        if(_disciplinaDao == null) {
          _disciplinaDao = new DisciplinaDao_Impl(this);
        }
        return _disciplinaDao;
      }
    }
  }

  @Override
  public OfertaDao ofertaDao() {
    if (_ofertaDao != null) {
      return _ofertaDao;
    } else {
      synchronized(this) {
        if(_ofertaDao == null) {
          _ofertaDao = new OfertaDao_Impl(this);
        }
        return _ofertaDao;
      }
    }
  }

  @Override
  public AlunoDao alunoDao() {
    if (_alunoDao != null) {
      return _alunoDao;
    } else {
      synchronized(this) {
        if(_alunoDao == null) {
          _alunoDao = new AlunoDao_Impl(this);
        }
        return _alunoDao;
      }
    }
  }

  @Override
  public EnturmacaoDao enturmacaoDao() {
    if (_enturmacaoDao != null) {
      return _enturmacaoDao;
    } else {
      synchronized(this) {
        if(_enturmacaoDao == null) {
          _enturmacaoDao = new EnturmacaoDao_Impl(this);
        }
        return _enturmacaoDao;
      }
    }
  }

  @Override
  public ModeloMotivoDao modeloMotivoDao() {
    if (_modeloMotivoDao != null) {
      return _modeloMotivoDao;
    } else {
      synchronized(this) {
        if(_modeloMotivoDao == null) {
          _modeloMotivoDao = new ModeloMotivoDao_Impl(this);
        }
        return _modeloMotivoDao;
      }
    }
  }

  @Override
  public OcorrenciaDao ocorrenciaDao() {
    if (_ocorrenciaDao != null) {
      return _ocorrenciaDao;
    } else {
      synchronized(this) {
        if(_ocorrenciaDao == null) {
          _ocorrenciaDao = new OcorrenciaDao_Impl(this);
        }
        return _ocorrenciaDao;
      }
    }
  }

  @Override
  public OcorrenciaMotivoDao ocorrenciaMotivoDao() {
    if (_ocorrenciaMotivoDao != null) {
      return _ocorrenciaMotivoDao;
    } else {
      synchronized(this) {
        if(_ocorrenciaMotivoDao == null) {
          _ocorrenciaMotivoDao = new OcorrenciaMotivoDao_Impl(this);
        }
        return _ocorrenciaMotivoDao;
      }
    }
  }
}

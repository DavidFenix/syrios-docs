package br.com.syrios.mobile.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.com.syrios.mobile.data.entities.Ocorrencia;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OcorrenciaDao_Impl implements OcorrenciaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Ocorrencia> __insertionAdapterOfOcorrencia;

  private final EntityDeletionOrUpdateAdapter<Ocorrencia> __updateAdapterOfOcorrencia;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public OcorrenciaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOcorrencia = new EntityInsertionAdapter<Ocorrencia>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ocorrencia` (`id`,`school_id`,`ano_letivo`,`vigente`,`aluno_id`,`professor_id`,`oferta_id`,`descricao`,`local`,`atitude`,`outra_atitude`,`comportamento`,`sugestao`,`status`,`nivel_gravidade`,`sync`,`recebido_em`,`encaminhamentos`,`sincronizado_em`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Ocorrencia entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.id);
        }
        if (entity.schoolId == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.schoolId);
        }
        statement.bindLong(3, entity.anoLetivo);
        final int _tmp = entity.vigente ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.alunoId == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.alunoId);
        }
        if (entity.professorId == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.professorId);
        }
        if (entity.ofertaId == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.ofertaId);
        }
        if (entity.descricao == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.descricao);
        }
        if (entity.local == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.local);
        }
        if (entity.atitude == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.atitude);
        }
        if (entity.outraAtitude == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.outraAtitude);
        }
        if (entity.comportamento == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.comportamento);
        }
        if (entity.sugestao == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.sugestao);
        }
        statement.bindLong(14, entity.status);
        statement.bindLong(15, entity.nivelGravidade);
        statement.bindLong(16, entity.sync);
        if (entity.recebidoEm == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.recebidoEm);
        }
        if (entity.encaminhamentos == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.encaminhamentos);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.sincronizadoEm);
        }
      }
    };
    this.__updateAdapterOfOcorrencia = new EntityDeletionOrUpdateAdapter<Ocorrencia>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `ocorrencia` SET `id` = ?,`school_id` = ?,`ano_letivo` = ?,`vigente` = ?,`aluno_id` = ?,`professor_id` = ?,`oferta_id` = ?,`descricao` = ?,`local` = ?,`atitude` = ?,`outra_atitude` = ?,`comportamento` = ?,`sugestao` = ?,`status` = ?,`nivel_gravidade` = ?,`sync` = ?,`recebido_em` = ?,`encaminhamentos` = ?,`sincronizado_em` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Ocorrencia entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.id);
        }
        if (entity.schoolId == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.schoolId);
        }
        statement.bindLong(3, entity.anoLetivo);
        final int _tmp = entity.vigente ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.alunoId == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.alunoId);
        }
        if (entity.professorId == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.professorId);
        }
        if (entity.ofertaId == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.ofertaId);
        }
        if (entity.descricao == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.descricao);
        }
        if (entity.local == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.local);
        }
        if (entity.atitude == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.atitude);
        }
        if (entity.outraAtitude == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.outraAtitude);
        }
        if (entity.comportamento == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.comportamento);
        }
        if (entity.sugestao == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.sugestao);
        }
        statement.bindLong(14, entity.status);
        statement.bindLong(15, entity.nivelGravidade);
        statement.bindLong(16, entity.sync);
        if (entity.recebidoEm == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.recebidoEm);
        }
        if (entity.encaminhamentos == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.encaminhamentos);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.sincronizadoEm);
        }
        if (entity.id == null) {
          statement.bindNull(20);
        } else {
          statement.bindLong(20, entity.id);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ocorrencia";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Ocorrencia o) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOcorrencia.insert(o);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Ocorrencia> ocorrencias) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOcorrencia.insert(ocorrencias);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Ocorrencia o) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOcorrencia.handle(o);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearTable() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearTable.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfClearTable.release(_stmt);
    }
  }

  @Override
  public List<Ocorrencia> findByProfessor(final Long professorId) {
    final String _sql = "SELECT * FROM ocorrencia WHERE professor_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (professorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, professorId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfAlunoId = CursorUtil.getColumnIndexOrThrow(_cursor, "aluno_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfOfertaId = CursorUtil.getColumnIndexOrThrow(_cursor, "oferta_id");
      final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descricao");
      final int _cursorIndexOfLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "local");
      final int _cursorIndexOfAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "atitude");
      final int _cursorIndexOfOutraAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "outra_atitude");
      final int _cursorIndexOfComportamento = CursorUtil.getColumnIndexOrThrow(_cursor, "comportamento");
      final int _cursorIndexOfSugestao = CursorUtil.getColumnIndexOrThrow(_cursor, "sugestao");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfNivelGravidade = CursorUtil.getColumnIndexOrThrow(_cursor, "nivel_gravidade");
      final int _cursorIndexOfSync = CursorUtil.getColumnIndexOrThrow(_cursor, "sync");
      final int _cursorIndexOfRecebidoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "recebido_em");
      final int _cursorIndexOfEncaminhamentos = CursorUtil.getColumnIndexOrThrow(_cursor, "encaminhamentos");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Ocorrencia> _result = new ArrayList<Ocorrencia>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Ocorrencia _item;
        _item = new Ocorrencia();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _item.schoolId = null;
        } else {
          _item.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
        }
        _item.anoLetivo = _cursor.getInt(_cursorIndexOfAnoLetivo);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfVigente);
        _item.vigente = _tmp != 0;
        if (_cursor.isNull(_cursorIndexOfAlunoId)) {
          _item.alunoId = null;
        } else {
          _item.alunoId = _cursor.getLong(_cursorIndexOfAlunoId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _item.professorId = null;
        } else {
          _item.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        if (_cursor.isNull(_cursorIndexOfOfertaId)) {
          _item.ofertaId = null;
        } else {
          _item.ofertaId = _cursor.getLong(_cursorIndexOfOfertaId);
        }
        if (_cursor.isNull(_cursorIndexOfDescricao)) {
          _item.descricao = null;
        } else {
          _item.descricao = _cursor.getString(_cursorIndexOfDescricao);
        }
        if (_cursor.isNull(_cursorIndexOfLocal)) {
          _item.local = null;
        } else {
          _item.local = _cursor.getString(_cursorIndexOfLocal);
        }
        if (_cursor.isNull(_cursorIndexOfAtitude)) {
          _item.atitude = null;
        } else {
          _item.atitude = _cursor.getString(_cursorIndexOfAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfOutraAtitude)) {
          _item.outraAtitude = null;
        } else {
          _item.outraAtitude = _cursor.getString(_cursorIndexOfOutraAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfComportamento)) {
          _item.comportamento = null;
        } else {
          _item.comportamento = _cursor.getString(_cursorIndexOfComportamento);
        }
        if (_cursor.isNull(_cursorIndexOfSugestao)) {
          _item.sugestao = null;
        } else {
          _item.sugestao = _cursor.getString(_cursorIndexOfSugestao);
        }
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.nivelGravidade = _cursor.getInt(_cursorIndexOfNivelGravidade);
        _item.sync = _cursor.getInt(_cursorIndexOfSync);
        if (_cursor.isNull(_cursorIndexOfRecebidoEm)) {
          _item.recebidoEm = null;
        } else {
          _item.recebidoEm = _cursor.getLong(_cursorIndexOfRecebidoEm);
        }
        if (_cursor.isNull(_cursorIndexOfEncaminhamentos)) {
          _item.encaminhamentos = null;
        } else {
          _item.encaminhamentos = _cursor.getString(_cursorIndexOfEncaminhamentos);
        }
        if (_cursor.isNull(_cursorIndexOfSincronizadoEm)) {
          _item.sincronizadoEm = null;
        } else {
          _item.sincronizadoEm = _cursor.getLong(_cursorIndexOfSincronizadoEm);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Ocorrencia> findByAluno(final Long alunoId) {
    final String _sql = "SELECT * FROM ocorrencia WHERE aluno_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (alunoId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, alunoId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfAlunoId = CursorUtil.getColumnIndexOrThrow(_cursor, "aluno_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfOfertaId = CursorUtil.getColumnIndexOrThrow(_cursor, "oferta_id");
      final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descricao");
      final int _cursorIndexOfLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "local");
      final int _cursorIndexOfAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "atitude");
      final int _cursorIndexOfOutraAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "outra_atitude");
      final int _cursorIndexOfComportamento = CursorUtil.getColumnIndexOrThrow(_cursor, "comportamento");
      final int _cursorIndexOfSugestao = CursorUtil.getColumnIndexOrThrow(_cursor, "sugestao");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfNivelGravidade = CursorUtil.getColumnIndexOrThrow(_cursor, "nivel_gravidade");
      final int _cursorIndexOfSync = CursorUtil.getColumnIndexOrThrow(_cursor, "sync");
      final int _cursorIndexOfRecebidoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "recebido_em");
      final int _cursorIndexOfEncaminhamentos = CursorUtil.getColumnIndexOrThrow(_cursor, "encaminhamentos");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Ocorrencia> _result = new ArrayList<Ocorrencia>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Ocorrencia _item;
        _item = new Ocorrencia();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _item.schoolId = null;
        } else {
          _item.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
        }
        _item.anoLetivo = _cursor.getInt(_cursorIndexOfAnoLetivo);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfVigente);
        _item.vigente = _tmp != 0;
        if (_cursor.isNull(_cursorIndexOfAlunoId)) {
          _item.alunoId = null;
        } else {
          _item.alunoId = _cursor.getLong(_cursorIndexOfAlunoId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _item.professorId = null;
        } else {
          _item.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        if (_cursor.isNull(_cursorIndexOfOfertaId)) {
          _item.ofertaId = null;
        } else {
          _item.ofertaId = _cursor.getLong(_cursorIndexOfOfertaId);
        }
        if (_cursor.isNull(_cursorIndexOfDescricao)) {
          _item.descricao = null;
        } else {
          _item.descricao = _cursor.getString(_cursorIndexOfDescricao);
        }
        if (_cursor.isNull(_cursorIndexOfLocal)) {
          _item.local = null;
        } else {
          _item.local = _cursor.getString(_cursorIndexOfLocal);
        }
        if (_cursor.isNull(_cursorIndexOfAtitude)) {
          _item.atitude = null;
        } else {
          _item.atitude = _cursor.getString(_cursorIndexOfAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfOutraAtitude)) {
          _item.outraAtitude = null;
        } else {
          _item.outraAtitude = _cursor.getString(_cursorIndexOfOutraAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfComportamento)) {
          _item.comportamento = null;
        } else {
          _item.comportamento = _cursor.getString(_cursorIndexOfComportamento);
        }
        if (_cursor.isNull(_cursorIndexOfSugestao)) {
          _item.sugestao = null;
        } else {
          _item.sugestao = _cursor.getString(_cursorIndexOfSugestao);
        }
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.nivelGravidade = _cursor.getInt(_cursorIndexOfNivelGravidade);
        _item.sync = _cursor.getInt(_cursorIndexOfSync);
        if (_cursor.isNull(_cursorIndexOfRecebidoEm)) {
          _item.recebidoEm = null;
        } else {
          _item.recebidoEm = _cursor.getLong(_cursorIndexOfRecebidoEm);
        }
        if (_cursor.isNull(_cursorIndexOfEncaminhamentos)) {
          _item.encaminhamentos = null;
        } else {
          _item.encaminhamentos = _cursor.getString(_cursorIndexOfEncaminhamentos);
        }
        if (_cursor.isNull(_cursorIndexOfSincronizadoEm)) {
          _item.sincronizadoEm = null;
        } else {
          _item.sincronizadoEm = _cursor.getLong(_cursorIndexOfSincronizadoEm);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Ocorrencia> findPendingSync() {
    final String _sql = "SELECT * FROM ocorrencia WHERE sync = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfAlunoId = CursorUtil.getColumnIndexOrThrow(_cursor, "aluno_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfOfertaId = CursorUtil.getColumnIndexOrThrow(_cursor, "oferta_id");
      final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descricao");
      final int _cursorIndexOfLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "local");
      final int _cursorIndexOfAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "atitude");
      final int _cursorIndexOfOutraAtitude = CursorUtil.getColumnIndexOrThrow(_cursor, "outra_atitude");
      final int _cursorIndexOfComportamento = CursorUtil.getColumnIndexOrThrow(_cursor, "comportamento");
      final int _cursorIndexOfSugestao = CursorUtil.getColumnIndexOrThrow(_cursor, "sugestao");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfNivelGravidade = CursorUtil.getColumnIndexOrThrow(_cursor, "nivel_gravidade");
      final int _cursorIndexOfSync = CursorUtil.getColumnIndexOrThrow(_cursor, "sync");
      final int _cursorIndexOfRecebidoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "recebido_em");
      final int _cursorIndexOfEncaminhamentos = CursorUtil.getColumnIndexOrThrow(_cursor, "encaminhamentos");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Ocorrencia> _result = new ArrayList<Ocorrencia>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Ocorrencia _item;
        _item = new Ocorrencia();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _item.schoolId = null;
        } else {
          _item.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
        }
        _item.anoLetivo = _cursor.getInt(_cursorIndexOfAnoLetivo);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfVigente);
        _item.vigente = _tmp != 0;
        if (_cursor.isNull(_cursorIndexOfAlunoId)) {
          _item.alunoId = null;
        } else {
          _item.alunoId = _cursor.getLong(_cursorIndexOfAlunoId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _item.professorId = null;
        } else {
          _item.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        if (_cursor.isNull(_cursorIndexOfOfertaId)) {
          _item.ofertaId = null;
        } else {
          _item.ofertaId = _cursor.getLong(_cursorIndexOfOfertaId);
        }
        if (_cursor.isNull(_cursorIndexOfDescricao)) {
          _item.descricao = null;
        } else {
          _item.descricao = _cursor.getString(_cursorIndexOfDescricao);
        }
        if (_cursor.isNull(_cursorIndexOfLocal)) {
          _item.local = null;
        } else {
          _item.local = _cursor.getString(_cursorIndexOfLocal);
        }
        if (_cursor.isNull(_cursorIndexOfAtitude)) {
          _item.atitude = null;
        } else {
          _item.atitude = _cursor.getString(_cursorIndexOfAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfOutraAtitude)) {
          _item.outraAtitude = null;
        } else {
          _item.outraAtitude = _cursor.getString(_cursorIndexOfOutraAtitude);
        }
        if (_cursor.isNull(_cursorIndexOfComportamento)) {
          _item.comportamento = null;
        } else {
          _item.comportamento = _cursor.getString(_cursorIndexOfComportamento);
        }
        if (_cursor.isNull(_cursorIndexOfSugestao)) {
          _item.sugestao = null;
        } else {
          _item.sugestao = _cursor.getString(_cursorIndexOfSugestao);
        }
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.nivelGravidade = _cursor.getInt(_cursorIndexOfNivelGravidade);
        _item.sync = _cursor.getInt(_cursorIndexOfSync);
        if (_cursor.isNull(_cursorIndexOfRecebidoEm)) {
          _item.recebidoEm = null;
        } else {
          _item.recebidoEm = _cursor.getLong(_cursorIndexOfRecebidoEm);
        }
        if (_cursor.isNull(_cursorIndexOfEncaminhamentos)) {
          _item.encaminhamentos = null;
        } else {
          _item.encaminhamentos = _cursor.getString(_cursorIndexOfEncaminhamentos);
        }
        if (_cursor.isNull(_cursorIndexOfSincronizadoEm)) {
          _item.sincronizadoEm = null;
        } else {
          _item.sincronizadoEm = _cursor.getLong(_cursorIndexOfSincronizadoEm);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

package br.com.syrios.mobile.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.com.syrios.mobile.data.entities.Enturmacao;
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
public final class EnturmacaoDao_Impl implements EnturmacaoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Enturmacao> __insertionAdapterOfEnturmacao;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public EnturmacaoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEnturmacao = new EntityInsertionAdapter<Enturmacao>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `enturmacao` (`id`,`school_id`,`ano_letivo`,`vigente`,`aluno_id`,`turma_id`,`sincronizado_em`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Enturmacao entity) {
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
        if (entity.turmaId == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.turmaId);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.sincronizadoEm);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM enturmacao";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Enturmacao e) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEnturmacao.insert(e);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Enturmacao> enturmacoes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEnturmacao.insert(enturmacoes);
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
  public List<Enturmacao> findByAluno(final Long alunoId) {
    final String _sql = "SELECT * FROM enturmacao WHERE aluno_id = ?";
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
      final int _cursorIndexOfTurmaId = CursorUtil.getColumnIndexOrThrow(_cursor, "turma_id");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Enturmacao> _result = new ArrayList<Enturmacao>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Enturmacao _item;
        _item = new Enturmacao();
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
        if (_cursor.isNull(_cursorIndexOfTurmaId)) {
          _item.turmaId = null;
        } else {
          _item.turmaId = _cursor.getLong(_cursorIndexOfTurmaId);
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
  public List<Enturmacao> findByTurma(final Long turmaId) {
    final String _sql = "SELECT * FROM enturmacao WHERE turma_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (turmaId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, turmaId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfAlunoId = CursorUtil.getColumnIndexOrThrow(_cursor, "aluno_id");
      final int _cursorIndexOfTurmaId = CursorUtil.getColumnIndexOrThrow(_cursor, "turma_id");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Enturmacao> _result = new ArrayList<Enturmacao>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Enturmacao _item;
        _item = new Enturmacao();
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
        if (_cursor.isNull(_cursorIndexOfTurmaId)) {
          _item.turmaId = null;
        } else {
          _item.turmaId = _cursor.getLong(_cursorIndexOfTurmaId);
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

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
import br.com.syrios.mobile.data.entities.Turma;
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
public final class TurmaDao_Impl implements TurmaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Turma> __insertionAdapterOfTurma;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public TurmaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTurma = new EntityInsertionAdapter<Turma>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `turma` (`id`,`school_id`,`serie_turma`,`turno`,`sincronizado_em`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Turma entity) {
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
        if (entity.serieTurma == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.serieTurma);
        }
        if (entity.turno == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.turno);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.sincronizadoEm);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM turma";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Turma t) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTurma.insert(t);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Turma> turmas) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTurma.insert(turmas);
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
  public List<Turma> findBySchool(final Long schoolId) {
    final String _sql = "SELECT * FROM turma WHERE school_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (schoolId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, schoolId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfSerieTurma = CursorUtil.getColumnIndexOrThrow(_cursor, "serie_turma");
      final int _cursorIndexOfTurno = CursorUtil.getColumnIndexOrThrow(_cursor, "turno");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Turma> _result = new ArrayList<Turma>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Turma _item;
        _item = new Turma();
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
        if (_cursor.isNull(_cursorIndexOfSerieTurma)) {
          _item.serieTurma = null;
        } else {
          _item.serieTurma = _cursor.getString(_cursorIndexOfSerieTurma);
        }
        if (_cursor.isNull(_cursorIndexOfTurno)) {
          _item.turno = null;
        } else {
          _item.turno = _cursor.getString(_cursorIndexOfTurno);
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
  public Turma findById(final Long id) {
    final String _sql = "SELECT * FROM turma WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfSerieTurma = CursorUtil.getColumnIndexOrThrow(_cursor, "serie_turma");
      final int _cursorIndexOfTurno = CursorUtil.getColumnIndexOrThrow(_cursor, "turno");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Turma _result;
      if (_cursor.moveToFirst()) {
        _result = new Turma();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _result.id = null;
        } else {
          _result.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _result.schoolId = null;
        } else {
          _result.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
        }
        if (_cursor.isNull(_cursorIndexOfSerieTurma)) {
          _result.serieTurma = null;
        } else {
          _result.serieTurma = _cursor.getString(_cursorIndexOfSerieTurma);
        }
        if (_cursor.isNull(_cursorIndexOfTurno)) {
          _result.turno = null;
        } else {
          _result.turno = _cursor.getString(_cursorIndexOfTurno);
        }
        if (_cursor.isNull(_cursorIndexOfSincronizadoEm)) {
          _result.sincronizadoEm = null;
        } else {
          _result.sincronizadoEm = _cursor.getLong(_cursorIndexOfSincronizadoEm);
        }
      } else {
        _result = null;
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

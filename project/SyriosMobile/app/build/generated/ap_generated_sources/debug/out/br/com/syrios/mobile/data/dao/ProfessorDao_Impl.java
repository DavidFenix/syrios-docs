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
import br.com.syrios.mobile.data.entities.Professor;
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
public final class ProfessorDao_Impl implements ProfessorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Professor> __insertionAdapterOfProfessor;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public ProfessorDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProfessor = new EntityInsertionAdapter<Professor>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `professor` (`id`,`usuario_id`,`school_id`,`sincronizado_em`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Professor entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.id);
        }
        if (entity.usuarioId == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.usuarioId);
        }
        if (entity.schoolId == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.schoolId);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.sincronizadoEm);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM professor";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Professor professor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProfessor.insert(professor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Professor> professores) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProfessor.insert(professores);
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
  public List<Professor> findByUsuario(final Long usuarioId) {
    final String _sql = "SELECT * FROM professor WHERE usuario_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (usuarioId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, usuarioId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsuarioId = CursorUtil.getColumnIndexOrThrow(_cursor, "usuario_id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Professor> _result = new ArrayList<Professor>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Professor _item;
        _item = new Professor();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfUsuarioId)) {
          _item.usuarioId = null;
        } else {
          _item.usuarioId = _cursor.getLong(_cursorIndexOfUsuarioId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _item.schoolId = null;
        } else {
          _item.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
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
  public List<Professor> findBySchool(final Long schoolId) {
    final String _sql = "SELECT * FROM professor WHERE school_id = ?";
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
      final int _cursorIndexOfUsuarioId = CursorUtil.getColumnIndexOrThrow(_cursor, "usuario_id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Professor> _result = new ArrayList<Professor>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Professor _item;
        _item = new Professor();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfUsuarioId)) {
          _item.usuarioId = null;
        } else {
          _item.usuarioId = _cursor.getLong(_cursorIndexOfUsuarioId);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolId)) {
          _item.schoolId = null;
        } else {
          _item.schoolId = _cursor.getLong(_cursorIndexOfSchoolId);
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

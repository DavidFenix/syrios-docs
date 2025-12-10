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
import br.com.syrios.mobile.data.entities.Disciplina;
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
public final class DisciplinaDao_Impl implements DisciplinaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Disciplina> __insertionAdapterOfDisciplina;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public DisciplinaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDisciplina = new EntityInsertionAdapter<Disciplina>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `disciplina` (`id`,`school_id`,`abr`,`descr_d`,`sincronizado_em`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Disciplina entity) {
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
        if (entity.abreviacao == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.abreviacao);
        }
        if (entity.descricao == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.descricao);
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
        final String _query = "DELETE FROM disciplina";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Disciplina d) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDisciplina.insert(d);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Disciplina> disciplinas) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDisciplina.insert(disciplinas);
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
  public List<Disciplina> findBySchool(final Long schoolId) {
    final String _sql = "SELECT * FROM disciplina WHERE school_id = ?";
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
      final int _cursorIndexOfAbreviacao = CursorUtil.getColumnIndexOrThrow(_cursor, "abr");
      final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descr_d");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Disciplina> _result = new ArrayList<Disciplina>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Disciplina _item;
        _item = new Disciplina();
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
        if (_cursor.isNull(_cursorIndexOfAbreviacao)) {
          _item.abreviacao = null;
        } else {
          _item.abreviacao = _cursor.getString(_cursorIndexOfAbreviacao);
        }
        if (_cursor.isNull(_cursorIndexOfDescricao)) {
          _item.descricao = null;
        } else {
          _item.descricao = _cursor.getString(_cursorIndexOfDescricao);
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
  public Disciplina findById(final Long id) {
    final String _sql = "SELECT * FROM disciplina WHERE id = ?";
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
      final int _cursorIndexOfAbreviacao = CursorUtil.getColumnIndexOrThrow(_cursor, "abr");
      final int _cursorIndexOfDescricao = CursorUtil.getColumnIndexOrThrow(_cursor, "descr_d");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Disciplina _result;
      if (_cursor.moveToFirst()) {
        _result = new Disciplina();
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
        if (_cursor.isNull(_cursorIndexOfAbreviacao)) {
          _result.abreviacao = null;
        } else {
          _result.abreviacao = _cursor.getString(_cursorIndexOfAbreviacao);
        }
        if (_cursor.isNull(_cursorIndexOfDescricao)) {
          _result.descricao = null;
        } else {
          _result.descricao = _cursor.getString(_cursorIndexOfDescricao);
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

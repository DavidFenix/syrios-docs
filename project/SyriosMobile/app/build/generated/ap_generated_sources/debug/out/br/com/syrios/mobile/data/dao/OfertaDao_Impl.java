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
import br.com.syrios.mobile.data.entities.Oferta;
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
public final class OfertaDao_Impl implements OfertaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Oferta> __insertionAdapterOfOferta;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public OfertaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOferta = new EntityInsertionAdapter<Oferta>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `oferta` (`id`,`school_id`,`ano_letivo`,`vigente`,`turma_id`,`disciplina_id`,`professor_id`,`status`,`sincronizado_em`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Oferta entity) {
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
        if (entity.turmaId == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.turmaId);
        }
        if (entity.disciplinaId == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.disciplinaId);
        }
        if (entity.professorId == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.professorId);
        }
        statement.bindLong(8, entity.status);
        if (entity.sincronizadoEm == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.sincronizadoEm);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM oferta";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Oferta o) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOferta.insert(o);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Oferta> ofertas) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOferta.insert(ofertas);
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
  public List<Oferta> findByProfessor(final Long professorId) {
    final String _sql = "SELECT * FROM oferta WHERE professor_id = ?";
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
      final int _cursorIndexOfTurmaId = CursorUtil.getColumnIndexOrThrow(_cursor, "turma_id");
      final int _cursorIndexOfDisciplinaId = CursorUtil.getColumnIndexOrThrow(_cursor, "disciplina_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Oferta> _result = new ArrayList<Oferta>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Oferta _item;
        _item = new Oferta();
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
        if (_cursor.isNull(_cursorIndexOfTurmaId)) {
          _item.turmaId = null;
        } else {
          _item.turmaId = _cursor.getLong(_cursorIndexOfTurmaId);
        }
        if (_cursor.isNull(_cursorIndexOfDisciplinaId)) {
          _item.disciplinaId = null;
        } else {
          _item.disciplinaId = _cursor.getLong(_cursorIndexOfDisciplinaId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _item.professorId = null;
        } else {
          _item.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
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
  public List<Oferta> findBySchool(final Long schoolId) {
    final String _sql = "SELECT * FROM oferta WHERE school_id = ?";
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
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfTurmaId = CursorUtil.getColumnIndexOrThrow(_cursor, "turma_id");
      final int _cursorIndexOfDisciplinaId = CursorUtil.getColumnIndexOrThrow(_cursor, "disciplina_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Oferta> _result = new ArrayList<Oferta>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Oferta _item;
        _item = new Oferta();
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
        if (_cursor.isNull(_cursorIndexOfTurmaId)) {
          _item.turmaId = null;
        } else {
          _item.turmaId = _cursor.getLong(_cursorIndexOfTurmaId);
        }
        if (_cursor.isNull(_cursorIndexOfDisciplinaId)) {
          _item.disciplinaId = null;
        } else {
          _item.disciplinaId = _cursor.getLong(_cursorIndexOfDisciplinaId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _item.professorId = null;
        } else {
          _item.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
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
  public Oferta findById(final Long id) {
    final String _sql = "SELECT * FROM oferta WHERE id = ?";
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
      final int _cursorIndexOfAnoLetivo = CursorUtil.getColumnIndexOrThrow(_cursor, "ano_letivo");
      final int _cursorIndexOfVigente = CursorUtil.getColumnIndexOrThrow(_cursor, "vigente");
      final int _cursorIndexOfTurmaId = CursorUtil.getColumnIndexOrThrow(_cursor, "turma_id");
      final int _cursorIndexOfDisciplinaId = CursorUtil.getColumnIndexOrThrow(_cursor, "disciplina_id");
      final int _cursorIndexOfProfessorId = CursorUtil.getColumnIndexOrThrow(_cursor, "professor_id");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Oferta _result;
      if (_cursor.moveToFirst()) {
        _result = new Oferta();
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
        _result.anoLetivo = _cursor.getInt(_cursorIndexOfAnoLetivo);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfVigente);
        _result.vigente = _tmp != 0;
        if (_cursor.isNull(_cursorIndexOfTurmaId)) {
          _result.turmaId = null;
        } else {
          _result.turmaId = _cursor.getLong(_cursorIndexOfTurmaId);
        }
        if (_cursor.isNull(_cursorIndexOfDisciplinaId)) {
          _result.disciplinaId = null;
        } else {
          _result.disciplinaId = _cursor.getLong(_cursorIndexOfDisciplinaId);
        }
        if (_cursor.isNull(_cursorIndexOfProfessorId)) {
          _result.professorId = null;
        } else {
          _result.professorId = _cursor.getLong(_cursorIndexOfProfessorId);
        }
        _result.status = _cursor.getInt(_cursorIndexOfStatus);
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

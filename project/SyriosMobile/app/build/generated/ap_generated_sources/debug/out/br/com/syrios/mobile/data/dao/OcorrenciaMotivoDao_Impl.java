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
import br.com.syrios.mobile.data.entities.OcorrenciaMotivo;
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
public final class OcorrenciaMotivoDao_Impl implements OcorrenciaMotivoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OcorrenciaMotivo> __insertionAdapterOfOcorrenciaMotivo;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public OcorrenciaMotivoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOcorrenciaMotivo = new EntityInsertionAdapter<OcorrenciaMotivo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ocorrencia_motivo` (`id`,`ocorrencia_id`,`modelo_motivo_id`,`sincronizado_em`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final OcorrenciaMotivo entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.id);
        }
        if (entity.ocorrenciaId == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.ocorrenciaId);
        }
        if (entity.modeloMotivoId == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.modeloMotivoId);
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
        final String _query = "DELETE FROM ocorrencia_motivo";
        return _query;
      }
    };
  }

  @Override
  public void insert(final OcorrenciaMotivo m) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOcorrenciaMotivo.insert(m);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<OcorrenciaMotivo> motivos) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOcorrenciaMotivo.insert(motivos);
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
  public List<OcorrenciaMotivo> findByOcorrencia(final Long ocorrenciaId) {
    final String _sql = "SELECT * FROM ocorrencia_motivo WHERE ocorrencia_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ocorrenciaId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, ocorrenciaId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOcorrenciaId = CursorUtil.getColumnIndexOrThrow(_cursor, "ocorrencia_id");
      final int _cursorIndexOfModeloMotivoId = CursorUtil.getColumnIndexOrThrow(_cursor, "modelo_motivo_id");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<OcorrenciaMotivo> _result = new ArrayList<OcorrenciaMotivo>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final OcorrenciaMotivo _item;
        _item = new OcorrenciaMotivo();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfOcorrenciaId)) {
          _item.ocorrenciaId = null;
        } else {
          _item.ocorrenciaId = _cursor.getLong(_cursorIndexOfOcorrenciaId);
        }
        if (_cursor.isNull(_cursorIndexOfModeloMotivoId)) {
          _item.modeloMotivoId = null;
        } else {
          _item.modeloMotivoId = _cursor.getLong(_cursorIndexOfModeloMotivoId);
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

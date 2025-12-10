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
import br.com.syrios.mobile.data.entities.Escola;
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
public final class EscolaDao_Impl implements EscolaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Escola> __insertionAdapterOfEscola;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public EscolaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEscola = new EntityInsertionAdapter<Escola>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `escola` (`id`,`nome`,`frase_efeito`,`logo_path`,`cidade`,`estado`,`secretaria_id`,`is_master`,`sincronizado_em`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Escola entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.id);
        }
        if (entity.nome == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.nome);
        }
        if (entity.fraseEfeito == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.fraseEfeito);
        }
        if (entity.logoPath == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.logoPath);
        }
        if (entity.cidade == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.cidade);
        }
        if (entity.estado == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.estado);
        }
        if (entity.secretariaId == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.secretariaId);
        }
        final int _tmp = entity.isMaster ? 1 : 0;
        statement.bindLong(8, _tmp);
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
        final String _query = "DELETE FROM escola";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Escola e) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEscola.insert(e);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Escola> escolas) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEscola.insert(escolas);
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
  public Escola findById(final Long id) {
    final String _sql = "SELECT * FROM escola WHERE id = ?";
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
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfFraseEfeito = CursorUtil.getColumnIndexOrThrow(_cursor, "frase_efeito");
      final int _cursorIndexOfLogoPath = CursorUtil.getColumnIndexOrThrow(_cursor, "logo_path");
      final int _cursorIndexOfCidade = CursorUtil.getColumnIndexOrThrow(_cursor, "cidade");
      final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
      final int _cursorIndexOfSecretariaId = CursorUtil.getColumnIndexOrThrow(_cursor, "secretaria_id");
      final int _cursorIndexOfIsMaster = CursorUtil.getColumnIndexOrThrow(_cursor, "is_master");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Escola _result;
      if (_cursor.moveToFirst()) {
        _result = new Escola();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _result.id = null;
        } else {
          _result.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _result.nome = null;
        } else {
          _result.nome = _cursor.getString(_cursorIndexOfNome);
        }
        if (_cursor.isNull(_cursorIndexOfFraseEfeito)) {
          _result.fraseEfeito = null;
        } else {
          _result.fraseEfeito = _cursor.getString(_cursorIndexOfFraseEfeito);
        }
        if (_cursor.isNull(_cursorIndexOfLogoPath)) {
          _result.logoPath = null;
        } else {
          _result.logoPath = _cursor.getString(_cursorIndexOfLogoPath);
        }
        if (_cursor.isNull(_cursorIndexOfCidade)) {
          _result.cidade = null;
        } else {
          _result.cidade = _cursor.getString(_cursorIndexOfCidade);
        }
        if (_cursor.isNull(_cursorIndexOfEstado)) {
          _result.estado = null;
        } else {
          _result.estado = _cursor.getString(_cursorIndexOfEstado);
        }
        if (_cursor.isNull(_cursorIndexOfSecretariaId)) {
          _result.secretariaId = null;
        } else {
          _result.secretariaId = _cursor.getLong(_cursorIndexOfSecretariaId);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsMaster);
        _result.isMaster = _tmp != 0;
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

  @Override
  public List<Escola> getAll() {
    final String _sql = "SELECT * FROM escola";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfFraseEfeito = CursorUtil.getColumnIndexOrThrow(_cursor, "frase_efeito");
      final int _cursorIndexOfLogoPath = CursorUtil.getColumnIndexOrThrow(_cursor, "logo_path");
      final int _cursorIndexOfCidade = CursorUtil.getColumnIndexOrThrow(_cursor, "cidade");
      final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
      final int _cursorIndexOfSecretariaId = CursorUtil.getColumnIndexOrThrow(_cursor, "secretaria_id");
      final int _cursorIndexOfIsMaster = CursorUtil.getColumnIndexOrThrow(_cursor, "is_master");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final List<Escola> _result = new ArrayList<Escola>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Escola _item;
        _item = new Escola();
        if (_cursor.isNull(_cursorIndexOfId)) {
          _item.id = null;
        } else {
          _item.id = _cursor.getLong(_cursorIndexOfId);
        }
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _item.nome = null;
        } else {
          _item.nome = _cursor.getString(_cursorIndexOfNome);
        }
        if (_cursor.isNull(_cursorIndexOfFraseEfeito)) {
          _item.fraseEfeito = null;
        } else {
          _item.fraseEfeito = _cursor.getString(_cursorIndexOfFraseEfeito);
        }
        if (_cursor.isNull(_cursorIndexOfLogoPath)) {
          _item.logoPath = null;
        } else {
          _item.logoPath = _cursor.getString(_cursorIndexOfLogoPath);
        }
        if (_cursor.isNull(_cursorIndexOfCidade)) {
          _item.cidade = null;
        } else {
          _item.cidade = _cursor.getString(_cursorIndexOfCidade);
        }
        if (_cursor.isNull(_cursorIndexOfEstado)) {
          _item.estado = null;
        } else {
          _item.estado = _cursor.getString(_cursorIndexOfEstado);
        }
        if (_cursor.isNull(_cursorIndexOfSecretariaId)) {
          _item.secretariaId = null;
        } else {
          _item.secretariaId = _cursor.getLong(_cursorIndexOfSecretariaId);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsMaster);
        _item.isMaster = _tmp != 0;
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

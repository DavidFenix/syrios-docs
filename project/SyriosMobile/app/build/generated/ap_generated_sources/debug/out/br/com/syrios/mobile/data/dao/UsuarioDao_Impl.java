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
import br.com.syrios.mobile.data.entities.Usuario;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UsuarioDao_Impl implements UsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Usuario> __insertionAdapterOfUsuario;

  private final SharedSQLiteStatement __preparedStmtOfClearTable;

  public UsuarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `usuario` (`id`,`school_id`,`cpf`,`nome_u`,`nascimento`,`status`,`senha_local_hash`,`ultimo_login`,`foto_url`,`roles_json`,`sincronizado_em`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Usuario entity) {
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
        if (entity.cpf == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.cpf);
        }
        if (entity.nome == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.nome);
        }
        if (entity.nascimento == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.nascimento);
        }
        if (entity.status == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.status);
        }
        if (entity.senhaLocalHash == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.senhaLocalHash);
        }
        if (entity.ultimoLogin == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.ultimoLogin);
        }
        if (entity.fotoUrl == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.fotoUrl);
        }
        if (entity.rolesJson == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.rolesJson);
        }
        if (entity.sincronizadoEm == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.sincronizadoEm);
        }
      }
    };
    this.__preparedStmtOfClearTable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM usuario";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Usuario usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsuario.insert(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Usuario> usuarios) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsuario.insert(usuarios);
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
  public Usuario findByCpf(final String cpf) {
    final String _sql = "SELECT * FROM usuario WHERE cpf = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (cpf == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, cpf);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "school_id");
      final int _cursorIndexOfCpf = CursorUtil.getColumnIndexOrThrow(_cursor, "cpf");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome_u");
      final int _cursorIndexOfNascimento = CursorUtil.getColumnIndexOrThrow(_cursor, "nascimento");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfSenhaLocalHash = CursorUtil.getColumnIndexOrThrow(_cursor, "senha_local_hash");
      final int _cursorIndexOfUltimoLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "ultimo_login");
      final int _cursorIndexOfFotoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_url");
      final int _cursorIndexOfRolesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "roles_json");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Usuario _result;
      if (_cursor.moveToFirst()) {
        _result = new Usuario();
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
        if (_cursor.isNull(_cursorIndexOfCpf)) {
          _result.cpf = null;
        } else {
          _result.cpf = _cursor.getString(_cursorIndexOfCpf);
        }
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _result.nome = null;
        } else {
          _result.nome = _cursor.getString(_cursorIndexOfNome);
        }
        if (_cursor.isNull(_cursorIndexOfNascimento)) {
          _result.nascimento = null;
        } else {
          _result.nascimento = _cursor.getString(_cursorIndexOfNascimento);
        }
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _result.status = null;
        } else {
          _result.status = _cursor.getInt(_cursorIndexOfStatus);
        }
        if (_cursor.isNull(_cursorIndexOfSenhaLocalHash)) {
          _result.senhaLocalHash = null;
        } else {
          _result.senhaLocalHash = _cursor.getString(_cursorIndexOfSenhaLocalHash);
        }
        if (_cursor.isNull(_cursorIndexOfUltimoLogin)) {
          _result.ultimoLogin = null;
        } else {
          _result.ultimoLogin = _cursor.getLong(_cursorIndexOfUltimoLogin);
        }
        if (_cursor.isNull(_cursorIndexOfFotoUrl)) {
          _result.fotoUrl = null;
        } else {
          _result.fotoUrl = _cursor.getString(_cursorIndexOfFotoUrl);
        }
        if (_cursor.isNull(_cursorIndexOfRolesJson)) {
          _result.rolesJson = null;
        } else {
          _result.rolesJson = _cursor.getString(_cursorIndexOfRolesJson);
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

  @Override
  public Usuario findById(final Long id) {
    final String _sql = "SELECT * FROM usuario WHERE id = ?";
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
      final int _cursorIndexOfCpf = CursorUtil.getColumnIndexOrThrow(_cursor, "cpf");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome_u");
      final int _cursorIndexOfNascimento = CursorUtil.getColumnIndexOrThrow(_cursor, "nascimento");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfSenhaLocalHash = CursorUtil.getColumnIndexOrThrow(_cursor, "senha_local_hash");
      final int _cursorIndexOfUltimoLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "ultimo_login");
      final int _cursorIndexOfFotoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "foto_url");
      final int _cursorIndexOfRolesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "roles_json");
      final int _cursorIndexOfSincronizadoEm = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado_em");
      final Usuario _result;
      if (_cursor.moveToFirst()) {
        _result = new Usuario();
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
        if (_cursor.isNull(_cursorIndexOfCpf)) {
          _result.cpf = null;
        } else {
          _result.cpf = _cursor.getString(_cursorIndexOfCpf);
        }
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _result.nome = null;
        } else {
          _result.nome = _cursor.getString(_cursorIndexOfNome);
        }
        if (_cursor.isNull(_cursorIndexOfNascimento)) {
          _result.nascimento = null;
        } else {
          _result.nascimento = _cursor.getString(_cursorIndexOfNascimento);
        }
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _result.status = null;
        } else {
          _result.status = _cursor.getInt(_cursorIndexOfStatus);
        }
        if (_cursor.isNull(_cursorIndexOfSenhaLocalHash)) {
          _result.senhaLocalHash = null;
        } else {
          _result.senhaLocalHash = _cursor.getString(_cursorIndexOfSenhaLocalHash);
        }
        if (_cursor.isNull(_cursorIndexOfUltimoLogin)) {
          _result.ultimoLogin = null;
        } else {
          _result.ultimoLogin = _cursor.getLong(_cursorIndexOfUltimoLogin);
        }
        if (_cursor.isNull(_cursorIndexOfFotoUrl)) {
          _result.fotoUrl = null;
        } else {
          _result.fotoUrl = _cursor.getString(_cursorIndexOfFotoUrl);
        }
        if (_cursor.isNull(_cursorIndexOfRolesJson)) {
          _result.rolesJson = null;
        } else {
          _result.rolesJson = _cursor.getString(_cursorIndexOfRolesJson);
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

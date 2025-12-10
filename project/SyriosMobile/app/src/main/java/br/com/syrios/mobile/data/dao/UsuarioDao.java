package br.com.syrios.mobile.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.syrios.mobile.data.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Usuario> usuarios);

    @Query("SELECT * FROM usuario WHERE cpf = :cpf")
    Usuario findByCpf(String cpf);

    @Query("SELECT * FROM usuario WHERE id = :id")
    Usuario findById(Long id);

    @Query("DELETE FROM usuario")
    void clearTable();
}

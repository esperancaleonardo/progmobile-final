package com.example.minhacaloria;

import org.jetbrains.annotations.NotNull;

public enum DBQueries {
    CREATE_TB_USUARIO("CREATE TABLE tb_usuario(id INTEGER PRIMARY key AUTOINCREMENT, " +
            "email  STRING NOT NULL, " +
            "senha TEXT NOT NULL);"),
    CREATE_TB_PERFIL("CREATE TABLE tb_perfil(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER REFERENCES tb_usuario(id) ON DELETE RESTRICT ON UPDATE CASCADE, " +
            "nome TEXT NOT NULL, " +
            "sexo TEXT NOT NULL, " +
            "peso FLOAT NOT NULL, " +
            "altura FLOAT NOT NULL, " +
            "idade INTEGER NOT NULL, " +
            "objetivo TEXT NOT NULL, " +
            "tbm INTEGER NOT NULL, " +
            "meta_calorica INTEGER NOT NULL, " +
            "data_perfil text NOT NULL);"),
    VERIFY_REGISTERED_MAIL("SELECT * from tb_usuario WHERE email=?"),
    USER_ID_BY_MAIL("SELECT id from tb_usuario where email=?");


//    CREATE_TB_ALIMENTO(""),       // id, nome/descricao, peso, gordura, proteina, carboidrato, kcal, id_diario, id_usuario
//    CREATE_TB_DIARIO(""),         // id, data, id_usuario, total de kcal, meta de kcal
//    CREATE_TB_NOTIFICACAO("");    // id_usuario, not1, not2, not3, not4
//

    private final String text;

    DBQueries(final String text) {
        this.text = text;
    }

    @Override
    public @NotNull String toString() {
        return text;
    }
}

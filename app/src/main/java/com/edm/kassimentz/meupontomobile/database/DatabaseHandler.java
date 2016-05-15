package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Cassio on 14/05/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "meuPontoMobile";



    // Table: endereco
    private static final String TABLE_ENDERECO = "CREATE TABLE endereco (id_endereco INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, logradouro VARCHAR (100), numero INTEGER (10), complemento VARCHAR (50), cidade VARCHAR (100), estado CHAR (2), pais CHAR (2));";

    // Table: telefone
    private static final String TABLE_TELEFONE = "CREATE TABLE telefone (id_telefone INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ddd CHAR (2), telefone CHAR (10));";

    // Table: periodo_trabalho
    private static final String TABLE_PERIODO_TRABALHO = "CREATE TABLE periodo_trabalho (id_periodo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, periodo VARCHAR (50));";

    // Table: empresa
    private static final String TABLE_EMPRESA = "CREATE TABLE empresa (id_empresa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_telefone INTEGER (10) REFERENCES telefone (id_telefone), id_endereco INTEGER (10) REFERENCES endereco (id_endereco), nome VARCHAR (100));";

    // Table: justificativa
    private static final String TABLE_JUSTIFICATIVA = "CREATE TABLE justificativa (id_justificativa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, justificativa VARCHAR (50));";

    //Table: jornada_trabalho
    private static final String TABLE_JORNADA_TRABALHO = "CREATE TABLE jornada_trabalho (id_jornada_trabalho INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, duracao_intervalo INTEGER, tempo_alerta_intervalo INTEGER, hora_inicio_jornada DATETIME, hora_saida_intervalo DATETIME, hora_termino_jornada DATETIME, horas_trabalho_dia DATETIME, dias_trabalho_semana INTEGER, trabalho_domingo BOOLEAN, id_periodo_trabalho INTEGER REFERENCES periodo_trabalho (id_periodo));";

    //Table: funcionario
    private static final String TABLE_FUNCIONARIO = "CREATE TABLE funcionario (id_funcionario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR (200), cpf INTEGER (12), cargo VARCHAR (100), id_empresa INTEGER REFERENCES endereco, id_jornada_trabalho INTEGER REFERENCES jornada_trabalho (id_jornada_trabalho));";

    // Table: calendario_justificativas
    private static final String TABLE_CALENDARIO_JUSTIFICATIVAS = "CREATE TABLE calendario_justificativas (id_calendario_justificativas INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data_hora DATETIME, observacao BLOB, id_justificativa INTEGER REFERENCES justificativa (id_justificativa));";

    // Table: funcionario_calendario_justificativas
    private static final String TABLE_FUNCIONARIO_CALENDARIO_JUSTIFICATIVAS = "CREATE TABLE funcionario_calendario_justificativas (id_funcionario_calendario_justificativas INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER REFERENCES telefone, id_calendario_justificativas INTEGER REFERENCES justificativa);";

    // Table: periodos_trabalhados
    private static final String TABLE_PERIODOS_TRABALHADOS = "CREATE TABLE periodos_trabalhados (id_periodos_trabalhados INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data_hora_inicio DATETIME, data_hora_fim DATETIME);";

    // Table: funcionario_periodos_trabalhados
    private static final String TABLE_FUNCIONARIO_PERIODOS_TRABALHADOS = "CREATE TABLE funcionario_periodos_trabalhados (id_funcionario_periodos_trabalhados INTEGER PRIMARY KEY NOT NULL, id_funcionario INTEGER REFERENCES endereco, id_periodos_trabalhados INTEGER REFERENCES periodos_trabalhados (id_periodos_trabalhados));";

    // Table: funcionario_endereco
    private static final String TABLE_FUNCIONARIO_ENDERECO = "CREATE TABLE funcionario_endereco (id_funcionario_endereco INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER REFERENCES telefone, id_endereco INTEGER REFERENCES endereco (id_endereco));";

    // Table: funcionario_telefone
    private static final String TABLE_FUNCIONARIO_TELEFONE = "CREATE TABLE funcionario_telefone (id_funcionario_telefone INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER REFERENCES endereco, id_telefone INTEGER REFERENCES telefone (id_telefone));";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_ENDERECO);
        Log.d("banco", "TABLE_ENDERECO criada");
        db.execSQL(TABLE_TELEFONE);
        Log.d("banco", "TABLE_TELEFONE criada");
        db.execSQL(TABLE_PERIODO_TRABALHO);
        Log.d("banco", "TABLE_PERIODO_TRABALHO criada");
        db.execSQL(TABLE_EMPRESA);
        Log.d("banco", "TABLE_EMPRESA criada");
        db.execSQL(TABLE_JUSTIFICATIVA);
        Log.d("banco", "TABLE_JUSTIFICATIVA criada");
        db.execSQL(TABLE_JORNADA_TRABALHO);
        Log.d("banco", "TABLE_JORNADA_TRABALHO criada");
        db.execSQL(TABLE_FUNCIONARIO);
        Log.d("banco", "TABLE_FUNCIONARIO criada");
        db.execSQL(TABLE_FUNCIONARIO_ENDERECO);
        Log.d("banco", "TABLE_FUNCIONARIO_ENDERECO criada");
        db.execSQL(TABLE_FUNCIONARIO_TELEFONE);
        Log.d("banco", "TABLE_FUNCIONARIO_TELEFONE criada");
        db.execSQL(TABLE_CALENDARIO_JUSTIFICATIVAS);
        Log.d("banco", "TABLE_CALENDARIO_JUSTIFICATIVAS criada");
        db.execSQL(TABLE_FUNCIONARIO_CALENDARIO_JUSTIFICATIVAS);
        Log.d("banco", "TABLE_FUNCIONARIO_CALENDARIO_JUSTIFICATIVAS criada");
        db.execSQL(TABLE_PERIODOS_TRABALHADOS);
        Log.d("banco", "TABLE_PERIODOS_TRABALHADOS criada");
        db.execSQL(TABLE_FUNCIONARIO_PERIODOS_TRABALHADOS);
        Log.d("banco", "TABLE_FUNCIONARIO_PERIODOS_TRABALHADOS criada");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS endereco");
        db.execSQL("DROP TABLE IF EXISTS telefone");
        db.execSQL("DROP TABLE IF EXISTS periodo_trabalho");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS justificativa");
        db.execSQL("DROP TABLE IF EXISTS jornada_trabalho");
        db.execSQL("DROP TABLE IF EXISTS funcionario");
        db.execSQL("DROP TABLE IF EXISTS calendario_justificativas");
        db.execSQL("DROP TABLE IF EXISTS funcionario_calendario_justificativas");
        db.execSQL("DROP TABLE IF EXISTS periodos_trabalhados");
        db.execSQL("DROP TABLE IF EXISTS funcionario_periodos_trabalhados");
        db.execSQL("DROP TABLE IF EXISTS funcionario_endereco");
        db.execSQL("DROP TABLE IF EXISTS funcionario_telefone");


        // Create tables again
        onCreate(db);
    }
}

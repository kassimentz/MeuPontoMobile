package com.edm.kassimentz.meupontomobile.database.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static String TAG = DB.class.getSimpleName();
    private static SQLiteDatabase mInstance = null;
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "meuPontoMobile";

    // Table: endereco
    private static final String TABLE_ENDERECO = "CREATE TABLE endereco (id_endereco INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, logradouro VARCHAR (100), numero INTEGER (10), complemento VARCHAR (50), cidade VARCHAR (100), estado CHAR (2), pais CHAR (2));";

    // Table: telefone
    private static final String TABLE_TELEFONE = "CREATE TABLE telefone (id_telefone INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ddd CHAR (2), telefone CHAR (10));";

    // Table: empresa
    private static final String TABLE_EMPRESA = "CREATE TABLE empresa (id_empresa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_endereco INTEGER (10) REFERENCES endereco (id_endereco), nome VARCHAR (100));";

    //Table: jornada_trabalho
    private static final String TABLE_JORNADA_TRABALHO = "CREATE TABLE jornada_trabalho (id_jornada_trabalho INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, duracao_intervalo INTEGER, tempo_alerta_intervalo INTEGER, hora_inicio_jornada DATETIME, hora_saida_intervalo DATETIME, hora_termino_jornada DATETIME, horas_trabalho_dia DATETIME, dias_trabalho_semana INTEGER, trabalho_domingo BOOLEAN, periodo_trabalho INTEGER);";

    //Table: funcionario
    private static final String TABLE_FUNCIONARIO = "CREATE TABLE funcionario (id_funcionario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR (200), cpf VARCHAR (12), cargo VARCHAR (100), id_empresa INTEGER REFERENCES endereco, id_jornada_trabalho INTEGER REFERENCES jornada_trabalho (id_jornada_trabalho));";

    // Table: calendario_justificativas
    private static final String TABLE_CALENDARIO_JUSTIFICATIVAS = "CREATE TABLE calendario_justificativas (id_calendario_justificativas INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data_hora DATETIME, observacao BLOB, justificativa INTEGER);";

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

    // Table: empresa_telefones
    private static final String TABLE_EMPRESA_TELEFONES = "CREATE TABLE empresa_telefones (id_empresa_telefones INTEGER PRIMARY KEY AUTOINCREMENT, id_empresa INTEGER REFERENCES empresa (id_empresa), id_telefone INTEGER REFERENCES telefone (id_telefone));";


    public synchronized static SQLiteDatabase instance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DB(ctx.getApplicationContext()).getWritableDatabase();
        }

        return mInstance;
    }

    private DB(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ENDERECO);
        db.execSQL(TABLE_TELEFONE);
        db.execSQL(TABLE_EMPRESA);
        db.execSQL(TABLE_JORNADA_TRABALHO);
        db.execSQL(TABLE_FUNCIONARIO);
        db.execSQL(TABLE_FUNCIONARIO_ENDERECO);
        db.execSQL(TABLE_FUNCIONARIO_TELEFONE);
        db.execSQL(TABLE_CALENDARIO_JUSTIFICATIVAS);
        db.execSQL(TABLE_FUNCIONARIO_CALENDARIO_JUSTIFICATIVAS);
        db.execSQL(TABLE_PERIODOS_TRABALHADOS);
        db.execSQL(TABLE_FUNCIONARIO_PERIODOS_TRABALHADOS);
        db.execSQL(TABLE_EMPRESA_TELEFONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS endereco");
        db.execSQL("DROP TABLE IF EXISTS telefone");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS jornada_trabalho");
        db.execSQL("DROP TABLE IF EXISTS funcionario");
        db.execSQL("DROP TABLE IF EXISTS calendario_justificativas");
        db.execSQL("DROP TABLE IF EXISTS funcionario_calendario_justificativas");
        db.execSQL("DROP TABLE IF EXISTS periodos_trabalhados");
        db.execSQL("DROP TABLE IF EXISTS funcionario_periodos_trabalhados");
        db.execSQL("DROP TABLE IF EXISTS funcionario_endereco");
        db.execSQL("DROP TABLE IF EXISTS funcionario_telefone");
        db.execSQL("DROP TABLE IF EXISTS empresa_telefones");
        onCreate(db);
    }

    public static List<ContentValues> selectRows(Context ctx, String sql, String[] params) {
        Cursor c = DB.instance(ctx).rawQuery(sql, params);

        List<ContentValues> retVal = new ArrayList<ContentValues>();
        ContentValues map;
        if(c.moveToFirst()) {
            do {
                map = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(c, map);
                retVal.add(map);
            } while(c.moveToNext());
        }
        c.close();
        return retVal;
    }

    public static void executeSQL(Context ctx, String sql, String[] params) {
        DB.instance(ctx).execSQL(sql, params);
    }

    public static long lastId(Context ctx, String tabela) {
        List<ContentValues> rows = DB.selectRows(ctx, "SELECT max(_id) as seq FROM " + tabela, null);
        return rows.get(0).getAsLong("seq");
    }


    /**
     *
     * Usage:

     ArrayList<ContentValues> rows = DB.selectRows(this, "SELECT * FROM users WHERE active = ?", new String[] { strActive });

     DB.executeSQL(this,
     "INSERT INTO users (name, active, email) VALUES (?, ?, ?)",
     new String[]{ name, active, email });

     */

}
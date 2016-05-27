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


    //-- Table: funcionario_periodos_trabalhados
    static String TABLE_FUNCIONARIO_PERIODOS_TRABALHADOS = "CREATE TABLE funcionario_periodos_trabalhados (id INTEGER PRIMARY KEY NOT NULL, id_funcionario INTEGER, id_periodos_trabalhados INTEGER);";

    //-- Table: empresa_telefones
    static String TABLE_EMPRESA_TELEFONES = "CREATE TABLE empresa_telefones (id INTEGER PRIMARY KEY AUTOINCREMENT, id_empresa INTEGER, id_telefone INTEGER);";

    //-- Table: jornada_trabalho
    static String TABLE_JORNADA_TRABALHO = "CREATE TABLE jornada_trabalho (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, duracao_intervalo INTEGER, tempo_alerta_intervalo INTEGER, hora_inicio_jornada BIGINT, hora_saida_intervalo BIGINT, hora_termino_jornada BIGINT, horas_trabalho_dia DOUBLE, dias_trabalho_semana INTEGER, trabalho_domingo BOOLEAN, periodo_trabalho INTEGER);";

    //-- Table: funcionario_calendario_justificativas
    static String TABLE_FUNCIONARIO_CALENDARIO_JUSTIFICATIVAS = "CREATE TABLE funcionario_calendario_justificativas (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER, id_calendario_justificativas INTEGER);";

    //-- Table: calendario_justificativas
    static  String TABLE_CALENDARIO_JUSTIFICATIVAS = "CREATE TABLE calendario_justificativas (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data_hora BIGINT, observacao BLOB, justificativa INTEGER);";

    //-- Table: funcionario_telefone
    static String TABLE_FUNCIONARIO_TELEFONE = "CREATE TABLE funcionario_telefone (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER, id_telefone INTEGER);";

    //-- Table: empresa
    static String TABLE_EMPRESA = "CREATE TABLE empresa (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_endereco INTEGER (10), nome VARCHAR (100));";

    //-- Table: funcionario
    static String TABLE_FUNCIONARIO = "CREATE TABLE funcionario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, usuario VARCHAR(100), senha VARCHAR(100), nome VARCHAR (200), cpf VARCHAR (12), cargo VARCHAR (100), id_empresa INTEGER, id_jornada_trabalho INTEGER);";

    //-- Table: telefone
    static String TABLE_TELEFONE = "CREATE TABLE telefone (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ddd CHAR (2), telefone CHAR (10));";

    //-- Table: funcionario_endereco
    static String TABLE_FUNCIONARIO_ENDERECO = "CREATE TABLE funcionario_endereco (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_funcionario INTEGER, id_endereco INTEGER);";

    //-- Table: endereco
    static String TABLE_ENDERECO = "CREATE TABLE endereco (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cep INTEGER(10), logradouro VARCHAR (100), numero INTEGER (10), complemento VARCHAR (50), cidade VARCHAR (100), estado CHAR (2), pais CHAR (3));";

    //-- Table: periodos_trabalhados
    static String TABLE_PERIODOS_TRABALHADOS = "CREATE TABLE periodos_trabalhados (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data_hora_inicio BIGINT, data_hora_fim BIGINT);";

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
        List<ContentValues> rows = DB.selectRows(ctx, "SELECT max(id) as seq FROM " + tabela, null);
        return rows.get(0).getAsLong("seq");
    }

    public static ContentValues selectRow(Context ctx, String sql, String[] params) {
        Cursor c = DB.instance(ctx).rawQuery(sql, params);

        ContentValues map = new ContentValues();
        if(c.moveToNext()) {
            DatabaseUtils.cursorRowToContentValues(c, map);
        }
        c.close();
        return map;
    }

    public static ContentValues byId(Context ctx, String tabela, Integer id){

        ContentValues row = new ContentValues();
        Cursor c = DB.instance(ctx).rawQuery("SELECT * FROM " + tabela + " WHERE id = ?", new String[]{String.valueOf(id)});
        if(c.moveToNext()){
            DatabaseUtils.cursorRowToContentValues(c, row);
        }
        c.close();
        return row;

    }

}
package com.example.minhacaloria.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.minhacaloria.model.Profile;
import com.example.minhacaloria.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="DB_MINHA_CALORIA.db";
    private final Context context;
    private SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        Log.d("DB", "DB Created");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBQueries.CREATE_TB_USUARIO.toString());
        db.execSQL(DBQueries.CREATE_TB_PERFIL.toString());
        this.db = db;
        Log.d("DB", "Tables Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean verify_mail_registered(String email){
        Cursor c = getReadableDatabase().rawQuery(DBQueries.VERIFY_REGISTERED_MAIL.toString(), new String[]{email});
        if(c.getCount() > 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public void insert_user(User user){
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("senha", user.getPassword());

        try {
            db.insert("tb_usuario", null, values);
        }catch (SQLiteAbortException e){
            throw e;
        }
    }

    public void update_user(int id, User user){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("senha", user.getPassword());

        try {
            db.update("tb_usuario", values, "id=?", new String[] {String.valueOf(id)});
        }catch (SQLiteAbortException e) {
            throw e;
        }
    }

    public Integer get_user_id(String email){
        Cursor c = getReadableDatabase().rawQuery(DBQueries.USER_ID_BY_MAIL.toString(), new String[]{email});
        c.moveToFirst();

        Integer user_id = c.getInt(c.getColumnIndex("id"));
        c.close();
        return user_id;
    }

    public void insert_profile(Profile prof){
        db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", prof.getName());
        cv.put("idade", prof.getAge());
        cv.put("sexo", prof.getSex());
        cv.put("peso", prof.getWeight());
        cv.put("altura", prof.getHeight());
        cv.put("objetivo", prof.getObjective());
        cv.put("tbm", prof.getTbm());
        cv.put("meta_calorica", prof.getTarget());
        cv.put("data_perfil", prof.getUpdate());
        cv.put("user_id", prof.getUser_id());

        try {
            db.insert("tb_perfil", null, cv);
        }catch (SQLiteAbortException e){
            throw e;
        }
    }

    public boolean verify_user_data(String email, String senha){
        Cursor c = this.getReadableDatabase().rawQuery(DBQueries.GET_DADOS_USUARIO.toString(), new String[]{email});

        if(c.getCount() > 0){
            c.moveToFirst();
            if(email.equals(c.getString(0)) && senha.equals(c.getString(1))){
                c.close();
                return true;
            }
            else {
                c.close();
                return false;
            }
        }
        else {
            c.close();
            return false;
        }
    }

    public List get_user_profile(User logged){
        Cursor c = getReadableDatabase().rawQuery(DBQueries.GET_PERFIL_USUARIO.toString(), new String[]{logged.getEmail()});
        c.moveToFirst();

        List<String> profile = new ArrayList<String>();
        profile.add(c.getString(2));
        profile.add(c.getString(5));
        profile.add(c.getString(4));
        profile.add(c.getString(6));
        profile.add(c.getString(7));
        profile.add(c.getString(3));
        profile.add(c.getString(9));
        profile.add(c.getString(10));
        profile.add(c.getString(11));
        c.close();
        return profile;
    }

    public void update_profile(int id, Profile prof, int old_target){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", prof.getName());
        values.put("sexo", prof.getSex());
        values.put("peso", prof.getWeight());
        values.put("altura", prof.getHeight());
        values.put("idade", prof.getAge());
        values.put("objetivo", prof.getObjective());
        values.put("tbm", prof.getTbm());
        values.put("meta_calorica", prof.getTarget());
        values.put("data_perfil", prof.getUpdate());
        values.put("meta_anterior", old_target);

        try {
            db.update("tb_perfil", values, "id=?", new String[] {String.valueOf(id)});
        }catch (SQLiteAbortException e) {
            throw e;
        }
    }

    public String get_user_target(User logged){
        Cursor c = getReadableDatabase().rawQuery(DBQueries.GET_META_USUARIO.toString(), new String[]{logged.getEmail()});
        c.moveToFirst();
        return  c.getString(0);
    }
}

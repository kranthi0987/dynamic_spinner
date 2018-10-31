package com.example.mahiti.json3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper{

    public static String DATABASE = "yaledatabase.db";
    public static String TABLE ="yaletable";
    public static String ID="id";
    public static String LHID ="lhid";
    public static String LMID ="lmid";
    public static String GIVENNAME ="given_name";
    public static String SURNAME ="surname";
    public static String HOUSEHOLDNAME="household_name";
    public static String DOORNO ="door_no";
    public static String BUILDING ="building_no";
    public static String STREET ="street";
    public static String HAMLET ="hamlet";
    public static String PRIMOCCUPATION ="primary_occupation";
    public static String SECOCCUPATION ="secondary_occupation";
    public static String CASTE ="caste";
    public static String SUBCASTE ="subcaste";
    public static String STATE ="state";
    public static String DISTRICT ="district";
    public static String VILLAGE ="village";
    public static String RELATIONSHIP ="relationship";
    public static String USERNAME ="username";
    public static String MSG ="msg";
    public static String STATUS ="status";
    public String districtName="district";
    public String stateName="state";
    public String villageName="village";
    String br;

    public DbHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE "+TABLE+"(" +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+LHID+ " Text, "+LMID+ " Text, "+GIVENNAME+ " Text, "+SURNAME+ " Text,"+HOUSEHOLDNAME+ " Text, "+DOORNO+ " Text,"+BUILDING+ " Text, "+STREET+ " Text,"+HAMLET+ " Text, "+PRIMOCCUPATION+ " Text, "+SECOCCUPATION+ " Text, "+CASTE+ " Text, "+SUBCASTE+ " Text, "+STATE+ " Text, "+DISTRICT+ " Text, "+VILLAGE+ " Text, "+RELATIONSHIP+ " Text, "+USERNAME+");";
        db.execSQL(br);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
    }

    public long insertdata(String lhid,String lmid ,String given_name,String surname,String household_name,String door_no ,String building_no,String street,String hamlet,String primary_occupation,String secondary_occupation,String caste,String subcaste ,String state,String district,String village,String relationship,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(LHID,lhid);
        contentValues.put(LMID,lmid);
        contentValues.put(GIVENNAME,given_name);
        contentValues.put(SURNAME,surname);
        contentValues.put(HOUSEHOLDNAME,household_name);
        contentValues.put(DOORNO,door_no);
        contentValues.put(BUILDING,building_no);
        contentValues.put(STREET,street);
        contentValues.put(HAMLET,hamlet);
        contentValues.put(PRIMOCCUPATION ,primary_occupation);
        contentValues.put(SECOCCUPATION,secondary_occupation);
        contentValues.put(CASTE,caste);
        contentValues.put(SUBCASTE,subcaste);
        contentValues.put(STATE ,state);
        contentValues.put(DISTRICT ,district);
        contentValues.put(VILLAGE,village);
        contentValues.put(RELATIONSHIP ,relationship);
        contentValues.put(USERNAME ,username);
        long id= db.insert(TABLE,null,contentValues);
        return id;
    }

    public List<Data> getdata(){
        List<Data> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        Data dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new Data();;
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String Lhid = cursor.getString(cursor.getColumnIndexOrThrow("lhid"));
            String Lmid = cursor.getString(cursor.getColumnIndexOrThrow("lmid"));
            String GivenName = cursor.getString(cursor.getColumnIndexOrThrow("given_name"));
            String Surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"));
            String HouseholdName = cursor.getString(cursor.getColumnIndexOrThrow("household_name"));
            String DoorNo = cursor.getString(cursor.getColumnIndexOrThrow("door_no"));
            String BuildingNo = cursor.getString(cursor.getColumnIndexOrThrow("building_no"));
            String Street = cursor.getString(cursor.getColumnIndexOrThrow("street"));
            String Hamlet = cursor.getString(cursor.getColumnIndexOrThrow("hamlet"));
            String PrimaryOccupation = cursor.getString(cursor.getColumnIndexOrThrow("primary_occupation"));
            String SecondaryOccupation = cursor.getString(cursor.getColumnIndexOrThrow("secondary_occupation"));
            String Caste = cursor.getString(cursor.getColumnIndexOrThrow("caste"));
            String Subcaste = cursor.getString(cursor.getColumnIndexOrThrow("subcaste"));
            String State = cursor.getString(cursor.getColumnIndexOrThrow("state"));
            String District = cursor.getString(cursor.getColumnIndexOrThrow("district"));
            String Village = cursor.getString(cursor.getColumnIndexOrThrow("village"));
            String Relationship = cursor.getString(cursor.getColumnIndexOrThrow("relationship"));
            String Username = cursor.getString(cursor.getColumnIndexOrThrow("username"));

            dataModel.setId(id);
            dataModel.setLhid(Lhid);
            dataModel.setLmid(Lmid);
            dataModel.setGivenName(GivenName);
            dataModel.setSurname(Surname);
            dataModel.setHouseholdName(HouseholdName);
            dataModel.setDoorNo(DoorNo);
            dataModel.setBuildingNo(BuildingNo);
            dataModel.setStreet(Street);
            dataModel.setHamlet(Hamlet);
            dataModel.setPrimaryOccupation(PrimaryOccupation);
            dataModel.setSecondaryOccupation(SecondaryOccupation);
            dataModel.setCaste(Caste);
            dataModel.setSubcaste(Subcaste);
            dataModel.setState(State);
            dataModel.setDistrict(District);
            dataModel.setVillage(Village);
            dataModel.setRelationship(Relationship);
            dataModel.setUsername(Username);
            data.add(dataModel);
        }
        db.close();
        return data;
    }

    public List<String> getStateList(){
        List<String> listState=new ArrayList<String>();
        listState.add("Select");
        String queryState="SELECT "+stateName+" FROM yaletable group by "+stateName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryState, null);
        if (cursor.moveToFirst()) {
            do {
                String dis=cursor.getString(cursor.getColumnIndex(stateName));
                listState.add(dis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listState;


    }

    public List<String> getDistrict(String state){
        List<String> listDistrict = new ArrayList<String>();
        listDistrict.add("Select");
        String queryDistrict = "SELECT "+districtName+" FROM yaletable WHERE "+stateName+" ='" + state +"' GROUP BY district";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryDistrict, null);
        if (cursor.moveToFirst()) {
            do {
                String dis=cursor.getString(cursor.getColumnIndex(districtName));
                listDistrict.add(dis);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listDistrict;

    }

    public List<String> getVillage(String district){
        List<String> listVillage=new ArrayList<String>();
        listVillage.add("Select");
        String queryVillage = "SELECT "+villageName+" FROM yaletable WHERE "+districtName+" ='" + district +"' GROUP BY "+villageName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryVillage, null);
        if (cursor.moveToFirst()) {
            do {
                String object=cursor.getString(cursor.getColumnIndex(villageName));
                listVillage.add(object);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listVillage;

    }
}


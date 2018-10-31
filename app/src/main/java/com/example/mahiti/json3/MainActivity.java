package com.example.mahiti.json3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public String yaledata;
    DbHelper dbHelper;
    Spinner spinner2, spinner3, spinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        yaledata = readJSONFromAsset("yale.json");
        Log.i("abc", "onCreate: " + yaledata);
/*
        TextView hamlet_text=(TextView) findViewById(R.id.edit_HamletLocality);
        TextView street_text=(TextView) findViewById(R.id.edit_StreetName);
        TextView person_name_text=(TextView) findViewById(R.id.edit_PersonName);
        TextView occupationtext=(TextView) findViewById(R.id.edit_Occupation);
        try {
            JSONObject jObj = new JSONObject(yaledata);
            JSONArray jArray= jObj.getJSONArray("data");
            for(int i=0;i<jArray.length();i++){

                String hamlet = jArray.getJSONObject(i).getString("hamlet");
                String  street= jArray.getJSONObject(i).getString("street");
                String person = jArray.getJSONObject(i).getString("given_name");
                String occupation = jArray.getJSONObject(i).getString("primary_occupation");
                hamlet_text.setText(hamlet);
                street_text.setText(street);
                person_name_text.setText(person);
                occupationtext.setText(occupation);
            }


        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
*/

        dbHelper = new DbHelper(this);
        Gson gson = new Gson();
        SuccessCase data = gson.fromJson(yaledata, SuccessCase.class);
        List<Data> dd = data.getData();
        for (int i = 0; i < dd.size(); i++) {
            Data ss = dd.get(i);
            dbHelper.insertdata(ss.getLhid(), ss.getLmid(), ss.getGivenName(), ss.getSurname(), ss.getHouseholdName(), ss.getDoorNo(), ss.getBuildingNo(), ss.getStreet(), ss.getHamlet(), ss.getPrimaryOccupation(), ss.getSecondaryOccupation(), ss.getCaste(), ss.getSubcaste(), ss.getState(), ss.getDistrict(), ss.getVillage(), ss.getRelationship(), ss.getUsername());
        }
        //lmid = String.valueOf(ss.getId());
        Log.i("ds", "onCreate: " + data);
        //  dbHelper.insertdata(ss.getLhid(),ss.getLmid() ,ss.getGivenName(),ss.getSurname(),ss.getHouseholdName(),ss.getDoorNo(), ss.getBuildingNo(),ss.getStreet(), ss.getHamlet(),ss.getPrimaryOccupation(),ss.getSecondaryOccupation(), ss.getCaste(), ss.getSubcaste() , ss.getState(), ss.getDistrict(), ss.getVillage(), ss.getRelationship(), ss.getUsername());
        Log.i("aa", "onCreate: " + dbHelper);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        //FirstScreen state=new FirstScreen(getApplicationContext(),dbHelper.DATABASE,null,1);
        //FirstScreen district=new FirstScreen(getApplicationContext(),dbHelper.DATABASE,null,1);
        //FirstScreen village=new FirstScreen(getApplicationContext(),dbHelper.DATABASE,null,1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dbHelper.getStateList());

        spinner2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();



        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {

                spinner3.setSelection(position);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, dbHelper.getDistrict(String.valueOf(spinner2.getSelectedItem())));
//                spinner3.setSelection(position);
                spinner3.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {

                spinner4.setSelection(position);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, dbHelper.getVillage((String)spinner3.getSelectedItem()));
//                spinner3.setSelection(position);
                spinner4.setAdapter(adapter4);
                adapter4.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {

               // spinnerCountry.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
///        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String spinnerValue = spinner2.getSelectedItem().toString();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

       /* //list
       ListView listView = (ListView) findViewById(R.id.yalelist);
        List<Data> dataList = dbHelper.getdata();
        CustomAdapter myAdapter = new CustomAdapter(dataList, this);
        listView.setAdapter(myAdapter); */


    }

    public String readJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private ArrayList<String> getState(String filename) {
        JSONArray jsonArray = null;
        ArrayList<String> state = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.getJSONArray("data");
            //jsonArray=new JSONArray(json);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    state.add(jArray.getJSONObject(i).getString("state"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return state;
    }

    private ArrayList<String> getDistrict(String filename) {
        JSONArray jsonArray = null;
        ArrayList<String> district = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.getJSONArray("data");
            //jsonArray=new JSONArray(json);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    district.add(jArray.getJSONObject(i).getString("district"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return district;
    }

    private ArrayList<String> getVillage(String filename) {
        JSONArray jsonArray = null;
        ArrayList<String> village = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.getJSONArray("data");
            //jsonArray=new JSONArray(json);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    village.add(jArray.getJSONObject(i).getString("village"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return village;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // String stateName=dbHelper.getDistrict((String) spinner2.getSelectedItem()).get(position).toString();
        // resetDistrict(stateName);

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    /*public void resetDistrict(String stateName)
    {
        dbHelper.getVillage(spinner3.getSelectedItem()).removeAll(dp);//i haven't checked this.
        if(stateName.eqauls("state_1"))
        {
            city_option.add("city_1_state_1");
            city_options.add("city_2_state_1");
            city_options.add("city_3_state_1");
            //you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
        else if(stateName.eqauls("state_2"))
        {
            city_option.add("city_1_state_2");
            city_options.add("city_2_state_2");
            city_options.add("city_3_state_2");
            // you can also get a cursor and add Strings as options to city_options instead of what i have done
        }
        else
        {
            city_option.add("city_1_state_3");
            city_options.add("city_2_state_3");
            city_options.add("city_3_state_3");
            //you can also get a cursor and add Strings as options to city_options instead of what i have done
        }

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,city_options);
        city.setAdapter(cityAdapter);
    }*/
}

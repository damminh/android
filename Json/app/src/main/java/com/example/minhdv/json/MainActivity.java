package com.example.minhdv.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.minhdv.model.Contract;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<Contract> dsContact;
    ArrayAdapter<Contract> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }
    private void addEvents() {

    }

    private void addControls() {
        lvContact = (ListView) findViewById(R.id.listView);
        dsContact = new ArrayList<>();
        adapterContact = new ArrayAdapter<Contract>(MainActivity.this,
                android.R.layout.simple_list_item_1,dsContact);

        lvContact.setAdapter(adapterContact);

        ContactTask task = new ContactTask();
        task.execute();
    }

    class ContactTask extends AsyncTask<Void,Void,ArrayList<Contract>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterContact.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Contract> contracts) {
            super.onPostExecute(contracts);
            adapterContact.clear();
            adapterContact.addAll(contracts);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contract> doInBackground(Void... params) {
            ArrayList<Contract> ds = new ArrayList<>();
            try {
                URL url = new URL("http://www.w3schools.com/website/customers_mysql.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while(line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for(int i = 0 ; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Contract contact = new Contract();
                    if(jsonObject.has("Name"))
                        contact.setName(jsonObject.getString("Name"));
                    if(jsonObject.has("City"))
                        contact.setCity(jsonObject.getString("City"));
                    if(jsonObject.has("Country"))
                        contact.setCountry(jsonObject.getString("Country"));
                    ds.add(contact);
                }
            }
            catch (Exception ex) {
                Log.e("loi", ex.toString());
            }
            return ds;
        }

    }
}

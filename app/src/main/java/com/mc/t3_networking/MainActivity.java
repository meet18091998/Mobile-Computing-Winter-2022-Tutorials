package com.mc.t3_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.mc.t3_networking.Model.Employee;
import com.mc.t3_networking.Adapter.EmployeesAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeesAdapter employeesAdapter;
    private ArrayList<Employee> employeeArrayList;

    private static final String TAG = "MainActivity";
    private static final String DEBUG_TAG = "NetworkStatusExample";
    private static final String Error_TAG = "Error";

    private boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //It requires ACCESS_NETWORK_STATE permission
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        employeeArrayList = new ArrayList<>();

        URL url = null;
        try {
            url = new URL("https://api.npoint.io/5a56bc1037d9ca5f5d72/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String stringUrl= url.toString();
        new FetchData().execute(stringUrl);

        employeesAdapter = new EmployeesAdapter(employeeArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(employeesAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    class FetchData extends AsyncTask<String, Void, String> {

        private InputStream inputStream;
        private BufferedReader bufferedReader;
        private HttpURLConnection connection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectivityManager connMng = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connMng.getActiveNetworkInfo();
            boolean connected = netInfo.isConnected();
            boolean isWiFiConnected=(netInfo.getType()==ConnectivityManager.TYPE_WIFI);
            boolean isMobileConnected=(netInfo.getType()==ConnectivityManager.TYPE_MOBILE);
            Log.d(DEBUG_TAG, "WIFi connected="+isWiFiConnected);
            Log.d(DEBUG_TAG,"Mobile data Connected"+isMobileConnected);

            if(!connected)
                cancel(true);

        }

        @Override
        protected String doInBackground(String... voids) {

                StringBuilder stringBuilder = null;
                try {
                    URL url = new URL(voids[0]);
                    connection = (HttpURLConnection) url.openConnection();

                    Log.i("TAG", "status code: " + connection.getResponseCode());

                    //Reading Response
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        //Reading data from InputStream
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        stringBuilder = new StringBuilder();

                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "error: " + e.getMessage());
                } finally {
                    try {
                        if (bufferedReader != null)
                            bufferedReader.close();

                        if (inputStream != null)
                            inputStream.close();

                        if(connection != null)
                            connection.disconnect();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (stringBuilder != null) {
                    return stringBuilder.toString();
                }
                return "no connection";
        }
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if(!response.equals("no connection")) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Employee employee;
                    if (jsonObject.get("status").toString().trim().equals("success")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject empObj = jsonArray.getJSONObject(i);
                            employee = new Employee();
                            employee.setId(empObj.getInt("id"));
                            employee.setName(empObj.getString("employee_name"));
                            employee.setSalary(empObj.getInt("employee_salary"));
                            employee.setAge((byte) empObj.getInt("employee_age"));

                            employeeArrayList.add(employee);
                        }

                        employeesAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG,"json error: "+ e.getMessage());
                }
            }else {
                Toast.makeText(MainActivity.this, "No internet!", Toast.LENGTH_LONG).show();
            }
        }
    }
}

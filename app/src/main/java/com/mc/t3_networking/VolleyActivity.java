package com.mc.t3_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mc.t3_networking.Adapter.EmployeesAdapter;
import com.mc.t3_networking.Model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private EmployeesAdapter employeesAdapter;
    private ArrayList<Employee> employeeArrayList;

    private static final String TAG = "VolleyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        
        requestQueue = Volley.newRequestQueue(this);
        fetchData("https://api.npoint.io/5a56bc1037d9ca5f5d72");

        recyclerView = findViewById(R.id.recyclerView);
        employeeArrayList = new ArrayList<>();

        employeesAdapter = new EmployeesAdapter(employeeArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void fetchData(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                        recyclerView.setAdapter(employeesAdapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.i(TAG, "volley error: " + error.getMessage());
            }
        });

        requestQueue.add(request); //Execution
    }
}
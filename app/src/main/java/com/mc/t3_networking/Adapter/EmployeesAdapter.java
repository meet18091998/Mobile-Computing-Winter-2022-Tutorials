package com.mc.t3_networking.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mc.t3_networking.Model.Employee;
import com.mc.t3_networking.R;

import java.util.ArrayList;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {

    private ArrayList<Employee> employeeArrayList;

    public  EmployeesAdapter(ArrayList<Employee> list) {
        employeeArrayList = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Employee employee = employeeArrayList.get(position);

        holder.employee_id.setText(String.valueOf(employee.getId()));
        holder.employee_name.setText(employee.getName());
        holder.employee_salary.setText("$" + employee.getSalary());
        holder.employee_age.setText(String.valueOf(employee.getAge()) + " yr");

    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView employee_id, employee_name, employee_salary, employee_age;

        public ViewHolder(View itemView)
        {
            super(itemView);

            employee_id = itemView.findViewById(R.id.employee_id);
            employee_name = itemView.findViewById(R.id.employee_name);
            employee_salary = itemView.findViewById(R.id.employee_salary);
            employee_age = itemView.findViewById(R.id.employee_age);
            
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i("clicked: ", "" + employeeArrayList.get(getAdapterPosition()).getName());
        }
    }
}

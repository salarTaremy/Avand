package com.smartVisitor.avand.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.CustomerRecyclerViewHolder;
import com.smartVisitor.avand.Holders.ItemClickListener;
import com.smartVisitor.avand.activities.CustomerDetailActivity;
import com.smartVisitor.avand.activities.OrderActivity;
import com.smartVisitor.avand.entities.Customer;

import java.util.List;
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerRecyclerViewHolder> {
    private List<Customer> customers;
    private Context context;
    private  boolean CallFromOrder;
    public CustomerListAdapter(Context context, List<Customer> customers ) {
        this.context = context;
        this.customers = customers;
        this.CallFromOrder =  false;
    }
    public CustomerListAdapter(Context context, List<Customer> customers ,boolean CallFromOrder) {
        this.context = context;
        this.customers = customers;
        this.CallFromOrder =  CallFromOrder;
    }

    @NonNull
    @Override
    public CustomerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_customer_list, parent,false);
        return new CustomerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerRecyclerViewHolder holder, int i) {
        Customer obj = this.customers.get(i);
        holder.tv_CustomerId.setText(Integer.toString(obj.getId()));
        holder.tv_CustomerCode.setText(obj.getCode());
        holder.tv_CustomerName.setText(obj.getName());
        holder.tv_CustomerDetail.setText(obj.getDescription());
        holder.tv_CustomerPhone.setText(obj.getTel());
        holder.tv_CustomerMobile.setText(obj.getMobile ());
        holder.tv_CustomerMail.setText(obj.getMail());
        //======================== Event Listener =============================================
        holder.tv_CustomerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "no perm"  , Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = holder.tv_CustomerPhone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.tv_CustomerMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "no perm"  , Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = holder.tv_CustomerMobile.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Customer selectedCustomer = customers.get(position);
                if (isLongClick) {
                    Intent intent = new Intent(context, CustomerDetailActivity.class);
                    intent.putExtra("Customer_Id", selectedCustomer.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    if (  CallFromOrder  ){
                        Intent intent = new Intent(context, OrderActivity.class);
                        intent.putExtra("Customer" , selectedCustomer);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {
                        Toast.makeText(context, "Click " + selectedCustomer.getName(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.customers.size();
    }
}

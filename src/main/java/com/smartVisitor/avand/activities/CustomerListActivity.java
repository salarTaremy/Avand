package com.smartVisitor.avand.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.adapters.CustomerListAdapter;
import com.smartVisitor.avand.database.CustomerDbHelper;
import com.smartVisitor.avand.entities.Customer;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private SearchView sv;
    private  boolean CallFromOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        this.CallFromOrder =  getIntent().getBooleanExtra("CallFromOrder" , false);
        initView();
        loadData(null);
        //=================================================================
        SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadData(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText);
                return false;
            }
        });
    }

    private void initView() {
        this.rv = findViewById(R.id.rv_Customer);
        this.sv = findViewById(R.id.searchViewCustomer);
    }

    private void loadData(String keyword) {
        CustomerDbHelper db = new CustomerDbHelper(getApplicationContext());
        List<Customer> lst ;
        if(keyword == null){
            lst= db.findAll();
        }
        else {
            lst= db.search(keyword);
        }
        CustomerListAdapter adapter = new CustomerListAdapter(getApplicationContext(), lst,this.CallFromOrder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.rv.setAdapter(null);
        if (lst != null) {
            this.rv.setLayoutManager(layoutManager);
            this.rv.setItemAnimator(new DefaultItemAnimator());
            this.rv.setAdapter(adapter);
        }
        else {
            this.rv.setAdapter(null);
        }
    }

}

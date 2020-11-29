package com.waterbottle.Activity.addresslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.waterbottle.R;

public class AddressListActivity extends AppCompatActivity {
    AddressListViewModel addressListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        addressListViewModel= ViewModelProviders.of(this).get(AddressListViewModel.class);


    }
}
package com.example.pizzaapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.pizzaapplication.databinding.ActivityPizzaStoreDetailBinding;
import com.example.pizzaapplication.datas.PizzaStore;

public class PizzaStoreDetailActivity extends BaseActivity {

    ActivityPizzaStoreDetailBinding binding;

    PizzaStore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pizza_store_detail);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        mStore = (PizzaStore) getIntent().getSerializableExtra("store");

        binding.nameTxt.setText(mStore.getName());
        binding.phoneNumTxt.setText(mStore.getPhoneNum());

        Glide.with(mContext).load(mStore.getLogoImgUrl()).into(binding.logoImg);

    }
}

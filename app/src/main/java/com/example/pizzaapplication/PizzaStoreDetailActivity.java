package com.example.pizzaapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.pizzaapplication.databinding.ActivityPizzaStoreDetailBinding;
import com.example.pizzaapplication.datas.PizzaStore;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.security.PublicKey;
import java.util.List;

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

        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener pl = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        String phoneNum = binding.phoneNumTxt.getText().toString();
                        Uri myUri = Uri.parse(String.format("tel:%s", phoneNum));
                        Intent myIntent = new Intent(Intent.ACTION_CALL, myUri);
                        startActivity(myIntent);

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                    }
                };

                TedPermission
                        .with(mContext)
                        .setPermissionListener(pl)
                        .setDeniedMessage("거부하면 통화가 불가능함. \n 설정에서 권한을 켜주세요.")
//                        줄바꿈은 \n 으로 가능함.
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();

            }
        });

    }

    @Override
    public void setValues() {

        mStore = (PizzaStore) getIntent().getSerializableExtra("store");

        binding.nameTxt.setText(mStore.getName());
        binding.phoneNumTxt.setText(mStore.getPhoneNum());

        Glide.with(mContext).load(mStore.getLogoImgUrl()).into(binding.logoImg);

    }
}

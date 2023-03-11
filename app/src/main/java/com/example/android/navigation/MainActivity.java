/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.android.navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements RateUsFragment.Listener {

    public static final String RATED = "rated";
    private NavController mNavController;
    private com.example.android.navigation.databinding.ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) fm.findFragmentById(R.id.fragmentContainerView);
        mNavController = navHostFragment.getNavController();
        mNavController.navigate(R.id.rateUsFragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mBinding.drawerLayout);
        NavigationUI.setupWithNavController(mBinding.navView, mNavController);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if (!preferences.getBoolean(RATED, false)) {
            mNavController.navigate(R.id.rateUsFragment);
            preferences.edit().putBoolean(RATED, true).apply();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mBinding.drawerLayout);
    }

    @Override
    public void onClick(float rating) {
        String msg;
        if (rating > 4) {
            msg = "This is going to be easy!";
        } else {
            msg = "Heh, good luck with this...";
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

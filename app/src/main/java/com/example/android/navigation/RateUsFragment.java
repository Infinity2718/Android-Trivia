package com.example.android.navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.android.navigation.databinding.RateUsBinding;

public class RateUsFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private com.example.android.navigation.databinding.RateUsBinding mBinding;

    interface Listener {
        void onClick(float rating);
    }

    Listener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mBinding = RateUsBinding.inflate(getLayoutInflater());
        return new AlertDialog.Builder(getContext())
                .setMessage(R.string.rate_us)
                .setView(mBinding.getRoot())
                .setPositiveButton("Submit", this)
                .setNegativeButton("Cancel", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {
        float rating = mBinding.ratingBar.getRating();
        mListener.onClick(rating);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }
}

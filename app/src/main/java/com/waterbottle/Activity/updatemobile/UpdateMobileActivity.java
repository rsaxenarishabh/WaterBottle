package com.waterbottle.Activity.updatemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.waterbottle.Activity.login.LoginActivity;
import com.waterbottle.R;
import com.waterbottle.databinding.ActivityUpdateMobileBinding;
import com.waterbottle.utils.AmplefreshPrefs;
import com.waterbottle.utils.Constants;
import com.waterbottle.utils.Utils;

public class UpdateMobileActivity extends AppCompatActivity {

    UpdateMobileViewModel updateMobileViewModel;
    ActivityUpdateMobileBinding binding;
    String userId = "", userMobile = "", newMobile = "";
    private static final String TAG = "UpdateMobileActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_mobile);
        updateMobileViewModel = ViewModelProviders.of(this).get(UpdateMobileViewModel.class);

        updateMobileViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Utils.showProgressDialog(UpdateMobileActivity.this);
                } else {
                    Utils.dismissProgressDialog();
                }
            }
        });
        updateMobileViewModel.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(UpdateMobileActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });


        binding.updateBtnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.updateEdtMobile.getText().toString().trim().equalsIgnoreCase("")) {
                    ErrorAlertDialog("Field can't be empty");
                    binding.updateEdtMobile.requestFocus();
                    return;
                } else if (binding.updateEdtMobile.getText().toString().trim().length() <= 9) {
                    //  Dphone.setError("Enter valid Phone No.");
                    ErrorAlertDialog("Please Enter Valid Phone No");
                    binding.updateEdtMobile.requestFocus();
                    return;
                } else if (!Utils.isValid(binding.updateEdtMobile.getText().toString().trim())) {
                    ErrorAlertDialog("Please Enter Valid Phone no");
                    binding.updateEdtMobile.requestFocus();
                    return;
                } else {
                    userId = AmplefreshPrefs.getString(getApplicationContext(), Constants.USER_ID);
                    userMobile = AmplefreshPrefs.getString(getApplicationContext(), Constants.MOBILE_NO);
                    newMobile = binding.updateEdtMobile.getText().toString().trim();
                    updateMobileViewModel.setAction(userId, userMobile, newMobile);
                }

            }
        });

        updateMobileViewModel.getUpdateData().observe(this, new Observer<UpdateMobileResponse>() {
            @Override
            public void onChanged(UpdateMobileResponse updateMobileResponse) {
                if (updateMobileResponse != null && updateMobileResponse.getStatusCode() == 200) {
                    {
                        Log.d(TAG, "onChanged: Success");
                        alertDialog(newMobile, updateMobileResponse.getOtp());
                    }
                }
            }
        });
        updateMobileViewModel.getUpdateOtpData().observe(this, new Observer<UpdateMobileUsingOtpResponse>() {
            @Override
            public void onChanged(UpdateMobileUsingOtpResponse updateMobileUsingOtpResponse) {
                if (updateMobileUsingOtpResponse != null) {
                    AmplefreshPrefs.putString(getApplicationContext(), Constants.MOBILE_NO,
                            updateMobileUsingOtpResponse.getUser().getMobile());
                    finish();
                }
            }
        });


    }

    private void alertDialog(final String mobileNo, final Integer otp) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.otp_layout_view, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        final EditText edtOtp = dialogView.findViewById(R.id.edtotp);

        TextView textView = dialogView.findViewById(R.id.txtphone);
        textView.setText("91-" + mobileNo);
        TextView textViewVerify = dialogView.findViewById(R.id.validate);
        dialog.setCancelable(false);
        edtOtp.setText("" + otp);
        textViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOtp.getText().toString().equalsIgnoreCase("")) {
                    ErrorAlertDialog("Field can't be empty");
                    return;
                } else if (edtOtp.getText().toString().length() <= 5) {
                    ErrorAlertDialog("Otp Lenght should be 6 digit");
                    return;
                } else {
                    updateMobileViewModel.setOtpAction(newMobile, edtOtp.getText().toString().trim());
                }

            }
        });


        dialog.show();

    }


    public void ErrorAlertDialog(String Error) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView texterror = dialogView.findViewById(R.id.txtError);
        ImageView txtOk = dialogView.findViewById(R.id.imagecancel);
        dialog.setCancelable(false);
        texterror.setText("" + Error);
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }
}
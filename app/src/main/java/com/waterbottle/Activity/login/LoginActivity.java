package com.waterbottle.Activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Intent;
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

import com.waterbottle.DashboardActivity;
import com.waterbottle.R;
import com.waterbottle.databinding.ActivityLoginBinding;
import com.waterbottle.utils.AmplefreshPrefs;
import com.waterbottle.utils.Constants;
import com.waterbottle.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    LoginViewModel loginViewModel;
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding binding;
    String mobile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = binding.edtMobile.getText().toString().trim();
                if (mobile.equalsIgnoreCase("")) {
                    binding.edtMobile.setError("Field can't be empty");
                    ErrorAlertDialog("Please Enter Valid Phone No");
                    binding.edtMobile.requestFocus();
                    return;
                } else if (mobile.length() <= 9) {
                    //  Dphone.setError("Enter valid Phone No.");
                    ErrorAlertDialog("Please Enter Valid Phone No");
                    binding.edtMobile.requestFocus();
                    return;
                } else if (!Utils.isValid(mobile)) {
                    ErrorAlertDialog("Please Enter Valid Phone no");
                    binding.edtMobile.requestFocus();
                    return;
                } else {
                    loginViewModel.setAction(mobile);

                }
            }
        });

        loginViewModel.getDataValid().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    if (loginResponse.getStatusCode() == 200) {
                        Log.d(TAG, "onChanged: Success");
                        alertDialog(loginResponse.getOtp(), mobile);
                    }
                }
            }
        });
        loginViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Utils.showProgressDialog(LoginActivity.this);
                } else {
                    Utils.dismissProgressDialog();
                }
            }
        });
        loginViewModel.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.getOtpResponse().observe(this, new Observer<LoginResponseOtp>() {
            @Override
            public void onChanged(LoginResponseOtp loginResponseOtp) {
                if (loginResponseOtp != null && loginResponseOtp.getStatusCode() == 200) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    AmplefreshPrefs.putBoolean(getApplicationContext(), Constants.LOGIN_CHECK, true);
                    AmplefreshPrefs.putString(getApplicationContext(), Constants.MOBILE_NO, loginResponseOtp.getUser().getMobile());
                    AmplefreshPrefs.putString(getApplicationContext(), Constants.USER_ID, String.valueOf(loginResponseOtp.getUser().getId()));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void alertDialog(Integer otp, String mobile) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.otp_layout_view, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        final EditText edtOtp = dialogView.findViewById(R.id.edtotp);
        TextView textView = dialogView.findViewById(R.id.txtphone);
        textView.setText("91-" + mobile);
        edtOtp.setText("" + otp);
        TextView textViewVerify = dialogView.findViewById(R.id.validate);
        dialog.setCancelable(false);
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
                    loginViewModel.setActionOtp(edtOtp.getText().toString().trim(), mobile);
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
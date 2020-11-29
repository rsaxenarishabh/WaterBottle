package com.waterbottle.Fragment.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.waterbottle.Activity.login.LoginActivity;
import com.waterbottle.Activity.productactivity.ProductActivity;
import com.waterbottle.Activity.search.SearchActivity;
import com.waterbottle.R;
import com.waterbottle.databinding.FragmentDashboardBinding;
import com.waterbottle.utils.AmplefreshPrefs;
import com.waterbottle.utils.Constants;
import com.waterbottle.utils.Utils;

import static android.content.ContentValues.TAG;

public class DashboardFragment extends Fragment {
    DashboardFragmentViewModel fragmentViewModel;
    FragmentDashboardBinding binding;
    String userId = "", fcmToken = "";
    HomeItemAdapter homeItemAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dashboard, container, false);

        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider

        fragmentViewModel = ViewModelProviders.of(this.getActivity()).get(DashboardFragmentViewModel.class);
        if (AmplefreshPrefs.getString(getActivity(), Constants.USER_ID) != null) {
            userId = AmplefreshPrefs.getString(getActivity(), Constants.USER_ID);
        }


        binding.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        if (Utils.isInternetConnected(getActivity())) {
            fcmToken="asjdhajdhkashk";
            fragmentViewModel.setAction(userId, fcmToken);
            Log.d(TAG, "onCreateView: "+userId);
        } else {
            Toast.makeText(getActivity(), "" + R.string.internet_failed, Toast.LENGTH_SHORT).show();
        }


        fragmentViewModel.homeResponseLiveData().observe(getActivity(), new Observer<HomeResponse>() {
            @Override
            public void onChanged(HomeResponse homeResponse) {
                if (homeResponse != null) {
                    homeItemAdapter = new HomeItemAdapter(getActivity(), homeResponse.getData().getCategories());
                    LinearLayoutManager layoutManager =
                            new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                    // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    homeItemAdapter.setOnItemClickData(new HomeItemAdapter.onItemClickData() {
                        @Override
                        public void onCategoryDataUsingId(int id, String name) {
                            Intent intent = new Intent(getActivity(), ProductActivity.class);
                            intent.putExtra(Constants.CATEGORY_ID, id);
                            intent.putExtra(Constants.CATEGORY_NAME, name);
                            startActivity(intent);
                        }
                    });
                    binding.recyclerProduct.setLayoutManager(layoutManager);
                    binding.recyclerProduct.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerProduct.setAdapter(homeItemAdapter);

                }
            }
        });
        fragmentViewModel.getProgress().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Utils.showProgressDialog(getActivity());
                } else {
                    Utils.dismissProgressDialog();
                }
            }
        });
        fragmentViewModel.getStatus().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), "" + s, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

package com.waterbottle.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.waterbottle.R;


public abstract class BaseFragment extends Fragment {
    protected Toolbar toolbar;
//    protected ProgressDialogFragment progressDialogFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    public void replaceFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        ((BaseActivity) getActivity()).addReplaceFragment(fragmentName, bundle, container, addToBackStack, true);
    }

    public void addFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        ((BaseActivity) getActivity()).addReplaceFragment(fragmentName, bundle, container, addToBackStack, false);
    }

    public void replaceChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        addReplaceChildFragment(fragmentName, bundle, container, addToBackStack, true);
    }

    public void addChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack) {
        addReplaceChildFragment(fragmentName, bundle, container, addToBackStack, false);
    }


    /**
     * This method used to add/replace fragment on desire container with bundle and back stack status.
     *
     * @param fragmentName
     * @param bundle
     * @param container
     * @param addToBackStack
     * @param isReplace
     */
    public void addReplaceChildFragment(String fragmentName, Bundle bundle, int container, boolean addToBackStack, boolean isReplace) {

        try {
            Fragment fragment = Fragment.instantiate(getActivity(), fragmentName, bundle);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            if (isReplace) {
                transaction.replace(container, fragment, fragmentName);
            } else {
                transaction.add(container, fragment, fragmentName);
            }
            transaction.addToBackStack(addToBackStack == true ? fragment.getClass().getName() : null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void popUpFragment(String tag) {
        hideSoftKeyboard(getActivity());
        try {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("popUpFragment: ", e.getMessage());
        }
    }

    public void hideSoftKeyboard(Context context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            // check if no view has focus:
            View v = ((Activity) context).getCurrentFocus();
            if (v == null)
                return;

            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("hideSoftKeyboard: ", e.getMessage());
        }
    }


    public String jsonToString(Object jsonObject) {
        return ((BaseActivity) getActivity()).jsonToString(jsonObject);
    }

    public Object stringToJson(String data, Object object) {
        return ((BaseActivity) getActivity()).stringToJson(data, object);
    }




    public boolean checkInternetConnection() {
        return ((BaseActivity) getActivity()).checkInternetConection();
    }

    public void addFragmentWithStack(int containerId, Fragment fragment, String name) {
        try {

            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(containerId, fragment, name);
            if (!fragment.isAdded()) {
                fragmentTransaction.addToBackStack(name);
                fragmentTransaction.commitAllowingStateLoss();

            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void replaceFragmentWithStack(int containerId, Fragment fragment, String name) {
        try {
            FragmentManager fragmentManager =getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, fragment, name);

            if (!fragment.isAdded()) {
                fragmentTransaction.addToBackStack(name);
                fragmentTransaction.commitAllowingStateLoss();

            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


    }


}

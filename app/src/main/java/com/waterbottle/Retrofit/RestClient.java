package com.waterbottle.Retrofit;


import com.waterbottle.Activity.login.LoginRequest;
import com.waterbottle.Activity.login.LoginRequestOtp;
import com.waterbottle.Activity.login.LoginResponse;
import com.waterbottle.Activity.login.LoginResponseOtp;
import com.waterbottle.Activity.updatemobile.UpdateMobileRequest;
import com.waterbottle.Activity.updatemobile.UpdateMobileResponse;
import com.waterbottle.Activity.updatemobile.UpdateMobileUsingOtpRequest;
import com.waterbottle.Activity.updatemobile.UpdateMobileUsingOtpResponse;
import com.waterbottle.Fragment.dashboard.HomeRequest;
import com.waterbottle.Fragment.dashboard.HomeResponse;

import retrofit2.Callback;

public class RestClient {



    public static void mobileVerify(LoginRequest mobileRequest, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().mobileVerify(mobileRequest).enqueue(callback);
    }


    public static void mobileVerifyUsingOtp(LoginRequestOtp loginRequestOtp, Callback<LoginResponseOtp> callback) {
        RetrofitClient.getClient().mobileVerifyUsingOtp(loginRequestOtp).enqueue(callback);
    }

    public static void homeData(HomeRequest homeRequest, Callback<HomeResponse> callback) {
        RetrofitClient.getClient().homeData(homeRequest).enqueue(callback);
    }

    public static void updateMobile(UpdateMobileRequest updateMobileRequest, Callback<UpdateMobileResponse> callback) {
        RetrofitClient.getClient().updateMobile(updateMobileRequest).enqueue(callback);
    }


    public static void verifyMobileUsingOtp(UpdateMobileUsingOtpRequest updateMobileUsingOtpRequest, Callback<UpdateMobileUsingOtpResponse> callback) {
        RetrofitClient.getClient().VerifyMobileUsingOtp(updateMobileUsingOtpRequest).enqueue(callback);
    }


//
//    public static void getHomeListData(HomeRequest homeRequest, Callback<HomeListResponse> callback) {
//        RetrofitClient.getClient().getHomeListData(homeRequest).enqueue(callback);
//    }
//
//    public static void getCategoryListData(CategoryRequest categoryRequest, Callback<CategoryListResponse> callback) {
//        RetrofitClient.getClient().getCategoryList(categoryRequest).enqueue(callback);
//    }
//
//
//    public static void addCart(AddCartRequest addCartRequest, Callback<AddCartResponse> callback) {
//        RetrofitClient.getClient().addCart(addCartRequest).enqueue(callback);
//    }
//
//
//
//    public static void otpVerify(MobileVerifyRequest mobileRequest, Callback<MobileVerifyResponse> callback) {
//        RetrofitClient.getClient().mobileOtpVerify(mobileRequest).enqueue(callback);
//    }
//
//    public static void addressSave(AddressSaveRequest addressSaveRequest, Callback<AddressSaveResponse> callback) {
//        RetrofitClient.getClient().addressSave(addressSaveRequest).enqueue(callback);
//    }
//
//    public static void getPincode(Callback<PincodeResponse> callback) {
//        RetrofitClient.getClient().getPincode().enqueue(callback);
//    }
//
//    public static void getAddressList(AddresslistRequest addresslistRequest, Callback<AddresslistResponse> callback) {
//        RetrofitClient.getClient().getAddressList(addresslistRequest).enqueue(callback);
//    }
//
//    public static void getCartList(CardDetailsRequest cardDetailsRequest, Callback<CardDetailsResponse> callback) {
//        RetrofitClient.getClient().getCartList(cardDetailsRequest).enqueue(callback);
//    }
//
//
//    public static void addressDelete(AddressDeleteRequest addressDeleteRequest, Callback<AddressDeleteResponse> callback) {
//        RetrofitClient.getClient().addressDelete(addressDeleteRequest).enqueue(callback);
//    }
//
//
//    public static void cartRemove(CartRemoveRequest cartRemoveRequest, Callback<CartRemoveResponse> callback) {
//        RetrofitClient.getClient().cartRemove(cartRemoveRequest).enqueue(callback);
//    }
//
//
//    public static void sendOrder(OrderRequest orderRequest, Callback<OrderResponse> callback) {
//        RetrofitClient.getClient().sendOrder(orderRequest).enqueue(callback);
//    }
//
//
//    public static void updateMobile(UpdateMobileRequest updateMobileRequest, Callback<MobileResponse> callback) {
//        RetrofitClient.getClient().updateMobile(updateMobileRequest).enqueue(callback);
//    }
//
//
//    public static void verifyUpdatemobile(VerifyMobileRequest verifyMobileRequest, Callback<MobileVerifyResponse> callback) {
//        RetrofitClient.getClient().updateVerify(verifyMobileRequest).enqueue(callback);
//    }
//
//
//    public static void getMyOrders(MyOrderRequest orderRequest, Callback<MyOrderResponse> callback) {
//        RetrofitClient.getClient().getMyOrders(orderRequest).enqueue(callback);
//    }
//
//    public static void getDeliveredOrders(MyOrderRequest orderRequest, Callback<MyOrderResponse> callback) {
//        RetrofitClient.getClient().getMyDeliveredOrders(orderRequest).enqueue(callback);
//    }
//
//
//
//    public static void getOrderDetails(OrderDetailRequest orderDetailRequest, Callback<OrderDetailResponse> callback) {
//        RetrofitClient.getClient().getOrderDetails(orderDetailRequest).enqueue(callback);
//    }
//
//
//    public static void updateAddress(AddressEditRequest addressEditRequest, Callback<AddressEditResponse> callback) {
//        RetrofitClient.getClient().updateAddress(addressEditRequest).enqueue(callback);
//    }


}


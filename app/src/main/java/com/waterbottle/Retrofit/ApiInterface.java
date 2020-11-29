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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("http://15.207.46.113/waterbottle/public/api/login-register")
    Call<LoginResponse> mobileVerify(@Body LoginRequest loginRequest);


    @POST("http://15.207.46.113/waterbottle/public/api/verify-otp")
    Call<LoginResponseOtp> mobileVerifyUsingOtp(@Body LoginRequestOtp loginRequestOtp);

    @POST("http://15.207.46.113/waterbottle/public/api/home")
    Call<HomeResponse> homeData(@Body HomeRequest homeRequest);



    @POST("http://15.207.46.113/waterbottle/public/api/update-mobile")
    Call<UpdateMobileResponse> updateMobile(@Body UpdateMobileRequest updateMobileRequest);




    @POST("http://15.207.46.113/waterbottle/public/api/verify-mobile")
    Call<UpdateMobileUsingOtpResponse> VerifyMobileUsingOtp(@Body UpdateMobileUsingOtpRequest updateMobileUsingOtpRequest);


    /*

    @POST("http://15.207.46.113/api/home")
    Call<HomeListResponse> getHomeListData(@Body HomeRequest homeRequest);


    @POST("http://15.207.46.113/api/get-products")
    Call<CategoryListResponse> getCategoryList(@Body CategoryRequest categoryRequest);

    @POST("http://15.207.46.113/api/cart")
    Call<AddCartResponse> addCart(@Body AddCartRequest addCartRequest);

    @POST("http://15.207.46.113/api/login-register")
    Call<MobileResponse> mobileVerify(@Body MobileRequest mobileRequest);

    @POST("http://15.207.46.113/api/verify-otp")
    Call<MobileVerifyResponse> mobileOtpVerify(@Body MobileVerifyRequest mobileVerifyRequest);


    @POST("http://15.207.46.113/api/add-address")
    Call<AddressSaveResponse> addressSave(@Body AddressSaveRequest addressSaveRequest);


    @GET("http://15.207.46.113/api/get-pincodes")
    Call<PincodeResponse> getPincode();

    @POST("http://15.207.46.113/api/get-address")
    Call<AddresslistResponse> getAddressList(@Body AddresslistRequest addresslistRequest);


    @POST("http://15.207.46.113/api/get-cart")
    Call<CardDetailsResponse> getCartList(@Body CardDetailsRequest cardDetailsRequest);


    @POST("http://15.207.46.113/api/delete-address")
    Call<AddressDeleteResponse> addressDelete(@Body AddressDeleteRequest addressDeleteRequest);

    @POST("http://15.207.46.113/api/cart")
    Call<CartRemoveResponse> cartRemove(@Body CartRemoveRequest cartRemoveRequest);


    @POST("http://15.207.46.113/api/order")
    Call<OrderResponse> sendOrder(@Body OrderRequest orderRequest);

    @POST("http://15.207.46.113/api/update-mobile")
    Call<MobileResponse> updateMobile(@Body UpdateMobileRequest updateMobileRequest);

    @POST("http://15.207.46.113/api/verify-mobile")
    Call<MobileVerifyResponse> updateVerify(@Body VerifyMobileRequest verifyMobileRequest);

    @POST("http://15.207.46.113/api/get-orders")
    Call<MyOrderResponse> getMyOrders(@Body MyOrderRequest orderRequest);

    @POST("http://15.207.46.113/api/get-deliveredorders")
    Call<MyOrderResponse> getMyDeliveredOrders(@Body MyOrderRequest orderRequest);




    @POST("http://15.207.46.113/api/order-detail")
    Call<OrderDetailResponse> getOrderDetails(@Body OrderDetailRequest orderDetailRequest);


    @POST("http://15.207.46.113/api/update-address")
    Call<AddressEditResponse> updateAddress(@Body AddressEditRequest addressEditRequest);

*/


}
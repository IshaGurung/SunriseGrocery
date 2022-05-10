package com.ishagurung.sunrisegrocery.api;

import com.ishagurung.sunrisegrocery.api.response.AddressResponse;
import com.ishagurung.sunrisegrocery.api.response.AllProductResponse;
import com.ishagurung.sunrisegrocery.api.response.CategoryResponse;
import com.ishagurung.sunrisegrocery.api.response.DashResponse;
import com.ishagurung.sunrisegrocery.api.response.LoginResponse;
import com.ishagurung.sunrisegrocery.api.response.OrderHistoryResponse;
import com.ishagurung.sunrisegrocery.api.response.RegisterResponse;
import com.ishagurung.sunrisegrocery.api.response.SingleProductResponse;
import com.ishagurung.sunrisegrocery.api.response.SliderResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/register")
    Call<RegisterResponse> register(@Field("name") String names, @Field("email") String email, @Field("password") String password);

    @GET("/api/v1/slider")
    Call<SliderResponse> getSliders();

    @GET("/api/v1/get-categories")
    Call<CategoryResponse> getCategories();

    @GET("/api/v1/get-all-products")
    Call<AllProductResponse> getAllProducts();

    @GET("/api/v1/get-products-by-category")
    Call<AllProductResponse> getProductsByCategory(@Query("c_id") int catID);

    @GET("/api/v1/get-all-products")
    Call<SingleProductResponse> getProductById(@Query("id") int c_id);

    @Multipart
    @POST("/api/v1/upload-category")
    Call<RegisterResponse> uploadCategory(
            @Header("Apikey") String apikey,
            @Part MultipartBody.Part file,
            @Part("name") RequestBody name

    );
    @FormUrlEncoded
    @POST("/api/v1/cart")
    Call<RegisterResponse> addToCart(@Header("Apikey") String apikey, @Field("p_id") int p, @Field("quantity") int q);

    @DELETE("/api/v1/category")
    Call<RegisterResponse> deleteCategory(@Header("Apikey") String apikey, @Query("c_id") int id);


    @GET("/api/v1/cart")
    Call<AllProductResponse> getMyCart(@Header("Apikey") String apikey);

    @FormUrlEncoded
    @POST("/api/v1/order")
    Call<RegisterResponse> order(@Header("Apikey") String apikey,
                                 @Field("p_type") int p_type,
                                 @Field("address_id") int address_id,
                                 @Field("payment_refrence") String paymentRefrence);

    @GET("/api/v1/order")
    Call<OrderHistoryResponse> orderHistory(@Header("Apikey") String apikey
    );



    @DELETE("/api/v1/cart")
    Call<RegisterResponse> deleteFromCart(@Header("Apikey") String apikey, @Query("c_id") int cartID);




    @GET("/api/v1/address")
    Call<AddressResponse> getMyAddresses(@Header("Apikey") String apikey);

    @FormUrlEncoded
    @POST("/api/v1/address")
    Call<RegisterResponse> addAddress(
            @Header("Apikey") String apikey,
            @Field("city") String city,
            @Field("street") String street,
            @Field("province") String province,
            @Field("description") String description);


    @GET("/api/v1/dash")
    Call<DashResponse> getDash(@Header("Apikey") String apikey);




}

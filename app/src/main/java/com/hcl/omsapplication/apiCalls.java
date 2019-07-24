package com.hcl.omsapplication;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiCalls {

    @GET("posts")
    Call<List<Post>> getPosts();

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @POST("login")
    Call<LoginStatus> loginPost(@Body LoginPost loginPost);

    @POST("orders/new")
    Call<CreateOrderStatus> createOrderPost(@Body JsonObject createOrder);
}
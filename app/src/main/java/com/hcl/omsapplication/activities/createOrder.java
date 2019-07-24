package com.hcl.omsapplication.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.Geofence;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hcl.omsapplication.models.createOrder.createOrderStatus;
import com.hcl.omsapplication.misc.Post;
import com.hcl.omsapplication.R;
import com.hcl.omsapplication.services.apiCalls;

import java.util.List;

import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createOrder extends AppCompatActivity {

    private TextView textViewResult;
    private apiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        TextView welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome: " + username);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://d0faf90d.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiCalls = retrofit.create(apiCalls.class);

        addGeofencing();
        startLocationListener();
        System.out.println("Here");
//        getPosts();
//        createPost();

    }

    private void addGeofencing(){
        GeofenceModel shipnode = new GeofenceModel.Builder("id_shinode")
                .setTransition(Geofence.GEOFENCE_TRANSITION_ENTER)
                .setLatitude(37.4219983)
                .setLongitude(-122.084)
                .setRadius(50)
                .build();

        SmartLocation.with(this).geofencing()
                .add(shipnode)
                .start(new OnGeofencingTransitionListener() {
                    @Override
                    public void onGeofenceTransition(TransitionGeofence fence) {
                        fence.notify();
                    }
                });
    }

    private void startLocationListener() {

        long mLocTrackingInterval = 1000 * 5; // 5 sec
        float trackingDistance = 0;
        LocationAccuracy trackingAccuracy = LocationAccuracy.HIGH;

        LocationParams.Builder builder = new LocationParams.Builder()
                .setAccuracy(trackingAccuracy)
                .setDistance(trackingDistance)
                .setInterval(mLocTrackingInterval);

        SmartLocation.with(this)
                .location()
                .continuous()
                .config(builder.build())
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        System.out.println(location.getLatitude());
                        System.out.println(location.getLongitude());
                    }
                });
    }

    public void createOrder(View view) {
        createOrderPost();
    }

    private JsonObject createOrderForm(){
        JsonObject paramObject = new JsonObject();
        JsonObject items = new JsonObject();
        JsonObject quantity = new JsonObject();
        JsonObject price = new JsonObject();
        JsonArray itemList = new JsonArray();
        items.addProperty("itemid", 1);
        items.addProperty("subtotal", 149);
        itemList.add(items);

        JsonArray quantityList = new JsonArray();
        quantity.addProperty("itemid", 1);
        quantity.addProperty("quantity", 2);
        quantityList.add(quantity);

        JsonArray priceList = new JsonArray();
        price.addProperty("itemid", 1);
        price.addProperty("price", 2);
        priceList.add(price);
        paramObject.addProperty("address", "123 Main St");
        paramObject.addProperty("channel", "asasas");
        paramObject.addProperty("city", "Frisco");
        paramObject.addProperty("username", "pat_abh");
        paramObject.addProperty("date", "2019-07-23T18:12:48.422Z");
        paramObject.addProperty("firstname", "Abhi");
        paramObject.add("items", itemList);
        paramObject.addProperty("lastname", "Patil");
        paramObject.addProperty("ordertype", "Austin");
        paramObject.addProperty("payment", "Credit");
        paramObject.add("quantity", quantityList);
        paramObject.add("price", priceList);
        paramObject.addProperty("shipnode", "Austin");
        paramObject.addProperty("state", "Texas");
        paramObject.addProperty("total", 200);
        paramObject.addProperty("zip", "75080");

        System.out.println(paramObject);

        return paramObject;
    }

    private void createOrderPost() {

        JsonObject paramObject = createOrderForm();

        Call<createOrderStatus> call = apiCalls.createOrderPost(paramObject);

        SmartLocation.with(this).location().stop();
        call.enqueue(new Callback<createOrderStatus>() {
            @Override
            public void onResponse(Call<createOrderStatus> call, Response<createOrderStatus> response) {
                System.out.println(response);
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                createOrderStatus status = response.body();

                if (status.success) {
                    textViewResult.setText("Success");
                } else
                    textViewResult.setText("Failure");
            }

            @Override
            public void onFailure(Call<createOrderStatus> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
    }

    private void getPosts() {
        Call<List<Post>> call = apiCalls.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
    }

    private void createPost() {

        Post post = new Post(23, "Title", "Text");

        Call<Post> call = apiCalls.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User Id: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
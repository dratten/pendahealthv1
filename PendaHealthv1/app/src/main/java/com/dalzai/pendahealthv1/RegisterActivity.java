package com.dalzai.pendahealthv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText fname, sname, email, password;
    ImageButton image;
    String encodedImage;
    private Uri profileImageUri = null ;
    private final static int GALLERY_REQ = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = findViewById (R.id.fname);
        sname = findViewById (R.id.sname);
        email = findViewById (R.id.email);
        password = findViewById (R.id.password);
        image = findViewById (R.id.image);
        image.setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent galleryIntent = new Intent ( Intent . ACTION_GET_CONTENT );
                galleryIntent . setType ( "image/*" );
                startActivityForResult ( galleryIntent , GALLERY_REQ );
            }
        });
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "https://penda-healthv1.herokuapp.com/api/register";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            })
    }

    protected void onActivityResult ( int requestCode , int resultCode , Intent data ) {
        super . onActivityResult ( requestCode , resultCode , data );
        if ( requestCode == GALLERY_REQ && resultCode == RESULT_OK ) {
            profileImageUri = data . getData ();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), profileImageUri);
                Bitmap lastBitmap = null;
                lastBitmap = bitmap;
                //encoding image to string
                String image = getStringImage(lastBitmap);
                Log.d("image",image);
                //passing the image to volley
                SendImage(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}

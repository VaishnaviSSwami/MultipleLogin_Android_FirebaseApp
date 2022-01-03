package com.example.foods;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class chef_add_menu extends AppCompatActivity {
    ImageView food_image;
    FloatingActionButton Btn_add_food_image;
    private EditText name_of_food, desco , quanto, prize;
    private Button btn_ADD_MENU_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_add_menu);
                name_of_food = findViewById(R.id.name_of_food);
                desco = findViewById(R.id.desco);
                quanto = findViewById(R.id.quanto);
                prize = findViewById(R.id.prize);
        btn_ADD_MENU_submit = findViewById(R.id.btn_ADD_MENU_submit);
        food_image=findViewById(R.id.food_image);
        Btn_add_food_image=findViewById(R.id.Btn_add_food_image);
        btn_ADD_MENU_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cheffood = name_of_food.getText().toString();
                String chefdesc = desco.getText().toString();
                String chefquant = quanto.getText().toString();
                String foodprize = prize.getText().toString();
                Intent i4 = new Intent(chef_add_menu.this,chef_view_menu.class);

                i4.putExtra("Foodkanam",cheffood);
                i4.putExtra("desckanam",chefdesc);
                i4.putExtra("quantitykanam",chefquant);
                i4.putExtra("pricekanam",foodprize);
                startActivity(i4);
            }
        });
        Btn_add_food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              chooseProfilePicture();
            }
        });

    }
    private void chooseProfilePicture(){
        AlertDialog.Builder builder =new AlertDialog.Builder(chef_add_menu.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_profile_picture,  null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageView_camera =dialogView.findViewById(R.id.imageView_camera);
        ImageView imageView_gallery = dialogView.findViewById(R.id.imageView_gallery);

        AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();

        imageView_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermission()) {

                    takePictureFromCamera();
                    alertDialogProfilePicture.cancel();
                }
            }
        });

        imageView_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureFromGallery();
                alertDialogProfilePicture.cancel();
            }
        });
    }

    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }
    private void takePictureFromCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager())!= null){
            startActivityForResult(takePicture,2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    food_image.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if(requestCode == RESULT_OK){
                 Bitmap Image = (Bitmap) data.getExtras().get("data");
                    food_image.setImageBitmap(Image);
                }
                break;
        }
    }
    private boolean checkAndRequestPermission(){
        if(Build.VERSION.SDK_INT >=23){
            int cameraPermission = ActivityCompat.checkSelfPermission(chef_add_menu.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(chef_add_menu.this, new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
         takePictureFromCamera();
        } else
            Toast.makeText(chef_add_menu.this, "Permission not Granted", Toast.LENGTH_SHORT).show();
    }
}

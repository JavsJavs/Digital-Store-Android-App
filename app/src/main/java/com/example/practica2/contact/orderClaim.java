package com.example.practica2.contact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class orderClaim extends AppCompatActivity {
    private TextView errorText;
    private EditText nameText;
    private EditText subjectText;
    private EditText billId;
    private EditText issueText;
    private boolean imageAdded;
    private ImageView userImage;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_order_claim);
        this.imageAdded = false;
        this.errorText = findViewById(R.id.claimErrorText);
        this.errorText.setText("");
        this.nameText = findViewById(R.id.editNameClaim);
        this.subjectText = findViewById(R.id.editSubjectClaim);
        this.billId = findViewById(R.id.editBillClaim);
        this.issueText = findViewById(R.id.editIssueClaim);
        this.userImage = findViewById(R.id.userImage);
        this.userImage.setImageResource(getResources().getIdentifier("add_image", "drawable", getPackageName()));
        this.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGalleryImage(v);
            }
        });
        Button addImageButton = findViewById(R.id.galleryButtonClaim);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGalleryImage(v);
            }
        });
        Button sendMailButton = findViewById(R.id.mailButtonClaim);
        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(v);
            }
        });
        Button addCameraButton = findViewById(R.id.cameraButtonClaim);
        addCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCameraImage(v);
            }
        });
        if (ContextCompat.checkSelfPermission(orderClaim.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(orderClaim.this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @SuppressLint("SetTextI18n")
    private void sendMail(View v) {
        resetText();
        if (!checkForm()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/image");
            //"tech_support@cubebusters.net"
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tech_support@cubebusters.net"});
            intent.putExtra(Intent.EXTRA_SUBJECT, String.valueOf(this.subjectText.getText()) + " (order " + String.valueOf(this.billId.getText()) + ")");
            intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(this.issueText.getText()) + "\n\n" + String.valueOf(this.nameText.getText()));
            if (this.imageAdded) {
                Log.e("OderClaim", "image added confirmed");
                intent.putExtra(Intent.EXTRA_STREAM, this.imageUri);
            }
            startActivity(intent);
        } else {
            this.errorText.setText("Please fill out every necessary field");
            Log.e("Contact Request", "Invalid form");
        }
    }

    private void addCameraImage(View v) {
        this.imageAdded = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    private void addGalleryImage(View v) {
        this.imageAdded = true;
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Pick a picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e("orderClaim", "Inside if");
            this.imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                this.userImage.setImageBitmap(bitmap);
                Log.e("orderClaim", "image set");
            } catch (IOException e) {
                Log.e("orderClaim", "Catch");
                e.printStackTrace();
            }
        } else if (requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            this.userImage.setImageBitmap(bitmap);
            this.imageUri = getImageUri(getApplicationContext(), bitmap);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void resetText() {
        this.nameText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.nameText.setHint("Write here…");
        this.subjectText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.subjectText.setHint("Write here…");
        this.billId.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.billId.setHint("Write here…");
        this.issueText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.issueText.setHint("Write here…");
        this.errorText.setText("");
    }

    @SuppressLint("SetTextI18n")
    private boolean checkForm() {
        return checkError(this.nameText) || checkError(this.subjectText) || checkError(this.billId) || checkError(this.issueText);
    }

    private boolean checkError(EditText editText) {
        boolean error = false;
        if (String.valueOf(editText.getText()).equals("")) {
            editText.setHint("This field is required");
            editText.setHintTextColor(getResources().getColor(R.color.secondary_dark));
            error = true;
        }
        return error;
    }
}
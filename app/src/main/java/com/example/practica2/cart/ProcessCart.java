package com.example.practica2.cart;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

import java.util.Random;

public class ProcessCart extends AppCompatActivity {
    private EditText editNameCart;
    private EditText editEmailCart;
    private EditText editAddressCart;
    private EditText editPhoneCart;
    private RadioGroup paymentRadio;
    private TextView errorText;
    private float totalPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.totalPrice = Float.parseFloat(getIntent().getStringExtra("total_price"));
        setContentView(R.layout.main_order);
        this.editNameCart = findViewById(R.id.editNameCart);
        this.editEmailCart = findViewById(R.id.editEmailCart);
        this.editAddressCart = findViewById(R.id.editAddressCart);
        this.editPhoneCart = findViewById(R.id.editPhoneCart);
        this.paymentRadio = findViewById(R.id.payOptionGroup);
        this.errorText = findViewById(R.id.errorTextCart);
        this.errorText.setText("");
        Button buyButton = findViewById(R.id.buyButton2);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOrder(v);
            }
        });
        Button homeButton = findViewById(R.id.cartHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        TextView textLogo = findViewById(R.id.cubeBusterLogoOrderCart);
        textLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
    }

    private void homeClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    private void processOrder(View v) {
        if (!checkForm()) {
            int id = this.paymentRadio.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioVisa:
                    emailConfirmation("VISA");
                    break;
                case R.id.radioMasterCard:
                    emailConfirmation("MasterCard");
                    break;
                default:
                    this.errorText.setText("Please pick your payment method");
                    break;
            }
        } else {
            this.errorText.setText("Please fill every input box below");
        }
    }

    private boolean checkForm() {
        return checkError(this.editNameCart) || checkError(this.editEmailCart) || checkError(this.editAddressCart) || checkError(this.editPhoneCart);
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

    private void emailConfirmation(String paymentMethod) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{String.valueOf(this.editEmailCart.getText())});
        intent.putExtra(Intent.EXTRA_SUBJECT, new String[]{"Billing information"});
        intent.putExtra(Intent.EXTRA_TEXT, emailBodyBuilder(paymentMethod));
        wipeCart();
        startActivity(intent);
    }

    private String emailBodyBuilder(String paymentMethod) {
        final int orderCode = new Random().nextInt(999) + 8000;
        return "Your order (code " + orderCode + ") is being processed, and will be delivered to " + this.editAddressCart.getText() + " by the end of next month. Your payment method is " + paymentMethod + " and the billing address for this order is situated at " + this.editAddressCart.getText() + ", the total cost of the order is " + this.totalPrice + "$ US. If anything happens, we will get in contact with you using this phone number: '" + this.editPhoneCart.getText() + "' by SMS, and using this address '" + this.editEmailCart.getText() + "' by email.\n\nHappy watching,\n\nThe team at CubeBusters";
    }

    private void wipeCart(){
        ContentValues filmValues = new ContentValues();
        filmValues.put("BOUGHT", 0);
        SQLiteOpenHelper filmDbHelper = new FilmDataHelper(this);
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        db.update("FILMS",
                filmValues,
                "bought > ?",
                new String[] {Integer.toString(0)});
    }
}
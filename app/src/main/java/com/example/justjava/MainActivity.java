package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int q = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (q == 50) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        q = q + 1;

        display(q);
    }

    public void decrement(View view) {
        if (q == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        q = q - 1;
        display(q);

    }

    public void submitOrder(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.wippedCream);
        boolean isCheckedCream = checkBox.isChecked();
        boolean isCheckedChoclate = ((CheckBox) findViewById(R.id.choclate)).isChecked();
        EditText text = (EditText) findViewById(R.id.text);
        String hasName = text.getText().toString();
        int price = calculatePrice(isCheckedCream, isCheckedChoclate);
        String message = messageTOPrint(price, isCheckedCream, isCheckedChoclate, hasName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shivangsrivastav55@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for "+hasName);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
    public  void contactUs(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shivangsrivastav55@gmail.com"});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private int calculatePrice(boolean wippedPrice, boolean choclatePrice) {
        int price = 0;
        if (wippedPrice == true) {
            price = (5 + 1) * q;
        } else if (choclatePrice == true) {
            price = (5 + 2) * q;
        }
        else{
            price=q*5;
        }
        return price;

    }

    private String messageTOPrint(int price, boolean hasCheckedCream, boolean hasCheckedChoclate, String hasName) {
        String temp = " ";
        String temp2 = " ";
        int toopingPrice1=0;
        int toopingPrice2=0;
        if (hasCheckedCream == true) {
            temp = "Yes";
            toopingPrice1=1;
        } else {
            temp = "No";

        }
        if (hasCheckedChoclate == true) {
            temp2 = "Yes";
            toopingPrice2=2;
        } else {
            temp2 = "No";
        }

        String message = "Name: " + hasName;
        message += "\nQuantity: " + " " + q;
        message += "\nWipped Cream: " + temp+" "+"$"+(q*toopingPrice1);
        message += "\nChoclate: " + temp2+" "+"$"+(q*toopingPrice2);
        message += "\nTotal Price: " + "  $" + price;

        message += "\nThankyou!";
        return message;
    }

    public void checkBox(View view) {

    }


    /**
     * This method displays the given quantity value on the screen.
     */

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}
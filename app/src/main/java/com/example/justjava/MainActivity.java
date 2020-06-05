package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;








import android.view.View;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int q=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view){

        q=q+1;

        display(q);
    }
    public void decrement(View view){

        q=q-1;
        display(q);
    }
    public void submitOrder(View view) {

        display(q);
        displayPrice(q*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number){
        TextView rupee=(TextView) findViewById(R.id.rupee);
        rupee.setText("$"+number);
    }
}
package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {


    int quantity = 2;

    CheckBox whippedCream ;
    boolean hasWhippeCream ;

    CheckBox chocholate;
    boolean hasChocholate;

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         whippedCream = (CheckBox) findViewById(R.id.has_whipped_cream);


         chocholate = (CheckBox) findViewById(R.id.has_chocolate);


         name = (TextView) findViewById(R.id.name);

        //TextView textView = new TextView(context);
       // ImageView img = new ImageView(context);
       // ToggleButton button = new ToggleButton(context);
       // Toast toast =  Toast.makeText(context,text,duration);

        //TextView nameTextView = (TextView) findViewById(R.id.name);
        //TextView description = (TextView) findViewById(R.id.quantity_text_view);
        //int maxLine = description.getMaxLines();

        /*ImageView iconImageView = (ImageView) findViewById(R.id.icon);
        iconImageView.setImageResource(R.drawable.logo);

        View textView =  findViewById(R.id.title);
        textView.setVisibility(textView.GONE);*/
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        int price = calculatePrice();
        String priceMessage = "Total :" + price + "\n";

        priceMessage = priceMessage + "Thank You!";

        displayMessage(priceMessage);

        createOrderSummnery();
    }


    public void createOrderSummnery() {

        hasWhippeCream = whippedCream.isChecked();
        hasChocholate = chocholate.isChecked();


        int price = calculatePrice();
        String priceMessage = "Name : "+ name.getText() +"\nQuantiry: " + quantity + "\n";

        if(hasWhippeCream == true){
            priceMessage = priceMessage + "Add whippe cream?" + hasWhippeCream + "\n";
        }

        if(hasChocholate == true){
            priceMessage = priceMessage + "Add Chocholate?" + hasChocholate + "\n";
        }

        priceMessage = priceMessage + "Total :" + price + "\n";

        priceMessage = priceMessage + "Thank You!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "mastrayasa@gmail.com");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage);
    }

    public void increment(View view) {
        if(quantity < 100){
            quantity = quantity + 1;
            displayQuantity(quantity);
        }

    }

    public void decrement(View view) {
        if(quantity >= 2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(number);
    }


    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int price = quantity * 5;

        hasWhippeCream = whippedCream.isChecked();
        hasChocholate = chocholate.isChecked();

        if(hasWhippeCream == true){
            price = price + (1 * quantity);

        }

        if(hasChocholate == true){
            price = price + (2 * quantity);
        }


        return price;
    }

}

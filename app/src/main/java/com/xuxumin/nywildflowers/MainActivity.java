package com.xuxumin.nywildflowers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //intent for mainActivity jump to SearchByColor
    public void clickToSearchByColor(View view){
        Intent intentForSearchByColor = new Intent(this, SearchByColor.class);
        startActivity(intentForSearchByColor);
        //startActivity SearchByColor
    }

    //intent for mainActivity jump to BloomingNow
    public void clickToBloomingNow(View view){
        Intent intentForBloomingNow = new Intent(this, BloomingNow.class);
        startActivity(intentForBloomingNow);
        //startActivity BloomingNow
    }

    //intent for mainActivity jump to FlowerLocator
    public void clickToFlowerLocator(View view){
        Intent intentForFlowerLocator = new Intent(this, FlowerLocator.class);
        startActivity(intentForFlowerLocator);
        //startActivity FlowerLocator
    }

    //intent for MyCollection
    public void clickToMyCollection(View view){
        Intent intentForMyCollection = new Intent(this, MyCollection.class);
        startActivity(intentForMyCollection);
        //start activity MyCollection
    }

}

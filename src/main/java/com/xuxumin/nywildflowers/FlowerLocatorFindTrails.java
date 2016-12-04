package com.xuxumin.nywildflowers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FlowerLocatorFindTrails extends FragmentActivity implements
        OnMapReadyCallback {

    private Toolbar toolbar;
    private ImageButton menu_icon;

    private static final LatLng ThorndenPark = new LatLng(43.0421, -76.1267);
    private static final LatLng OnondagaLakePark = new LatLng(43.0997, -76.2068);
    private static final LatLng GreenLakesStatePark = new LatLng(43.0582, -75.9714);
    private static final LatLng ClarkReservationStatePark = new LatLng(42.9972, -76.0941);
    private static final LatLng UpperOnondagaPark = new LatLng(43.0270, -76.1652);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locator_find_trail);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        menu_icon = (ImageButton)findViewById(R.id.my_menu_icon);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the instance of popupMenu
                PopupMenu popup = new PopupMenu(FlowerLocatorFindTrails.this, menu_icon);
                //inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener

                popup.show(); //showing popup menu
            }
        });
    }

    public void menuClickToMyCollection (MenuItem menuItem){
        Intent intentForMyCollection = new Intent(this, MyCollection.class);
        startActivity(intentForMyCollection);
    }

    /*
        11/14/20116 Xumin
        intent for the item in menu Search By Color
     */
    public void menuClickToSearchByColor (MenuItem menuItem){
        Intent intentForSearchByColor = new Intent(this, SearchByColor.class);
        startActivity(intentForSearchByColor);
    }

    /*
        11/14/20116 Xumin
        intent for the item in menu Flower Locator
     */
    public void menuClickToFlowerLocator (MenuItem menuItem){
        Intent intentForFlowerLocator = new Intent(this, FlowerLocator.class);
        startActivity(intentForFlowerLocator);
    }

    public void menuClickToHome (MenuItem menuItem){
        Intent intentForHomeNow = new Intent(this, MainActivity.class);
        startActivity(intentForHomeNow);
    }

    /*
        11/14/20116 Xumin
        intent for the item in menu Blooming Now
     */
    public void menuClickToBloomingNow (MenuItem menuItem){
        Intent intentForBloomingNow = new Intent(this, BloomingNow.class);
        startActivity(intentForBloomingNow);
    }

    private GoogleMap mMap;

    private Marker mThorndenPark;
    private Marker mOnondagaLakePark;
    private Marker mGreenLakesStatePark;
    private Marker mClarkReservationStatePark;
    private Marker mUpperOnondagaPark;

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(43.0481, -76.1474);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Syracuse"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.getUiSettings().setZoomControlsEnabled(true);

        addMarkersToMap();

        // Pan to see all markers in view.
        // Cannot zoom to bounds until the map has a size.
        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(ThorndenPark)
                            .include(OnondagaLakePark)
                            .include(GreenLakesStatePark)
                            .include(ClarkReservationStatePark)
                            .include(UpperOnondagaPark)
                            .build();
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                }
            });
        }
    }

    private void addMarkersToMap() {
        // Uses a colored icon.
        mThorndenPark = mMap.addMarker(new MarkerOptions()
                .position(ThorndenPark)
                .title("Thornden Park")
                .snippet("Rate: 4.0")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.action_arrow))
                .infoWindowAnchor(0.5f, 0.5f));

        // Uses a custom icon with the info window popping out of the center of the icon.
        mOnondagaLakePark = mMap.addMarker(new MarkerOptions()
                .position(OnondagaLakePark)
                .title("Onondaga Lake Park")
                .snippet("Rate: 4.0")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.action_arrow))
                .infoWindowAnchor(0.5f, 0.5f));

        // Creates a draggable marker. Long press to drag.
        mGreenLakesStatePark = mMap.addMarker(new MarkerOptions()
                .position(GreenLakesStatePark)
                .title("Green Lakes State Park")
                .snippet("Rate: 4.0")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.action_arrow))
                .infoWindowAnchor(0.5f, 0.5f));

        // A few more markers for good measure.
        mClarkReservationStatePark = mMap.addMarker(new MarkerOptions()
                .position(ClarkReservationStatePark)
                .title("Clark Reservation State Park")
                .snippet("Rate: 4.0")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.action_arrow))
                .infoWindowAnchor(0.5f, 0.5f));

        mUpperOnondagaPark = mMap.addMarker(new MarkerOptions()
                .position(UpperOnondagaPark)
                .title("Upper Onondaga Park")
                .snippet("Rate: 4.0")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.action_arrow))
                .infoWindowAnchor(0.5f, 0.5f));
    }

}

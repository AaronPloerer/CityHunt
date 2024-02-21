package com.example.please;

import static com.example.please.AMapFragmentDirections.actionAMapFragmentToADestinationFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class AMapFragment extends Fragment implements OnMapReadyCallback {

    private PopupWindow popupWindow;
    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap mMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    public CameraPosition savedCameraPosition;
    private boolean fromMap;
    private boolean keepCameraPositionStatus;
    int destinationNumber;
    float longitude;
    float latitude;
    float loadLongitude;
    float loadLatitude;
    LatLng cameraPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.afragment_map, container, false);

        if (getArguments() != null && getArguments().containsKey("keepCameraPosition")) {
            keepCameraPositionStatus = AMapFragmentArgs.fromBundle(requireArguments()).getKeepCameraPosition();
        } else {
            keepCameraPositionStatus = false;
        }

        if (getArguments() != null && getArguments().containsKey("longitude") && getArguments().containsKey("latitude")) {
            loadLongitude = AMapFragmentArgs.fromBundle(requireArguments()).getLongitude();
            loadLatitude = AMapFragmentArgs.fromBundle(requireArguments()).getLatitude();
        } else {
            loadLongitude = (float) currentLocation.getLongitude();
            loadLatitude = (float) currentLocation.getLatitude();
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        getLastLocation();

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                NavHostFragment.findNavController(AMapFragment.this)
                        .navigate(R.id.action_AMapFragment_to_AOverviewFragment);
            }
        });

        return view;
    }

    private void getLastLocation() {

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(AMapFragment.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (currentLocation != null) {
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setPadding(25, 25, 25, 25);
            mMap.setMyLocationEnabled(true);
            float zoomLevel = 16.0f;


            if (keepCameraPositionStatus) {
                LatLng currentLatLng = new LatLng(loadLatitude, loadLongitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, zoomLevel));
                savedCameraPosition = new CameraPosition.Builder()
                        .target(currentLatLng)
                        .zoom(zoomLevel)
                        .build();
            } else {
                LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, zoomLevel));
                savedCameraPosition = new CameraPosition.Builder()
                        .target(currentLatLng)
                        .zoom(zoomLevel)
                        .build();
            }

            LatLng location1 = new LatLng(48.83309708854275, 12.962412134732796);
            mMap.addMarker(new MarkerOptions().position(location1).title(getString(R.string.aurum_deggendorf)));

            LatLng location2 = new LatLng(48.83411491271655, 12.95947219179031);
            mMap.addMarker(new MarkerOptions().position(location2).title(getString(R.string.lord_lobo_nelson)));

            LatLng location3 = new LatLng(48.832154308891795, 12.958777726760008);
            mMap.addMarker(new MarkerOptions().position(location3).title(getString(R.string.cafe_bar_first_floor)));

            LatLng location4 = new LatLng(48.83402076890391, 12.960701717204982);
            mMap.addMarker(new MarkerOptions().position(location4).title(getString(R.string.home_bar)));

            LatLng location5 = new LatLng(48.83344639058812, 12.963771758483603);
            mMap.addMarker(new MarkerOptions().position(location5).title(getString(R.string.sams_bar)));

            LatLng location6 = new LatLng(48.83227821262307, 12.96447638076771);
            mMap.addMarker(new MarkerOptions().position(location6).title(getString(R.string.cafe_extra_deggendorf_gmbh)));

            LatLng location7 = new LatLng(48.83141059426033, 12.961989613034282);
            mMap.addMarker(new MarkerOptions().position(location7).title(getString(R.string.flair_deggendorf)));

            LatLng location8 = new LatLng(48.833897471124196, 12.961568694626008);
            mMap.addMarker(new MarkerOptions().position(location8).title(getString(R.string.c2_coffee_cocktails_deggendorf)));


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    showCustomWindow(marker.getTitle());
                    return true;
                }

            });

            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    cameraPosition = googleMap.getCameraPosition().target;
                }
            });

        } else {
            // Handle the case where location is null
            Toast.makeText(requireContext(), "Unable to retrieve current location", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCustomWindow(String markerTitle) {
        View customView = getLayoutInflater().inflate(R.layout.custom_info_window, null);

        // Find the TextView and ImageViews in the custom layout
        TextView textViewInfo = customView.findViewById(R.id.textViewInfo);

        // Set the text of the TextView with the marker title
        textViewInfo.setText(markerTitle);

        // Set images based on the marker title or other conditions
        if (markerTitle.equals(getString(R.string.aurum_deggendorf))) {
            destinationNumber = 1;
        } else if (markerTitle.equals(getString(R.string.lord_lobo_nelson))) {
            destinationNumber = 2;
        } else if (markerTitle.equals(getString(R.string.cafe_bar_first_floor))) {
            destinationNumber = 3;
        } else if (markerTitle.equals(getString(R.string.home_bar))) {
            destinationNumber = 4;
        } else if (markerTitle.equals(getString(R.string.sams_bar))) {
            destinationNumber = 5;
        } else if (markerTitle.equals(getString(R.string.cafe_extra_deggendorf_gmbh))) {
            destinationNumber = 6;
        } else if (markerTitle.equals(getString(R.string.c2_coffee_cocktails_deggendorf))) {
            destinationNumber = 7;
        } else {
            destinationNumber = 8;
        }

        popupWindow = new PopupWindow(
                customView,
                1000,
                350
        );

        customView.setOnTouchListener(new View.OnTouchListener() {
            private float downY;
            private int initialY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getRawY();
                        initialY = popupWindow.getContentView().getTop();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getRawY();
                        int deltaY = (int) (moveY - downY);
                        int newY = initialY + deltaY;

                        ViewGroup.MarginLayoutParams moveparams = (ViewGroup.MarginLayoutParams) popupWindow.getContentView().getLayoutParams();
                        if (deltaY > 0) {
                            moveparams.topMargin = newY;
                            moveparams.bottomMargin = -newY;
                        }
                        popupWindow.getContentView().requestLayout();

                        return true;

                    case MotionEvent.ACTION_UP:
                        if (event.getRawY() > (downY+130)) {
                            popupWindow.dismiss();
                        } else {
                            ViewGroup.MarginLayoutParams revertparams = (ViewGroup.MarginLayoutParams) popupWindow.getContentView().getLayoutParams();
                            revertparams.topMargin = initialY;
                            revertparams.bottomMargin = -initialY;
                            popupWindow.getContentView().requestLayout();
                        }
                        return true;
                }
                return false;
            }
        });

        popupWindow.showAtLocation(customView, Gravity.BOTTOM, 0,100);

        customView.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = true;

                if (currentLocation != null) {
                    longitude = (float) cameraPosition.longitude;
                    latitude = (float) cameraPosition.latitude;
                    popupWindow.dismiss();
                    AMapFragmentDirections.ActionAMapFragmentToADestinationFragment fromMapToDestAction =
                            actionAMapFragmentToADestinationFragment(fromMap, longitude, latitude, destinationNumber);
                    NavHostFragment.findNavController(AMapFragment.this).navigate(fromMapToDestAction);
                } else {
                    Toast.makeText(requireContext(), "Current location is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
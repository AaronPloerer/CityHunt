package com.example.please;

import static com.example.please.AOverviewFragmentDirections.actionAOverviewFragmentToADestinationFragment;
import static com.example.please.AOverviewFragmentDirections.actionAOverviewFragmentToAInfoFragment;
import static com.example.please.AOverviewFragmentDirections.actionAOverviewFragmentToAMapFragment;
import static com.example.please.AOverviewFragmentDirections.actionAOverviewFragmentToASettingsFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentOverviewBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.time.LocalDateTime;


public class AOverviewFragment extends Fragment implements OnMapReadyCallback {

    private AfragmentOverviewBinding binding;
    TextView showCountTextView;
    private boolean fromStart;

    private boolean fromMap;
    private boolean keepCameraPosition;

    private boolean hasTicketStatus;

    private MapView mapView;
    private GoogleMap googleMap;

    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = AfragmentOverviewBinding.inflate(inflater, container, false);
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LocalDateTime currentDateTime = LocalDateTime.now();

        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);

        LocalDateTime endDateTime = LocalDateTime.of(2028, 1, 1, 12, 0);

        MyApplication myApp = (MyApplication) requireActivity().getApplication();

        if (myApp.getHasTicket()) {
            hasTicketStatus = true;
        } else {
            hasTicketStatus = false;
        }

        ImageView myImageView = binding.imageButton;

        if(myApp.getHasWon()){
            myImageView.setImageResource(R.drawable.won);
        }else {
            if (hasTicketStatus) {
                myImageView.setImageResource(R.drawable.visit);
            } else {
                myImageView.setImageResource(R.drawable.buy);
            }
        }

        MapView mapViewer = view.findViewById(R.id.mapView);
        mapViewer.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        keepCameraPosition = false;
                        AOverviewFragmentDirections.ActionAOverviewFragmentToAMapFragment keepCameraLocationAction =
                                actionAOverviewFragmentToAMapFragment(2.0f, 2.0f, false);
                        NavHostFragment.findNavController(AOverviewFragment.this).navigate(keepCameraLocationAction);
                    }
                });
            }
        });

        view.findViewById(R.id.imageLogo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AOverviewFragment.this)
                        .navigate(R.id.action_AOverviewFragment_to_AStartFragment);
            }
        });

        view.findViewById(R.id.imageInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromStart = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToAInfoFragment info2_action =
                        actionAOverviewFragmentToAInfoFragment(fromStart);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(info2_action);
            }
        });

        view.findViewById(R.id.imageSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromStart = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToASettingsFragment settings2_action =
                        actionAOverviewFragmentToASettingsFragment(fromStart);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(settings2_action);
            }
        });

        view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasTicketStatus) {
                    NavHostFragment.findNavController(AOverviewFragment.this)
                            .navigate(R.id.action_AOverviewFragment_to_ARegisterFragment);
                }else{
                    NavHostFragment.findNavController(AOverviewFragment.this)
                            .navigate(R.id.action_AOverviewFragment_to_ATicketFragment);
                }
            }
        });

        view.findViewById(R.id.destinationButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 1);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 2);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 3);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 4);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 5);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 6);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 7);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

        view.findViewById(R.id.destinationButton8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromMap = false;
                AOverviewFragmentDirections.ActionAOverviewFragmentToADestinationFragment toMapAction =
                        actionAOverviewFragmentToADestinationFragment(fromMap, 1.0f, 1.0f, 8);
                NavHostFragment.findNavController(AOverviewFragment.this).navigate(toMapAction);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng sanFrancisco = new LatLng(37.7749, -122.4194);
        float zoomLevel = 16.0f;

        // Move camera to San Francisco
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanFrancisco, zoomLevel));

        // Enable the "My Location" button on the map
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Enable the "My Location" layer
            googleMap.setMyLocationEnabled(true);

            // Get the current location using FusedLocationProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                        // Remove the marker
                        googleMap.clear();

                        // Move camera to the current location
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel));
                    }
                }
            });
        } else {
            // Handle the case where location permission is not granted
            Toast.makeText(requireContext(), "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }
}
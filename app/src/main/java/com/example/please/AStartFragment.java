package com.example.please;

import static com.example.please.AStartFragmentDirections.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentStartBinding;

public class AStartFragment extends Fragment { // Changed class name to AInfoFragment

    private AfragmentStartBinding binding; // Changed layout file reference

    private boolean fromStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AfragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_event1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AStartFragment.this)
                        .navigate(R.id.action_AStartFragment_to_AOverviewFragment);
            }
        });

        view.findViewById(R.id.imageInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromStart = true;
                AStartFragmentDirections.ActionAStartFragmentToAInfoFragment info_action =
                        actionAStartFragmentToAInfoFragment(fromStart);
                NavHostFragment.findNavController(AStartFragment.this).navigate(info_action);
            }
        });

        view.findViewById(R.id.imageSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromStart = true;
                AStartFragmentDirections.ActionAStartFragmentToASettingsFragment settings_action =
                        actionAStartFragmentToASettingsFragment(fromStart);
                NavHostFragment.findNavController(AStartFragment.this).navigate(settings_action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
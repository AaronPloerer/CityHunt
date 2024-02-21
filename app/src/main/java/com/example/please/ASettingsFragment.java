package com.example.please;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentSettingsBinding;

public class ASettingsFragment extends Fragment {

    private AfragmentSettingsBinding binding;
    private boolean fromStartStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AfragmentSettingsBinding.inflate(inflater, container, false);

        if (getArguments() != null && getArguments().containsKey("fromStart")) {
            fromStartStatus = ASettingsFragmentArgs.fromBundle(requireArguments()).getFromStart();
        } else {
            fromStartStatus = false;
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!fromStartStatus) {
                    NavHostFragment.findNavController(ASettingsFragment.this)
                            .navigate(R.id.action_ASettingsFragment_to_AOverviewFragment);
                }else{
                    NavHostFragment.findNavController(ASettingsFragment.this)
                            .navigate(R.id.action_ASettingsFragment_to_AStartFragment);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
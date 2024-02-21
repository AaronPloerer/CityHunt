package com.example.please;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentInfoBinding;

public class AInfoFragment extends Fragment { // Changed class name to AInfoFragment
    private AfragmentInfoBinding binding; // Changed layout file reference
    private boolean fromStartStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AfragmentInfoBinding.inflate(inflater, container, false);

        if (getArguments() != null && getArguments().containsKey("fromStart")) {
            fromStartStatus = AInfoFragmentArgs.fromBundle(requireArguments()).getFromStart();
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
                    NavHostFragment.findNavController(AInfoFragment.this)
                            .navigate(R.id.action_AInfoFragment_to_AOverviewFragment);
                }else{
                    NavHostFragment.findNavController(AInfoFragment.this)
                            .navigate(R.id.action_AInfoFragment_to_AStartFragment);
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
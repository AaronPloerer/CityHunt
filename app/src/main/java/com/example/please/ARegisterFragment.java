package com.example.please;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentRegisterBinding;


public class ARegisterFragment extends Fragment {

    private AfragmentRegisterBinding binding;
    private boolean hasTicket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = AfragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_free).setOnClickListener(new View.OnClickListener() {
            MyApplication myApp = (MyApplication) requireActivity().getApplication();
            @Override
            public void onClick(View view) {
                myApp.setHasTicket(true);
                NavHostFragment.findNavController(ARegisterFragment.this)
                        .navigate(R.id.action_ARegisterFragment_to_AOverviewFragment);
            }
        });

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ARegisterFragment.this)
                        .navigate(R.id.action_ARegisterFragment_to_AOverviewFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
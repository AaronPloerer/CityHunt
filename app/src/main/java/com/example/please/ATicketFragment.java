package com.example.please;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentTicketBinding;

public class ATicketFragment extends Fragment {

    private AfragmentTicketBinding binding;
    private boolean fromStartStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AfragmentTicketBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication myApp = (MyApplication) requireActivity().getApplication();

        TextView textView = view.findViewById(R.id.textView);

        if(myApp.getHasWon()) {
            textView.setText("You Won");
        }else {
            textView.setText("Get All Codes!");
        }
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ATicketFragment.this)
                        .navigate(R.id.action_ATicketFragment_to_AOverviewFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
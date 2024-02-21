package com.example.please;

import static com.example.please.ADestinationFragmentDirections.actionADestinationFragmentToAMapFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.please.databinding.AfragmentDestinationBinding;
public class ADestinationFragment extends Fragment {
    private AfragmentDestinationBinding binding;
    private boolean fromMapStatus;
    private boolean keepCameraPosition;
    float loadLongitude;
    float loadLatitude;
    int whatDestination;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AfragmentDestinationBinding.inflate(inflater, container, false);

        loadLongitude = ADestinationFragmentArgs.fromBundle(getArguments()).getLongitude();
        loadLatitude = ADestinationFragmentArgs.fromBundle(getArguments()).getLatitude();
        whatDestination = ADestinationFragmentArgs.fromBundle(getArguments()).getDestinationNumber();


        if (getArguments() != null && getArguments().containsKey("fromMap")) {
            fromMapStatus = ADestinationFragmentArgs.fromBundle(requireArguments()).getFromMap();
        } else {
            fromMapStatus = false;
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication myApp = (MyApplication) requireActivity().getApplication();

        EditText editText = view.findViewById(R.id.editText);
        ImageView okButton = view.findViewById(R.id.imageButton);
        TextView textView = view.findViewById(R.id.textView);
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayout);
        int overlayColor = Color.argb(90, 0, 0, 0);

        if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
            myApp.setHasWon(true);
        }

        if (whatDestination >= 1 && whatDestination <= 8) {
            switch (whatDestination) {
                case 1:
                    textView.setText(getString(R.string.aurum_deggendorf));
                    Drawable originalDrawable1 = getResources().getDrawable(R.drawable.aurum);
                    Drawable overlay1 = new ColorDrawable(overlayColor);
                    Drawable[] layers1 = {originalDrawable1, overlay1};
                    LayerDrawable layerDrawable1 = new LayerDrawable(layers1);
                    constraintLayout.setBackground(layerDrawable1);
                    break;
                case 2:
                    textView.setText(getString(R.string.lord_lobo_nelson));
                    Drawable originalDrawable2 = getResources().getDrawable(R.drawable.lobo);
                    Drawable overlay2 = new ColorDrawable(overlayColor);
                    Drawable[] layers2 = {originalDrawable2, overlay2};
                    LayerDrawable layerDrawable2 = new LayerDrawable(layers2);
                    constraintLayout.setBackground(layerDrawable2);
                    break;
                case 3:
                    textView.setText(getString(R.string.cafe_bar_first_floor));
                    Drawable originalDrawable3 = getResources().getDrawable(R.drawable.firstfloor);
                    Drawable overlay3 = new ColorDrawable(overlayColor);
                    Drawable[] layers3 = {originalDrawable3, overlay3};
                    LayerDrawable layerDrawable3 = new LayerDrawable(layers3);
                    constraintLayout.setBackground(layerDrawable3);
                    break;
                case 4:
                    textView.setText(getString(R.string.home_bar));
                    Drawable originalDrawable4 = getResources().getDrawable(R.drawable.home);
                    Drawable overlay4 = new ColorDrawable(overlayColor);
                    Drawable[] layers4 = {originalDrawable4, overlay4};
                    LayerDrawable layerDrawable4 = new LayerDrawable(layers4);
                    constraintLayout.setBackground(layerDrawable4);
                    break;
                case 5:
                    textView.setText(getString(R.string.sams_bar));
                    Drawable originalDrawable5 = getResources().getDrawable(R.drawable.sams);
                    Drawable overlay5 = new ColorDrawable(overlayColor);
                    Drawable[] layers5 = {originalDrawable5, overlay5};
                    LayerDrawable layerDrawable5 = new LayerDrawable(layers5);
                    constraintLayout.setBackground(layerDrawable5);
                    break;
                case 6:
                    textView.setText(getString(R.string.cafe_extra_deggendorf_gmbh));
                    Drawable originalDrawable6 = getResources().getDrawable(R.drawable.extra);
                    Drawable overlay6 = new ColorDrawable(overlayColor);
                    Drawable[] layers6 = {originalDrawable6, overlay6};
                    LayerDrawable layerDrawable6 = new LayerDrawable(layers6);
                    constraintLayout.setBackground(layerDrawable6);
                    break;
                case 7:
                    textView.setText(getString(R.string.flair_deggendorf));
                    Drawable originalDrawable7 = getResources().getDrawable(R.drawable.flair);
                    Drawable overlay7 = new ColorDrawable(overlayColor);
                    Drawable[] layers7 = {originalDrawable7, overlay7};
                    LayerDrawable layerDrawable7 = new LayerDrawable(layers7);
                    constraintLayout.setBackground(layerDrawable7);
                    break;
                case 8:
                    textView.setText(getString(R.string.c2_coffee_cocktails_deggendorf));
                    Drawable originalDrawable8 = getResources().getDrawable(R.drawable.c2);
                    Drawable overlay8 = new ColorDrawable(overlayColor);
                    Drawable[] layers8 = {originalDrawable8, overlay8};
                    LayerDrawable layerDrawable8 = new LayerDrawable(layers8);
                    constraintLayout.setBackground(layerDrawable8);
                    break;
            }
        }

        if (myApp.getHasTicket() == false) {
            editText.setEnabled(false);
            editText.setFocusable(false);
            editText.setHint("You Don't Have A Ticket");
            okButton.setImageResource(R.drawable.codedestination);
        }else{

            if (whatDestination == 1) {
                if (myApp.getHasCode1()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode1(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 2) {
                if (myApp.getHasCode2()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode2(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 3) {
                if (myApp.getHasCode3()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode3(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 4) {
                if (myApp.getHasCode4()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode4(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 5) {
                if (myApp.getHasCode5()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode5(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 6) {
                if (myApp.getHasCode6()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode6(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else if (whatDestination == 7) {
                if (myApp.getHasCode7()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode7(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            } else {
                if (myApp.getHasCode8()) {
                    okButton.setImageResource(R.drawable.codedestination);
                    editText.setEnabled(false);
                    editText.setFocusable(false);
                    editText.setHint("Code Already Used");
                } else {
                    okButton.setImageResource(R.drawable.destination);
                    view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userInput = editText.getText().toString().trim();
                            boolean codeFound = false;
                            for (int i = 0; i < myApp.codeListLenght(); i++) {
                                String code = myApp.getListElement(i);
                                if (code.equals(userInput)) {
                                    codeFound = true;
                                    myApp.removeListElement(code);
                                    break;
                                }
                            }
                            if (codeFound) {
                                okButton.setImageResource(R.drawable.codedestination);
                                myApp.setHasCode8(true);
                                editText.setEnabled(false);
                                editText.setFocusable(false);
                                okButton.setClickable(false);
                                editText.setText("");
                                editText.setHint("Code Is Correct");
                                if(myApp.getHasCode1() && myApp.getHasCode2() && myApp.getHasCode3() && myApp.getHasCode4() && myApp.getHasCode5() && myApp.getHasCode6() && myApp.getHasCode7() && myApp.getHasCode8()){
                                    myApp.setHasWon(true);
                                }
                            } else {
                                okButton.setImageResource(R.drawable.destination);
                                editText.setText("");
                            }
                            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });
                }

            }
        }

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fromMapStatus) {
                    NavHostFragment.findNavController(ADestinationFragment.this)
                            .navigate(R.id.action_ADestinationFragment_to_AOverviewFragment);
                } else {
                    keepCameraPosition = true;
                    ADestinationFragmentDirections.ActionADestinationFragmentToAMapFragment keepCameraLocationAction =
                            actionADestinationFragmentToAMapFragment(loadLongitude, loadLatitude, true);
                    NavHostFragment.findNavController(ADestinationFragment.this).navigate(keepCameraLocationAction);
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
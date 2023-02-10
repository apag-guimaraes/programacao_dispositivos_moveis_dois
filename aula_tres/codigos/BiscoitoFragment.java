package com.example.appnavigation.ui.biscoito;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appnavigation.R;

public class BiscoitoFragment extends Fragment {

   private ImageView imageViewBiscoito;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_biscoito,
                container, false);
        root.findViewById(R.id.imageViewBiscoito);
        return root;
    }
}

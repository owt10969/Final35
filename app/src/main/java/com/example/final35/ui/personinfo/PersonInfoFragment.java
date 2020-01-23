package com.example.final35.ui.personinfo;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.final35.R;
import com.example.final35.ui.notifications.NotificationsViewModel;

public class PersonInfoFragment extends Fragment {

    private PersonInfoViewModel personInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personInfoViewModel =
                ViewModelProviders.of(this).get(PersonInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_person, container, false);

        return root;
    }
}

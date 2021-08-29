package com.example.taskapp.ui.onBoarding;

import android.content.ClipData;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentBoardBinding;
import com.example.taskapp.ui.OnItemListener;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;


public class BoardFragment extends Fragment implements OnItemListener, BoardAdapter.GetPosition {

    private FragmentBoardBinding binding;
    private BoardAdapter adapter;
    private BoardAdapter.ViewHolder holder;
    private SpringDotsIndicator dotsIndicator;
    private int position;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BoardAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.skipBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_home);

        });



        adapter.setListener(this);
        adapter.setPosListener(this);
        dotsIndicator = (SpringDotsIndicator) view.findViewById(R.id.dots_indicator);
        ViewPager2 viewPager2 = binding.viewPager;
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);


                if (position == 2) {
                    binding.skipBtn.setVisibility(View.GONE);
                } else  {
                    binding.skipBtn.setVisibility(View.VISIBLE);
                }

            }
        });

        dotsIndicator.setViewPager2(viewPager2);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
        viewPager2.setPageTransformer(new ZoomOutSlideTransformer());

        };

    @Override
    public void onClick(int position) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_home);
    }



    @Override
    public void getPosition(int position) {
        Log.e("test34523523", "adapter position"+ position);
//            if (position == 2){
//                binding.skipBtn.setVisibility(View.INVISIBLE);
//            }if(position == 0) {
//                binding.skipBtn.setVisibility(View.VISIBLE);
//            }if (position == 1){
//                binding.skipBtn.setVisibility(View.VISIBLE);
//        }

    }
}



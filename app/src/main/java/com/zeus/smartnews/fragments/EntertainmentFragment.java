package com.zeus.smartnews.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.zeus.smartlearner.chatSection.NetworkConnectionLiveData;
import com.zeus.smartnews.adapters.CategoryAdapter;
import com.zeus.smartnews.api.ApiUtilities;
import com.zeus.smartnews.databinding.FragmentEntertainmentBinding;
import com.zeus.smartnews.models.ArticleModel;
import com.zeus.smartnews.models.CountriesClass;
import com.zeus.smartnews.models.NewsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    FragmentEntertainmentBinding binding;
    private ShimmerFrameLayout shimmerLayout;

    private ArrayList<ArticleModel> articleModels;
    private CategoryAdapter categoryAdapter;
    private String selectedCountry="ng";
    private RecyclerView entertainmentRecyclerView;
    private String category="entertainment";

    private String api = CountriesClass.apiKey;
    private NetworkConnectionLiveData networkConnection;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEntertainmentBinding.inflate(inflater, container, false);

        articleModels = new ArrayList<>();
        shimmerLayout = binding.entertainHomeScreenShimmerLayout;
        entertainmentRecyclerView = binding.entertainmentRecyclerView;
        entertainmentRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        categoryAdapter = new CategoryAdapter(requireContext(), articleModels);
        entertainmentRecyclerView.setAdapter(categoryAdapter);

        shimmerLayout.startShimmer();

        loadChosenCountry();

        networkConnection = new NetworkConnectionLiveData(requireContext());
        networkConnection.observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isConnected) {
                if (isConnected) {
                    findNews();
                }
            }
        });

        return binding.getRoot();
    }

    //Set selected country 2 digits ISO code
    private void loadChosenCountry() {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("ChosenCountry", Context.MODE_PRIVATE);
        selectedCountry = sharedPref.getString("ChosenCountryKey", "ng");
    }

    @Override
    public void onResume() {
        super.onResume();
        loadChosenCountry();
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(selectedCountry,category,100,api).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    articleModels.addAll(response.body().getArticles());
                    categoryAdapter.notifyDataSetChanged();

                    shimmerLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);
                    entertainmentRecyclerView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        binding = null;
        networkConnection.removeObservers(getViewLifecycleOwner());
        super.onDestroyView();
    }
}
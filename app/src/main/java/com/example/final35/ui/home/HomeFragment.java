package com.example.final35.ui.home;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final35.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView,mRecyclerView2;
    private GalleryAdapter mAdapter,mAdapter2;
    private List<Integer> mDatas,mDatas2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        initDatas();

        //得到控件
        mRecyclerView =root.findViewById(R.id.id_recyclerview_horizontal);
        mRecyclerView2=root.findViewById(R.id.id_recyclerview_horizontal2);
        //設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView2.setLayoutManager(linearLayoutManager2);

        //設置適配器
        mAdapter = new GalleryAdapter(getContext(), mDatas);
        mAdapter2 =new GalleryAdapter(getContext(),mDatas2);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter2);

        return root;
    }

    private void initDatas()
    {
        mDatas = new ArrayList<>(Arrays.asList(
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a53,
                R.drawable.a4,
                R.drawable.a6));

        mDatas2 = new ArrayList<>(Arrays.asList(
                R.drawable.f1,
                R.drawable.f2,
                R.drawable.f3,
                R.drawable.f4,
                R.drawable.f5,
                R.drawable.f6));
    }
    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>
    {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;
        public GalleryAdapter(Context context, List<Integer> datats)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View arg0)
            {
                super(arg0);
            }
            ImageView mImg;
            TextView mTxt;
        }
        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }
        /**
         * 創建ViewHolder
         */
       @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = mInflater.inflate(R.layout.tt,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mImg = view
                    .findViewById(R.id.id_index_gallery_item_image);
            return viewHolder;
        }


        /**
         * 設置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i)
        {
            viewHolder.mImg.setImageResource(mDatas.get(i));
        }
    }
}

package com.kuluo.mvvmhelper.base;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.kuluo.mvvmhelper.R;
import com.kuluo.mvvmhelper.view.recyclerview.listener.OnItemChildClickListener;
import com.kuluo.mvvmhelper.view.recyclerview.listener.OnItemChildLongClickListener;
import com.kuluo.mvvmhelper.view.recyclerview.listener.OnItemClickListener;
import com.kuluo.mvvmhelper.view.recyclerview.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * Created by liucr on 2018/5/7.
 */
public abstract class RecyclerViewModel extends BaseViewModel implements OnItemClickListener, OnItemChildClickListener, OnItemLongClickListener, OnItemChildLongClickListener {

    public final MergeObservableList<Object> items = new MergeObservableList<>();
    public final OnItemBindClass<Object> itemBinding = new OnItemBindClass<>();

    public final List<Integer> mItemChildClickIds = new ArrayList<>();
    public final List<Integer> mItemChildLongClickIds = new ArrayList<>();

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
    }

    public abstract void initRecyclerView();

    @Override
    public void onItemChildClick(int position, int viewId) {

    }

    @Override
    public void onItemChildLongClick(int Position, int viewId) {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}

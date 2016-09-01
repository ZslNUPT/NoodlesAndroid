package com.njupt.sniper.mylibrary.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njupt.sniper.mylibrary.R;
import com.njupt.sniper.mylibrary.ui.RecyclerDecoration;
import com.njupt.sniper.mylibrary.ui.adapter.HeaderFooterRecyclerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-29
 */
public abstract class ItemListFragment<E, S extends RecyclerView.Adapter> extends Fragment implements LoaderManager.LoaderCallbacks<List<E>> {

    protected List<E> items = Collections.emptyList();

    protected S wrapped;

    protected RecyclerView recyclerView;

    protected LinearLayoutManager layoutManager;

    //无数据缺省页
    protected RelativeLayout emptyView;
    protected TextView emptyText;

    // 加载框
    protected ProgressBar progressBar;
    // 列表是否显示
    protected boolean listShown;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLoader();
    }

    protected void initLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
        layoutManager = new LinearLayoutManager(getContext());
        //emptyView
        emptyView = (RelativeLayout) view.findViewById(android.R.id.empty);
        emptyText = (TextView) view.findViewById(R.id.empty_text);

        configureRecyclerView(getActivity(), recyclerView);

        setEmptyText();
    }

    @Override
    public void onDestroyView() {
        listShown = false;
        emptyView = null;
        progressBar = null;
        recyclerView = null;

        super.onDestroyView();
    }


    protected void setEmptyText() {
        emptyText.setText(getEmptyText());
        emptyText.setCompoundDrawablesWithIntrinsicBounds(0, getEmptyIconId(), 0, 0);
    }

    protected String getEmptyText() {
        return "没有数据";
    }

    protected int getEmptyIconId() {
        return R.drawable.empty_icon_1;
    }

    /**
     * @param activity
     * @param recyclerView
     */
    protected void configureRecyclerView(Activity activity, RecyclerView recyclerView) {
        //线性布局
        recyclerView.setLayoutManager(layoutManager);
        //设置 分割线
        recyclerView.addItemDecoration(new RecyclerDecoration(getContext(), RecyclerDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(createAdapter());
    }

    protected HeaderFooterRecyclerAdapter<E, S> createAdapter() {
        wrapped = createAdapter(items);
        return null;
    }

    protected abstract S createAdapter(final List<E> items);

}

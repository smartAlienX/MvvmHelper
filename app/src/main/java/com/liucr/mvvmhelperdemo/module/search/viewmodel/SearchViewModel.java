package com.liucr.mvvmhelperdemo.module.search.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.liucr.mvvmhelperdemo.module.RecyclerViewModel;
import com.liucr.mvvmhelper.event.DialogData;
import com.liucr.mvvmhelperdemo.BR;
import com.liucr.mvvmhelperdemo.R;
import com.liucr.mvvmhelperdemo.mode.Book;
import com.liucr.mvvmhelperdemo.mode.BookListResult;
import com.liucr.mvvmhelperdemo.module.search.model.SearchModel;

import io.reactivex.functions.Consumer;

/**
 * 搜索页面
 * Created by liucr on 2018/4/25.
 */
public class SearchViewModel extends RecyclerViewModel {

    private ObservableList<Book> dataList = new ObservableArrayList<>();
    public final MutableLiveData<String> mKeyword = new MutableLiveData<>();
    public final DialogData mDialogData = new DialogData();

    private SearchModel mSearchModel;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        mSearchModel = new SearchModel(mBookListResultConsumer, mThrowableConsumer);
    }

    @Override
    public void initRecyclerView() {
        itemBinding.map(Book.class, BR.book, R.layout.item_book);
        items.insertList(dataList);
        mItemChildClickIds.add(R.id.book_name);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mSearchModel.destroy();
    }

    /**
     * 根据关键字搜索
     *
     * @param keyword 关键字
     */
    public void searchByKey(String keyword) {

        mDialogData.show.call();
        mSearchModel.setKeyword(keyword)
                .setStart(1)
                .setCount(5)
                .search();
    }

    /**
     * 刷新
     */
    public void refresh() {
        mDialogData.show();
        mSearchModel
                .setStart(1)
                .setCount(5)
                .search();
    }

    /**
     * 加载更多
     */
    public void loadMore() {
        mSearchModel.loadMore();
    }

    /**
     * 搜索成功结果
     */
    private final Consumer<BookListResult> mBookListResultConsumer = new Consumer<BookListResult>() {
        @Override
        public void accept(BookListResult bookListResult) {
            mDialogData.dismiss();
            dataList.addAll(bookListResult.getBooks());
        }
    };

    /**
     * 搜索异常
     */
    private final Consumer<Throwable> mThrowableConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) {
            mDialogData.dismiss();
        }
    };

    @Override
    public void onItemClick(int position) {
        Log.e("liucr", "onItemClick  " + position);
    }

    @Override
    public void onItemChildClick(int position, int viewId) {
        Log.e("liucr", "onItemChildClick  " + position);
    }
}

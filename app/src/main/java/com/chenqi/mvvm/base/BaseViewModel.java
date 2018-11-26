package com.chenqi.mvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.chenqi.mvvm.common.State;
import com.chenqi.mvvm.util.TUtil;

import java.lang.reflect.InvocationTargetException;

public abstract class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {

    public T mRepository;
    public MutableLiveData<State> loadState;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        try {
            loadState = new MutableLiveData<>();
            mRepository = (T) ((Class) TUtil.getInstance(this, 0))
                    .getDeclaredConstructor(MutableLiveData.class)
                    .newInstance(loadState);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unDisposable();
        }
    }
}

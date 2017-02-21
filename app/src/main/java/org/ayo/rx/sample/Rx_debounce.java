package org.ayo.rx.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_debounce extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "sample";
    }

    @Override
    protected String getImageName() {
        return "debounce1";
    }

    @Override
    protected String getCodeNormal() {
        return "";
    }

    private Disposable task;

    protected void runOk(){
        task = Flowable.interval(1000, 1000, TimeUnit.MILLISECONDS)
                .filter(new Predicate<Long>() {  ///发射0,1,2,3,-,-,6,7,-,-,-,11
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        int i = aLong.intValue();
                        //Log.i("debounce", (i >= 0 && i <= 3) + "");
                        if(i >= 0 && i <= 3) return true;
                        else if(i >= 6 && i <= 7) return true;
                        else if(i == 11) return true;
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(final Long aLong) throws Exception {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notifyy(aLong + "");
                            }
                        });
                        return aLong;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .debounce(2000, TimeUnit.MILLISECONDS)   ///采样3,7,11
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                               @Override
                               public void accept(Long s) throws Exception {
                                   notifyy("------采样" + s + "");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   notifyy("出错@@：" + throwable.getMessage());
                               }
                           }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    notifyy("onComplete---结束了！@@");
                                }
                        }, FlowableInternalHelper.RequestMax.INSTANCE);

    }

    protected void runError(){
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }


}

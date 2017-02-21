package org.ayo.rx.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_throttleFirst extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "sample";
    }

    @Override
    protected String getImageName() {
        return "throttleFirst";
    }


    @Override
    protected String getCodeNormal() {
        return "Flowable.interval(0, 1, TimeUnit.SECONDS)\n" +
                "       .throttleFirst(3000, TimeUnit.MILLISECONDS)";
    }

    private Disposable task;

    protected void runOk(){
        Flowable.interval(0, 1, TimeUnit.SECONDS)
                .throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                               @Override
                               public void accept(Long s) throws Exception {
                                   notifyy(s + "");
                               }
                           }, Functions.ERROR_CONSUMER,
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                notifyy("onComplete---结束了！@@");
                            }
                        },
                        FlowableInternalHelper.RequestMax.INSTANCE);

    }

    protected void runError(){
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }


}

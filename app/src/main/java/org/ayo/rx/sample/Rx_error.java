package org.ayo.rx.sample;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_error extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "Flowable.error";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - error
            - 直接调用onError
         */
        task = Flowable.error(new RuntimeException("测试Flowable.error()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object s) throws Exception {
                                   notifyy("item--" + s);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   notifyy("意料中的出错--" + throwable.getMessage());
                               }
                           },
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

package org.ayo.rx.sample;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_firstElement extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "take";
    }

    @Override
    protected String getImageName() {
        return "firstElement";
    }

    @Override
    protected String getCodeNormal() {
        return "Flowable.just(1L)\n" +
                "    .firstElement()";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        Flowable.just(1L)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long s) throws Exception {
//                        notifyy(s + "");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        notifyy("出错：" + throwable.getMessage());
//                    }
//                });
        .subscribe(new MaybeObserver<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                task = d;
            }

            @Override
            public void onSuccess(Long value) {
                notifyy(value + "");
            }

            @Override
            public void onError(Throwable e) {
                notifyy("出错：" + e.getMessage());
            }

            @Override
            public void onComplete() {
                notifyy("onComplete!@@!");
            }
        });

    }

    protected void runError(){
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }


}

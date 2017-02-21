package org.ayo.rx.sample;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_first extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "take";
    }

    @Override
    protected String getImageName() {
        return "first";
    }

    @Override
    protected String getCodeNormal() {
        return "Flowable.<Long>empty()\n" +
                "     .first(100L)";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        Flowable.<Long>empty()
                .first(100L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
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

package org.ayo.rx.sample;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_timeout2 extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "timeout";
    }

    @Override
    protected String getImageName() {
        return "timeout2";
    }

    @Override
    protected String getCodeNormal() {
        return "";
    }

    private Disposable task;

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        task = Flowable.interval(1, 1, TimeUnit.SECONDS)
                .take(10)
                .flatMap(new Function<Long, Publisher<Long>>() {
                    @Override
                    public Publisher<Long> apply(Long aLong) throws Exception {
                        if(aLong <= 5){
                            return Flowable.just(aLong);
                        }else{
                            return Flowable.just(aLong).delay(2000, TimeUnit.MILLISECONDS);
                        }
                    }
                })
                .timeout(new Function<Long, Publisher<Long>>() {
                    @Override
                    public Publisher<Long> apply(Long aLong) throws Exception {
                        return Flowable.just(aLong).delay(2000, TimeUnit.MILLISECONDS); ////4秒后发射，而原始Flowable会在3秒内发射
                    }
                }, Flowable.just(1000L))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                               @Override
                               public void accept(Long s) throws Exception {
                                   notifyy(s + "");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   notifyy("出错：其实是超时了（2000毫秒不发就超时）--" + throwable.getMessage());
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

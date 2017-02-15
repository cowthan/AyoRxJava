package org.ayo.rx.sample;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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

public class Rx_defer extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "Flowable.defer";
    }

    private Disposable task;
    String s;
    protected void runOk(){
        /*
        - defer
            - 在有人注册，即subscribe时，才会创建observable，所以可以保证状态是最新的
            - 并且会给每个观察者创建一个Observable对象
         */
        s = "112334--初始值";
        Observable<String> justObservable = Observable.just(s);
        s = "3344556--第二次变化值";
        Observable<String> deferObservable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                Log.i("defer", "创建defer obserable");
                return Observable.just(s);
            }
        });
        s = "5566778--最新值";

        justObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                notifyy("非defer，值是：" + s);  //112334--初始值
            }
        });
        deferObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                notifyy("defer，值是：" + s); //5566778--最新值
            }
        });
        s = "77889910--最后的最新值";
        deferObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                notifyy("defer，值是：" + s);  //77889910--最后的最新值
            }
        });

    }

    protected void runError(){
        task = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                List<String> list = DataMgmr.server.getDataListSlow();
                Log.i("repeat", "取数据");
                for(String s: list){
                    e.onNext(s);
                    sleep(200);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   notifyy(s);
                               }
                           }, Functions.ERROR_CONSUMER,
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                Log.i("repeat", "on complete");
                            }
                        },
                        FlowableInternalHelper.RequestMax.INSTANCE);
    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }
}

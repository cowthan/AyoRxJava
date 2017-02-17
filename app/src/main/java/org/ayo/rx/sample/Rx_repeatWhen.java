package org.ayo.rx.sample;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_repeatWhen extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "repeatWhen";
    }

    private Disposable task;

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();
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
        }, BackpressureStrategy.BUFFER).repeatWhen(new Function<Flowable<Object>, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Flowable<Object> objectFlowable) throws Exception {
                return Flowable.just("11111", "22222");
            }
        })
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

    protected void runError(){

    }

    @Override
    protected void onDestroy2() {
        super.onDestroy2();
        if(task != null) task.dispose();
    }
}

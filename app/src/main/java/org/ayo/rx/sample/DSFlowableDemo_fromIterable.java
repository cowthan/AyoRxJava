package org.ayo.rx.sample;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_fromIterable extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "from";
    }

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();
        Flowable.fromIterable(list)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                //这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
                //调用request()方法，会立即触发onNext()方法
                //在onComplete()方法完成，才会再执行request()后边的代码
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.e("hahahahha--onNext", s);
                notifyy(s);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("hahahahha--onError", t.getMessage());
            }

            @Override
            public void onComplete() {
                //由于Reactive-Streams的兼容性，方法onCompleted被重命名为onComplete
                Log.e("hahahahha--onComplete", "complete--flowable");
            }
        });
    }

    protected void runError(){

    }

}

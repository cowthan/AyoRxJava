package org.ayo.rx.sample;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
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

public class Rx_buffer extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "buffer";
    }

    private Disposable task;

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();
        task = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                List<String> list = DataMgmr.Memory.getDataListQuick();
                Log.i("repeat", "取数据");
                for(String s: list){
                    e.onNext(s);
                    sleep(500);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                //.buffer(3)  ///攒够3个，形成一个List<String>，然后再当成一条发送
                //.buffer(3, 4)  //buffer(count, skip)，每当收到2项数据，就用3项数据来填充缓存
                                //buffer(3, 3)相当于buffer(3)
                                //
                                //会有重叠，当skip < count，buffer(3, 2)收到的顺序是123,345,567，因为收到2时，会用123填充，接着发3,4，这时就用3,4,5填充，这不就重了
                                //会有间隙，即消息丢失，当skip > count，buffer(3. 4)收到的顺序是123 567 9 10 11 13 14 15，因为收到4时，会用123填充，接着发5,6,7,8，用567填充，这不4就丢了
//                .buffer(3, new Callable<List<String>>() {
//                    @Override
//                    public List<String> call() throws Exception {
//                        return new ArrayList<String>();
//                    }
//                })               ////由用户提供一个缓存

                //.buffer(1000, 2000, TimeUnit.MILLISECONDS)   ///1秒钟收集一次，2秒钟发射一次（新建list，发射之前的list），中间的都丢弃了
                .buffer(new Callable<Flowable<Long>>() {
                    @Override
                    public Flowable<Long> call() throws Exception {
                        return Flowable.interval(1, 2, TimeUnit.SECONDS);
                    }
                })   ///buffer(boundary)，传入一个Observable，buffer会监视这个Observable，一旦这个Observable发了一个值，buffer就创建一个新缓存开始收集原始Observable，并发射旧的缓存
                    ///好像只能根据initialDelay的值来？？？ 例如上面是initailDelay是1秒，后面的间隔是2秒，但收到的订阅消息都是1秒一次，总共8次
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> list) throws Exception {
                        notifyy("-------------onNext---------------");
                        for(String s: list){
                            ///onNext一次
                            notifyy(s);
                        }
                    }
                }, Functions.ERROR_CONSUMER,
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                notifyy("\nonComplete---结束了！@@");
                            }
                        },
                        FlowableInternalHelper.RequestMax.INSTANCE);

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

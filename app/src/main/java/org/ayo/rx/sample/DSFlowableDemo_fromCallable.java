package org.ayo.rx.sample;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_fromCallable extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "Flowable.fromCallable";
    }

    protected void runOk(){


        /*ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep();
                return "欲迎还拒羞解衣";
            }
        });*/

        Flowable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                sleep();
                List<String> list = DataMgmr.Memory.getDataListQuick();
                return list; //"半似游客半似鸡";
            }
        }).subscribeOn(Schedulers.io())
                .flatMap(new Function<List<String>, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(List<String> strings) throws Exception {
                        return Flowable.fromIterable(strings);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        notifyy(s);
                    }
                });
    }

    protected void runError(){

    }

}

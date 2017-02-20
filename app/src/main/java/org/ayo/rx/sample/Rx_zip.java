package org.ayo.rx.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class Rx_zip extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "zip";
    }

    private Disposable task;

    protected void runOk(){
        final String[] list1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        final String[] list2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};

        Flowable<String> flowable1 = Flowable.interval(0, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                int i = aLong.intValue();
                if(i >= list1.length){
                    return "flowable 1 " + "到头了";
                }else{
                    return  list1[i];
                }
            }
        });

        Flowable<String> flowable2 = Flowable.interval(500, 1000, TimeUnit.MILLISECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                int i = aLong.intValue();
                if(i >= list2.length){
                    return "flowable 2 " + "到头了";
                }else{
                    return list2[i];
                }
            }
        });
        task = Flowable.zip(flowable1, flowable2, new BiFunction<String, String, CharSequence>() {
                    @Override
                    public CharSequence apply(String s, String s2) throws Exception {
                        return s + "----" + s2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence s) throws Exception {
                        notifyy("item--" + s);
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

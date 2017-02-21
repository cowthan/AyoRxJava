package org.ayo.rx.sample;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
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

public class Rx_skipUntil extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "take";
    }
    @Override
    protected String getImageName() {
        return "skipUntil";
    }
    private Disposable task;

    @Override
    protected String getCodeNormal() {
        return "Flowable.interval(1, 1, TimeUnit.SECONDS)\n" +
                "                .skipUntil(new Publisher<Long>() {\n" +
                "                    @Override\n" +
                "                    public void subscribe(Subscriber<? super Long> s) {\n" +
                "\n" +
                "                        s.onNext(1L);\n" +
                "                        s.onComplete();\n" +
                "                    }\n" +
                "                })";
    }

    protected void runOk(){
        /*
        - empty
            - 直接调用complete
         */
        task = Flowable.interval(1, 1, TimeUnit.SECONDS)
                .skipUntil(new Publisher<Long>() {
                    @Override
                    public void subscribe(Subscriber<? super Long> s) {

                        s.onNext(1L);
                        s.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Exception {
                        notifyy(s + "");
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

package org.ayo.rx.sample;

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

public class Rx_merge2 extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "concat";
    }

    private Disposable task;

    protected void runOk(){
        final String[] list1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        final String[] list2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};

        Flowable<String> flowable1 = Flowable.fromArray(list1);
        Flowable<String> flowable2 = Flowable.fromArray(list2);
        task = Flowable.merge(flowable1, flowable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object s) throws Exception {
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

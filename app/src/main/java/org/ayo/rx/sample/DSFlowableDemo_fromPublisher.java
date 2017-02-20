package org.ayo.rx.sample;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_fromPublisher extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "from";
    }

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();
        Flowable<String> flowable = Flowable.fromIterable(list);

        Flowable.fromPublisher(flowable)
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

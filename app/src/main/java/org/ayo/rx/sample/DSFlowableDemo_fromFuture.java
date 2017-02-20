package org.ayo.rx.sample;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DSFlowableDemo_fromFuture extends BaseRxDemo {

    @Override
    protected String getTitle() {
        return "from";
    }

    protected void runOk(){
        List<String> list = DataMgmr.Memory.getDataListQuick();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep();
                return "//  \n" +
                        "//                            _ooOoo_  \n" +
                        "//                           o8888888o  \n" +
                        "//                           88\" . \"88  \n" +
                        "//                           (| -_- |)  \n" +
                        "//                           O\\  =  /O  \n" +
                        "//                        ____/`---'\\____  \n" +
                        "//                      .'  \\\\|     |//  `.  \n" +
                        "//                     /  \\\\|||  :  |||//  \\  \n" +
                        "//                    /  _||||| -:- |||||-  \\  \n" +
                        "//                    |   | \\\\\\  -  /// |   |  \n" +
                        "//                    | \\_|  ''\\---/''  |   |  \n" +
                        "//                    \\  .-\\__  `-`  ___/-. /  \n" +
                        "//                  ___`. .'  /--.--\\  `. . __  \n" +
                        "//               .\"\" '<  `.___\\_<|>_/___.'  >'\"\".  \n" +
                        "//              | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |  \n" +
                        "//              \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /  \n" +
                        "//         ======`-.____`-.___\\_____/___.-`____.-'======  \n" +
                        "//                            `=---='  \n" +
                        "//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  \n" +
                        "//                      佛祖保佑       永无BUG  ";
            }
        });
        Flowable.fromFuture(future, Schedulers.computation())
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

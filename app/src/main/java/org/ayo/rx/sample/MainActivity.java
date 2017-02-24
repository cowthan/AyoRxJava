package org.ayo.rx.sample;

import android.os.Bundle;

import com.jiang.android.rxjavaapp.R;
import com.jiang.android.rxjavaapp.activity.LauncherActivity;

import org.ayo.sample.menu.Leaf;
import org.ayo.sample.menu.MainPagerActivity;
import org.ayo.sample.menu.Menu;
import org.ayo.sample.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MainPagerActivity {

    private List<Menu> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }

    private void init(){
        menus = new ArrayList<Menu>();

        ///--------------------------菜单
        Menu m = new Menu("RxJava", R.drawable.weixin_normal, R.drawable.weixin_pressed);
        menus.add(m);
        {
            MenuItem menuItem = new MenuItem("基础", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("关于ReactiveX和Rxjava第一代", "", LauncherActivity.class, 1));
                menuItem.addLeaf(new Leaf("----Rxjava2的数据源----", "", null));
                menuItem.addLeaf(new Leaf("Flowable: 0到N个item，支持backpressure", "", DSFlowableDemo_create.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: create", "", DSFlowableDemo_create.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: fromIterable", "", DSFlowableDemo_fromIterable.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: fromArray", "", DSFlowableDemo_fromArray.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: fromCallable", "", DSFlowableDemo_fromCallable.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: fromFuture", "", DSFlowableDemo_fromFuture.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: fromPublisher", "", DSFlowableDemo_fromPublisher.class));
                menuItem.addLeaf(new Leaf("- Flowable创建: just", "", DSFlowableDemo_just.class));
                menuItem.addLeaf(new Leaf("Observable: 0到N个item，no backpressure", "", null));
                menuItem.addLeaf(new Leaf("Single：1个item或者error", "", null));
                menuItem.addLeaf(new Leaf("Completable：无item，只有comletion或者error", "", null));
                menuItem.addLeaf(new Leaf("Maybe：no item，或者one item，或者an error", "", null));
            }

            menuItem = new MenuItem("操作符", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("-----创建操作", "", null));
                menuItem.addLeaf(new Leaf("just", "", DSFlowableDemo_just.class));
                menuItem.addLeaf(new Leaf("from", "", DSFlowableDemo_create.class));
                menuItem.addLeaf(new Leaf("create", "", DSFlowableDemo_create.class));
                menuItem.addLeaf(new Leaf("repeat", "", Rx_repeat.class));
                menuItem.addLeaf(new Leaf("repeatWhen ？？？", "", Rx_repeatWhen.class));
                menuItem.addLeaf(new Leaf("defer", "", Rx_defer.class));
                menuItem.addLeaf(new Leaf("range", "", Rx_range.class));
                menuItem.addLeaf(new Leaf("interval", "", Rx_interval.class));
                menuItem.addLeaf(new Leaf("timer", "", Rx_timer.class));
                menuItem.addLeaf(new Leaf("empty", "", Rx_empty.class));
                menuItem.addLeaf(new Leaf("error", "", Rx_error.class));
                menuItem.addLeaf(new Leaf("never", "", Rx_never.class));
                menuItem.addLeaf(new Leaf("throw", "", null));
                menuItem.addLeaf(new Leaf("start", "", null));

                menuItem.addLeaf(new Leaf("-----变换操作", "", null));
                menuItem.addLeaf(new Leaf("map", "", Rx_map.class));
                menuItem.addLeaf(new Leaf("flatMap", "", Rx_flatMap.class));
                menuItem.addLeaf(new Leaf("flatMapIterable", "", Rx_flatMapIterable.class));
                menuItem.addLeaf(new Leaf("flatMapMaybe", "", Rx_flatMapMaybe.class));
                menuItem.addLeaf(new Leaf("flatMapSingle", "", Rx_flatMapSingle.class));
                menuItem.addLeaf(new Leaf("flatMapCompletable", "", Rx_flatMapCompletable.class));
                menuItem.addLeaf(new Leaf("concatMap", "", Rx_contactMap.class));
                menuItem.addLeaf(new Leaf("scan", "", Rx_scan.class));
                menuItem.addLeaf(new Leaf("buffer", "", Rx_buffer.class));
                menuItem.addLeaf(new Leaf("window", "", Rx_window.class));
                menuItem.addLeaf(new Leaf("cast", "", Rx_cast.class));
                menuItem.addLeaf(new Leaf("groupBy", "", Rx_groupBy.class));
                menuItem.addLeaf(new Leaf("switchMap", "", null));

                menuItem.addLeaf(new Leaf("-----合并操作", "", null));
                menuItem.addLeaf(new Leaf("startWith", "", Rx_startWith.class));
                menuItem.addLeaf(new Leaf("concat", "", Rx_concat.class));
                menuItem.addLeaf(new Leaf("merge", "", Rx_merge.class));
                menuItem.addLeaf(new Leaf("merge 2", "", Rx_merge2.class));
                menuItem.addLeaf(new Leaf("zip", "", Rx_zip.class));
                menuItem.addLeaf(new Leaf("combineLatest", "",  Rx_combineLatest.class));
                menuItem.addLeaf(new Leaf("and", "", Rx_and.class));
                menuItem.addLeaf(new Leaf("then", "", Rx_then.class));
                menuItem.addLeaf(new Leaf("when", "", Rx_when.class));
                menuItem.addLeaf(new Leaf("join", "", Rx_join.class));
                menuItem.addLeaf(new Leaf("groupJoin", "", Rx_groupJoin.class));
                menuItem.addLeaf(new Leaf("switchOnNext", "", Rx_switchOnNext.class));



            }

            menuItem = new MenuItem("操作符2", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("-----过滤操作", "", null));
                menuItem.addLeaf(new Leaf("filter", "", Rx_filter.class));
                menuItem.addLeaf(new Leaf("ofType", "", Rx_ofType.class));
                menuItem.addLeaf(new Leaf("timeout--by time", "", Rx_timeout.class));
                menuItem.addLeaf(new Leaf("timeout--by function", "", Rx_timeout2.class));
                menuItem.addLeaf(new Leaf("timeout--by publisher？？", "", Rx_timeout3.class));
                menuItem.addLeaf(new Leaf("distinct", "去重", null));
                menuItem.addLeaf(new Leaf("distinctUntilChanged", "去连续的重", null));
                menuItem.addLeaf(new Leaf("ignoreElements：忽略item，不走onNext", "", null));

                menuItem.addLeaf(new Leaf("-----过滤操作：take系列", "", null));
                menuItem.addLeaf(new Leaf("take--by count", "", Rx_take.class));
                menuItem.addLeaf(new Leaf("take---by time", "", Rx_take2.class));
                menuItem.addLeaf(new Leaf("takeLast--by count", "", Rx_takeLast.class));
                menuItem.addLeaf(new Leaf("takeLast---by time", "", Rx_takeLast2.class));
                menuItem.addLeaf(new Leaf("takeUntil--Pushlisher", "", Rx_takeUntil1.class));
                menuItem.addLeaf(new Leaf("takeUntil--Predicate", "", Rx_takeUntil2.class));
                menuItem.addLeaf(new Leaf("takeWhile--Predicate", "", Rx_takeWhile.class));
                menuItem.addLeaf(new Leaf("skip--by count", "", Rx_skip.class));
                menuItem.addLeaf(new Leaf("skip--by time", "", Rx_skip2.class));
                menuItem.addLeaf(new Leaf("skipLast--by count", "", Rx_skipLast.class));
                menuItem.addLeaf(new Leaf("skipLast--by time", "", Rx_skipLast2.class));
                menuItem.addLeaf(new Leaf("skipUntil--Pushlisher", "", Rx_skipUntil.class));
                menuItem.addLeaf(new Leaf("skipWhile--Predicate", "", Rx_skipWhile.class));
                menuItem.addLeaf(new Leaf("first", "", Rx_first.class));
                menuItem.addLeaf(new Leaf("firstElement", "", Rx_firstElement.class));
                menuItem.addLeaf(new Leaf("firstOrError", "", Rx_firstOrError.class));
                menuItem.addLeaf(new Leaf("-----过滤操作：采样系列", "", null));

                menuItem.addLeaf(new Leaf("sample和throttleLast--by time", "", Rx_sample.class));
                menuItem.addLeaf(new Leaf("sample和throttleLast--by Publisher", "", Rx_sample2.class));
                menuItem.addLeaf(new Leaf("debounce和throttleWithTimeout--by time", "", Rx_debounce.class));
                menuItem.addLeaf(new Leaf("debounce和throttleWithTimeout--by sampler", "", Rx_debounce2.class));
                menuItem.addLeaf(new Leaf("throttleFirst", "", Rx_throttleFirst.class));


                menuItem.addLeaf(new Leaf("-----条件和布尔操作", "", null));
                menuItem.addLeaf(new Leaf("amb", "给定多个Observable，只让第一个发射数据的Observable发射全部数据", null));
                menuItem.addLeaf(new Leaf("defaultIfEmpty", "发射来自原始Observable的数据，如果原始Observable没有发射数据，就发射默认数据", null));
                menuItem.addLeaf(new Leaf("doWhile", "发射原始Observable的数据序列，然后重复发射，直到不满足条件", null));
                menuItem.addLeaf(new Leaf("ifThen", "只有当某个条件为真时，才发射原始Observable的数据，否则发射一个别的", null));
                menuItem.addLeaf(new Leaf("switchCase", "", null));
                menuItem.addLeaf(new Leaf("whileDo", "", null));
                menuItem.addLeaf(new Leaf("all", "", null));
                menuItem.addLeaf(new Leaf("contains", "", null));
                menuItem.addLeaf(new Leaf("exists", "", null));
                menuItem.addLeaf(new Leaf("isEmpty", "", null));
                menuItem.addLeaf(new Leaf("sequenceEqual", "", null));

                menuItem.addLeaf(new Leaf("-----算数和聚合操作", "", null));
                menuItem.addLeaf(new Leaf("math模块", "", null));
                menuItem.addLeaf(new Leaf("contact", "", null));
                menuItem.addLeaf(new Leaf("count和countLong", "", null));
                menuItem.addLeaf(new Leaf("reduce", "", null));
                menuItem.addLeaf(new Leaf("collect", "", null));
                menuItem.addLeaf(new Leaf("toList", "", null));
                menuItem.addLeaf(new Leaf("toSortedList", "", null));
                menuItem.addLeaf(new Leaf("toMap", "", null));
                menuItem.addLeaf(new Leaf("toMultiMap", "", null));


                menuItem.addLeaf(new Leaf("-----字符串操作", "", null));
                menuItem.addLeaf(new Leaf("byLine", "", null));
                menuItem.addLeaf(new Leaf("decode", "", null));
                menuItem.addLeaf(new Leaf("encode", "", null));
                menuItem.addLeaf(new Leaf("from", "", null));
                menuItem.addLeaf(new Leaf("join", "", null));
                menuItem.addLeaf(new Leaf("split", "", null));
                menuItem.addLeaf(new Leaf("stringConcat", "", null));

                menuItem.addLeaf(new Leaf("-----其他操作", "", null));
                menuItem.addLeaf(new Leaf("自定义", "", null));
            }

            menuItem = new MenuItem("操作符3", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("-----错误操作", "", null));
                menuItem.addLeaf(new Leaf("onErrorResumeNext", "", Rx_onErrorResumeNext.class));
                menuItem.addLeaf(new Leaf("onErrorReturn", "", null));
                menuItem.addLeaf(new Leaf("onEcxceptionResumeNext", "", null));
                menuItem.addLeaf(new Leaf("retry", "", null));
                menuItem.addLeaf(new Leaf("retryWhen", "", null));

                menuItem.addLeaf(new Leaf("-----辅助操作", "", null));
                menuItem.addLeaf(new Leaf("materialize", "将Observable转换成一个通知列表", null));
                menuItem.addLeaf(new Leaf("dematerialize", "将通知列表转换成一个Observable", null));
                menuItem.addLeaf(new Leaf("timestamp", "给Observeable发射的每个数据项添加一个时间戳", null));
                menuItem.addLeaf(new Leaf("serialize", "强制Observable按次序发射数据并要求功能是完好的", null));
                menuItem.addLeaf(new Leaf("observeOn", "", null));
                menuItem.addLeaf(new Leaf("subscribeOn", "", null));
                menuItem.addLeaf(new Leaf("doOnEach", "注册一个动作，对Observable发射的每个数据项使用", null));
                menuItem.addLeaf(new Leaf("doOnSubscribe", "注册一个动作，在观察者订阅时使用", null));
                menuItem.addLeaf(new Leaf("doOnUnsubscribe", "注册一个动作，在观察者取消订阅时使用", null));
                menuItem.addLeaf(new Leaf("doOnCompleted", "注册一个动作，对正常完成的Observabl使用", null));
                menuItem.addLeaf(new Leaf("doOnError", "注册一个动作，对Observable的错误使用", null));
                menuItem.addLeaf(new Leaf("doOnTerminate", "注册一个动作，对Observable完成时使用，无论是否出错", null));
                menuItem.addLeaf(new Leaf("finallyDo", "注册一个动作，在Observable完成时使用", null));
                menuItem.addLeaf(new Leaf("delay", "延时发射Observable的结果", null));
                menuItem.addLeaf(new Leaf("delaySubscription", "延时处理订阅请求", null));
                menuItem.addLeaf(new Leaf("timeInterval", "定期发送数据", null));
                menuItem.addLeaf(new Leaf("using", "创建一个只在Observable生命周期存在的资源", null));
                menuItem.addLeaf(new Leaf("single", "强制返回单个数据，否则抛出异常", null));
                menuItem.addLeaf(new Leaf("toFuture", "将Observable转换成其他对象或数据结构，只能用于BlockingObservable", null));
                menuItem.addLeaf(new Leaf("toIterable", "", null));

                menuItem.addLeaf(new Leaf("-----阻塞操作", "", null));
                menuItem.addLeaf(new Leaf("forEach", "", null));
                menuItem.addLeaf(new Leaf("first", "", null));
                menuItem.addLeaf(new Leaf("firstOrDefault", "", null));
                menuItem.addLeaf(new Leaf("last", "", null));
                menuItem.addLeaf(new Leaf("lastOrDefault", "", null));
                menuItem.addLeaf(new Leaf("mostRecent", "", null));
                menuItem.addLeaf(new Leaf("next", "", null));
                menuItem.addLeaf(new Leaf("single", "", null));
                menuItem.addLeaf(new Leaf("singleOrDefault", "", null));
                menuItem.addLeaf(new Leaf("toFuture", "", null));
                menuItem.addLeaf(new Leaf("toIterable", "", null));
                menuItem.addLeaf(new Leaf("getIterator", "", null));

                menuItem.addLeaf(new Leaf("-----异步操作", "", null));
                menuItem.addLeaf(new Leaf("start", "", null));
                menuItem.addLeaf(new Leaf("toAsync", "", null));
                menuItem.addLeaf(new Leaf("startFuture", "", null));
                menuItem.addLeaf(new Leaf("deferFuture", "", null));
                menuItem.addLeaf(new Leaf("forEachFuture", "", null));
                menuItem.addLeaf(new Leaf("fromAction", "", null));
                menuItem.addLeaf(new Leaf("fromCallable", "", null));
                menuItem.addLeaf(new Leaf("fromRunnable", "", null));
                menuItem.addLeaf(new Leaf("runAsync", "", null));

                menuItem.addLeaf(new Leaf("-----连接操作", "", null));
                menuItem.addLeaf(new Leaf("connect", "", null));
                menuItem.addLeaf(new Leaf("publish", "", null));
                menuItem.addLeaf(new Leaf("replay", "", null));
                menuItem.addLeaf(new Leaf("refCount", "", null));
            }
        }

        ///--------------------------菜单
        m = new Menu("RxAndroid", R.drawable.weixin_normal, R.drawable.weixin_pressed);
        menus.add(m);
        {
            MenuItem menuItem = new MenuItem("官方", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("1111", "", null));
                menuItem.addLeaf(new Leaf("2222", "", null));
                menuItem.addLeaf(new Leaf("3333", "", null));
                menuItem.addLeaf(new Leaf("44444", "", null));
                menuItem.addLeaf(new Leaf("5666", "", null));
            }

            menuItem = new MenuItem("权威第三方", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("1111", "", null));
                menuItem.addLeaf(new Leaf("2222", "", null));
                menuItem.addLeaf(new Leaf("3333", "", null));
                menuItem.addLeaf(new Leaf("44444", "", null));
                menuItem.addLeaf(new Leaf("5666", "", null));
            }

        }


        /////menu finished
    }

    @Override
    public List<Menu> getMenus() {
        return menus;
    }
}

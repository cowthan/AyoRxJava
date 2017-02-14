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
                menuItem.addLeaf(new Leaf("1111", "", null));
                menuItem.addLeaf(new Leaf("2222", "", null));
                menuItem.addLeaf(new Leaf("3333", "", null));
                menuItem.addLeaf(new Leaf("44444", "", null));
                menuItem.addLeaf(new Leaf("5666", "", null));
            }
            menuItem = new MenuItem("其他问题", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("1111", "", null));
                menuItem.addLeaf(new Leaf("2222", "", null));
                menuItem.addLeaf(new Leaf("3333", "", null));
                menuItem.addLeaf(new Leaf("44444", "", null));
                menuItem.addLeaf(new Leaf("5666", "", null));
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

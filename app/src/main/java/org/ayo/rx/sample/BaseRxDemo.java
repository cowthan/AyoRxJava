package org.ayo.rx.sample;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiang.android.rxjavaapp.App;
import com.jiang.android.rxjavaapp.R;
import com.zzhoujay.markdown.MarkDown;

import org.ayo.component.MasterFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.ayo.bitmap.BitmapUtils.computeSampleSize;
import static org.ayo.lang.Bitmaps.getOptions;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public abstract class BaseRxDemo extends MasterFragment {

    public static class OperatorInfo{
        public int ballGraphId;
        public String name;
        public String desc;
        public String code;
    }

    protected abstract void runOk();
    protected abstract void runError();
    protected abstract String getTitle();

    @Override
    protected int getLayoutId() {
        return R.layout.demo_ds;
    }

    TextView tv;
    ScrollView scrollView;
    View container_doc;
    TextView textView;
    ImageView iv_ball;

    private Spanned doc;

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {
        Button btn1 = id(R.id.btn1);
        Button btn2 = id(R.id.btn2);
        Button btn3 = id(R.id.btn3);

        tv = id(R.id.tv);
        scrollView = id(R.id.scrollView);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clearLog();
                runOk();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clearLog();
                runError();
            }
        });

        TextView title = id(R.id.title);
        title.setText(getTitle());

        container_doc = id(R.id.container_doc);

        textView = id(R.id.tv_doc);
        iv_ball = id(R.id.iv_ball);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                readDoc();
            }
        }, 1, TimeUnit.SECONDS);
        executorService.shutdown();

        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDoc();
            }
        });
        container_doc.post(new Runnable() {
            @Override
            public void run() {
                container_doc.setVisibility(View.VISIBLE);
                container_doc.setTranslationX(-container_doc.getWidth());
            }
        });

        container_doc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideDoc();
            }
        });
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideDoc();
            }
        });
        iv_ball.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideDoc();
            }
        });
    }

    private void readDoc(){
        String assetPath = getTitle() + ".md";
        try {
            InputStream ism = getActivity().getAssets().open(assetPath);
            doc = MarkDown.fromMarkdown(ism, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {

                    Log.i("markdown-image", "需要一个图片：" + source);
                    Drawable drawable = new ColorDrawable(Color.LTGRAY);
                    drawable.setBounds(0, 0, textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight(), 400);
                    return drawable;
                }
            }, textView);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("debug", doc.toString());
                    textView.setText(doc);
                    try {
                        Bitmap ballBm = getBitmapFromAssets(getTitle() + ".png", iv_ball.getWidth(), iv_ball.getHeight());
                        iv_ball.setImageBitmap(ballBm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (final Exception e) {
            e.printStackTrace();
            iv_ball.setVisibility(View.GONE);
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(e.getLocalizedMessage());
                }
            });
        }
    }

    private boolean docOpened = false;

    private void showDoc(){
        ObjectAnimator oa = ObjectAnimator.ofFloat(container_doc, "translationX", -container_doc.getWidth(), 0);
        oa.setDuration(400);
        oa.start();
        docOpened = true;
    }

    private void hideDoc(){
        ObjectAnimator oa = ObjectAnimator.ofFloat(container_doc, "translationX", 0, -container_doc.getWidth());
        oa.setDuration(300);
        oa.start();
        docOpened = false;
    }


    @Override
    public boolean onBackPressedSupport() {
        if(docOpened){
            hideDoc();
            return true;
        }else{
            return super.onBackPressedSupport();
        }

    }

    @Override
    protected void onDestroy2() {

    }

    protected void notifyy(String s){
        tv.append("\n" + s);
        if(tv.getHeight() > scrollView.getHeight()){
            int dis = scrollView.getHeight() - tv.getHeight(); //负数
            //Log.i("notify", scrollView.getHeight() + ", " + tv.getHeight());
            scrollView.scrollTo(0, -dis);
        }
    }

    protected void clearLog(){
        tv.setText("");
        scrollView.scrollTo(0, 0);
    }

    protected void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }

    public static Bitmap getBitmapFromAssets(String path, int viewWidth, int viewHeight) throws Exception {
        InputStream inputStream = App.app.getAssets().open(path);
        BitmapFactory.Options options = getOptions(path);
        if(options != null) {
            Rect bitmap = new Rect(0, 0, viewWidth, viewHeight);
            int e = bitmap.width();
            int h = bitmap.height();
            int maxSize = e > h?e:h;
            int inSimpleSize = computeSampleSize(options, maxSize, e * h);
            options.inSampleSize = inSimpleSize;
            options.inJustDecodeBounds = false;
        }

        Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream, (Rect)null, options);

        try {
            inputStream.close();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        return bitmap1;
    }
}

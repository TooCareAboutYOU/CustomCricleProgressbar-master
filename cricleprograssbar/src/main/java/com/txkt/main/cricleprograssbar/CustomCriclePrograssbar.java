package com.txkt.main.cricleprograssbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class CustomCriclePrograssbar extends View {

    //画笔
    private Paint mPaint,mPaint1,mPaintText;



    //圆环宽
    private float mWidth=50;
    private float width=mWidth/2;
    //中心点
    private float mX=200+width;
    private float mY=200+width;
    //半径
    private float mRadius=200;

    //定义矩形
    private RectF rectf;

    //最小值
    private int mMin=0;
    //最大值
    private int mMax=100;

    //宽高
    private int CWidth=100;
    private int CHeight=100;

    public CustomCriclePrograssbar(Context context) { super(context);  init();  }
    public CustomCriclePrograssbar(Context context, AttributeSet attrs) {  super(context, attrs);  init();  }
    public CustomCriclePrograssbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initRectF();

        //当前角度
        float angli=(float)mMin/mMax*360;

        canvas.drawCircle(CWidth/2,CHeight/2,mRadius,mPaint);
        canvas.drawArc(rectf,-90,angli,false,mPaint1);
        canvas.drawText(mMin + "%",CWidth/2,CHeight/2,mPaintText);

        if (mMin < mMax){
            mMin++;
            invalidate();  //强制调用onDraw()方法
        }
    }

    private void init() {
        //第一个画笔
        mPaint=new Paint();
        mPaint.setColor(Color.RED);   //画笔颜色
        mPaint.setAntiAlias(true);   //去掉锯齿
        mPaint.setStyle(Paint.Style.STROKE);  //画笔样式 圆
        mPaint.setStrokeWidth(mWidth);  //圆环宽度

        //第一个画笔
        mPaint1=new Paint();
        mPaint1.setColor(Color.GREEN);   //画笔颜色
        mPaint1.setAntiAlias(true);   //去掉锯齿
        mPaint1.setStyle(Paint.Style.STROKE);  //画笔样式 圆
        mPaint1.setStrokeWidth(mWidth);  //圆环宽度


        //第一个画笔
        mPaintText=new Paint();
        mPaintText.setColor(Color.RED);   //画笔颜色
        mPaintText.setAntiAlias(true);   //去掉锯齿
        mPaintText.setTextAlign(Paint.Align.CENTER);  //文字居中
        mPaintText.setTextSize(50);

        //rectf=new RectF(width, width, mRadius * 2+width, mRadius * 2+width);
    }

    private void initRectF(){
        if (rectf == null){
            rectf=new RectF();
            int viewsize= (int) (mRadius*2);
            int left=(CWidth-viewsize)/2;
            int top=(CHeight-viewsize)/2;
            int right=left+viewsize;
            int bottom=top+viewsize;
            rectf.set(left,top,right,bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        CWidth=getSize(widthMeasureSpec);
        CHeight=getSize(heightMeasureSpec);

        setMeasuredDimension(CWidth,CHeight);
    }

    public int getSize(int spec) {
        int result=-1;
        int mode=MeasureSpec.getMode(spec);
        int size=MeasureSpec.getSize(spec);

        if(mode==MeasureSpec.AT_MOST || mode==MeasureSpec.UNSPECIFIED){
          //测量宽高
            result = (int) (mRadius*2+mWidth);
        }else{
            //默认
            result=size;
        }


        return result;
    }
}


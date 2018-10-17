package mobi.example.zack.bottomnavegationbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BottomNavigationBar extends View {

    private final String TAG = BottomNavigationBar.class.getName();

    private float deep;
    private float circleButtonRadius;
    private float circleButtonBorder;
    private float backgroundHeight;
    private ColorStateList backgroundBottomColor;
    private ColorStateList circleButtonColor;
    private ColorStateList circleButtonBorderColor;

    private int during;

    private int width;
    private int height;

    private Paint circleButtonPaint;
    private Paint circleBorderPaint;
    private Paint bottomBackgroundPaint;


    public BottomNavigationBar(Context context) {
        super(context);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public BottomNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar ,
                defStyleAttr, defStyleRes);
        int indexCount = typedArray.getIndexCount();
        for ( int i = 0 ; i < indexCount ; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.BottomNavigationBar_deep :
                    deep = typedArray.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.BottomNavigationBar_circleButtonRadius:
                    circleButtonRadius = typedArray.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.BottomNavigationBar_circleButtonBorder:
                    circleButtonBorder = typedArray.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.BottomNavigationBar_backgroundHeight:
                    backgroundHeight = typedArray.getDimensionPixelSize(attr,0);
                    break;
                case R.styleable.BottomNavigationBar_backgroundBottomColor:
                    backgroundBottomColor = typedArray.getColorStateList(attr);
                    break;
                case R.styleable.BottomNavigationBar_circleButtonBorderColor:
                    circleButtonBorderColor = typedArray.getColorStateList(attr);
                    break;
                case R.styleable.BottomNavigationBar_circleButtonColor:
                    circleButtonColor = typedArray.getColorStateList(attr);
                    break;
                case R.styleable.BottomNavigationBar_during:
                    during = typedArray.getInt(attr,0);
                    break;
            }
        }

        Log.i(TAG, toString());

    }

    void initCircleButtonPaint() {
        circleButtonPaint = new Paint();
        circleButtonPaint.setColor(circleButtonColor.getColorForState(getDrawableState(),0));
        circleButtonPaint.setAntiAlias(true);
    }

    void initCircleBorderPaint() {

        circleBorderPaint = new Paint();
        circleBorderPaint.setStyle(Paint.Style.STROKE);
        circleBorderPaint.setAntiAlias(true);
        circleBorderPaint.setColor(circleButtonBorderColor.getColorForState(getDrawableState(),0));
        circleBorderPaint.setStrokeWidth(circleButtonBorder);
    }


    void iniBackgroundPaint() {
        bottomBackgroundPaint = new Paint();
        bottomBackgroundPaint.setColor(backgroundBottomColor.getColorForState(getDrawableState(),0));
    }

    @Override
    public String toString() {
        return "BottomNavigationBar{" +
                "deep=" + deep +
                ", \ncircleButtonRadius=" + circleButtonRadius +
                ", \ncircleButtonBorder=" + circleButtonBorder +
                ", \ncircleButtonColor=" + circleButtonColor +
                ", \ncircleButtonBorderColor=" + circleButtonBorderColor +
                ", \nduring=" + during +
                '}';
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w > 0 && h > 0) {
            width = w;
            height = h;
        }

        Log.i(TAG, "width : " + width +  " height : " + height);
    }


    


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initCircleButtonPaint();
        initCircleBorderPaint();
        iniBackgroundPaint();
        canvas.drawCircle(width / 2 , height - circleButtonRadius - backgroundHeight - (circleButtonBorder + 5),
                circleButtonRadius , circleButtonPaint);
        canvas.drawCircle(width / 2 , height - circleButtonRadius - backgroundHeight - (circleButtonBorder + 5),
                circleButtonRadius + 5, circleBorderPaint);
        Rect rect = new Rect();
        rect.set(0, (int) (height - backgroundHeight) , width , height);
        canvas.drawRect(rect,bottomBackgroundPaint);
    }
}

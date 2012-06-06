package org.witness.securesmartcam;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

public class AnimatedBlockView extends View {

	  private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	  private float initX, initY, radius;
	  private boolean drawing = false;

		Random rand = new Random();

	public AnimatedBlockView(Context context) {
	 super(context);
	 // TODO Auto-generated constructor stub
	 init();
	}

	public AnimatedBlockView(Context context, AttributeSet attrs) {
	 super(context, attrs);
	 init();
	}

	public AnimatedBlockView(Context context, AttributeSet attrs, int defStyle) {
	 super(context, attrs, defStyle);
	 
	 init();
	}

	private void init(){
	 paint.setStyle(Paint.Style.FILL);
	 paint.setColor(Color.WHITE);
	 paint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	 // TODO Auto-generated method stub
	 setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
	   MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < 25; i++)
		{
			int a = 150;
			float r = rand.nextFloat()*255f;
			float g = rand.nextFloat()*255f;
			float b = rand.nextFloat()*255f;
	
			paint.setARGB(a,(int)r,(int)g,(int)b);
		
			float x = rand.nextFloat() * getWidth();
			float y = rand.nextFloat() * getHeight();
			
			canvas.drawRect(x, y, 100,100, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	 

	 int action = event.getAction();
	 if (action==MotionEvent.ACTION_MOVE){
	  float x = event.getX();
	  float y = event.getY();

	 // radius = (float) Math.sqrt(Math.pow(x-initX, 2) + Math.pow(y-initY, 2));

	 }
	 else if (action==MotionEvent.ACTION_DOWN){
	  initX = event.getX();
	  initY = event.getY();
	  radius = 1;
	  drawing = true;
	 }
	 else if (action==MotionEvent.ACTION_UP){
	  drawing = false;
	 }
	 invalidate();
	 return true;
	}

}
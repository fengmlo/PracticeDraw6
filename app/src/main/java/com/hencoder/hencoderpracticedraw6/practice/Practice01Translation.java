package com.hencoder.hencoderpracticedraw6.practice;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw6.R;

import static android.os.Build.VERSION.SDK_INT;
import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice01Translation extends RelativeLayout {
	Button animateBt;
	ImageView imageView;
	int count;

	public Practice01Translation(Context context) {
		super(context);
	}

	public Practice01Translation(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public Practice01Translation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		animateBt = (Button) findViewById(R.id.animateBt);
		imageView = (ImageView) findViewById(R.id.imageView);
		if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			// 给音乐图标加上合适的阴影
			imageView.setOutlineProvider(new MusicOutlineProvider());
		}

		animateBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				// TODO 在这里处理点击事件，通过 View.animate().translationX/Y/Z() 来让 View 平移
				count++;
				int x = SDK_INT > Build.VERSION_CODES.LOLLIPOP ? 6 : 4;
				switch (count % x) {
					case 1:
						imageView.animate().translationX(dpToPixel(100));
						break;
					case 2:
						imageView.animate().translationX(0);
						break;
					case 3:
						imageView.animate().translationY(dpToPixel(50));
						break;
					case 4:
						imageView.animate().translationY(0);
						break;
					case 5:
						if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
							imageView.animate().translationZ(dpToPixel(15));
						}
						break;
					case 0:
						if (SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
							imageView.animate().translationZ(0);
						}
						break;
				}
			}

		});
	}

	/**
	 * 为音乐图标设置三角形的 Outline。
	 */
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	class MusicOutlineProvider extends ViewOutlineProvider {
		Path path = new Path();

		{
			path.moveTo(0, dpToPixel(10));
			path.lineTo(dpToPixel(7), dpToPixel(2));
			path.lineTo(dpToPixel(116), dpToPixel(58));
			path.lineTo(dpToPixel(116), dpToPixel(70));
			path.lineTo(dpToPixel(7), dpToPixel(128));
			path.lineTo(0, dpToPixel(120));
			path.close();
		}

		@Override
		public void getOutline(View view, Outline outline) {
			outline.setConvexPath(path);
		}
	}
}
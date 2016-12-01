/**
 * Copyright (c) 2016 bon カードプロジェクト
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 *    https://www.apache.org/license/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on as "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.ac.shohoku.bon.speedcard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *一枚のカードを表すクラス
 * @author 高井萌
 * @version 0.1
 */
public class Card {
private Bitmap mBmp = null;  // 表示用のBitmap
    private Rect mLocation;  // カードの位置
    private boolean mIsTapped;  // カードがタップされたか
private int mW, mH;           // カードの幅と高さ

/**
 * カードのコンストラクタ
 * @param sview リソースを読み込むため、SpeedCardViewを読み込む
 * @param cardName カード名からBitmapを読み込む
  */
    public Card(SpeedCardView sview, String cardName) {
        Resources rs = sview.getResources();  // リソースを取得
        Context context = sview.getContext();  // パッケージ名を取得するためにContextを取得
        int resId = rs.getIdentifier(cardName, "drawable", context.getPackageName());
        mBmp = BitmapFactory.decodeResource(rs, resId);  // 画像取得
        mW = mBmp.getWidth();
        mH = mBmp.getHeight();
        setmLocation(0, 0, mW, mH);   // いったん左上に配置
        mIsTapped = false;  // タップされていない
    }

    /**
     * カードの位置を設定する
     * @param left 左上のx座標
     * @param top 左上のy座標
     * @param right 右下のx座標
     * @param bottom 右下のy座標
     */
    public void setmLocation(int left, int top, int right, int bottom) {
        mLocation = new Rect(left, top, right, bottom);
    }

    /**
     * 長方形をタップされたかどうかをチェックする.タップされていればtrue,そうでなければfalse
     * @param x タップされた位置のx座標
     * @param y タップされた位置のy座標
     * @return タップされたかどうか
     */
    public boolean checkTapped(int x, int y) {
        if (mLocation.left < x && y < mLocation.right &&
                mLocation.top < y && y < mLocation.bottom) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * タップ設定
     * @param b true or false
     */
    public void setTapped(boolean b) {
        mIsTapped = b;
    }

    /**
     * カードがタップされているかどうかチェック
     */
    public boolean isTapped() {
        return mIsTapped;
    }

    /**
     * カードの幅を返す
     * @return
     */
    public int getW() {
        return mW;
    }

    /**
     * カードの高さを返す
     * @return
     */
    public int getH() {
        return mH;
    }

    /**
     * カードの画像を描画する
     * @param canvas
     */
    public void draw(Canvas canvas) {
        float left = mLocation.left;
        float top = mLocation.top;
        if (mBmp != null) {
            canvas.drawBitmap(mBmp, left, top, nwe Paint());  //カードの描画
        }
    }



}













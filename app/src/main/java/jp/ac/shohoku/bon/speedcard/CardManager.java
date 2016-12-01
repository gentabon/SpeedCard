/**
 * Copyright (c) 2016bon カードプロジェクト
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 *      http://www.apache. org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * limitations under the License.
 */
package jp.ac.shohoku.bon.speedcard;

import android.graphics.Canvas;

import android.util.Random;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

/**
 * カード全体を管理するクラス
 * @author bon
 * @version 2016.12.01
 */
public class CardManager {
    Card mCards[];  //ゲームで使うカードの配列
    int mCount;    //どこまでカウントしたかを表す変数

    /**
     * カードを数枚分用意する
     * @param sview 表示する SpeedCardView
     * @param num カードの枚数
     */
    CardManager(SpeedCardView sview, int num){
        mCards = new Card[num];
        for (int i=1; i<=num; i++){
            mCards[i-1] = new Card(sview, "card"+i); //カードの名前はcard1, card2, ...
        }
        mCount = 0; //はじめはカウントされていないので0にしておく
        disCards();  //カード配布
    }

    /**
     * カードを配布
     */
    public void disCards() {
        int left, top, right, bottom;
        Random rand = new Random();

        for (int i = mCards.length - 1; i >= 0; i--){
            left = rand.nextInt(SpeedCardView.NEUX7_WIDTH - mCards[i].getW());
            top = rand.nextInt(SpeedCardVIew.NEUX7_HEIGHT - mCards[i].getH());
            right = left + mCards[i].getW();
            bottom = top + mCards[i].getH();
            mCards[i].setmLocation(left, top, right, bottom);
        }
    }


    /**
     * タップされるべき最小の番号のカードをタップされているかどうかチェックする
     * @param x
     * @param y
     */
    public void checkCards(int x, int y) {
        if (mCards.[mCount].checkTapped(x, y) ){
            mCards[mCount].setTapped(true);
            mCount++;
        }


    /**
     *すべてのカードがタップされたかどうかチェックする.
     * 最後のカードタップされたかどうかでチェックしている.
     * すべてのカードがタップされていれば　true,そうでなければ false
     *@return true or false
     */
   public boolean isFinished(){
       if (mCards.length <= mCount) {
           return true;
       } else {
           return false;
       }
   }

    /**
     * タップされていないすべてのカードを描画する
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (int i = mCards.length - 1; i >= mCount; i--){
            if(mCards[i].isTapped() == false){
                mCards[i].draw(canvas);
            }
        }
    }

}

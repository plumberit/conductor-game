package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Background {

    private Texture sky;
    private Texture skyNext;
    private Texture train;
    private Vector2 position1;
    private Vector2 position2;
    private int speed;


    Background () {
         sky = new Texture("back.png");
         skyNext = new Texture("back.png");
         train = new Texture("train.png");
         position1 = new Vector2(0, 0);
         position2 = new Vector2(1200, 0);
         speed=3;
    }

    public void render(SpriteBatch batch){
        batch.draw(sky, position1.x, position1.y);
        batch.draw(skyNext, position2.x, position2.y);
        batch.draw(train, 50, 175);
    }

    public void update(){
        if (position1.x==-1200) {
            position1.x=1200;
        }
        if (position2.x==-1200) {
            position2.x=1200;
        }
        position1.x-=speed;
        position2.x-=speed;
    }

}

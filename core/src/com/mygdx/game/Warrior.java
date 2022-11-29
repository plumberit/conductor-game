package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Warrior {
    Texture img;
    Texture img1;
    Texture img2;
    Texture img3;
    Texture img4;
    Texture img5;
    Texture img6;
    Texture img7;
    Texture img8;
    Texture img9;
    Texture img10;
    Texture img11;

    Animation anim;
    float stateTime;
    Texture currentFrame;

    Texture imgMirror;
    Vector2 position;
    boolean rotate;
    Rectangle rect;
    int speed;


    public Warrior(){
        imgMirror = new Texture("warrior/golemM.png");
        img = new Texture("warrior/golem1.png");
        img1 = new Texture("warrior/golem2.png");
        img2 = new Texture("warrior/golem3.png");
        img3 = new Texture("warrior/golem4.png");
        //img4 = new Texture("warrior/golem5.png");
        //img5 = new Texture("warrior/golem1.png");
        //img6 = new Texture("warrior/golem1.png");
        //img7 = new Texture("warrior/golem1.png");
        //img8 = new Texture("warrior/golem1.png");
        //img9 = new Texture("warrior/golem1.png");
        //img10 = new Texture("warrior/golem1.png");
        //img11 = new Texture("warrior/golem1.png");
        Texture [] keyFrames = new Texture[4];
        keyFrames[0] = img;
        keyFrames[1] = img1;
        keyFrames[2] = img2;
        keyFrames[3] = img3;
        anim = new Animation(0.1f, keyFrames);
        stateTime=0f;

        position = new Vector2(400,200);
        rotate=true;
        rect = new Rectangle(400, 200, 90, 75);
        speed = 5;
    }

    public void render(SpriteBatch batch){
        if(rotate) {
            //batch.draw(img, position.x, position.y);
            stateTime += Gdx.graphics.getDeltaTime(); // #15
            currentFrame = (Texture) anim.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, position.x, position.y);
        } else {
            batch.draw(imgMirror, position.x, position.y);
        }

    }

    public void update(){
        move();
        connectRect();
    }


    public void move() {
        if (rotate){
            position.x += speed;
            if(position.x>1100) {
                rotate=false;
                if(speed < 15) {
                    //speed+=3;
                }
            }
        } else {
            position.x -= speed;
            if(position.x<50) {
                rotate=true;
            }
        }

    }

    void connectRect() {
        rect.x=position.x;
    }


    public void recreate(){
        position = new Vector2(400,200);
        rotate=true;
    }
}

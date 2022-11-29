package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Behaviour {
    Texture img;
    Vector2 position;
    float vy;
    float gravity;
    boolean jump;

    {
        img = new Texture("bird1.png");
        position = new Vector2(100,380);
        vy = 0;
        gravity = - 0.7f;
        jump=false;
    }

    public void jumpMode() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && position.y<=200){
            jump=true;
        }
        if (jump==true) {
            vy = 15;
            vy += gravity;
            position.y += vy;
            jump = false;
        } else if (position.y>200) {
            vy += gravity;
            position.y += vy;
        }
    }

    public static void runMode() {

    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Person {
    Texture img;
    Texture imgMirror;
    Texture imgJumpUp;
    Texture imgJumpDown;

    Texture runImg1;
    Texture runImg2;
    Texture runImg3;
    Texture runImg4;
    Texture runImg5;
    Texture runImg6;

    Animation anim;
    float stateTime;
    Texture currentFrame;

    Vector2 position;

    float vy;
    float gravity;
    float moveInJump;
    float scrolling;

    boolean timeToJump;
    boolean isFallen;
    boolean isMoveJumpRight;
    boolean isMoveJumpLeft;

    boolean scrollRight;
    boolean letScrollRight;
    boolean scrollLeft;
    boolean letScrollLeft;

    boolean moveRight;
    boolean moveLeft;

    boolean startJump;
    boolean endJump;

    boolean startMoveRight;

    public Person() {
        img = new Texture("person/default/person.png");
        imgMirror = new Texture("person/mirror/person.png");
        vy = 0;
        position = new Vector2(100, 380);
        gravity = -0.65f;
        moveRight = true;
        scrolling = 2;
        imgJumpUp = new Texture("person/default/jump/personJumpUp.png");
        imgJumpDown = new Texture("person/default/jump/personJumpDown.png");

        runImg1= new Texture("person/default/run/person1.png");
        runImg2= new Texture("person/default/run/person2.png");
        runImg3= new Texture("person/default/run/person3.png");
        runImg4= new Texture("person/default/run/person4.png");
        runImg5= new Texture("person/default/run/person5.png");
        runImg6= new Texture("person/default/run/person6.png");

        Texture [] keyFrames = new Texture [6];
        keyFrames[0] = runImg1;
        keyFrames[1] = runImg2;
        keyFrames[2] = runImg3;
        keyFrames[3] = runImg4;
        keyFrames[4] = runImg5;
        keyFrames[5] = runImg6;
        anim = new Animation(0.13f, keyFrames);
        stateTime = 0f;
    }


    public void render(SpriteBatch batch) {
        if(!startJump && !endJump) {
            if (startMoveRight) {
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = (Texture) anim.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, position.x, position.y);
            }
            else if(moveRight) {
                batch.draw(img, position.x, position.y);
            } else if (moveLeft) {
                batch.draw(imgMirror, position.x, position.y);
            }
        } else {
            if(startJump) {
                batch.draw(imgJumpUp, position.x, position.y);
            }
            if (endJump) {
                batch.draw(imgJumpDown, position.x, position.y);
            }
        }
    }

    public void update() {
        if (!isFallen) {
            fall();
        } else {
            tryToJump();
            if (position.y == 200) {
                tryToMove();
            }
        }
    }

    public boolean fall() {
        if (position.y > 200) {
            vy += gravity;
            position.y += vy;
            if (position.y <= 200) {
                position.y = 200;
                isFallen = true;
            }
        }
        return isFallen;
    }

    public void tryToJump() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && position.y == 200) {
            startJump=true;
            vy = 15;
            position.y += vy;
        } else if (position.y > 200) {
            if(vy<0) {
                startJump = false;
                endJump = true;
                gravity=-0.5f;
            }
            vy += gravity;
            if(vy<-10) {
                vy = -10;
            }
            position.y += vy;
            moveInJumpRight();
            moveInJumpLeft();
            if (position.y <= 200) {
                endJump=false;
                position.y = 200;
                if (isMoveJumpRight) {
                    fallInRight();
                }
                if (isMoveJumpLeft) {
                    fallInLeft();
                }
            }
        }
        if (position.y == 200 && letScrollRight) {
            scrollRight();
            gravity = - 0.65f;
        }
        if (position.y == 200 && letScrollLeft) {
            scrollLeft();
            gravity = - 0.65f;
        }
    }

    public void scrollRight() {
        if (scrollRight) {
            position.x = position.x + scrolling;
            scrolling = scrolling - 0.1f;
            if (scrolling < 0) {
                scrollRight = false;
            }
        } else {
            scrolling = 2;
            letScrollRight = false;
        }
    }

    public void scrollLeft() {
        if (scrollLeft) {
            position.x = position.x - scrolling;
            scrolling = scrolling - 0.1f;
            if (scrolling < 0) {
                scrollLeft = false;
            }
        } else {
            scrolling = 2;
            letScrollLeft = false;
        }
    }


    public void moveInJumpRight() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (position.x < 1100) {
                moveLeft = false;
                moveRight = true;
                isMoveJumpRight = true;
                position.x += moveInJump;
                moveInJump += 0.06f;
            }
        }
        if (isMoveJumpRight && position.x < 1100) {
            position.x += moveInJump;
            moveInJump += 0.06f;
            if (moveInJump>3) {
                moveInJump = 3;
            }
        }

    }

    public void fallInRight() {
        isMoveJumpRight = false;
        moveInJump = 0;
        scrollRight = true;
        letScrollRight = true;
    }


    public void moveInJumpLeft() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (position.y > 200 && position.x > 50) {
                moveRight = false;
                moveLeft = true;
                isMoveJumpLeft = true;
                position.x -= moveInJump;
                moveInJump += 0.06f;
            }
        }
        if (isMoveJumpLeft && position.x > 50) {
            position.x -= moveInJump;
            moveInJump += 0.06f;
        }
    }

    public void fallInLeft() {
        isMoveJumpLeft = false;
        moveInJump = 0;
        scrollLeft = true;
        letScrollLeft = true;
    }


    public void tryToMove() {
        startMoveRight = false;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            startMoveRight= true;
            if (position.x < 1100) {
                position.x += 6;
                moveLeft = false;
                moveRight = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (position.x > 50) {
                position.x -= 6;
                moveRight = false;
                moveLeft = true;
            }
        }
    }


    public void recreate() {
        position = new Vector2(100, 380);
        vy = 0;
        timeToJump = false;
        isFallen = false;
        moveRight = true;
    }

    public Vector2[] dotsOfTouch() {
        Vector2[] arr = new Vector2[4];
        arr[0] = position;
        arr[1] = new Vector2(position.x + 50, position.y);
        arr[2] = new Vector2(position.x, position.y + 75);
        arr[3] = new Vector2(position.x + 50, position.y + 75);
        return arr;
    }
}

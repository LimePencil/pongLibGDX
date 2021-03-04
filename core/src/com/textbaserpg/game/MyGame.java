package com.textbaserpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MyGame extends ApplicationAdapter {

	float rect1X = 100;
	float rect1Y = 100;
	float rect2X;
	float rect2Y;
	float speedX = 10;
	float speedY = 3;
	float width = 10;
	float height = 70;
	float barSpeed = 11;
	int point1 = 0;
	int point2 = 0;
	private ShapeRenderer rect1;
	float circleX;
	float circleY;
	float radius = 5;
	private ShapeRenderer circle;
	private ShapeRenderer rect2;
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		rect1 = new ShapeRenderer();
		rect2 = new ShapeRenderer();
		circle = new ShapeRenderer();
		circleX = Gdx.graphics.getWidth()/2;
		circleY = Gdx.graphics.getHeight()/2;
		rect2X = Gdx.graphics.getWidth() - 100;
		rect2Y = Gdx.graphics.getHeight()/2;
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		circleX += speedX;
		circleY += speedY;
		if((circleX >= rect1X && circleX<= rect1X + width+radius&&circleY >= rect1Y && circleY<= rect1Y + height)||(circleX >= rect2X && circleX<= rect2X + width+radius &&circleY >= rect2Y && circleY<= rect2Y + height)){
			speedX *= -1;
		}
		if(circleX < radius){
			speedX *= -1;
			point2 ++;
		}
		if (circleX > Gdx.graphics.getWidth() - radius){
			speedX *= -1;
			point1++;
		}
		if(circleY < radius || circleY> Gdx.graphics.getHeight()-radius){
			speedY *= -1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			rect1Y+=barSpeed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			rect1Y-=barSpeed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			rect2Y+=barSpeed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			rect2Y-=barSpeed;
		}
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		circle.begin(ShapeRenderer.ShapeType.Filled);
		rect1.begin(ShapeRenderer.ShapeType.Line);
		rect2.begin(ShapeRenderer.ShapeType.Line);
		rect1.setColor(0,1,0,1);
		rect2.setColor(0,1,0,1);
		circle.setColor(1,0,0,1);
		circle.circle(circleX,circleY,radius);
		rect1.rect(rect1X,rect1Y,width,height);
		rect1.rect(rect2X,rect2Y,width,height);
		rect2.end();
		rect1.end();
		circle.end();
		batch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, String.valueOf(point1),200,Gdx.graphics.getHeight()-50);
		font.draw(batch, String.valueOf(point2),Gdx.graphics.getWidth()-200,Gdx.graphics.getHeight()-50);
		batch.end();
	}
	
	@Override
	public void dispose () {
		rect1.dispose();
		rect2.dispose();
		circle.dispose();
		batch.dispose();
		font.dispose();
	}
}

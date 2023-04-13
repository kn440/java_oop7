package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DDD.Unit.BaseUnit;
import com.mygdx.game.DDD.Unit.Crossbowman;
import com.mygdx.game.DDD.Unit.Farmer;
import com.mygdx.game.DDD.Unit.Monk;
import com.mygdx.game.DDD.Unit.Robber;
import com.mygdx.game.DDD.Unit.Sniper;
import com.mygdx.game.DDD.Unit.Spearman;
import com.mygdx.game.DDD.Unit.Wizard;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon,crossBowMan,farmer, monk,robber,sniper,spearMan,wizard;
	Music music;
	public static final int  GANG_SIZE =10;
	public static ArrayList<BaseUnit> whiteSide;
	public static ArrayList<BaseUnit> darkSide;
	public static ArrayList<BaseUnit> allUnit;

	private static int step = 0;

	@Override
	public void create () {





		batch = new SpriteBatch();
		fon = new Texture(String.valueOf("fons/"+new Random().nextInt(5))+".png");
		music = Gdx.audio.newMusic(Gdx.files.internal(String.valueOf("music/"+new Random().nextInt(3))+".mp3"));
		music.setVolume(.125f);
		music.play();
		init();

		crossBowMan = new Texture(String.valueOf("units/CrossBowMan.png"));
		farmer = new Texture(String.valueOf("units/Farmer.png"));
		monk = new Texture(String.valueOf("units/Monk.png"));
		robber = new Texture(String.valueOf("units/Robber.png"));
		sniper = new Texture(String.valueOf("units/Sniper.png"));
		spearMan = new Texture(String.valueOf("units/SpearMan.png"));
		wizard = new Texture(String.valueOf("units/Wizard.png"));

	}

	@Override
	public void render () {
		if (step == 0) {
			Gdx.graphics.setTitle("Ход " + step);
		} else Gdx.graphics.setTitle("Ход " + step);
		batch.begin();
		int npcIndex = 0;

		for (int i = 1; i <= MyGdxGame.GANG_SIZE; i++) {
			for (int j = 1; j <= MyGdxGame.GANG_SIZE; j++) {
				//System.out.print(getChar(new Position(j, i)));
			}

			npcIndex++;
		}


		batch.draw(fon, 0, 0);
		batch.end();
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			step++;
			makeStep();

		}

	}

	private static void makeStep(){
		//BaseUnit hero = whiteSide.get(0);
		for (int i = 0; i < GANG_SIZE; i++) {
			BaseUnit hero = whiteSide.get(i);
			hero.step(darkSide,whiteSide);
			BaseUnit villain = darkSide.get(i);
			villain.step(whiteSide, darkSide);

		}}



	private static void init() {
		whiteSide=new ArrayList<>();
		darkSide=new ArrayList<>();
		allUnit=new ArrayList<>();
		int x=1;
		int y=1;
		for (int i=0;i<GANG_SIZE;i++ ){
			switch(new Random().nextInt(4)){
				case 0: whiteSide.add(new Farmer(whiteSide, x, y++, GANG_SIZE)); break;
				case 1: whiteSide.add(new Robber(whiteSide, x, y++, GANG_SIZE)); break;
				case 2: whiteSide.add(new Sniper(whiteSide, x, y++, GANG_SIZE)); break;
				default: whiteSide.add(new Monk(whiteSide, x, y++, GANG_SIZE)); break;
			}
		}
		x=GANG_SIZE;
		y=1;
		for (int i=0; i<GANG_SIZE; i++){
			switch(new Random().nextInt(4)){
				case 0: darkSide.add(new Farmer(darkSide, x, y++, GANG_SIZE)); break;
				case 1: darkSide.add(new Spearman(darkSide, x, y++, GANG_SIZE)); break;
				case 2: darkSide.add(new Crossbowman(darkSide, x, y++, GANG_SIZE)); break;
				default: darkSide.add(new Wizard(darkSide, x, y++, GANG_SIZE)); break;
			}
		}
		allUnit.addAll(whiteSide);
		allUnit.addAll(darkSide);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}

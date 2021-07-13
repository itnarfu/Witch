package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import model.SaveProgress;
import screen.GameScreen;
import screen.WorkbenchScreen;
import screen.MainMenu;



public class Witch extends Game {

	public Screen gameScreen;
	public Screen mainMenu;
	public Screen workbenchScreen;
	SaveProgress save;
	@Override
	public void create () {

		save=new SaveProgress();
		save.SaveProgress();

		gameScreen= new GameScreen(this);
		workbenchScreen=new WorkbenchScreen(this);
		mainMenu = new MainMenu(this);
		setScreen(mainMenu);
	}


}

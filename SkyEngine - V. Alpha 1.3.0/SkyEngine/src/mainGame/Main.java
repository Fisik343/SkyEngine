package mainGame;

import org.lwjgl.input.Keyboard;

import skyEngine.Core.AbstractGame;
import skyEngine.Core.GameContainer;
import skyEngine.Core.GamePad;
import skyEngine.Core.Input;
import skyEngine.Core.Renderer;
import skyEngine.RenderingUtilities.AntiAliasing;
import skyEngine.RenderingUtilities.VSync;

public class Main extends AbstractGame
{
	
	GameState state = GameState.Initialize;
	GamePad controller;
	
	float x;
	float y;
	float width;
	float height;
	
	float rotation;
	
	float red;
	float green;
	float blue;
	float alpha;
	
	public static void main(String[] args)
	{
		GameContainer gc = new GameContainer(new Main());
		gc.setDesignWidth(1920);
		gc.setDesignHeight(1080);
		gc.setFullscreenOnStart(true);
		gc.setMaxResolutionOnStart(true);
		AntiAliasing.setInitialAntiAliasValues(8, 0, 0, 4);
		VSync.enableVSync();
		gc.startGame();
	}

	@Override
	public void update(GameContainer gc, float deltaTime)
	{
		switch (state)
		{
		case Initialize:
			width = 64;
			height = 64;
			x = (1920 - width) / 2;
			y = (1080 - height) / 2;
			rotation = 0;
			red = 255f;
			green = 255f;
			blue = 255f;
			alpha = 255f;
			
			controller = new GamePad();
			break;
		
		case Runtime:
			controller.update();
			
			x += controller.getAxisValue(GamePad.leftJoyX) * 4;
			y += controller.getAxisValue(GamePad.leftJoyY) * 4;
			
			if (controller.isButtonPressed(GamePad.btnA))
			{
				red = 0;
				green = 255;
				blue = 0;
			}
			if (controller.isButtonPressed(GamePad.btnB))
			{
				red = 255;
				green = 0;
				blue = 0;
			}
			if (controller.isButtonPressed(GamePad.btnX))
			{
				red = 0;
				green = 0;
				blue = 255;
			}
			if (controller.isButtonPressed(GamePad.btnY))
			{
				red = 255;
				green = 255;
				blue = 0;
			}
			
			break;
		}
		if (Input.isKeyPressed(Keyboard.KEY_ESCAPE))
		{
			controller.destroy();
			System.exit(0);
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r)
	{
		switch (state)
		{
		case Initialize:
			state = GameState.Runtime;
			break;
		
		case Runtime:
			r.drawColorQuad(x, y, width, height, red, green, blue, alpha, rotation);
			break;
		}
		
	}

}

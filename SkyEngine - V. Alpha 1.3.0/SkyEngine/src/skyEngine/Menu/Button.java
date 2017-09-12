package skyEngine.Menu;

import org.newdawn.slick.opengl.Texture;

import skyEngine.Core.Input;
import skyEngine.Core.Renderer;

public class Button
{

	private float x;
	private float y;
	private int width;
	private int height;
	private Texture image;
	private boolean interpolation = true;

	/**
	 * Constructor for a Button object, uses dimensions of the texture as the
	 * dimensions of the button.
	 * 
	 * @param x
	 *            X-coordinate of the button
	 * @param y
	 *            Y-coordinate of the button
	 * @param image
	 *            Texture of the button
	 */
	public Button(float x, float y, Texture image)
	{
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = image.getImageWidth();
		this.height = image.getImageHeight();
	}

	/**
	 * Constructor for a Button object, uses provided dimensions, and uses
	 * interpolation
	 * 
	 * @param x
	 *            X-coordinate of the button
	 * @param y
	 *            Y-coordinate of the button
	 * @param image
	 *            Texture of the button
	 * @param width
	 *            Width of the button
	 * @param height
	 *            Height of the button
	 */
	public Button(float x, float y, Texture image, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructor for a Button object, uses provided dimensions and
	 * interpolation
	 * 
	 * @param x
	 *            X-coordinate of the button
	 * @param y
	 *            Y-coordinate of the button
	 * @param image
	 *            Texture of the button
	 * @param width
	 *            Width of the button
	 * @param height
	 *            Height of the button
	 * @param interpolation
	 *            Value for using interpolation
	 */
	public Button(float x, float y, Texture image, int width, int height, boolean interpolation)
	{
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = width;
		this.height = height;
		this.interpolation = interpolation;
	}

	/**
	 * Render method for Button objects
	 * 
	 * @param r
	 *            An instance of a Renderer object
	 */
	public void render(Renderer r)
	{
		r.drawTextureQuad(x, y, width, height, image, interpolation);
	}

	/**
	 * Gets if the button has been pressed
	 * 
	 * @return True if the button was pressed last update
	 */
	public boolean isPressed()
	{
		if (Input.isButtonPressed(Input.leftMouseButton))
		{
			if (Input.getMouseX() >= x && Input.getMouseX() <= x + width && Input.getMouseY() >= y
					&& Input.getMouseY() <= y + height)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets if the button is down
	 * 
	 * @return True if the button is down
	 */
	public boolean isDown()
	{
		if (Input.isButton(Input.leftMouseButton))
		{
			if (Input.getMouseX() >= x && Input.getMouseX() <= x + width && Input.getMouseY() >= y
					&& Input.getMouseY() <= y + height)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets if the button was just released
	 * 
	 * @return True if the button was released
	 */
	public boolean isReleased()
	{
		if (Input.isButtonReleased(Input.leftMouseButton))
		{
			if (Input.getMouseX() >= x && Input.getMouseX() <= x + width && Input.getMouseY() >= y
					&& Input.getMouseY() <= y + height)
			{
				return true;
			}
		}
		return false;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public int getWidth()
	{
		return image.getImageWidth();
	}

	public int getHeight()
	{
		return image.getImageHeight();
	}

	public Texture getImage()
	{
		return image;
	}

}

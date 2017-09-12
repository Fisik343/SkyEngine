package skyEngine.Core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class GamePad
{

	private Controller controller = null;
	
	public static final int btnA = 0;
	public static final int btnB = 1;
	public static final int btnX = 2;
	public static final int btnY = 3;
	public static final int btnLB = 4;
	public static final int btnRB = 5;
	public static final int btnSelect = 6;
	public static final int btnStart = 7;
	public static final int btnLJoy = 8;
	public static final int btnRJoy = 9;
	
	public static final int leftJoyY = 0;
	public static final int leftJoyX = 1;
	public static final int rightJoyY = 2;
	public static final int rightJoyX = 3;
	public static final int triggerAxis = 4;
	
	public static final int dpadUp = 0;
	public static final int dpadDown = 1;
	public static final int dpadLeft = 2;
	public static final int dpadRight = 3;
	
	private static boolean[] btnCurrent = new boolean[10];
	private static boolean[] btnLast = new boolean[10];
	
	private static float[] axisCurrent = new float[5];
	private static float[] axisDelta = new float[5];
	
	private static boolean[] dPadCurrent = new boolean[4];
	private static boolean[] dPadLast = new boolean[4];

	/**
	 * Tries to create an instance of a GamePad object. If there is no
	 * controller attached to the computer, this is going to crash your program.
	 */
	public GamePad()
	{
		try
		{
			Controllers.create();
			Controllers.poll();
			for (int i = 0; i < Controllers.getControllerCount(); i++)
			{
				controller = Controllers.getController(i);
				System.out.println(controller.getName());
				if (controller.getName().equals("Controller (XBOX One For Windows)"))
				{
					break;
				}
				if (controller.getName().equals("Controller (XBOX 360 For Windows)"))
				{
					break;
				}
			}
			for (int i = 0; i < controller.getAxisCount(); i++)
			{
				controller.setDeadZone(i, 0.3f);
			}
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < btnCurrent.length; i++)
		{
			btnCurrent[i] = false;
		}
		for (int i = 0; i < btnLast.length; i++)
		{
			btnLast[i] = false;
		}
		for (int i = 0; i < axisCurrent.length; i++)
		{
			axisCurrent[i] = 0.0f;
		}
		for (int i = 0; i < axisDelta.length; i++)
		{
			axisDelta[i] = 0.0f;
		}
	
		for (int i = 0; i < dPadCurrent.length; i++)
		{
			dPadCurrent[i] = false;
		}
		for (int i = 0; i < dPadLast.length; i++)
		{
			dPadLast[i] = false;
		}
	}

	/**
	 * The method to update the controller's variables
	 */
	public void update()
	{
		// Update button variables
		btnLast = btnCurrent.clone();
		for (int i = 0; i < btnCurrent.length; i++)
		{
			btnCurrent[i] = controller.isButtonPressed(i);
		}
		
		// Update axis variables
		for (int i = 0; i < axisDelta.length; i++)
		{
			axisDelta[i] = axisCurrent[i] - (axisCurrent[i] = controller.getAxisValue(i));
		}
		
		// Update d-pad variables
		dPadLast = dPadCurrent.clone();
		dPadCurrent[0] = (controller.getPovY() == -1.0f);
		dPadCurrent[1] = (controller.getPovY() == 1.0f);
		dPadCurrent[2] = (controller.getPovX() == -1.0f);
		dPadCurrent[3] = (controller.getPovX() == 1.0f);
	}
	
	/**
	 * The method to destroy the GamePad object
	 */
	public void destroy()
	{
		Controllers.destroy();
	}
	
	/**
	 * Gets whether or not a button is currently down
	 * @param button The button to check
	 * @return If the button is currently down
	 */
	public boolean isButton(int button)
	{
		if (button >= 0 && button < btnCurrent.length)
		{
			return btnCurrent[button];
		}
		return false;
	}
	
	/**
	 * Gets whether or not a button was just pressed
	 * @param button The button to check
	 * @return If the button was just pressed
	 */
	public boolean isButtonPressed(int button)
	{
		if (button >= 0 && button < btnCurrent.length)
		{
			return (btnCurrent[button] && !btnLast[button]);
		}
		return false;
	}

	/**
	 * Gets whether or not a button was just released
	 * @param button The button to check
	 * @return If the button was just released
	 */
	public boolean isButtonReleased(int button)
	{
		if (button >= 0 && button < btnCurrent.length)
		{
			return (!btnCurrent[button] && btnLast[button]);
		}
		return false;
	}
	
	public float getAxisValue(int axis)
	{
		if (axis >= 0 && axis < axisCurrent.length)
		{
			return axisCurrent[axis];
		}
		return 0.0f;
	}
	
	public float getAxisDelta(int axis)
	{
		if (axis >= 0 && axis < axisDelta.length)
		{
			return axisDelta[axis];
		}
		return 0.0f;
	}
	
	public boolean isDPad(int direction)
	{
		if (direction >= 0 && direction < dPadCurrent.length)
		{
			return dPadCurrent[direction];
		}
		return false;
	}
	
	public boolean isDPadPressed(int direction)
	{
		if (direction >= 0 && direction < dPadCurrent.length)
		{
			return (dPadCurrent[direction] && !dPadLast[direction]);
		}
		return false;
	}
	
	public boolean isDPadReleased(int direction)
	{
		if (direction >= 0 && direction < dPadCurrent.length)
		{
			return (!dPadCurrent[direction] && dPadLast[direction]);
		}
		return false;
	}
}

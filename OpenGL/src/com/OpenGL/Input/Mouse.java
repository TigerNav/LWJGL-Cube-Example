package com.OpenGL.Input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse extends GLFWCursorPosCallback{

	public static float mx, my;

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		mx = (float) arg1;
		my = (float) arg2;
		
		
	}

}

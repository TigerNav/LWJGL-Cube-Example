package com.OpenGL.Camera;

import org.joml.Matrix4f;

import com.OpenGL.Utils.Globals;
import com.OpenGL.Utils.Transformation;
import com.OpenGL.Window.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Projection {
	
	private Transformation transformaiton;
	public Matrix4f projectionMatrix;
	private float aspectRatio;
	
	public Projection() {
		transformaiton = new Transformation();
		aspectRatio = (float) Window.getWidth() / Window.getHeight();
		projectionMatrix = transformaiton.getProjectionMatrix(Globals.FOV, Window.getWidth(), Window.getHeight(), Globals.Z_NEAR, Globals.Z_FAR);
		
	}
	
}

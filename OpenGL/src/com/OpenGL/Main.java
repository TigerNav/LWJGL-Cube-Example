package com.OpenGL;
import static org.lwjgl.glfw.GLFW.*;

import com.OpenGL.Window.Window;

public class Main {

	public static void main(String[] args) {
		
		if(!glfwInit())
			throw new IllegalStateException("no no glfw bad shid poo");
		
		
		Window window = new Window();
		
		window.run();
	}
}

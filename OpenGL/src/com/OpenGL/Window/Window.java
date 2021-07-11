package com.OpenGL.Window;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import com.OpenGL.Input.Mouse;
import com.OpenGL.MainLoop.Loop;
import com.OpenGL.Objects.GameItem;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	private long window;
	private Loop loop;
	private static GLFWVidMode vidMode;
	private GLFWCursorPosCallback cursorPos;
	
	public Window() {
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(1600, 800, "shid", NULL, NULL);
		
		if(window == 0)
			System.out.println("window no open");
		
		glfwMakeContextCurrent(window);
		
		vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, 100, 100);
		
		glfwSwapInterval(1);
		glfwShowWindow(window);
		
		GL.createCapabilities();
		glEnable(GL_DEPTH_TEST);
		glfwSetCursorPosCallback(window, cursorPos = new Mouse());
		
	}
	
	public void run() {

		loop = new Loop();
		
		double secsPerUpdate = 1.0d / 30.0d;
		double previous = glfwGetTime();
		double steps = 0.0;
		double current = glfwGetTime();

		
		while(!glfwWindowShouldClose(window)) {
			double loopStartTime = glfwGetTime();
			double elapsed = loopStartTime - previous;
			previous = current;
			steps += elapsed;
			
			
			glViewport(0, 0, getWidth(), getHeight());
			
			while(steps >= secsPerUpdate) {
				loop.Update();
				
				steps -= secsPerUpdate;
			}
			
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glfwPollEvents();
			
			loop.Render();
			sync(current);
			glfwSwapBuffers(window);
			
			
			
		}
		
		glfwTerminate();		
	}
	
	public static int getWidth() {
		return vidMode.width();
	}
	public static int getHeight() {
		return vidMode.height();
	}
	
	private void sync(double loopStartTime) {
		float loopSlot = 1f/50;
		double endTime = loopStartTime + loopSlot;
		while(glfwGetTime() < endTime) {
			try {
				Thread.sleep(1);
			}catch(InterruptedException ie) {}
		}
	}
	
}

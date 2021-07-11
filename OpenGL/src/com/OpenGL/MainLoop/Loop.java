package com.OpenGL.MainLoop;

import com.OpenGL.Camera.Projection;
import com.OpenGL.Input.Mouse;
import com.OpenGL.Objects.GameItem;
import com.OpenGL.Utils.Model;
import com.OpenGL.Utils.Shader;
import com.OpenGL.Utils.Transformation;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Loop {
	
	Model model;
	Transformation transformation;
	Shader shader;
	Projection projection;
	GameItem[] gameItems;
	GameItem gameItem;
	Matrix4f worldMatrix;
	Vector3f offset = new Vector3f();
	public static float x = -1, y = 0, z = -4;
	
	public Loop() {

		float[] positions = new float[] {
				-0.5f, 0.5f, 0.5f,
				// V1
				-0.5f, -0.5f, 0.5f,
				// V2
				0.5f, -0.5f, 0.5f,
				// V3
				0.5f, 0.5f, 0.5f,
				// V4
				-0.5f, 0.5f, -0.5f,
				// V5
				0.5f, 0.5f, -0.5f,
				// V6
				-0.5f, -0.5f, -0.5f,
				// V7
				0.5f, -0.5f, -0.5f,
		};

		float[] colours = new float[]{
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				0.0f, 0.5f, 0.5f,
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				0.0f, 0.5f, 0.5f,
		};
		
		int[] indices = new int[]{
				0, 1, 3, 3, 1, 2,
				// Top Face
				4, 0, 3, 5, 4, 3,
				// Right face
				3, 2, 7, 5, 3, 7,
				// Left face
				6, 1, 0, 6, 0, 4,
				// Bottom face
				2, 1, 6, 2, 6, 7,
				// Back face
				7, 6, 4, 7, 4, 5,
		};
		
		shader = new Shader("Shader");	
		transformation = new Transformation();
		projection = new Projection();
		model = new Model(positions, colours, indices);
		gameItem = new GameItem(model);
		gameItem.setPosition(x, y, z);
		gameItems = new GameItem[]{gameItem};
	
	
	}

	
	public void Update() {
		
		gameItem.setPosition(Mouse.mx / 800, -Mouse.my / 300, z);
		
		System.out.println(Mouse.mx / 800+","+ -Mouse.my / 300);
		
		shader.bind();
		
				
		
	}
	
	public void Render() { 
		shader.createUniform("worldMatrix");
		shader.createUniform("projectionMatrix"); 
		shader.setUniform("projectionMatrix", projection.projectionMatrix);
		
		float rotation = gameItem.getRotation().x + 1.5f;
		if ( rotation > 360 ) {
		rotation = 0;
		}
		gameItem.setRotation(rotation, rotation, rotation);
		for(GameItem gameItem : gameItems) {
			worldMatrix = transformation.getWorldMatrix(gameItem.getPosition(), gameItem.getRotation(), gameItem.getScale());
			shader.setUniform("worldMatrix", worldMatrix);
			
			gameItem.getModel().render();
			
			
			
		}	
		
	}
	
}

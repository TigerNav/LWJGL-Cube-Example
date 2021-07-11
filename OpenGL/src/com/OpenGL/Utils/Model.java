package com.OpenGL.Utils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;


public class Model {
private int drawCount;
	
	private int vertexObject;
	private int ColorObject;
	
	private int indexObject;
	
	public Model(float[] vertices, float[] colours,int[] indices) {
		drawCount = indices.length;
		
		vertexObject = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexObject);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		
		ColorObject = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, ColorObject);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(colours), GL_STATIC_DRAW);
		
		indexObject = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexObject);
		
		IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	protected void finalize() throws Throwable {
		glDeleteBuffers(vertexObject);
		glDeleteBuffers(ColorObject);
		glDeleteBuffers(indexObject);
		super.finalize();
	}
	
	public void render() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glBindBuffer(GL_ARRAY_BUFFER, vertexObject);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, ColorObject);
		glVertexAttribPointer(1, 3, GL_FLOAT, false , 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexObject);
		glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
	}
	
	private FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}

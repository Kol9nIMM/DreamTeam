package Core;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {
	
	public Main() {
		if(!glfwInit()) {				//��� ������� ����������� ����
			System.err.println("Failed to initialize GLFW!");//������� �������
			System.exit(1);				//������� �����
		}
		long window = glfwCreateWindow(640, 480, "TestWindow", 0, 0);//������, ������ �� ����� �����
		
		glfwShowWindow(window);			//����� ����
		glfwMakeContextCurrent(window); //���������� �������� (�� �� ��)
		
		GL.createCapabilities(); 
		
		glEnable(GL_TEXTURE_2D);
		
		float[] vertices = new float[] {
				-0.5f,0.5f,0,	//0	
				0.5f,0.5f,0,	//1
				0.5f,-0.5f,0,	//2
				-0.5f,-0.5f,0,	//4
		};
		float[] texture = new float[] {
				0,0,
				1,0,
				1,1,			
				0,1,
		};
		int[] indices = new int[] {
				0, 1, 2, 2, 3, 0
		};
		
		Model model = new Model(vertices, texture, indices);
		
		Shader shader = new Shader("shader");
		
		Texture tex = new Texture("./res/TestTexture.png");
		
		glClearColor(0, 0, 0, 0);		//���� ���� � ������ RGBA (�������� �� 0 �� 1)
		
		Input input = new Input(window);
		float x = 0;
		
		while(glfwWindowShouldClose(window) != true) {
			
			if(input.GetKey(KeyCode.A)) {
				x -= 0.001f;
			}
			if(input.GetKey(KeyCode.D)) {
				x += 0.001f;
			}
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);//�������� ����,���� ������� ��������, �������� � glClearColor
			
			shader.bind();
			shader.setUniform("sampler", 0);//������ ����������������� �������� � ������� ������ (������ 32)
			tex.bind(0);					//��������� �������� � ������� ������
			model.render();
			
			glfwSwapBuffers(window);
		}
		glfwTerminate();
	}
	public static void main(String[] args) {
		new Main();
	}

}

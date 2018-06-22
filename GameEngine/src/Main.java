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
			
			glClear(GL_COLOR_BUFFER_BIT);//�������� ����,����� ���� ������� ��������, �������� � glClearColor
			
			tex.bind();
			glBegin(GL_QUADS);				
				glTexCoord2f(0, 0);
				glVertex2f(-0.5f+x, 0.5f);
				
				glTexCoord2f(1, 0);
				glVertex2f(0.5f+x, 0.5f);
				
				glTexCoord2f(1, 1);
				glVertex2f(0.5f+x, -0.5f);
				
				glTexCoord2f(0, 1);
				glVertex2f(-0.5f+x, -0.5f);
			glEnd();
			
			glfwSwapBuffers(window);
		}
		glfwTerminate();
	}
	public static void main(String[] args) {
		new Main();
	}

}

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {
	
	public Main() {
		if(!glfwInit()) {				//при помилці ініціалізації вікна
			System.err.println("Failed to initialize GLFW!");//вивести помилку
			System.exit(1);				//закрити прогу
		}
		long window = glfwCreateWindow(640, 480, "TestWindow", 0, 0);//ширина, висота та текст фікна
		
		glfwShowWindow(window);			//показ вікна
		glfwMakeContextCurrent(window); //встановити контекст (хз що це)
		
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
		
		Texture tex = new Texture("./res/TestTexture.png");
		
		glClearColor(0, 0, 0, 0);		//Колір фону у системі RGBA (значення від 0 до 1)
		
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
			
			glClear(GL_COLOR_BUFFER_BIT);//очищення вікна,тобто його залиття кольором, вказаним у glClearColor
			
			tex.bind();
			
			model.render();
			
			/*glBegin(GL_QUADS);				
				glTexCoord2f(0, 0);
				glVertex2f(-0.5f+x, 0.5f);
				
				glTexCoord2f(1, 0);
				glVertex2f(0.5f+x, 0.5f);
				
				glTexCoord2f(1, 1);
				glVertex2f(0.5f+x, -0.5f);
				
				glTexCoord2f(0, 1);
				glVertex2f(-0.5f+x, -0.5f);
			glEnd();*/
			
			glfwSwapBuffers(window);
		}
		glfwTerminate();
	}
	public static void main(String[] args) {
		new Main();
	}

}

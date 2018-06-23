package Core;
import static org.lwjgl.glfw.GLFW.*;
import java.util.*;

public class Input {
		
	private EnumMap<KeyCode, Integer> em = new EnumMap<>(KeyCode.class);
	private EnumMap<KeyCode, Integer> keysState = new EnumMap<>(KeyCode.class);
	private Map<Integer, Integer> mk = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> mouseKeysState = new HashMap<Integer,Integer>();
	private long window;
	
	public Input(long win) {//конструктор приймає, походу хандл, вікна
		window = win;
		//тут ініціалізуються усі клавіші 
		em.put(KeyCode.A, GLFW_KEY_A);		em.put(KeyCode.B, GLFW_KEY_B);		em.put(KeyCode.C, GLFW_KEY_C);
		em.put(KeyCode.D, GLFW_KEY_D);		em.put(KeyCode.E, GLFW_KEY_E);		em.put(KeyCode.F, GLFW_KEY_F);
		em.put(KeyCode.G, GLFW_KEY_G);		em.put(KeyCode.H, GLFW_KEY_H);		em.put(KeyCode.I, GLFW_KEY_I);
		em.put(KeyCode.K, GLFW_KEY_K);		em.put(KeyCode.L, GLFW_KEY_L);		em.put(KeyCode.M, GLFW_KEY_M);
		em.put(KeyCode.N, GLFW_KEY_N);		em.put(KeyCode.O, GLFW_KEY_O);		em.put(KeyCode.P, GLFW_KEY_P);
		em.put(KeyCode.Q, GLFW_KEY_Q);		em.put(KeyCode.R, GLFW_KEY_R);		em.put(KeyCode.S, GLFW_KEY_S);
		em.put(KeyCode.T, GLFW_KEY_T);		em.put(KeyCode.U, GLFW_KEY_U);		em.put(KeyCode.V, GLFW_KEY_V);
		em.put(KeyCode.W, GLFW_KEY_W);		em.put(KeyCode.X, GLFW_KEY_X);		em.put(KeyCode.Y, GLFW_KEY_Y);
		em.put(KeyCode.Z, GLFW_KEY_Z);		em.put(KeyCode.A, GLFW_KEY_A);
		em.put(KeyCode.Num0, GLFW_KEY_0);		em.put(KeyCode.Num1, GLFW_KEY_1);
		em.put(KeyCode.Num2, GLFW_KEY_2);		em.put(KeyCode.Num3, GLFW_KEY_3);
		em.put(KeyCode.Num4, GLFW_KEY_4);		em.put(KeyCode.Num5, GLFW_KEY_5);
		em.put(KeyCode.Num6, GLFW_KEY_6);		em.put(KeyCode.Num7, GLFW_KEY_7);
		em.put(KeyCode.Num8, GLFW_KEY_8);		em.put(KeyCode.Num9, GLFW_KEY_9);
		
		for(KeyCode k: KeyCode.values()) {
			keysState.put(k, 0x0); // ініціалізація кожної клавіші як не нажатої
		}
		mk.put(0, GLFW_MOUSE_BUTTON_LEFT); 		//ліва клавіша миші
		mk.put(1, GLFW_MOUSE_BUTTON_RIGHT);		//права клавіша миші
		mk.put(2, GLFW_MOUSE_BUTTON_MIDDLE);	//середня клавіша миші
		
		for(int i = 0; i < 3; i++) {
			mouseKeysState.put(i, 0x0); //ініціалізація кожної клавіші миші як не нажатої
		}
	}
	
	public boolean GetKey(KeyCode k) {
		if(glfwGetKey(window, em.get(k)) == GLFW_PRESS) {
			keysState.put(k, GLFW_PRESS);
			return true;
		}
		return false;
	}
	
	public boolean GetKeyDown(KeyCode k) {
		if(keysState.get(k) == GLFW_RELEASE) {
			if(glfwGetKey(window, em.get(k)) == GLFW_PRESS) {
				keysState.put(k, GLFW_PRESS);
				return true;
			}
		}
		GetKeyUp(k);
		return false;
	}
	
	public boolean GetKeyUp(KeyCode k) {
		GetKey(k);
		if(keysState.get(k) == GLFW_PRESS) {
			if(glfwGetKey(window, em.get(k)) == GLFW_RELEASE) {
				keysState.put(k, GLFW_RELEASE);
				return true;
			}
		}
		return false;
	}
	
	public boolean GetMouseKey(int k) {
		if(glfwGetMouseButton(window, mk.get(k)) == GLFW_PRESS) {
			mouseKeysState.put(k, GLFW_PRESS);
			return true;
		}
		return false;
	}
	
	public boolean GetMouseKeyDown(int k) {
		if(mouseKeysState.get(k) == GLFW_RELEASE) {
			if(glfwGetMouseButton(window, mk.get(k)) == GLFW_PRESS) {
				mouseKeysState.put(k, GLFW_PRESS);
				return true;
			}
		}
		GetMouseKeyUp(k);
		return false;
	}
	
	public boolean GetMouseKeyUp(int k) {
		GetMouseKey(k);
		if(mouseKeysState.get(k) == GLFW_PRESS) {
			if(glfwGetMouseButton(window, mk.get(k)) == GLFW_RELEASE) {
				mouseKeysState.put(k, GLFW_RELEASE);
				return true;
			}
		}
		return false;
	}
}

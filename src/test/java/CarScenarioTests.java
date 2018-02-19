import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import controller.Actuator;
import controller.CarImp;
import model.Lidar;
import model.Radar;

public class CarScenarioTests {
	private CarImp auto;
	
	
	
	@Mock
	Actuator act = mock(Actuator.class);
	
	
	//Instantiating a new automobile object for each test case
	@Before
    public void setUp() {			
		auto = new CarImp(3, 0, act);
	}
	
	public Radar[] setUpRadar(int a,int b, int c) {
		auto.r1 = mock(Radar.class);
    	auto.r2 = mock(Radar.class);
    	auto.r3 = mock(Radar.class);
    	
    	when(auto.r1.getReading()).thenReturn(a);
    	when(auto.r2.getReading()).thenReturn(b);
    	when(auto.r3.getReading()).thenReturn(c);
   		
    	Radar r1 = auto.r1;
    	Radar r2 = auto.r2;
    	Radar r3 = auto.r3;
    	Radar[] radars = {r1, r2, r3};
		return  radars;
	}
	public Lidar setUpLidar(int a) {
		auto.lidar = mock(Lidar.class);
		when(auto.lidar.getReading()).thenReturn(a);
   		return auto.lidar;
	}
	@Test
	public void scenario2() {
		Radar[] radars = new Radar[3] ;
		Lidar lidar;
		auto.carPos.setX(3);
		auto.carPos.setY(26);
		System.out.print(auto.carPos.getY()+"\n");
		when(auto.act.moveCar(auto.carPos, 5)).thenReturn(36);
		auto.moveForward();
		System.out.print(auto.carPos.getY()+"\n");
		radars =setUpRadar(3,4,4);
		lidar = setUpLidar(3);
		// changeLane will change x direction but, will 0 the y position when run
		auto.changeLane(auto, radars, lidar);
		when(auto.act.moveCar(auto.carPos, 5)).thenReturn(96);
		auto.moveForward();
		System.out.print(auto.carPos.getY()+"\n");
		radars =setUpRadar(6,8,9);
		lidar = setUpLidar(9);
		assertEquals("y value incorrrect", auto.changeLane(auto, radars, lidar));
	}
	
}

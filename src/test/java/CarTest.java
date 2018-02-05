/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class CarTest {
	private Automobile auto;
	
	//Instantiating a new automobile object for each test case
	@Before
    public void setUp() {
		auto = new Automobile();
    }
	//Test that WhereIs returns a value
    @Test public void testThatWhereIsReturnNotNull(){
    	assertNotNull(auto.whereIs());
    }
    // Tests that it will return the values of the initialized car.
    @Test public void testThatWhereIsReturnAutoXAndY(){
    	assertEquals(auto.whereIs(), "Longitude:" + auto.x + " latitude:" + auto.y);
    }
    //Tests that it returns the values of the car even if it's unexpected values and outside the track.
    @Test public void testWhereIsUnexpectedVaules() {
    	auto.x = -567;
    	auto.y = 1337;
    	assertEquals(auto.whereIs(), "Longitude:" + auto.x + " latitude:" + auto.y);
    }
    
    // This test runs the moveForward method from the position 96, 
    // the car should not moveForward.
    @Test public void maxDistance() {
	    auto.y=96;
		auto.moveForward(auto);
		System.out.println(auto.y);
	    assertEquals("we check if starting value is correct",96, auto.y);
	}
    // This test will test when the input is 50 the output should give 55
    // else the test case fails.	
    @Test public void fiveDistance() {
	    auto.y=50;
		auto.moveForward(auto);
		System.out.println(auto.y);
	    assertEquals("we check if starting value is correct",55, auto.y);
    }
    // This test runs the moveForward method from the position 97, 
    // the car should not moveForward.	
    @Test public void maxDistanceV2() {
	    auto.y=97;
		auto.moveForward(auto);
		System.out.println(auto.y);
	    assertEquals("we check if starting value is correct",97, auto.y);
    }
    // this test is when a faulty position is given, the output should return -1.	
    @Test public void underDistance() {
	    auto.y=-1;
	    assertEquals("we check if starting value is correct",-1, auto.moveForward(auto));
    }
    // this test is when a faulty position is given, the output should return -1.	
    @Test public void overDistance() {
	    auto.y=101;
	    assertEquals("we check if starting value is correct",-1, auto.moveForward(auto));
    }
    // This test will test when the input is 95 the output should give 100
    // else the test case fails.	
    @Test public void distanceTest() {
	    auto.y=95;
	    auto.moveForward(auto);
		//System.out.println(auto.y);
	    assertEquals("we check if starting value is correct",100,auto.y);
    }
    
    //Testing a normal scenario were a car is not detected.
    @Test public void noCarDetected() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(30);
   		radars[1] = new Radar(34);
   		radars[2] = new Radar(28);
   		
    	Lidar lidar = new Lidar(26);
    	
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "No car detected");
    }
    
    //Testing with one of the sensors having a faulty reading but otherwise no car is detected.
    @Test public void oneFaultyReading() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(930);
   		radars[1] = new Radar(41);
   		radars[2] = new Radar(44);
   		
    	Lidar lidar = new Lidar(39);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "No car detected");
    }
    
    //Testing with 2 faulty readings from radar 1 and 2. An error should be returned.
    @Test public void twoFaultyReadings1() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(865);
   		radars[1] = new Radar(430);
   		radars[2] = new Radar(17);
   		
    	Lidar lidar = new Lidar(15);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Error: faulty readings");
    }
    
    //Testing with 2 faulty readings from radar 3 and the lidar. An error should be returned.
    @Test public void twoFaultyReadings2() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(28);
   		radars[1] = new Radar(30);
   		radars[2] = new Radar(460);
   		
    	Lidar lidar = new Lidar(370);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Error: faulty readings");
    }
    
    //Testing 2 faulty readings with a sensor also detecting a nearby car.
    //Error about the faulty readings should take precedence, thus returning an error.
    @Test public void twoFaultyReadingsWithNearbyCar() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(4);
   		radars[1] = new Radar(259);
   		radars[2] = new Radar(270);
   		
    	Lidar lidar = new Lidar(15);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Error: faulty readings");
    }
    
    //Testing if the car confirms the detection of a car from radar 1
    @Test public void nearbyCarDetectedRadar1() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(4);
   		radars[1] = new Radar(10);
   		radars[2] = new Radar(7);
   		
    	Lidar lidar = new Lidar(13);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing if the car confirms the detection of a car from radar 2
    @Test public void nearbyCarDetectedRadar2() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(12);
   		radars[1] = new Radar(3);
   		radars[2] = new Radar(8);
   		
    	Lidar lidar = new Lidar(9);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing if the car confirms the detection of a car from radar 3
    @Test public void nearbyCarDetectedRadar3() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(8);
   		radars[1] = new Radar(10);
   		radars[2] = new Radar(2);
   		
    	Lidar lidar = new Lidar(7);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing if the car confirms the detection of a car from lidar
    @Test public void nearbyCarDetectedRadar4() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(13);
   		radars[1] = new Radar(14);
   		radars[2] = new Radar(12);
   		
    	Lidar lidar = new Lidar(3);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing 1 faulty reading with a sensor also detecting a nearby car.
    //Return car detected.
    @Test public void oneFaultyReadingWithNearbyCar() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(300);
   		radars[1] = new Radar(2);
   		radars[2] = new Radar(13);
   		
    	Lidar lidar = new Lidar(7);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing how the system handles a negative faulty reading.
    //Return faulty readings error as well.
    @Test public void negativeFaultyReadings() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(-42);
   		radars[1] = new Radar(18);
   		radars[2] = new Radar(15);
   		
    	Lidar lidar = new Lidar(203);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Error: faulty readings");
    }
    
    //Testing the lowest possible detection reading.
    @Test public void nearbyCarDetectedLowerBoundary() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(0);
   		radars[1] = new Radar(12);
   		radars[2] = new Radar(30);
   		
    	Lidar lidar = new Lidar(0);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing the highest possible detection reading.
    @Test public void nearbyCarDetectedUpperBoundary() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(5);
   		radars[1] = new Radar(14);
   		radars[2] = new Radar(17);
   		
    	Lidar lidar = new Lidar(5);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Car detected");
    }
    
    //Testing highest possible non-faulty reading
    @Test public void noCarDetectedUpperBoundary() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(50);
   		radars[1] = new Radar(25);
   		radars[2] = new Radar(50);
   		
    	Lidar lidar = new Lidar(23);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "No car detected");
    }
    
    //Testing how the system handles all sensor readings being faulty.
    @Test public void allFaultyReadings() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(305);
   		radars[1] = new Radar(800);
   		radars[2] = new Radar(340);
   		
    	Lidar lidar = new Lidar(200);
    	assertEquals(auto.leftLaneDetect(radars, lidar, 1), "Error: faulty readings");
    }
	
	//Testing how the system handles one false, zero and positive reading .
    @Test public void detectedCarOnLeftLanePostionTwoWithZeroReading() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(5);
   		radars[1] = new Radar(4);
   		radars[2] = new Radar(17);
   		
    	Lidar lidar = new Lidar(5);

    	auto.y = 0;
    	auto.x = 2;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed car detected");
    }
    
	  //Testing how the system handles one false,two positive reading .
	@Test public void detectedCarOnLeftLanePostionTwoWithTwoPositive() {
    	Radar[] radars = new Radar[3];
   		radars[0] = new Radar(0);
   		radars[1] = new Radar(12);
   		radars[2] = new Radar(30);
   		
    	Lidar lidar = new Lidar(0);

		auto.y = 20;
	  	auto.x = 2;
	  	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed car detected");
	 }
   
  //Testing how the system handles all positive reading .
   @Test public void noDetectedCarOnLeftLanePostion2AllPositive() {
   		Radar[] radars = new Radar[3];
  		radars[0] = new Radar(930);
  		radars[1] = new Radar(41);
  		radars[2] = new Radar(44);
  		
  		Lidar lidar = new Lidar(39);
  		
	   	auto.y = 20;
	   	auto.x = 2;
	   	assertEquals(auto.changeLane(auto, radars, lidar), "Lane changed");
   }
	
  //Testing how the system handles negative reading .
  @Test public void noDetectedCarOnLeftLanePostionThreeWithNegativeReading() {
  		Radar[] radars = new Radar[3];
 		radars[0] = new Radar(30);
 		radars[1] = new Radar(34);
 		radars[2] = new Radar(28);
 		
 		Lidar lidar = new Lidar(26);

	  	auto.y = -1;
	  	auto.x = 3;
	  	assertEquals(auto.changeLane(auto, radars, lidar), "y value incorrrect");
  }
  
  //Testing how the system handles one false,two positive reading .
  @Test public void noDetectedCarOnLeftLanePostionThreeOutOfBoundReading() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(930);
		radars[1] = new Radar(41);
		radars[2] = new Radar(44);
		
		Lidar lidar = new Lidar(39);

	  	auto.y = 96;
	  	auto.x = 3;
	  	assertEquals(auto.changeLane(auto, radars, lidar), "y value incorrrect");
  }
  
  //Testing faulty readings on lower bounds value .
    @Test public void errorFaultyReadingLeftLaneTwoWithNegativePosition() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(4);
		radars[1] = new Radar(259);
		radars[2] = new Radar(270);
		
		Lidar lidar = new Lidar(15);
		
    	auto.y = -1;
    	auto.x = 2;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed,Error:faulty readings");
   }
	
   //Testing how the negative value when negative input detected .
    @Test public void negativeDetectedCarOnLeftLanePostionThree() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(8);
		radars[1] = new Radar(10);
		radars[2] = new Radar(2);
		
		Lidar lidar = new Lidar(7);
		
    	auto.y = -1;
    	auto.x = 3;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed car detected");
  }
	
  //Testing how the negative value when negative input detected .
    @Test public void noDetectedCarOnLeftLanePostionThreeAllPositive() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(930);
		radars[1] = new Radar(41);
		radars[2] = new Radar(44);
		
		Lidar lidar = new Lidar(39);

    	auto.y = 70;
    	auto.x = 3;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane changed");
    }
    
    @Test public void errorFaultyReadingLeftLaneThreeWithUpperBound() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(-42);
		radars[1] = new Radar(18);
		radars[2] = new Radar(15);
		
		Lidar lidar = new Lidar(203);
		
    	auto.y = 96;
    	auto.x = 3;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed,Error:faulty readings");
    }
    
   @Test public void errorFaultyReadingLeftLaneOneWithZeroReading() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(305);
		radars[1] = new Radar(800);
		radars[2] = new Radar(340);
		
		Lidar lidar = new Lidar(200);
		
    	auto.y = 0;
    	auto.x = 1;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed,Error:faulty readings");
    }
	
    @Test public void detectedCarOnLanePostionOneWithZeroReading() {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(5);
		radars[1] = new Radar(14);
		radars[2] = new Radar(17);
		
		Lidar lidar = new Lidar(5);

    	auto.y = 0;
    	auto.x = 1;
    	assertEquals(auto.changeLane(auto, radars, lidar), "Lane change failed car detected");
    }
    
     //Testing readings on lower bounds value 
   @Test public void noDetectedCarOnLeftLanePostionThreeWithBoundReading()  {
		Radar[] radars = new Radar[3];
		radars[0] = new Radar(930);
		radars[1] = new Radar(41);
		radars[2] = new Radar(44);
		Lidar lidar = new Lidar(39);
		auto.y = 95;
	   	auto.x = 3;
	   	assertEquals(auto.changeLane(auto, radars, lidar), "Lane changed");
   }
}

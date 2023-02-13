// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.sql.Time;

import javax.lang.model.util.ElementScanner6;

import org.w3c.dom.css.Counter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
  public class Robot extends TimedRobot {   
  
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private DifferentialDrive m_myRobot;
 // private Joystick m_leftStick;
 // private Joystick m_rightStick;
  private XboxController m_xbox;
  private double SQRT_Left;
  private double SQRT_Right;
  private final MotorController m_leftMotor1 = new PWMVictorSPX(2);
  private final MotorController m_leftMotor2 = new PWMVictorSPX(3);
  private final MotorController m_rightMotor1 = new PWMVictorSPX(0);
  private final MotorController m_rightMotor2 = new PWMVictorSPX(1);
  private final MotorControllerGroup m_Left = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
  private final MotorControllerGroup m_Right = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  private final MotorController m_arm1 = new PWMVictorSPX(4);
  private final MotorController m_arm2 = new PWMVictorSPX(5);
  private final MotorController m_frank = new PWMVictorSPX(6);
  private final MotorController m_climber = new PWMVictorSPX(7);
  private long count;
  private long Countermotor;
  private double speed;
  private long startTime;
  CameraServer server;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    CameraServer.startAutomaticCapture();
    
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
   

    //m_Right.setInverted(true);
    m_Left.setInverted(true);
    speed = 0.75;
    m_myRobot = new DifferentialDrive(m_Left, m_Right);
   // m_leftStick = new Joystick(0);
   // m_rightStick = new Joystick(1);
    m_xbox = new XboxController(0);
    count=0;
    Countermotor=0;
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    startTime = System.currentTimeMillis();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
      //if (System.currentTimeMillis()-startTime<=3000){
        //Countermotor = Countermotor+1;
       // m_myRobot.tankDrive(0.5,0.5);
        
        
        //m_Left.set(0.5);
        //m_Right.set(0.5);
       // Timer.delay(2.0);
      //}
      if (System.currentTimeMillis()-startTime>=6000 && System.currentTimeMillis()-startTime<=11000 ){
        m_myRobot.tankDrive(0.53,0.53);
      }
     
      else{
        m_myRobot.tankDrive(0.0,0.0);
      }
      
      
      if (System.currentTimeMillis()-startTime>1000 && count == 0){
        
  
        m_arm1.set(-0.88);
        Timer.delay(0);
        m_frank.set(-1.0);
        Timer.delay(0.75);
        m_frank.set(1.00);
        Timer.delay(0.75);
        m_frank.stopMotor();
        Timer.delay(0.5);//increase to one if ball does not go high enough.
        m_arm1.stopMotor();
      //Countermotor=0;
      count=1;  
      }
        
        break;
  
        

    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    count=1;
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
   // SQRT_Left = Math.sqrt(m_xbox.getLeftY());
   //SQRT_Right = Math.sqrt(m_xbox.getRightY());

   
   if (m_xbox.getPOV()==0){
     speed=1.0;
   }
   else if (m_xbox.getPOV()==90){
     speed=0.8;
   }
   else if (m_xbox.getPOV()==180){
    speed=0.6;
  }
  else if (m_xbox.getPOV()==270){
    speed=0.4;
  }
   
   
    m_myRobot.tankDrive(m_xbox.getLeftY()*speed,m_xbox.getRightY()*speed);
    //m_myRobot.tankDrive(SQRT_Left,SQRT_Right);

    if (m_xbox.getRightBumperPressed() && count==1){
     

      m_arm1.set(-0.88);
      count=2;
      startTime = System.currentTimeMillis();
    }
    if (System.currentTimeMillis()-startTime>3000 && count == 2){
      //Timer.delay(3.0);
      count=3;
      m_frank.set(-0.5);
      startTime = System.currentTimeMillis(); 
  }
  if (System.currentTimeMillis()-startTime>500 && count == 3){
    //Timer.delay(0.5);
    m_frank.set(0.5);
    startTime = System.currentTimeMillis();
    count=4;
  }
  if(System.currentTimeMillis()-startTime>500 && count == 4){
    //Timer.delay(0.5);
    m_frank.stopMotor();
    startTime = System.currentTimeMillis();
    count=5;
  }
  if(System.currentTimeMillis()-startTime>500 && count == 5){
      
      
     // Timer.delay(0.5);//increase to one if ball does not go high enough.
      m_arm1.stopMotor();
    count=1;
      
    }
    
    
    

    if (m_xbox.getBButton()){
      m_arm2.set(-1.0);
    }
    else{
      m_arm2.stopMotor();
    
     if (m_xbox.getXButton()){
      m_frank.set(0.5);
    }

    if (m_xbox.getLeftBumper()){
      m_arm2.set(0.65);
    }
   
    
    else{
      m_arm2.stopMotor();
    }
   
    if (m_xbox.getYButton()){
     m_climber.set(1.0); 
    }
    else if (m_xbox.getAButton()){
      m_climber.set(-1.0);
    }
    else if (m_xbox.get)
    else{
      m_climber.stopMotor();
    }
    
  
   }
  }
    
    
  

  

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}

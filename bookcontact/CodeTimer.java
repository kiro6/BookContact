/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookcontact;

public class CodeTimer {
    private long startTime;
    private long endTime;
    public CodeTimer()
    {
    startTime=System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    public  void Start()
    {
    startTime=System.currentTimeMillis();
    }
     public  void Stop()
    {
    endTime=System.currentTimeMillis(); 
    System.out.println(endTime-startTime+"ml"); 
    }
   
     
}
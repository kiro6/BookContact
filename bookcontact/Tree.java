/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookcontact;

/**
 *
 * @author kokom
 * @param <String>
 */
public interface Tree  {
    
    
    
    void insert(String name ,String phone ,String email , String adress);
      void insertAll(String name ,String phone ,String email , String adress);

    void delete(String data);

    void inOrderTraverse();
    
    Queue getNewQueue() ;

    String[]  getMax();

    String[] getMin();

    boolean isEmpty(); 
    
    Node searchByDataKey ( String key) ; 
   
    Queue searchByPart ( String key) ;
    
    Queue searchByFirst ( String key) ;
    
    Queue searchByLast ( String key) ;
     
    
}

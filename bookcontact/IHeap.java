/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookcontact;

/**
 *
 * @author kokom
 */
public interface IHeap {
    
    void insert(String name  ,String phone ,String email , String adress)  ;
    void insertAll(String name  ,String phone ,String email , String adress)  ;
    
    void delete(String name) ; 
    
    Node getRoot() ; 
    
    Node deleteRoot() ;
    
    void sort() ; 
    
    boolean isSorted() ; 
    
    Queue searchByKey(String data) ; 
    
    Queue searchByFirst(String data) ; 
    
    Queue searchByLast(String data) ; 
    
    Queue searchByPart(String data) ; 
    
    Queue getNewQueue() ;
    
}

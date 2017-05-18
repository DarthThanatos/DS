/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akkalab.z2;

/**
 *
 * @author Robert
 */
public class Answer implements java.io.Serializable {
    private String answer;
    
    public Answer(String answer){
        this.answer = answer;
    }
    
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public String getAnswer(){
        return answer;
    }
}

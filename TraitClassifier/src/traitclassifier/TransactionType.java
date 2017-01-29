/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitclassifier;

/**
 *
 * @author dgiai
 */
public class TransactionType {
    private String myType;
    private String[] myClassifiers;
    
    public TransactionType(String type, String[] classifiers){
        myType = type;
        myClassifiers = classifiers;
    }
    
    public boolean ofThisType(String transaction){
        for (String classifier : myClassifiers) {
            if(transaction.contains(classifier)){
                return true;
            }
        }
        return false;
    }
    
    public String getType(){
        return myType;
    }
}

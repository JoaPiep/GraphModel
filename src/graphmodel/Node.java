/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

/**
 *
 * @author Joanna Pieper <joanna.pieper@gcsc.uni-frankfurt.de>
 */
public class Node {

    String name;
    private Node parentNode = null;

    /**
     * 
     * @return name from the given node
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return parent node
     */
    public Node getParentNode() {
        return parentNode;
    }

    /**
     * 
     * @param parentNode parent node to set
     */
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
    /**
     * remove parent node from the node
     */
    public void removeParentNode() {
        parentNode = null;
    }
}
